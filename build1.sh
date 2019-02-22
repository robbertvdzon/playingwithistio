#/bin/bash
cd service1
./mvnw package
oc login -u system:admin
eval $(minishift docker-env)
docker build -t service1:v1 .
minishift ssh docker images | grep service1
cd ..
