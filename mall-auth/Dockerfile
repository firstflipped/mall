FROM openjdk:8-jre
# 镜像作者及邮箱
LABEL maintainer=firstflipped@163.com
# 指定应用名
ARG APP_NAME="mall-auth"
# 从宿主机把目标文件拷贝到目标镜像中
COPY ./target/mall-auth-0.0.1-SNAPSHOT.jar /opt
# 指定工作目录
WORKDIR /opt
# 修改docker时区为东八区，规避应用程序和北京时间相差8小时问题
ENV TZ=Asia/Shanghai
# CMD ["java", "-jar", "mall-admin-0.0.1-SNAPSHOT.jar"]
CMD java -DNACOS.SERVER-ADDR=http://192.168.137.47:8848 -jar mall-auth-0.0.1-SNAPSHOT.jar