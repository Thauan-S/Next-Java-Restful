FROM eclipse-temurin:17-jdk-alpine
COPY . . 
RUN mvn clean install -DskipTests
ENTRYPOINT ["java","-jar","target/Tropical-0.0.1-SNAPSHOT.jar"]
