FROM eclipse-temurin
ARG JAR_FILE=target/task_management_system_ampada-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]