package com.btc.ratecheck.dto.coindesk;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <code>CoinDeskResponseDto</code> class is response dto class which holds all the data related to bitcoin price
 * obtained from web exposed endpoint.
 *
 * @author Bosko Mijin.
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CoinDeskResponseDto {

    /** The time. */
    private CoinDeskTimeDto time;

    /** The disclaimer. */
    private String disclaimer;

    /** The chart name. */
    private String chartName;

    /** The bpi. */
    private Map<String, CoinDeskBpiDto> bpi;
}
