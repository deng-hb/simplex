#!/bin/bash

# frontend
cd simplex-frontend
npm install

cd ..

mvn clean install -DskipTests=true

# only one
project='simplex'
#version=`date +%Y%m%d%H%M%S`
version=$(git describe --tags `git rev-list --tags --max-count=1`)

echo ''
echo "build docker image $project:$version"

# build
docker build --force-rm -t $project:$version .

# stop
docker stop $project

# rm container
docker container rm $project

# run
docker run -p 8081:8081 --name $project -d $project:$version

# in
# docker exec -it $project bash


