# cy-unified-dispose-springboot-starter

[本项目参考](https://www.purgeteam.com/guide/old/unified-dispose-springboot-starter/#%E7%AE%80%E4%BB%8B)

## 简介

在 SpringBoot 项目里都有全局异常处理以及返回包装等，返回前端是带上succ、code、msg、data等字段。单个项目情况下很好解决，当微服务模块多的情况下，很多情况开发都是复制原有代码进行构建另外一个项目的，导致这些功能升级需要修改多个服务，在这个基础上，我们封装了一个组件,在里面包含了一些基础的异常处理以及返回包装功能。

### 1. 依赖添加启动功能
```xml
<dependency>
  <groupId>com.changyi</groupId>
  <artifactId>cy-unified-dispose-springboot-starter</artifactId>
</dependency>
```

已经在父pom**声明依赖**，子模块使用的时候直接引用即可使用

### 2. 启动类添加 `@EnableGlobalDispose` 注解开启以下功能

```java
@EnableGlobalDispose
@SpringBootApplication
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
```

## One 异常处理⚠️

### 1. 异常处理包含类型

```properties
# 404异常，需要在配置文件中配置，否则不抛
NoHandlerFoundException

# 可以抛出404异常
spring:
  mvc:
    throw-exception-if-no-handler-found: true
    static-path-pattern: /statics/**

# 暂时未测试
405 HttpRequestMethodNotSupportedException
415 HttpMediaTypeNotSupportedException

# 通用500异常
Exception

# feign调用异常
FeignException

# 业务异常
BusinessException

# 参数异常
HttpMessageNotReadableException
ConstraintViolationException
MethodArgumentNotValidException
BindException
```

### 2. 程序主动抛出异常

```java
throw new BusinessException(BusinessErrorCode.BUSINESS_ERROR);
// 或者
throw new BusinessException("B0001","没有多余的库存");
```

通常不建议直接抛出通用的BusinessException异常，应当在对应的模块里添加对应的领域的异常处理类以及对应的枚举错误类型。

如会员模块： 创建`UserException`异常类、`UserErrorCode`枚举。

UserException:

继承 `BusinessException`

```java
/**
 * {@link RuntimeException} user 业务异常
 *
 * @author purgeyao
 * @since 1.0
 */
@Getter
public class UserException extends BusinessException {

  private String code;
  private boolean isShowMsg = true;

  /**
   * 使用枚举传参
   *
   * @param errorCode 异常枚举
   */
  public UserException(UserErrorCode errorCode) {
    super(errorCode.getCode(), errorCode.getMessage());
    this.code = errorCode.getCode();
  }

}
```

UserErrorCode

```java
@Getter
@AllArgsConstructor
public enum UserErrorCode implements IErrorCodeEnum{
    /**
     * 权限异常
     */
    NOT_PERMISSIONS("B0001","您没有操作权限");

    private String code;

    private String message;
}
```

最后业务使用如下：

```java
// 判断是否有权限抛出异常
throw new UserException(UserErrorCode.NOT_PERMISSIONS);
```

**上述方式，抛出异常后会被模块处理。前台返回如下**：

```text
{
  "succ": false,        // 是否成功
  "ts": 1566467628851,  // 时间戳
  "data": null,         // 数据
  "code": "CLOUD800",   // 错误类型
  "msg": "业务异常"      // 错误描述
}
```

## Tow 统一返回封装🗳

在REST风格的开发中，避免通常会告知前台返回是否成功以及状态码等信息。这里我们通常返回的时候做一次`util`的包装处理工作，如：`Result`类似的类，里面包含`succ`、`code`、`msg`、`data`等字段。

接口调用处理类似如下:

```text
  @GetMapping("hello")
  public Result list(){
    return Result.ofSuccess("hello");
  }
```

结果:

```text
{
  "succ": ture,         // 是否成功
  "ts": 1566467628851,  // 时间戳
  "data": "hello",      // 数据
  "code": null,         // 错误类型
  "msg": null           // 错误描述
}
```

### 1. 功能使用

默认情况所有的 `web controller` 都会被封装为以下返回格式。

接口：

```java
@GetMapping("test")
public String test(){
  return "test";
}
```

返回

```json
{
  "succ": true,             // 是否成功
  "ts": 1566386951005,      // 时间戳
  "data": "test",           // 数据
  "code": null,             // 错误类型
  "msg": null               // 错误描述         
}
```

### 2. 忽略封装注解:@IgnoreResponseAdvice

`@IgnoreResponseAdvice`允许范围为：**类 + 方法**，标识在类上这个类下的所有方法的返回都将忽略返回封装。

接口：

```java
@IgnoreResponseAdvice // 忽略数据包装 可添加到类、方法上
@GetMapping("test")
public String test(){
  return "test";
}
```

返回 `test`