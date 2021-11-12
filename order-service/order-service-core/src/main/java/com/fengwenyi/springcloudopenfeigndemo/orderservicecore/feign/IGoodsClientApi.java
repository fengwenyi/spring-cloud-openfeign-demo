package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign;

import com.fengwenyi.api.result.IReturnCode;
import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsApi;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
//@FeignClient(value = "goods-service", fallback = GoodsFallbackApi.class)
public interface IGoodsClientApi extends IGoodsApi {
}

@Component
@RequestMapping("/fallback")
class GoodsFallbackApi implements IGoodsClientApi {
    @Override
    public ResponseTemplate<GoodsResponseVo> get(Integer id) {
        return ResponseTemplate.fail(IReturnCode.Default.SERVICE_CALL_EXCEPTION);
    }
}