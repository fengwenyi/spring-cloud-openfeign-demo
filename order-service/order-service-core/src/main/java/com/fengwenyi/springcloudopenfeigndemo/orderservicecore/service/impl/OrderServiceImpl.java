package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service.impl;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsFeignClient;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service.IOrderService;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderRequestVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IGoodsFeignClient goodsFeignClient;

    @Override
    public ResultTemplate<CreateOrderResponseVo> create(CreateOrderRequestVo requestVo) {

        ResultTemplate<GoodsResponseVo> goodsResult = goodsFeignClient.get(requestVo.getGoodsId());
        log.info(goodsResult.toString());

        return ResultTemplate.success();
    }
}
