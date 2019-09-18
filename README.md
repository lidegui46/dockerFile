# dockerFile
local code -> git -> linux -> docker -> run

# local code 注意事项
1、application.yml 
   
   port：是指项目在容器中运行的端口，外部访问时，需要通过 docker 
   run 时把对外访问的接口与 docker 内运行的接口进行绑定，外部才能
   访问 docker 内运行的项目；

2、pom.xml

    build.finalName：构建后，jar 或 war 包的名称
    build.plugins.plugin.spring-boot-maven-plugin：使用“spring-boot-maven-plugin”重新打包
        注：
        <goal>repackage</goal>：向打包文件中添加 MainClass 和 Start-Class，以便通过“java -jar xxx.jar”运行时，找到对应的
            main入口
   
# linux 服务器注意事项
1、jdk

    下载“jdk_version_linux_64.tar.gz”到指定目录，如：/opt/jdk   
    配置 jdk 环境变量：
        a、找到并找开环境配置文件
            sudo vim /etc/profile
        b、在配置文件底部，追加 jdk 环境配置
            export JAVA_HOME=/opt/jdk/jdk1.8.0_202
            export JRE_HOME=${JAVA_HOME}/jre
            export CLASSPATH=${CLASSPATH}:${JAVA_HOME}/lib:${JRE_HOME}/lib
            export PATH=$PATH:${JAVA_HOME}/bin
        c、在当前环境中执行环境配置文件
            source /etc/profile
2、maven

    下载“apache-maven-3.3.3-bin.zip”到指定目录，如：/opt/maven
    配置 maven 环境变量：
        a、找到并找开环境配置文件   
            sudo vim /etc/profile  
        b、在配置文件底部，追加 maven 环境配置
            export M2_HOME=/opt/maven/apache-maven-3.3.3
            export CLASSPATH=$CLASSPATH:$M2_HOME/lib
            export PATH=$PATH:$M2_HOME/bin
        c、在当前环境中执行环境配置文件
            source /etc/profile
3、编辑运行脚本
    
    1、start.sh
        a、本地编辑“start.sh”文件
        b、上传"start.sh"到linux服务器的指定目录（上传工具，自行安装）
            命令：
                cd /opt/app
                sudo rz -be 
                选择文件
    2、Dockerfile
        a、本地编辑“Dockerfile”文件，并放在 maven 项目 “src/document”目录下
        b、git push -u origin master
                
4、docker
    1、安装 docker 环境

# docker 环境配置
1、 查找 “openjdk8”镜像，并 pull 到本地

    命令：
        docker search openjdk8
        docker pull 镜像名称
        
# 前期准备工作完成，运行 start.sh
    命令：
        cd /opt/app
        sudo chmod +x start.sh
        ./start.sh
# 后续
    1、运行 start.sh 后，自动从 git pull 代码到 linux 服务器“/opt/app/source”目录；
    2、通过 maven 命令 package 成 jar，并把 jar 拷贝到指定的运行目录 “/opt/app/runntion”
        注：调用 maven package 命令（必须进入“POM 对应的目录”），package 找到 pom 文件进行打包
    3、运行 Dockerfile 文件中的命令
        拷贝 jar 到镜像中，并生成新镜像
    4、对 新镜像 instance，生成 容器，并设置对外访问的端口