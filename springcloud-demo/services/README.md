# 服务拆分
> 技术栈：**SpringCloud Finchley.SR1**  
> SpringBoot 2.0.4.RELEASE、Redis 4.0.9、Mybatis、MySQL、日志分析（ELK）

### 一、用户服务：
1. **用户接口设计**
2. **接入Spring Data Redis**
3. **用户注册、查询、鉴权等接口实现**

### 二、网关服务：
1. **网关服务设计**
2. **RestTemplate调用服务接口**
3. **封装RestTemplate组件**

### 三、房产服务：
1. **房产服务接口设计**
2. **通过Api-Gateway及房产服务实现房产功能**
3. **通过Swagger(接口文档生成工具)生成文档**

### 四、评论博客服务：
1. **评论服务接口设计**
2. **博客服务接口设计**



#### 附：
1. Swagger接入步骤：  
    * pom文件添加Swagger2依赖
    * 启动类添加 **@EnableSwagger2** 注解
    * 浏览器打开/swagger-ui.html查看生成文档  

2. 跨域解决方案：Springboot CORS
    * 浏览器同源策略：从一个源（域名）加载的的脚本或文档不能访问来自另一个源（域名）的资源
    * 跨域资源请求CORS：
        >1. CORS是一个W3C标准，全称是跨域资源共享  
        >2. 通过添加Respones Header实现
        >3. 与Jsonp相比，Jsonp只支持GET，CORS需要较新的浏览器支持
    * CORS介绍：
        >1. CORS头部定义：Access-Control-Allow-Origin、Access-Control-Allow-Credentials、Access-Control-Expose-Headers
    * springboot支持CORS步骤：
        >1. 全局支持：覆盖WebMvcConfigurerAdapter（springboot2.0是覆盖WebMvcConfigurer）的addCorsMappings方法
        >2. 方法级支持：在Controller方法体添加注解@CrossOrigin
 
3. 熔断器Hystrix
    * 引入mavn依赖：
        ```java
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
          </dependency>
        ```
    * 添加默认配置：
        ```java
          //在类上进行hystrix属性配置
          @DefaultProperties(groupKey="userDao",
          		commandProperties={@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")},	//命令属性--超时时间
          		threadPoolProperties={@HystrixProperty(name="coreSize",value="10")		//线程池属性--线程池大小、最大队列大小
          				,@HystrixProperty(name="maxQueueSize",value="1000")},
          		threadPoolKey="userDao"
          )
    
          //在方法上配置注解（hystrix命令配置，包括服务降级等）
          @HystrixCommand
    
          //在启动类上添加注解
          @EnableCircuitBreaker
        ```