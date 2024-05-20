FROM openjdk:17-jdk-alpine
COPY target/sml-backend-0.0.1-SNAPSHOT.jar sml-backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/sml-backend-0.0.1-SNAPSHOT.jar"]