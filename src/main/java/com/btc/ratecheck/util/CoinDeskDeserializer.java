package com.btc.ratecheck.util;

import java.text.MessageFormat;
import java.util.Map;

import com.btc.ratecheck.dto.coindesk.CoinDeskBpiDto;
import com.btc.ratecheck.dto.coindesk.CoinDeskResponseDto;
import com.btc.ratecheck.dto.coindesk.CoinDeskTimeDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.java.Log;

/**
 * The <code>CoinDeskDeserializer</code> class is util class which is responsible for all the actions related to JSON
 * de-serializing coin desk responses.
 *
 * @author Bosko Mijin.
 */
@Log
public final class CoinDeskDeserializer {

    /** The Constant TIME. */
    private static final String TIME = "time";

    /** The Constant DISCLAIMER. */
    private static final String DISCLAIMER = "disclaimer";

    /** The Constant CHART_NAME. */
    private static final String CHART_NAME = "chartName";

    /** The Constant BPI. */
    private static final String BPI = "bpi";

    /**
     * The <code>deserializeCoinDeskResponse</code> method deserializes the JSON response obtained for coin desk.
     *
     * @param responseString - The response string obtained from coin desk (in JSON format).
     * @return CoinDsekDto - the coin desk response dto or null in case of exception.
     */
    public static CoinDeskResponseDto deserializeCoinDeskResponse(String responseString) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(responseString);
            JsonNode timeNode = jsonNode.get(TIME);
            CoinDeskTimeDto cdt = mapper.convertValue(timeNode, CoinDeskTimeDto.class);
            String disclaimer = jsonNode.get(DISCLAIMER).asText();
            String chartName = jsonNode.get(CHART_NAME).asText();
            JsonNode bpi = jsonNode.get(BPI);
            Map<String, CoinDeskBpiDto> bpiList = mapper.convertValue(bpi,
                    new TypeReference<Map<String, CoinDeskBpiDto>>() {
                    });
            return new CoinDeskResponseDto(cdt, disclaimer, chartName, bpiList);
        } catch (JsonProcessingException e) {
            log.info(MessageFormat.format("CoinDeskDeserializer :: deserializeCoinDeskResponse, {}", e.getMessage()));
            return null;
        }

    }
}
