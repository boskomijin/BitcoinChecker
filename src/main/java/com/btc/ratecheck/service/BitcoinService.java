package com.btc.ratecheck.service;

import java.time.ZonedDateTime;
import java.util.Collection;

import com.btc.ratecheck.model.Bitcoin;

/**
 * The <code>BitcoinService</code> interface defines all public business behaviors for operations on the Bitcoin entity
 * model. This interface should be injected into BitcoinService clients, not the implementation bean.
 *
 * @author Bosko Mijin
 */
public interface BitcoinService {

    /**
     * Find all Bitcoin entities in desired date range.
     *
     * @return The Collection of Bitcoin objects.
     */
    Collection<Bitcoin> findAllInRange(ZonedDateTime dateTimeFrom, ZonedDateTime dateTimeTo);

    /**
     * Find a single Bitcoin entity by latest time.
     *
     * @return Bitcoin - The Bitcoin or <code>null</code> if none found.
     */
    Bitcoin findLatest();

    /**
     * Persists or updates already persisted Bitcoin entity in the data store.
     *
     * @param bitcoin - The Bitcoin object to be persisted or updated.
     * @return The persisted or updated Bitcoin entity.
     */
    Bitcoin createOrUpdate(Bitcoin bitcoin);

    /**
     * Removes a previously persisted Bitcoin entity from the data store.
     *
     * @param id - The Long primary key identifier.
     */
    void delete(Long id);

    /**
     * Evicts all members of the "bitcoin" cache.
     */
    void evictCache();
}
