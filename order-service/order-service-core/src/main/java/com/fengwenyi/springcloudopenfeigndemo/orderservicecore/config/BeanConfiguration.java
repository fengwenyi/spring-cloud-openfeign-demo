package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-10-25
 */
@Configuration
public class BeanConfiguration {

    @Bean
    @LoadBalanced // 开启负载均衡，不开启，找不到服务
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
