package com.btc.ratecheck.service;

import java.util.concurrent.CompletableFuture;

import com.btc.ratecheck.dto.coindesk.CoinDeskResponseDto;

public interface BitCoinPriceCollector {

    /**
     * Get current bit coin price from coin price exposed endpoint.
     *
     * @return A CompletableFuture of CoinDeskResponse object.
     */
    public CompletableFuture<CoinDeskResponseDto> getCurrentBitCoinPrice();
}
