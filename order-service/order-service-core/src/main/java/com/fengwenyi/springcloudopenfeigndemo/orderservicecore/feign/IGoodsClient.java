package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign;

import com.fengwenyi.api.result.IReturnCode;
import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsApi;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
//@FeignClient(value = "goods-service", fallback = GoodsFallback.class)
public interface IGoodsClient {

    @GetMapping("/goods/{id}")
    ResultTemplate<GoodsResponseVo> get(@PathVariable Integer id);

}

@Component
class GoodsFallback implements IGoodsClient {
    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_SERVICE_CALL_EXCEPTION);
    }
}