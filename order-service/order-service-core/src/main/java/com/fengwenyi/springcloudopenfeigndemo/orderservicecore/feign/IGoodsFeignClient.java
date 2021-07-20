package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign;

import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@FeignClient("spring-cloud-openfeign-demo-goods-service")
public interface IGoodsFeignClient extends IGoodsApi {
}
