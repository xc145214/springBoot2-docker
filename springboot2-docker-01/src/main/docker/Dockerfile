# 使用jdk8
FROM openjdk:8-jdk-alpine

# 在宿主机的/var/lib/docker目录下创建一个临时文件并把它链接到容器中的/tmp目录

VOLUME /tmp

# 拷贝文件并且重命名
ADD springboot2-docker-01-1.0-SNAPSHOT.jar app.jar

# 为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]