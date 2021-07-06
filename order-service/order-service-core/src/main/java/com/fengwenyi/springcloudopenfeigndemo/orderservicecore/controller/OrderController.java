package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.controller;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderRequestVo;
import com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo.CreateOrderResponseVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Erwin Feng
 * @since 2021-07-07
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/create")
    public ResultTemplate<CreateOrderResponseVo> create(@RequestBody @Validated CreateOrderRequestVo requestVo) {
        return null;
    }

}
