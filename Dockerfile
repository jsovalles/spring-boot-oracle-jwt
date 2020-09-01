FROM adoptopenjdk/openjdk11:latest
FROM maven-dep

# Image layer: with the application
WORKDIR /app

COPY . /app

RUN mvn clean install

WORKDIR /app/target

RUN sh -c 'touch spring-boot-oracle-jwt-proyect-1.0.0.jar'

ENTRYPOINT ["java","-jar","spring-boot-oracle-jwt-proyect-1.0.0.jar"]