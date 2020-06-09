package com.btc.ratecheck.dto.coindesk;

import java.io.Serializable;
import java.security.SecureRandom;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <code>CoinDeskBpiDto</code> class is an POJO which is mapping bpi object from coin desk response. A
 * CoinDeskBpiDto describes the bpi with their attributes.
 *
 * @author Bosko Mijin
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CoinDeskBpiDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = new SecureRandom().nextLong();

    /** The code. */
    private String code;

    /** The symbol. */
    private String symbol;

    /** The rate. */
    private String rate;

    /** The description. */
    private String description;

    /** The rate float. */
    private float rate_float;
}
