package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign;

import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsDemoApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@FeignClient(value = "goods-service", contextId = "goods-demo")
public interface IGoodsDemoClient extends IGoodsDemoApi {
}
