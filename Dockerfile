FROM openjdk:19-jdk-slim-bullseye AS builder
ADD . /app
WORKDIR /app
RUN ./mvnw clean package -DskipTests

FROM openjdk:19-jdk-slim-bullseye
COPY --from=builder /app/target/*.jar  app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
