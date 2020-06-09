package com.btc.ratecheck.service;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.btc.ratecheck.model.Bitcoin;
import com.btc.ratecheck.repository.BitcoinRepository;

import lombok.extern.java.Log;

/**
 * The <code>BitcoinServiceImpl</code> class encapsulates all business behaviors operating on the Bitcoin entity model
 * object.
 *
 * @author Bosko Mijin
 */
@Log
@Service
@Transactional(propagation = Propagation.SUPPORTS,
        readOnly = true)
public class BitcoinServiceImpl implements BitcoinService {

    /** The Spring Data repository for Bitcoin entities. */
    @Autowired
    private BitcoinRepository bitcoinRepository;

    /**
     * Find all Bitcoin entities in desired date range.
     *
     * @return The Collection of Bitcoin objects.
     */
    @Override
    public Collection<Bitcoin> findAllInRange(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo) {
        log.info("> BitcoinServiceImpl :: findAllInRange");
        Collection<Bitcoin> categories = bitcoinRepository.findBitcoinsInDateRange(dateTimeFrom, dateTimeTo);
        log.info("< BitcoinServiceImpl :: findAllInRange");
        return categories;
    }

    /**
     * Find a single Bitcoin entity by latest time.
     *
     * @return Bitcoin - The Bitcoin or <code>null</code> if none found.
     */
    @Override
    public Bitcoin findLatest() {
        log.info("> BitcoinServiceImpl :: findLatest");
        Bitcoin bitcoin = bitcoinRepository.findLatest();
        log.info("< BitcoinServiceImpl :: findLatest");
        return bitcoin;
    }

    /**
     * Persists or updates already persisted Bitcoin entity in the data store.
     *
     * @param bitcoin - The Bitcoin object to be persisted or updated.
     * @return The persisted or updated Bitcoin entity.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(value = "bitcoins",
            key = "#result.id")
    public Bitcoin createOrUpdate(Bitcoin bitcoin) {
        log.info("> BitcoinServiceImpl :: createOrUpdate");
        Bitcoin savedBitcoin = bitcoinRepository.save(bitcoin);
        log.info("< BitcoinServiceImpl :: createOrUpdate");
        return savedBitcoin;
    }

    /**
     * Removes a previously persisted Bitcoin entity from the data store.
     *
     * @param id - The Long primary key identifier.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(value = "bitcoins",
            key = "#id")
    public void delete(Long id) {
        log.info("> BitcoinServiceImpl :: delete");
        bitcoinRepository.deleteById(id);
        log.info("< BitcoinServiceImpl :: delete");
    }

    /**
     * Evicts all members of the "bitcoins" cache.
     */
    @Override
    @CacheEvict(value = "bitcoins",
            allEntries = true)
    public void evictCache() {
        log.info("> BitcoinServiceImpl :: evictCache");
        log.info("< BitcoinServiceImpl :: evictCache");
    }
}
