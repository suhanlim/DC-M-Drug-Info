FROM openjdk:17-jdk
COPY target/druginfo-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]