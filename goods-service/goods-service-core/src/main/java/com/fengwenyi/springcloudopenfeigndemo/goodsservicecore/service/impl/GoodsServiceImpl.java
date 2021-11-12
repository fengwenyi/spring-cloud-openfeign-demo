package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.impl;

import com.fengwenyi.api.result.ResponseTemplate;
import com.fengwenyi.springcloudopenfeigndemo.goodsserviceapi.vo.GoodsResponseVo;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.entity.GoodsEntity;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.repository.GoodsRepository;
import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.service.IGoodsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public ResponseTemplate<GoodsResponseVo> get(@NotNull Integer id) {
        /*try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        GoodsEntity entity = goodsRepository.findById(id);
        if (entity == null) {
            return ResponseTemplate.success();
        }
        GoodsResponseVo responseVo = new GoodsResponseVo();
        BeanUtils.copyProperties(entity, responseVo);
        return ResponseTemplate.success(responseVo);
    }

    @Override
    public ResponseTemplate<List<GoodsResponseVo>> getByPriceScope(@NotNull BigDecimal minPrice, @NotNull BigDecimal maxPrice) {
        List<GoodsEntity> goodsEntityList = goodsRepository.findAll();
        List<GoodsResponseVo> responseVoList = goodsEntityList.stream().map(entity -> {
            if (entity.getPrice().compareTo(minPrice) > 0 && entity.getPrice().compareTo(maxPrice) < 0) {
                GoodsResponseVo responseVo = new GoodsResponseVo();
                BeanUtils.copyProperties(entity, responseVo);
                return responseVo;
            }
            return null;
        }).collect(Collectors.toList());
        return ResponseTemplate.success(responseVoList);
    }

    @Override
    public ResponseTemplate<List<GoodsResponseVo>> getByMap(Map<String, BigDecimal> requestMap) {
        BigDecimal minPrice = requestMap.get("minPrice");
        BigDecimal maxPrice = requestMap.get("maxPrice");
        return getByPriceScope(minPrice, maxPrice);
    }
}
