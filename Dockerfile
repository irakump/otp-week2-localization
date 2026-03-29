FROM  maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="irakump"

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package

CMD ["java", "-jar", "target/org.example.CalculatorApp.jar"]
