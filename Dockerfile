FROM openjdk:8u191-jdk-alpine3.9
ADD target/gateway-1.0.0.jar .
EXPOSE 8081
CMD java -jar gateway-1.0.0.jar