# 使用docker-compose 部署简单 springBoot 项目(mysql)

## 启动mysql
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
```
启动 
```sh
$ docker-compose up
Creating network "springboot2-docker-02_default" with the default driver
Pulling mysql (mysql/mysql-server:5.7)...
Trying to pull repository docker.io/mysql/mysql-server ...
b0efbbec3b2e: Pull complete
5053dccc7425: Pull complete
16b4ec95c155: Pull complete
8b211b61b1a0: Pull complete
Digest: sha256:eb3aa08c047efcb3e6bfcc3a28b80a2ec8c67b4315712b26679b0b22320f0b4a
Status: Downloaded newer image for docker.io/mysql/mysql-server:5.7
Creating v-mysql ... error

ERROR: for v-mysql  Cannot start service mysql: driver failed programming external connectivity on endpoint v-mysql (60f881aef4b4bd36805defbae245a298a88639003d9adc2cb72ca19b7c1909ae): Error starting userland proxy: listen tcp 0.0.0.0:3306: bind: address already in use

ERROR: for mysql  Cannot start service mysql: driver failed programming external connectivity on endpoint v-mysql (60f881aef4b4bd36805defbae245a298a88639003d9adc2cb72ca19b7c1909ae): Error starting userland proxy: listen tcp 0.0.0.0:3306: bind: address already in use
ERROR: Encountered errors while bringing up the project.

```
查看端口
```sh
sudo netstat -apn |grep 3306
[sudo] password for xiac:
tcp6       0      0 :::33060                :::*                    LISTEN      1269/mysqld
tcp6       0      0 :::3306                 :::*                    LISTEN      1269/mysqld
```
调整后启动
```sh
$ docker-compose up
Recreating v-mysql ... done
Attaching to v-mysql
v-mysql  | [Entrypoint] MySQL Docker Image 5.7.22-1.1.5
v-mysql  | [Entrypoint] Initializing database
v-mysql  | [Entrypoint] Database initialized
v-mysql  | Warning: Unable to load '/usr/share/zoneinfo/iso3166.tab' as time zone. Skipping it.
v-mysql  | Warning: Unable to load '/usr/share/zoneinfo/leapseconds' as time zone. Skipping it.
v-mysql  | Warning: Unable to load '/usr/share/zoneinfo/tzdata.zi' as time zone. Skipping it.
v-mysql  | Warning: Unable to load '/usr/share/zoneinfo/zone.tab' as time zone. Skipping it.
v-mysql  | Warning: Unable to load '/usr/share/zoneinfo/zone1970.tab' as time zone. Skipping it.
v-mysql  |
v-mysql  | [Entrypoint] ignoring /docker-entrypoint-initdb.d/*
v-mysql  |
v-mysql  | [Entrypoint] Server shut down
v-mysql  |
v-mysql  | [Entrypoint] MySQL init process done. Ready for start up.
v-mysql  |
v-mysql  | [Entrypoint] Starting MySQL 5.7.22-1.1.5

```

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

