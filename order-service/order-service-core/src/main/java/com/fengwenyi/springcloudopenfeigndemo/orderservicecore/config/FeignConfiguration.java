package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.config;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-29
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(10, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true);
    }

}
