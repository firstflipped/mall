FROM openjdk:8
# 镜像作者及邮箱
LABEL maintainer=firstflipped@163.com
# 指定应用名
ARG APP_NAME="mall-admin"
COPY ./target/mall-gateway-0.0.1-SNAPSHOT.jar /usr/local
WORKDIR /usr/local
# 修改docker时区为东八区，规避应用程序和北京时间相差8小时问题
ENV TZ=Asia/Shanghai
CMD ["java", "-jar", "mall-gateway-0.0.1-SNAPSHOT.jar"]