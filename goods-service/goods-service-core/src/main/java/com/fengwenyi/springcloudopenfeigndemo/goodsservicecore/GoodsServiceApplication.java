package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore;

import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

/**
 * @author Erwin Feng
 * @since 2021-06-26
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsServiceApplication.class, args);
    }

    @Autowired
    private GoodsRepository goodsRepository;

    @PostConstruct
    public void init() {
        goodsRepository.init();
    }

}
