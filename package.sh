#!/bin/bash
mvn clean install -DskipTests=true

project='simplex'
#version=`date +%Y%m%d%H%M%S`
version=$(git describe --tags `git rev-list --tags --max-count=1`)

echo ''
echo "build docker image $project:$version"

# build
docker build -t $project:$version .

# stop
docker stop $project

# run
docker run -p 8081:8081 --name $project -d $project:$version