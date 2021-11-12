package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
public interface IGoodsService {

    ResponseTemplate<GoodsResponseVo> get(Integer id);

    ResponseTemplate<List<GoodsResponseVo>> getByPriceScope(BigDecimal minPrice, BigDecimal maxPrice);

    ResponseTemplate<List<GoodsResponseVo>> getByMap(Map<String, BigDecimal> requestMap);
}
