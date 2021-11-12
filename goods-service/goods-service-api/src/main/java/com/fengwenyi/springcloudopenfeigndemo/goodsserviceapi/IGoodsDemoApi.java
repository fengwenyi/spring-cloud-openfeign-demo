package com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@RequestMapping("/goodsDemo")
public interface IGoodsDemoApi {

    @GetMapping("/byId")
    ResponseTemplate<GoodsResponseVo> getById(@RequestParam("id") Integer id);

    @GetMapping("/byPriceScope")
//    ResultTemplate<List<GoodsResponseVo>> getByPriceScope(BigDecimal minPrice, BigDecimal maxPrice);
    ResponseTemplate<List<GoodsResponseVo>> getByPriceScope(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice);
//    ResultTemplate<List<GoodsResponseVo>> getByPriceScope(@RequestParam("minPrice") BigDecimal minPrice, @RequestParam("maxPrice") BigDecimal maxPrice);

    @GetMapping("/byMap")
    ResponseTemplate<List<GoodsResponseVo>> getByMap(Map<String, BigDecimal> requestMap);
}
