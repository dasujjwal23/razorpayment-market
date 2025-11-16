package com.dell.market.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableAsync
public class SpringAsyncConfig{

	@Bean("asyncTaskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setQueueCapacity(20);
        threadPoolTaskExecutor.setMaxPoolSize(1);
        threadPoolTaskExecutor.setThreadNamePrefix("AsyncTaskThread-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
