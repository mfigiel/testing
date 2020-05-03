FROM openjdk:8u191-jdk-alpine3.9
ADD target/gateway-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD java -jar gateway-0.0.1-SNAPSHOT.jar