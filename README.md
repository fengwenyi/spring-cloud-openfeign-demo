# Spring Cloud OpenFeign Demo

## 示例描述

用户下单，

调用商品服务查询商品信息，调用用户服务查询用户信息，

调用商品服务进行减库存

## 推荐使用

### 单个参数

**Get**

```java
@GetMapping("/byId")
ResultTemplate<GoodsResponseVo> getById(@RequestParam Integer id);
```

**Post**

```java
@PostMapping("/byId")
ResultTemplate<GoodsResponseVo> getById(@RequestParam Integer id);
```

## 错误使用

### 单个参数

```java
@GetMapping("/byId")
ResultTemplate<GoodsResponseVo> getById(Integer id);
```

### 多个参数

```java
@GetMapping("/byPriceScope")
ResultTemplate<List<GoodsResponseVo>> getByPriceScope(BigDecimal minPrice, BigDecimal maxPrice);
```

```java
@GetMapping("/byPriceScope")
ResultTemplate<List<GoodsResponseVo>> getByPriceScope(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice);
```

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

## 常见错误

### 同一个服务，多个客户端报错问题

错误如下：

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The bean 'goods-service.FeignClientSpecification' could not be registered. A bean with that name has already been defined and overriding is disabled.

Action:

Consider renaming one of the beans or enabling overriding by setting spring.main.allow-bean-definition-overriding=true
```

**解决方案一**

添加配置，允许Feign Client重复

> spring.main.allow-bean-definition-overriding=true

**解决方案二**

为Feign Client设置不同的 `contextId`

> @FeignClient(value = "goods-service", contextId = "goods-demo")

### GET请求自动转POST请求

**API写法**

```java
@GetMapping("/byId")
ResultTemplate<GoodsResponseVo> getById(Integer id);
```

**调用服务报错**

```
2021-07-30 16:06:58.930 ERROR 74290 --- [nio-8082-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException: No fallback available.] with root cause

feign.FeignException$MethodNotAllowed: [405] during [GET] to [http://goods-service/goodsDemo/byId] [IGoodsDemoClient#getById(Integer)]: [{"timestamp":"2021-07-30T08:06:58.909+00:00","status":405,"error":"Method Not Allowed","path":"/goodsDemo/byId"}]
	at feign.FeignException.clientErrorStatus(FeignException.java:203) ~[feign-core-10.12.jar:na]
	at feign.FeignException.errorStatus(FeignException.java:177) ~[feign-core-10.12.jar:na]
	at feign.FeignException.errorStatus(FeignException.java:169) ~[feign-core-10.12.jar:na]
	at feign.codec.ErrorDecoder$Default.decode(ErrorDecoder.java:92) ~[feign-core-10.12.jar:na]
	at feign.AsyncResponseHandler.handleResponse(AsyncResponseHandler.java:96) ~[feign-core-10.12.jar:na]
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:138) ~[feign-core-10.12.jar:na]
	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:89) ~[feign-core-10.12.jar:na]
	at org.springframework.cloud.openfeign.FeignCircuitBreakerInvocationHandler.lambda$asSupplier$1(FeignCircuitBreakerInvocationHandler.java:109) ~[spring-cloud-openfeign-core-3.0.3.jar:3.0.3]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
```

**被调服务报错**

```
2021-07-30 16:06:58.887  WARN 74285 --- [nio-8081-exec-1] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'POST' not supported]
```

**分析**

奇怪了，明明用的Get请求，怎么报不支持Post请求呢。

很明显，Feign将Get请求，自动转为Post请求了。

为什么呢？

尝试将请求换成 `@PostMapping`，这种倒是不报错，但是没有数据返回。

[Feign-Get请求自动转成Post问题分析](https://blog.csdn.net/zcswl7961/article/details/106328174/)

这篇博文，大概分析，Feign发现请求的body有参数，会自动转成Post请求。

**解决方案**

其实解决方案很简单，只需要在参数前加：`@RequestParam`。
