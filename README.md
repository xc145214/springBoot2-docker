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

