FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./target/emart-identity-service-0.0.1-SNAPSHOT.jar ./

EXPOSE 9011

ENTRYPOINT ["java","-jar", "/app/emart-identity-service-0.0.1-SNAPSHOT.jar"]

