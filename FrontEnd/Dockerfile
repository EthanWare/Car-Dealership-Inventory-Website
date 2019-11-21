# Start with ubuntu version 18.04, with postgres and maven
#FROM ubuntu:18.04
# FROM alpine:3.10
# FROM postgres:12-alpine
FROM maven:3.6-ibmjava-8-alpine
FROM node:12-alpine

# load FrontEnd files
WORKDIR /FrontEnd
COPY . /FrontEnd

# mount SQL data
# -v full path:full path

# check for updates
RUN apk update

# install angular cli
RUN npm install -g @angular/cli

# start server
ENTRYPOINT ng serve --host 0.0.0.0 --port 4200

# allow other microaservices to talk to each other
# CMD listen_addresses = '*'

# expose angular port
# EXPOSE 4200

# CMD while true; do sleep 10000; done