package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderRequestVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderResponseVo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
public interface IOrderService {

    ResponseTemplate<CreateOrderResponseVo> create(CreateOrderRequestVo requestVo);

}
