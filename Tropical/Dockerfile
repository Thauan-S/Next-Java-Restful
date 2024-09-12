FROM eclipse-temurin:17-jdk-alpine
COPY . . 
RUN ./mvnw clean install -DskipTests
ENTRYPOINT ["java","-jar","target/Tropical-0.0.1-SNAPSHOT.jar"]
