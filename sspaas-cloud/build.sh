#!/bin/bash


mvn clean install -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true
cp ./target/sspaas-cloud.war .

docker login -u guohaiying -p guohaiying -e 1275656991@qq.com sspaas.net

docker build -t sspaas.net/guohaiying/sspaas_cloud:v1.4.1 .
docker push sspaas.net/guohaiying/sspaas_cloud:v1.4.1 
