FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/conecta-social-api-0.7.2.jar app.jar

EXPOSE 3001

ENTRYPOINT ["java", "-jar", "app.jar"]
