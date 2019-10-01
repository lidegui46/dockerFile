一、部署应用

	1、下载 JDK 镜像
		【linux】docker pull adoptopenjdk/openjdk8
	2、从本地拷贝”start_app_container_8090.sh“ 和 ”start_app_container_8091.sh“ 到 linux 任意位置； 
		【linux】cd /opt
		【linux】sudo mkdir app
		【linux】cd /opt/app
		【linux】sudo rz -be
		【本地】选择”start_app_container_8090.sh“ 文件进行上传 
		【linux】sudo rz -be
		【本地】选择”start_app_container_8091.sh“ 文件进行上传
		【linux】sudo chmod +x start_app_container_8090.sh
		【linux】sudo chmod +x start_app_container_8091.sh
		【linux】./start_app_container_8090.sh
		【linux】./start_app_container_8091.sh

二、安装 haproxy 和 keepalived 服务（环境：2台 haproxy）
	
	1、下载 haproxy 镜像
		【linux】docker search haproxy
		【linux】docker pull haproxy

	2、上传文件 [从本地拷贝 “haproxy0102.sh”、“haproxy01.cfg”、“haproxy02.cfg”、“keepalived_backup.cfg”、“keepalived_master.cfg” 到 linux ”/opt/haproxy/conf/“目录 ]
		【linux】cd /opt
		【linux】sudo mkdir haproxy
		【linux】cd /opt/haproxy
		【linux】sudo mkdir conf
		【linux】cd /opt/haproxy/conf
		【linux】sudo rz -be
		【本地】选择”haproxy0102.sh“ 文件进行上传	
		【linux】sudo rz -be
		【本地】选择”haproxy01.cfg“ 文件进行上传 
		【linux】sudo rz -be
		【本地】选择”haproxy02.cfg“ 文件进行上传
		【linux】sudo rz -be
		【本地】选择”keepalived_backup.cfg“ 文件进行上传
		【linux】sudo rz -be
		【本地】选择”keepalived_master.cfg“ 文件进行上传

	3、创建 haproxy01、haproxy02 容器
		【linux】cd /opt/haproxy/conf
		【linux】sudo chmod +x haproxy0102.sh
		【linux】./haproxy0102.sh
	
三、浏览器访问

	应用服务：
		app_container_8090： 
			linux 内 docker 虚拟地址：	http://172.17.0.5:8090/test/get?arg=10
				172.17.0.5：	docker容器"app_container_8090"在 linux 的虚拟地址；
				8090：			应用在docker 容器内的运行端口(Dockerfile_7035 在start_app_container_8090.sh 执行后通过“/opt/app/runtime”查找端口)
				
			linux 服务器地址：			http://192.168.137.10:8090/test/get?arg=10
				192.168.137.10：linux 地址；
				8090：			linux对外暴露的应用地址（在“start_app_container_8090.sh”的 docker run 可查看映射地址）
			
		app_container_8091： 
			linux 内 docker 虚拟地址：	http://172.17.0.4:8091/test/get?arg=10			【同上】
			linux 服务器地址：			http://192.168.137.10:8091/test/get?arg=10		【同上】
	负载均衡：
		haproxy01：
			监控：
				地址：
					linux 内 docker 虚拟地址：	http://172.17.0.6:8889/dbs
						172.17.0.6:		docker容器"haproxy01"在 linux 的虚拟地址；
						8889：			应用在docker 容器内的运行端口(可通过 haproxy01.cfg 文件查看监控端口)
						dbs：			haproxy01.cfg 文件中配置的监控访问路径
						
					linux 服务器地址：			http://192.168.137.10:8889/dbs
						192.168.137.10:	linux 服务器对外地址；
						8889：			容器内启动的监控端口 与 linux 服务器对外暴露的端口映射（可通过 “haproxy0102.sh”的 docker run 查看）
				用户名：admin   
				密码： 	admin
				
		haproxy02： 
			监控：
				地址：	
					linux 内 docker 虚拟地址：	http://172.17.0.7:8888/dbs
						172.17.0.7:		docker容器 “haproxy02”在 linux 的虚拟地址；
						8888：			应用在docker 容器内的运行端口(可通过 haproxy01.cfg 文件查看监控端口)
						dbs：			haproxy01.cfg 文件中配置的监控访问路径
						
					linux 服务器地址：			http://192.168.137.10:8888/dbs
						192.168.137.10:	linux 服务器对外地址；
						8888：			容器内启动的监控端口 与 linux 服务器对外暴露的端口映射（可通过 “haproxy0102.sh”的 docker run 查看）
				用户名：admin
				密码：	admin
				
	高可用:（未配置成功）
		keepalived_vip:
		
		keepalived_master:
		
		keepalived_backup:

四、命令

	进入指定docker 容器
		【linux】docker exec -it haproxy01 /bin/bash
		
	查看 docker 在 linux 的虚拟 ip
		查看 docker 有哪些网络
		【linux】docker network ls
		查看指定网络内，docker 容器在 linux 内的虚拟IP
		【linux】docker network inspect bridge
		
	无权限操作时，记得添加 sudo 
		【linux】 sudo
		
	文件增加权限 
		【linux】sudo chmod +x 文件
		
	上传文件到 linux 
		【linux】sudo rz -be
	
	下载 linux 文件到本地
		【linux】sz 文件
	
五、注意事项：
	
	检查以下IP、端口是否正确： haproxy监听端口、haproxy对外暴露端口、haporxy负载均衡的应用IP和端口；

	