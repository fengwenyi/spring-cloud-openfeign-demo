package com.fengwenyi.springcloudopenfeigndemo.orderservicecore.vo;

import lombok.Data;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-07
 */
@Data
public class CreateOrderRequestVo {

    private Integer userId;
    private Integer goodsId;
    private Integer goodsNum;

}
