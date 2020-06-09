package com.btc.ratecheck.batch;

import java.time.ZonedDateTime;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.btc.ratecheck.dto.coindesk.CoinDeskResponseDto;
import com.btc.ratecheck.model.Bitcoin;
import com.btc.ratecheck.service.BitCoinPriceCollector;
import com.btc.ratecheck.service.BitcoinService;

import lombok.extern.java.Log;

/**
 * The <code>BitCoinBatchChecker</code> contains <code>@Scheduled</code> methods consuming exposed web service over
 * <code>BitCoinPriceCollector</code> service to perform batch operations.
 *
 * @author Bosko Mijin
 */
@Log
@Profile("batch")
@Component
public class BitCoinBatchChecker {

    /** The Constant USD. */
    private static final String USD = "USD";

    /** The BitCoinPriceCollector service. */
    @Autowired
    private BitCoinPriceCollector collectorService;

    /** The BitcoinService. */
    @Autowired
    private BitcoinService bitcoinService;

    /**
     * Uses a cron expression to execute logic on a schedule. Expression: second minute hour day-of-month month weekday
     *
     * @see http ://docs.spring.io/spring/docs/current/javadoc-api/org/ springframework
     *      /scheduling/support/CronSequenceGenerator.html
     */
    @Scheduled(cron = "${batch.cron}")
    public void cronJob() {
        log.info("> BitCoinBatchChecker :: cronJob.");
        try {
            CoinDeskResponseDto response = collectorService.getCurrentBitCoinPrice().get();
            bitcoinService.createOrUpdate(
                    Bitcoin.builder().disclaimer(response.getDisclaimer()).chartName(response.getChartName())
                            .updatedTime(ZonedDateTime.parse(response.getTime().getUpdatedISO()))
                            .rate(response.getBpi().get(USD).getRate_float()).build());
        } catch (InterruptedException | ExecutionException e) {
            log.info(e.getMessage());
        }
        log.info("< BitCoinBatchChecker :: cronJob.");
    }
}
