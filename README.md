# Spring Cloud OpenFeign Demo

## 示例描述

用户下单，

调用商品服务查询商品信息，调用用户服务查询用户信息，

调用商品服务进行减库存

## 推荐使用

## 错误使用

## Feign示例

### 根据ID查询

**Goods Service API**

```java
@RequestMapping("/goods")
public interface IGoodsApi {

    @GetMapping("{id}")
    ResultTemplate<GoodsResponseVo> get(@PathVariable Integer id);

}
```

**Goods Service API Impl**

```java
@RestController
public class GoodsController implements IGoodsApi {

    @Autowired
    private IGoodsService goodsService;

    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return goodsService.get(id);
    }
}
```

**Order Service**

```java
@FeignClient("spring-cloud-openfeign-demo-goods-service")
public interface IGoodsFeignClient extends IGoodsApi {
}
```

```java
@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IGoodsFeignClient goodsFeignClient;

    @Override
    public ResultTemplate<CreateOrderResponseVo> create(CreateOrderRequestVo requestVo) {

        ResultTemplate<GoodsResponseVo> goodsResult = goodsFeignClient.get(requestVo.getGoodsId());
        log.info(goodsResult.toString());

        return ResultTemplate.success();
    }
}
```

## fallback

### fallback

#### 不继承API

```java
@FeignClient(value = "goods-service", fallback = GoodsFallback.class)
public interface IGoodsClient {

    @GetMapping("/goods/{id}")
    ResultTemplate<GoodsResponseVo> get(@PathVariable Integer id);

}

@Component
class GoodsFallback implements IGoodsClient {
    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_SERVICE_CALL_EXCEPTION);
    }
}
```

#### 继承API

```java
@FeignClient(value = "goods-service", fallback = GoodsFallbackApi.class)
public interface IGoodsClientApi extends IGoodsApi {
}

@Component
@RequestMapping("/fallback")
class GoodsFallbackApi implements IGoodsClientApi {
    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_SERVICE_CALL_EXCEPTION);
    }
}
```

### fallbackFactory

```java
@FeignClient(value = "goods-service", fallbackFactory = GoodsFallbackFactory.class)
public interface IGoodsClientWithFactory extends IGoodsApi {
}

@Component
class GoodsFallbackFactory implements FallbackFactory<GoodsFallbackWithFactory> {
    @Override
    public GoodsFallbackWithFactory create(Throwable cause) {
        return new GoodsFallbackWithFactory();
    }
}

class GoodsFallbackWithFactory implements IGoodsClientWithFactory {
    @Override
    public ResultTemplate<GoodsResponseVo> get(Integer id) {
        return ResultTemplate.fail(IReturnCode.Default.ERROR_SERVICE_CALL_EXCEPTION);
    }
}
```

