package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.IGoodsApi;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@RestController
public class GoodsController implements IGoodsApi {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return goodsService.get(id);
    }
}
