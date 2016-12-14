## Spring boot
    Takes an opinionated view of building production-ready Spring applications. 
    Spring Boot favors convention over configuration and is designed to get you up and running as quickly as possible.

###Features
- Create stand-alone Spring applications：创建独立的spring应用
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)：嵌入Tomcat, Jetty Undertow 而且不需要用war的方式部署他们
- Provide opinionated 'starter' POMs to simplify your Maven configuration：提供的“starters”poms来简化Maven配置
- Automatically configure Spring whenever possible：尽可能自动配置spring应用
- Provide production-ready features such as metrics, health checks and externalized configuration：提供生产指标,健壮检查和外部化配置
- Absolutely no code generation and no requirement for XML configuration：绝对没有代码生成和XML配置要求

        快速、门槛低、Java config

###缺点：
- 文档较少
- 出错不容易排查
- 入门容易、深入难


### 发布微服务
    
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
###启动类
        @SpringBootApplication
        public class Application {
            public static void main(String[] args) {
                SpringApplication.run(Application.class, args);
            }
        }
   
        
-   提供RESTful接口
-   发布静态资源(/resources/static)

### mysql + mybatis
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>1.1.1</version>
            <exclusions>
                <exclusion>
                    <groupId>org.apache.tomcat</groupId>
                    <artifactId>tomcat-jdbc</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.21</version>
        </dependency>
        <!--  ============= 光  连接池  ================ -->
        <dependency>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP-java7</artifactId>
            <version>2.4.9</version>
        </dependency>
        
application.properties       
    
    spring.datasource.url=jdbc:mysql://localhost:3306/test
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.jdbc.Driver
    
springboot 默认采用tomcat-jdbc连接池，应用排除了默认连接池，采用“光”连接池



================================================================

到此一个 mvc + mysql的服务已可以发布，能够完成大部分的web开发工作

================================================================

### 模板引擎 
Spring Boot提供了默认配置的模板引擎主要有以下几种：
-   Thymeleaf
-   FreeMarker
-   Velocity
-   Groovy
-   Mustache


    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

\resources\templates目录下创建模板文件


###RESTful文档自动生成——swagger2

        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>${version.swagger2}</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>${version.swagger2}</version>
        </dependency>

- swagger配置类


    @Configuration
    @EnableSwagger2
    public class Swagger {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.yd.web.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }
   
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("比价工具接口")
                    .description("App版比价工具接口")
                    .termsOfServiceUrl("")
                    .contact("易到-大数据技术组")
                    .version("1.0")
                    .build();
        }
   
    }

- 访问 http://localhost:8080/swagger-ui.htm

================================================================

前后端分离后的契约

================================================================

### 指标监控
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>   
    
####主要暴露的功能

- GET	/autoconfig	查看自动配置的使用情况	true
- GET	/configprops	查看配置属性，包括默认配置	true
- GET	/beans	查看bean及其关系列表	true
- GET	/dump	打印线程栈	true
- GET	/env	查看所有环境变量	true
- GET	/env/{name}	查看具体变量值	true
- GET	/health	查看应用健康指标	false
- GET	/info	查看应用信息	false
- GET	/mappings	查看所有url映射	true
- GET	/metrics	查看应用基本指标	true
- GET	/metrics/{name}	查看具体指标	true
- POST	/shutdown	关闭应用	true
- GET	/trace	查看基本追踪信息	true

###Spring Boot 其他内容
- 定时任务Scheduled
- 异步Async
- AOP
- 缓存支持EnCache Redis
- 返回结果统一处理（Advice）
- 指标监控 

### Spring Boot 官方文档
    http://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/
    
###Application properties
    http://docs.spring.io/spring-boot/docs/2.0.0.BUILD-SNAPSHOT/reference/htmlsingle/#appendix

