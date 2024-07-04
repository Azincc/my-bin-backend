FROM rockylinux:9.3
RUN yum update -y && yum install -y wget
# 下载并解压jdk
RUN wget -O /tmp/openjdk-17.0.2_linux-x64_bin.tar.gz https://repo.huaweicloud.com/openjdk/17.0.2/openjdk-17.0.2_linux-x64_bin.tar.gz && \
    mkdir -p /opt && \
    tar -xzf /tmp/openjdk-17.0.2_linux-x64_bin.tar.gz -C /opt && \
    rm -f /tmp/openjdk-17.0.2_linux-x64_bin.tar.gz \
# 设置环境变量
ENV PATH="/opt/jdk-17.0.2/bin:${PATH}"
COPY ../target/PasteBin-0.0.1-SNAPSHOT.jar /app/PasteBin-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "/app/PasteBin-0.0.1-SNAPSHOT.jar"]
