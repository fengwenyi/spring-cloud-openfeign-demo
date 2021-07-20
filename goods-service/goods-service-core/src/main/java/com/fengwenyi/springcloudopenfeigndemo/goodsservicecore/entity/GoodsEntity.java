package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Data
@Accessors(chain = true)
public class GoodsEntity {

    private Integer id;

    private String name;

    private BigDecimal price;

    private Integer stock;

}
