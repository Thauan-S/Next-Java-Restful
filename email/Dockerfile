FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . . 
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/email-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
