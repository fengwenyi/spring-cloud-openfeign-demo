package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
public interface IGoodsService {

    ResultTemplate<GoodsResponseVo> get(Integer id);

    ResultTemplate<List<GoodsResponseVo>> getByPriceScope(BigDecimal minPrice, BigDecimal maxPrice);

    ResultTemplate<List<GoodsResponseVo>> getByMap(Map<String, BigDecimal> requestMap);
}
