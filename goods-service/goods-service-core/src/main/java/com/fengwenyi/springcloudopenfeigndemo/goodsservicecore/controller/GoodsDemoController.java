package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.controller;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsDemoApi;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-30
 */
@RestController
public class GoodsDemoController implements IGoodsDemoApi {

    private final IGoodsService goodsService;

    public GoodsDemoController(IGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @Override
    public ResponseTemplate<GoodsResponseVo> getById(Integer id) {
        return goodsService.get(id);
    }

    @Override
    public ResponseTemplate<List<GoodsResponseVo>> getByPriceScope(BigDecimal minPrice, BigDecimal maxPrice) {
        return goodsService.getByPriceScope(minPrice, maxPrice);
    }

    @Override
    public ResponseTemplate<List<GoodsResponseVo>> getByMap(Map<String, BigDecimal> requestMap) {
        return goodsService.getByMap(requestMap);
    }
}
