#!/bin/bash
mvn clean install -DskipTests=true

project='simplex'
#version=`date +%Y%m%d%H%M%S`
version=`git tag -l`

echo ''
echo "build docker image $project:$version"

docker build -t $project:$version .

# run
docker run -p 8081:8081 --name $project -d $project:$version