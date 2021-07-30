package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsDemoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {

    private final IGoodsDemoClient goodsDemoClient;

    public DemoController(IGoodsDemoClient goodsDemoClient) {
        this.goodsDemoClient = goodsDemoClient;
    }

    @GetMapping("/getGoodsById")
    private ResultTemplate<GoodsResponseVo> getGoodsById(Integer id) {
        log.info("id={}", id);
        return goodsDemoClient.getById(id);
    }

    @GetMapping("/getGoodsByPriceScope")
    private ResultTemplate<List<GoodsResponseVo>> getGoodsByPriceScope(BigDecimal minPrice, BigDecimal maxPrice) {
        return goodsDemoClient.getByPriceScope(minPrice, maxPrice);
    }

    @GetMapping("/getGoodsByMap")
    private ResultTemplate<List<GoodsResponseVo>> getGoodsByMap(@RequestParam Map<String, BigDecimal> requestMap) {
        BigDecimal minPrice = requestMap.get("minPrice");
        BigDecimal maxPrice = requestMap.get("maxPrice");
        return goodsDemoClient.getByPriceScope(minPrice, maxPrice);
    }

}
