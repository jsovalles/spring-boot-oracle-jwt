FROM maven:3.6.3-jdk-11

# image layer
WORKDIR /build
ADD pom.xml /build 
RUN mvn verify clean --fail-never