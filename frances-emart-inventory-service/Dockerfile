FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./target/emart-inventory-servicee-0.0.1-SNAPSHOT.jar ./

EXPOSE 9012

ENTRYPOINT ["java","-jar", "/app/emart-inventory-service-0.0.1-SNAPSHOT.jar"]

