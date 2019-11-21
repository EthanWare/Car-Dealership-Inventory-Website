# Start with ubuntu version 18.04, with postgres and maven
# FROM ubuntu:18.04
# FROM alpine:3.10
# FROM postgres:12-alpine
FROM maven:3.6-ibmjava-8-alpine
# FROM maven:3.6-jdk-8
# FROM node:12-alpine

# check for updates
RUN apk update

# load admin files
WORKDIR /Authentication
COPY pom.xml /Authentication/pom.xml
COPY target /Authentication/target
COPY src /Authentication/src

# build jar
# RUN mvn clean install

# start Authentication api
# ENTRYPOINT java -jar target/admin-1.jar
ENTRYPOINT mvn spring-boot:run

# allow other microaservices to talk to each other
# CMD listen_addresses = '*'

# expose angular port
# EXPOSE 8082

# CMD while true; do sleep 10000; done