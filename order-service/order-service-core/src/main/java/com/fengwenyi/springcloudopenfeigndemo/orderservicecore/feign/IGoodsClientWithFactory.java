package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign;

import com.fengwenyi.api.result.IReturnCode;
import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsApi;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@FeignClient(value = "goods-service", contextId = "goods", fallbackFactory = GoodsFallbackFactory.class)
public interface IGoodsClientWithFactory extends IGoodsApi {
}

@Component
class GoodsFallbackFactory implements FallbackFactory<GoodsFallbackWithFactory> {
    @Override
    public GoodsFallbackWithFactory create(Throwable cause) {
        return new GoodsFallbackWithFactory();
    }
}

class GoodsFallbackWithFactory implements IGoodsClientWithFactory {
    @Override
    public ResponseTemplate<GoodsResponseVo> get(Integer id) {
        return ResponseTemplate.fail(IReturnCode.Default.SERVICE_CALL_EXCEPTION);
    }
}
