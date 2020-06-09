package com.btc.ratecheck.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.btc.ratecheck.model.Bitcoin;
import com.btc.ratecheck.service.BitcoinService;

import lombok.extern.java.Log;

/**
 * The <code>BitcoinController</code> class is a REST controller responsible for handling bitcoin requests. It returns a
 * <code>@ResponseBody</code> which, by default, contains a ResponseEntity converted into JSON with an associated HTTP
 * status code.
 *
 * @author Bosko Mijin
 */

@Log
@RestController
public class BitcoinController {

    /** The service. */
    @Autowired
    private BitcoinService service;

    /**
     * Gets the current bit coin price.
     *
     * @return the current bit coin price
     * @throws InterruptedException the interrupted exception
     * @throws ExecutionException the execution exception
     */
    @RequestMapping(value = "${app.paths.latest-path}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bitcoin> getCurrentBitCoinPrice() throws InterruptedException, ExecutionException {
        log.info("> BitcoinController :: getCurrentBitCoinPrice");
        Bitcoin response = service.findLatest();
        log.info("< BitcoinController :: getCurrentBitCoinPrice");
        return new ResponseEntity<Bitcoin>(response, HttpStatus.OK);
    }

    /**
     * Gets the bitcoin historical rates in date range.
     *
     * @param dateFromString - The date from string defines start of period.
     * @param dateToString - The date to string defines end of period.
     * @return ResponseEntity - The bitcoin historical rates in date range in JSON format.
     * @throws InterruptedException - The interrupted exception
     * @throws ExecutionException - The execution exception
     */
    @RequestMapping(value = "${app.paths.historical-path}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Bitcoin>> getBitcoinHistoricalRatesInDateRange(
            @RequestParam("dateFrom") String dateFromString, @RequestParam("dateTo") String dateToString)
            throws InterruptedException, ExecutionException {
        log.info("> BitcoinController :: getBitcoinPricesInDateTimeRange");
        ZonedDateTime dateTimeFrom = LocalDate.parse(dateFromString).atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime dateTimeTo = LocalDate.parse(dateToString).atStartOfDay(ZoneId.systemDefault()).plusDays(1)
                .minusNanos(1);
        Collection<Bitcoin> response = service.findAllInRange(dateTimeFrom, dateTimeTo);
        log.info("< BitcoinController :: getBitcoinPricesInDateTimeRange");
        return new ResponseEntity<Collection<Bitcoin>>(response, HttpStatus.OK);
    }
}
