# Spring Boot 2 & docker

## 使用dockers 部署 springBoot 项目

```sh
# 下载代码
git clone https://github.com/xc145214/springBoot2-docker.git
# 编译
mvn package
# 启动测试
java -jar target/springboot2-docker-1.0-SNAPSHOT.jar
```
看到 Spring Boot 的启动日志后表明环境配置没有问题，接下来我们使用 DockerFile 构建镜像。
```sh
# docker build
mvn package docker:build
```
构建结果
```sh
[xiac@bogon springboot2-docker]$ mvn package docker:build
……
[INFO] Building image springboot/springboot2-docker
Step 1/4 : FROM openjdk:8-jdk-alpine
 ---> 6a6a75aac6c9
Step 2/4 : VOLUME /tmp
 ---> Using cache
 ---> 159fa5d0b8d2
Step 3/4 : ADD springboot2-docker-1.0-SNAPSHOT.jar app.jar
 ---> 1b8ac022e62e
Removing intermediate container c7ed4b4cd4ba
Step 4/4 : ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
 ---> Running in 7220798f9091
 ---> 72ab9f116453
Removing intermediate container 7220798f9091
Successfully built 72ab9f116453
……
```
查看构建好的镜像
```sh
[xiac@bogon springboot2-docker]$ docker images
REPOSITORY                      TAG                 IMAGE ID            CREATED                  SIZE
docker.io/openjdk               8-jdk-alpine        6a6a75aac6c9        Less than a second ago   102 MB
springboot/springboot2-docker   latest              72ab9f116453        12 seconds ago           118 MB
……
```
`springboot/springboot2-docker` 就是我们构建好的镜像，下一步就是运行该镜像
```sh
docker run -p 8080:8080 -t springboot/springboot2-docker
```
启动结果
```sh

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.0.0.RELEASE)

2018-06-16 05:42:19.836  INFO 1 --- [           main] com.github.xc145214.DockerApplication    : Starting DockerApplication v1.0-SNAPSHOT on cfcc732bded6 with PID 1 (/app.jar started by root in /)
2018-06-16 05:42:19.849  INFO 1 --- [           main] com.github.xc145214.DockerApplication    : No active profile set, falling back to default profiles: default
2018-06-16 05:42:19.983  INFO 1 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@1c6b6478: startup date [Sat Jun 16 05:42:19 GMT 2018]; root of context hierarchy
2018-06-16 05:42:21.935  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2018-06-16 05:42:21.973  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2018-06-16 05:42:21.973  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.28
2018-06-16 05:42:22.000  INFO 1 --- [ost-startStop-1] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [/usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64/server:/usr/lib/jvm/java-1.8-openjdk/jre/lib/amd64:/usr/lib/jvm/java-1.8-openjdk/jre/../lib/amd64:/usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib]
2018-06-16 05:42:22.202  INFO 1 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2018-06-16 05:42:22.202  INFO 1 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 2231 ms
2018-06-16 05:42:22.403  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Servlet dispatcherServlet mapped to [/]
2018-06-16 05:42:22.407  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2018-06-16 05:42:22.408  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2018-06-16 05:42:22.408  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2018-06-16 05:42:22.409  INFO 1 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2018-06-16 05:42:22.888  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@1c6b6478: startup date [Sat Jun 16 05:42:19 GMT 2018]; root of context hierarchy
2018-06-16 05:42:23.000  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/]}" onto public java.lang.String com.github.xc145214.web.DockerController.index()
2018-06-16 05:42:23.007  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2018-06-16 05:42:23.008  INFO 1 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2018-06-16 05:42:23.069  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2018-06-16 05:42:23.069  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2018-06-16 05:42:23.130  INFO 1 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2018-06-16 05:42:23.318  INFO 1 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2018-06-16 05:42:23.380  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2018-06-16 05:42:23.385  INFO 1 --- [           main] com.github.xc145214.DockerApplication    : Started DockerApplication in 4.402 seconds (JVM running for 5.616)
2018-06-16 05:42:37.803  INFO 1 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2018-06-16 05:42:37.804  INFO 1 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2018-06-16 05:42:37.825  INFO 1 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 21 ms
```
