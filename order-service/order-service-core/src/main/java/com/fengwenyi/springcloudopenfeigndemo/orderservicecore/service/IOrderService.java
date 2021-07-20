package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.service;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderRequestVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderResponseVo;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
public interface IOrderService {

    ResultTemplate<CreateOrderResponseVo> create(CreateOrderRequestVo requestVo);

}
