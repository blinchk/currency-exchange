FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY --from=build /app/target/*.jar /app/backend.jar

CMD ["java", "-jar", "/app/backend.jar"]