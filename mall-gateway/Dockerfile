FROM openjdk:8
MAINTAINER wangjie firstflipped@163.com
COPY ./target/gulimall-gateway-0.0.1-SNAPSHOT.jar /usr/local
WORKDIR /usr/local
# 修改docker时区为东八区，规避应用程序和北京时间相差8小时问题
ENV TZ=Asia/Shanghai
CMD ["java", "-jar", "gulimall-gateway-0.0.1-SNAPSHOT.jar"]