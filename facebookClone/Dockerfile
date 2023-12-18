FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/facebookClone.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8091
