package com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.repository;

import com.fengwenyi.springcloudopenfeigndemo.goodsservicecore.entity.GoodsEntity;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.fengwenyi.com">Erwin Feng</a>
 * @since 2021-07-20
 */
@Repository
public class GoodsRepository {

    private final Map<Integer, GoodsEntity> entityMap = new HashMap<>();

    public List<GoodsEntity> findAll() {
        List<GoodsEntity> list = new ArrayList<>();
        entityMap.forEach((k, v) -> list.add(v));
        return list;
    }

    public GoodsEntity findById(@NotNull Integer id) {
        return entityMap.get(id);
    }

    public void updateById(@NotNull GoodsEntity entity) {
        Integer id = entity.getId();
        entityMap.put(id, entity);
    }


    public void init() {
        GoodsEntity entity1 = new GoodsEntity()
                .setId(1)
                .setName("vivo S10 Pro 5G手机 12GB+256GB 绮光焕彩 后置一亿像素 自然柔光人像 光致变色工艺 6nm旗舰芯片")
                .setPrice(new BigDecimal(3399))
                .setStock(1000)
                ;
        entityMap.put(entity1.getId(), entity1);

        GoodsEntity entity2 = new GoodsEntity()
                .setId(2)
                .setName("Apple iPhone 12 (A2404) 128GB 紫色 支持移动联通电信5G 双卡双待手机")
                .setPrice(new BigDecimal(6799))
                .setStock(1000)
                ;
        entityMap.put(entity2.getId(), entity2);

        GoodsEntity entity3 = new GoodsEntity()
                .setId(3)
                .setName("【搭载HarmonyOS 2】华为 HUAWEI Mate 40 Pro 4G 全网通 麒麟9000旗舰芯片 8GB+128GB秘银色手机")
                .setPrice(new BigDecimal(6099))
                .setStock(1000)
                ;
        entityMap.put(entity3.getId(), entity3);

        GoodsEntity entity4 = new GoodsEntity()
                .setId(4)
                .setName("华为 HUAWEI nova 8 Pro 4G 全网通 Vlog视频双镜头 120Hz环幕屏 66W华为超级快充8GB+128GB 普罗旺斯手机")
                .setPrice(new BigDecimal(3699))
                .setStock(1000)
                ;
        entityMap.put(entity4.getId(), entity4);

        GoodsEntity entity5 = new GoodsEntity()
                .setId(5)
                .setName("Redmi K40 Pro 旗舰骁龙888 三星E4旗舰120Hz高刷直屏 6400万高清三摄相机 杜比全景音 33W快充 8GB+256GB 晴雪 游戏电竞智能5G手机 小米 红米")
                .setPrice(new BigDecimal(3299))
                .setStock(1000)
                ;
        entityMap.put(entity5.getId(), entity5);
    }

}
