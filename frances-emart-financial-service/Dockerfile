FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./target/emart-financial-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9015

ENTRYPOINT ["java","-jar", "/app/emart-financial-service-0.0.1-SNAPSHOT.jar"]

