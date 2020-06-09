package com.btc.ratecheck.repository;

import java.time.ZonedDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.btc.ratecheck.model.Bitcoin;

/**
 * The <code>BitcoinRepository</code> interface is a Spring Data JPA data repository for Bitcoin entities. The
 * BitcoinRepository provides all the data access behaviors exposed by <code>JpaRepository</code> and additional custom
 * behaviors may be defined in this interface.
 *
 * @author Bosko Mijin
 */
@Repository
public interface BitcoinRepository extends JpaRepository<Bitcoin, Long> {

    /**
     * The <code>findBitCoinsInDateRange</code> method defines the prepared query for a Collection of Bitcoin entities
     * in date range. Uses a Query annotated SQL statement to search the database.
     *
     * @return Collection - Collection of Bitcoin entities in date range.
     */
    @Query("select btc from Bitcoin btc where btc.createdAt >= :dateFrom and btc.createdAt <= :dateTo")
    Collection<Bitcoin> findBitcoinsInDateRange(@Param("dateFrom") ZonedDateTime dateFrom,
            @Param("dateTo") ZonedDateTime dateTo);

    /**
     * The <code>findLatest</code> method defines the prepared query for a single, the latest bitcoin entity according
     * to date time entered. Uses a Query annotated SQL statement to search the database.
     *
     * @return Bitcoin - The bitcoin which satisfies the search criteria.
     */
    @Query(value = "select * from Bitcoin order by createdAt desc limit 1",
            nativeQuery = true)
    Bitcoin findLatest();

}
