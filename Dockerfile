FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY target/assignment-0.0.1-SNAPSHOT.jar assignment-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/assignment-0.0.1.jar"]
