FROM openjdk:17

WORKDIR /app

ARG JAR_FILE

COPY target/*.jar /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]