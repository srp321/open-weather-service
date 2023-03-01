FROM maven:3.6-jdk-8 AS build
WORKDIR /open-weather-service
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline
COPY src ./src
RUN mvn -B -DskipTests clean package

FROM openjdk:8-jdk-alpine
MAINTAINER srpbit@gmail.com
COPY --from=build /open-weather-service/target/open-weather-service-0.0.1-SNAPSHOT.jar /open-weather-service/open-weather-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=uat", "/open-weather-service/open-weather-service-0.0.1-SNAPSHOT.jar"]
