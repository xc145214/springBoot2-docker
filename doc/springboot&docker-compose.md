# 使用docker-compose 部署简单 springBoot 项目(mysql)

## docker-compose.yaml 文件详解
```yaml
# 表示使用第三代语法
version: '3'
# 用来表示 compose 需要启动的服务
services:

# mysql 服务配置
  mysql:
  # 容器名称
   container_name: v-mysql
   image: mysql/mysql-server:5.7
   # 环境变量
   environment:
    MYSQL_DATABASE: test
    MYSQL_ROOT_PASSWORD: root
    MYSQL_ROOT_HOST: '%'
   # 对外开放端口
   ports:
   - "3306:3306"
   # 如果服务启动不成功会一直尝试
   restart: always

  springboot2-docker:
    restart: always
    build: ./springboot2-docker
    working_dir: /springboot2-docker
    volumes:
      - ./springboot2-docker:/springboot2-docker
      - ~/.m2:/home/xiac/.m2
    expose:
      - "8080"
    # 依赖服务
    depends_on:
      - mysql
    command: mvn clean spring-boot:run -Dspring-boot.run.profiles=docker
```
+ version: '3'： 表示使用第三代语法来构建 docker-compose.yaml 文件。
+ services: 用来表示 compose 需要启动的服务，我们可以看出此文件中有三个服务分别为：nginx、mysql、app。
+ container_name: 容器名称
+ environment: 此节点下的信息会当作环境变量传入容器，此示例中 mysql 服务配置了数据库、密码和权限信息。
+ ports: 表示对外开放的端口
+ restart: always 表示如果服务启动不成功会一直尝试。
+ volumes: 加载本地目录下的配置文件到容器目标地址下
+ depends_on：可以配置依赖服务，表示需要先启动 depends_on 下面的服务后，再启动本服务。
+ command: mvn clean spring-boot:run -Dspring-boot.run.profiles=docker: 表示以这个命令来启动项目，-Dspring-boot.run.profiles=docker表示使用 application-docker.properties文件配置信息进行启动。