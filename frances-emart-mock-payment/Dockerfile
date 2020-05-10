FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./target/emart-mock-payment-0.0.1-SNAPSHOT.jar ./

EXPOSE 9015

ENTRYPOINT ["java","-jar", "/app/emart-mock-payment-0.0.1-SNAPSHOT.jar"]

