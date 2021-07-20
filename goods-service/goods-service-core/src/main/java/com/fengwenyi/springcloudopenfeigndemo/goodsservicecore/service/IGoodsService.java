package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
public interface IGoodsService {

    ResultTemplate<GoodsResponseVo> get(Integer id);

}
