#!/bin/bash


mvn clean install -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
cp ./target/sspaas-cloud-mng.war .

docker login -u guohaiying -p guohaiying -e 1275656991@qq.com sspaas.net
docker build -t sspaas.net/guohaiying/sspaas_cloud_mng:v1.1 .
docker push sspaas.net/guohaiying/sspaas_cloud_mng:v1.1
