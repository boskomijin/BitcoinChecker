package com.btc.ratecheck.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.btc.ratecheck.dto.coindesk.CoinDeskResponseDto;
import com.btc.ratecheck.util.CoinDeskDeserializer;

import lombok.extern.java.Log;

/**
 * The <code>BitCoinPriceCollectorImpl</code> class is service class which is responsible for obtaining data from coin
 * desk exposed endpoint using rest template asynchronously, and storing it to local database over defined repository.
 * 
 * @author Bosko Mijin.
 */
@Log
@Service
public class BitCoinPriceCollectorImpl implements BitCoinPriceCollector {

    /** The RestTemplate. */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * The <code>restTemplate</code> method is bean definition where rest template is initializing, since that rest
     * template is used only in this class, it is defined here.
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /** The current bitcoin url. */
    @Value("${api.paths.current-bit-coin-price-path}")
    private String currentBitCoinPriceUrl;

    /**
     * Get current bit coin price from coin price exposed endpoint.
     *
     * @return A Completable future of CoinDeskResponse objects.
     */
    @Override
    @Async("asyncExecutor")
    public CompletableFuture<CoinDeskResponseDto> getCurrentBitCoinPrice() {
        log.info("> BitCoinPriceCollectorImpl :: getCurrentBitCoinPrice");
        CoinDeskResponseDto response = CoinDeskDeserializer
                .deserializeCoinDeskResponse(restTemplate.getForObject(currentBitCoinPriceUrl, String.class));
        log.info("< BitCoinPriceCollectorImpl :: getCurrentBitCoinPrice");
        return CompletableFuture.completedFuture(response);
    }

}
