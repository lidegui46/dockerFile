docker rm -f haproxy01
docker rm -f haproxy02

# 启动容器 haproxy01
docker run -d -p 8888:8888 -p 9000:9000 -v /opt/haproxy/conf/haproxy01.cfg:/usr/local/etc/haproxy/haproxy.cfg -v /opt/haproxy/conf/keepalived_master.cfg:/opt/keepalived/keepalived.cfg --name haproxy01 --privileged haproxy_keepalived:v20191001

#docker exec -it haproxy01 /bin/bash
#sudo service keepalived start
#exit 0


# 启动容器 haproxy02
docker run -d -p 8889:8889 -p 9001:9001 -v /opt/haproxy/conf/haproxy02.cfg:/usr/local/etc/haproxy/haproxy.cfg -v /opt/haproxy/conf/keepalived_backup.cfg:/opt/keepalived/keepalived.cfg --name haproxy02 --privileged haproxy_keepalived:v20191001

#docker exec -it haproxy02 /bin/bash
#sudo service keepalived start
#exit 0

echo -e "deploy success............................."
