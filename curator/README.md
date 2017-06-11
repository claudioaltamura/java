Curator
==========
Apache Curator Example using Zookeeper Docker image
https://hub.docker.com/_/zookeeper/

start Zookeeper Instances with
zookeeper-docker/3.4.10$docker-compose -d up

connect to Zookeeper
zookeeper-docker-offical/3.4.10$docker run -it --rm --link 3410_zoo3_1:zookeeper --net 3410_default zookeeper zkCli.sh -server zookeeper