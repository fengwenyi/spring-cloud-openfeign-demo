package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.controller;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsDemoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private RestTemplate restTemplate;

    public DemoController(IGoodsDemoClient goodsDemoClient) {
        this.goodsDemoClient = goodsDemoClient;
    }

    @GetMapping("/getGoodsById")
    private ResponseTemplate<GoodsResponseVo> getGoodsById(Integer id) {
        log.info("id={}", id);
        return goodsDemoClient.getById(id);
    }

    @GetMapping("/getGoodsByIdUseRestTemplate")
    private ResponseTemplate<GoodsResponseVo> getGoodsByIdUseRestTemplate(Integer id) {
        log.info("id={}", id);
        String url = "http://GOODS-SERVICE/goodsDemo/byId?id=" + id;
        ResponseTemplate<?> resultTemplate = restTemplate.getForEntity(url, ResponseTemplate.class).getBody();
        return (ResponseTemplate<GoodsResponseVo>) resultTemplate;
    }

    /*@GetMapping("/getGoodsByIdUseOkHttp")
    private ResultTemplate<GoodsResponseVo> getGoodsByIdUseOkHttp(Integer id) throws IOException {
        log.info("id={}", id);
        String url = "http://GOODS-SERVICE/goodsDemo/byId?id=" + id;
        String responseString = new OkHttpClient()
                .newCall(
                        new Request.Builder()
                                .url(url)
                                .build())
                .execute()
                .body()
                .string();
        log.info("响应：{}", responseString);
        return JsonUtils.convertObject(responseString, ResultTemplate.class);
    }*/

    @GetMapping("/getGoodsByPriceScope")
    private ResponseTemplate<List<GoodsResponseVo>> getGoodsByPriceScope(BigDecimal minPrice, BigDecimal maxPrice) {
        return goodsDemoClient.getByPriceScope(minPrice, maxPrice);
    }

    @GetMapping("/getGoodsByMap")
    private ResponseTemplate<List<GoodsResponseVo>> getGoodsByMap(@RequestParam Map<String, BigDecimal> requestMap) {
        BigDecimal minPrice = requestMap.get("minPrice");
        BigDecimal maxPrice = requestMap.get("maxPrice");
        return goodsDemoClient.getByPriceScope(minPrice, maxPrice);
    }

}
