package com.btc.ratecheck.configuration;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
 
/**
 * The AsyncConfiguration class handles all the features which are requiring thread pool executor and
 * prepares it for asynchronous usage.
 *
 * @author Bosko Mijin
 */
@Configuration
@EnableAsync
public class AsyncConfiguration {
    
    /**
     * The <code>asyncExecutor</code> method returns initialized thread pool executor with all defined values,
     * prepared for asynchronous usage.
     *
     * @return the executor - thread pool executor.
     */
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread-");
        executor.initialize();
        return executor;
    }
}
