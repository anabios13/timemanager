FROM openjdk:17-jdk-alpine
EXPOSE 8000
COPY target/timeTracker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]