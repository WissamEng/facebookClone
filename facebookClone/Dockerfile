FROM openjdk:17-jdk-slim

WORKDIR /app

COPY jarPth/facebookClone-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8090
