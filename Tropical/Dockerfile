FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/*.jar
RUN mvn clean package -DskipTests
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
