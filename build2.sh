#/bin/bash
cd service2
./mvnw package
oc login -u system:admin
eval $(minishift docker-env)
docker build -t service2:v1 .
minishift ssh docker images | grep service2
cd ..
