package com.btc.ratecheck.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The <code>SecurityConfiguration</code> class provides a centralized location for application mapping configuration.
 * This class bootstraps the ModelMapper components during application startup.
 *
 * @author Bosko Mijin
 */
@Configuration
public class MappingConfiguration {

    /**
     * Model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
