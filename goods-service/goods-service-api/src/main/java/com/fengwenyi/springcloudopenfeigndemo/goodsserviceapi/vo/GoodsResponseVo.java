package com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Data
public class GoodsResponseVo {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

}
