FROM maven:latest AS build

RUN apt-get update && apt-get install -y curl \
    && curl -fsSL https://deb.nodesource.com/setup_18.x | bash - \
    && apt-get install -y nodejs \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
COPY frontend/styles/ ./frontend/styles
COPY frontend/themes/ ./frontend/themes
RUN mvn clean package -Pproduction -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/webapp-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
