package com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@RequestMapping("/goods")
public interface IGoodsApi {

    @GetMapping("{id}")
    ResponseTemplate<GoodsResponseVo> get(@PathVariable Integer id);

}
