# Source Code

FeignCircuitBreakerInvocationHandler

```
FeignCircuitBreakerInvocationHandler

#invoke()
feign.Feign.configKey(java.lang.Class, java.lang.reflect.Method)
    => IGoodsDemoClient#getById(Integer
    
org.springframework.cloud.client.circuitbreaker.CircuitBreaker
org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreaker.run
```

## 

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
```



```java
@Bean
public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(20)).build())
            .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
            .build());
}
```
