# 参数说明
# https://www.dwhd.org/20151202_113538.html

# jdk 是一个已有的包含 jdk 的镜像
FROM adoptopenjdk/openjdk8

# 作者签名
MAINTAINER ldg

# 从 linux 服务器拷贝 jar 到 镜像
COPY app_runtime.jar app.jar

# 环境
ENV minx 256m
ENV mins 256m
ENV max 512m
ENV envs prod
ENV port 7035
ENV JAVA_OPTS="\
-server \
-Xmx256m \
-Xms256m \
-Xmn256m \
-XX:SurvivorRatio=8 \
-XX:MetaspaceSize=256m \
-XX:MaxMetaspaceSize=512m \
-XX:+UseParallelGC \
-XX:ParallelGCThreads=4 \
-XX:+UseParallelOldGC \
-XX:+UseAdaptiveSizePolicy \
-XX:+PrintGCDetails \
-XX:+PrintTenuringDistribution \
-XX:+PrintGCTimeStamps \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=/ \
-Xloggc:/gc.log \
-XX:+UseGCLogFileRotation \
-XX:NumberOfGCLogFiles=5 \
-XX:GCLogFileSize=10M"

# 执行 java -jar 命令 （CMD：在启动容器时才执行此行。RUN：构建镜像时就执行此行）
# CMD java -jar /app.jar
# CMD ["/opt/script/start.sh"]

# ENTRYPOINT 不能使用变量
ENTRYPOINT ["java","-Xmx256m","-Xms256m","-Xmn512m","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

# 设置对外端口为
EXPOSE ${port}