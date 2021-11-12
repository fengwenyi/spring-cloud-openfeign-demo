package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service.impl;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsClient;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsClientApi;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.feign.IGoodsClientWithFactory;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service.IOrderService;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderRequestVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    private final IGoodsClientWithFactory goodsClient;

    public OrderServiceImpl(IGoodsClientWithFactory goodsClient) {
        this.goodsClient = goodsClient;
    }

    @Override
    public ResponseTemplate<CreateOrderResponseVo> create(CreateOrderRequestVo requestVo) {

        ResponseTemplate<GoodsResponseVo> goodsResult = goodsClient.get(requestVo.getGoodsId());
        log.info(goodsResult.toString());

        return ResponseTemplate.success();
    }
}
