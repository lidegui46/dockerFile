#!/bin/bash

# 运行环境
appDirectory="/opt/app"
sourceDirectory="${appDirectory}/source"
runtimeDirectory="${appDirectory}/runtime"
backupDirectory="${appDirectory}/backup"

# 项目变量
appName_dockerFile="dockerFile"
gitUrl="https://github.com/lidegui46/${appName_dockerFile}.git"

# 当前目录 及 子目录 增加操作权限
# sudo chmod -R 777 "${appDirectory}"

# 创建源代码目录
if [ ! -d "${sourceDirectory}" ] ; then
	mkdir -p "${sourceDirectory}"
fi
# 创建备份目录
if [ ! -d "${backupDirectory}" ] ; then
        mkdir -p "${backupDirectory}"
fi

# 创建运行时目录
if [ ! -d "${runtimeDirectory}" ] ; then
        mkdir -p "${runtimeDirectory}"
fi

# 访问源代码目录
cd "${sourceDirectory}"

# 备份 源代码 到指定目录
rm -rf "${backupDirectory}/source.tar"
tar -zcvPf "${backupDirectory}/source.tar" "${backupDirectory}"

# 删除 源代码 文件夹
rm -rf "${sourceDirectory}/${appName_dockerFile}"

# 拉取源代码
echo -e "pull code start......................"
git clone "${gitUrl}"
echo -e "pull code  done......................"

# 工程打包(需进入 POM 对应的目录)
cd "${sourceDirectory}/${appName_dockerFile}"

echo -e "maven package starting................"
mvn clean package -Dmaven.test.skip=true
echo -e "maven package done...................."

# 删除原运行文件
rm -rf "${runtimeDirectory}/app_runtime.jar"
rm -rf "${runtimeDirectory}/Dockerfile"
cp "${sourceDirectory}/${appName_dockerFile}/target/code_target.jar" "${runtimeDirectory}/app_runtime.jar"
cp -rf "${sourceDirectory}/${appName_dockerFile}/document/Dockerfile" "${runtimeDirectory}/Dockerfile"

# 访问运行目录 
cd "${runtimeDirectory}"

# 删除原镜像
docker rmi app_image &> /dev/null
echo -e "rmi old image done...................."

# 构建新镜像
echo -e "build new image starting.............."
docker build -t app_image .
echo -e "build new image done.................."

# 删除原容器
docker rm -f app_container

# 启动容器
docker run -d -p 8090:8090 --name app_container app_image
echo -e "deploy success............................."
