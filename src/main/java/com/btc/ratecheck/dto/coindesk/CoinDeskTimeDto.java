package com.btc.ratecheck.dto.coindesk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The <code>CoinDeskTimeDto</code> class is dto class which holds the values related to the times from coind desk
 * response.
 *
 * @author Bosko Mijin
 */
@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CoinDeskTimeDto {

    /** The updated. */
    private String updated;

    /** The updated ISO. */
    private String updatedISO;

    /** The updateduk. */
    private String updateduk;

}
