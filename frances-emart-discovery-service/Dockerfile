FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./target/emart-discovery-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9999

ENTRYPOINT ["java","-jar", "/app/emart-discovery-service-0.0.1-SNAPSHOT.jar"]

