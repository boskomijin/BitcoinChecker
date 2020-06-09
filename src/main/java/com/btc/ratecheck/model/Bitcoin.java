package com.btc.ratecheck.model;

import java.security.SecureRandom;
import java.time.ZonedDateTime;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The <code>Bitcoin</code> class is an entity model object. An Bitcoin describes the bitcoin information stored with
 * this application.
 * 
 * @author Bosko Mijin
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Bitcoin extends TransactionalEntity {

    /** Default serialization. */
    private static final long serialVersionUID = new SecureRandom().nextLong();

    /** The disclaimer. */
    private String disclaimer;

    /** The chart name. */
    private String chartName;

    /** The updated time. */
    private ZonedDateTime updatedTime;

    /** The rate. */
    private float rate;

}
