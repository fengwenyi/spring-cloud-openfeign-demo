package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.impl;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.entity.GoodsEntity;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.repository.GoodsRepository;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.IGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public ResultTemplate<GoodsResponseVo> get(@NotNull Integer id) {
        GoodsEntity entity = goodsRepository.findById(id);
        if (entity == null) {
            return ResultTemplate.success();
        }
        GoodsResponseVo responseVo = new GoodsResponseVo();
        BeanUtils.copyProperties(entity, responseVo);
        return ResultTemplate.success(responseVo);
    }
}
