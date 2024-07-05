FROM openjdk:17
 
LABEL maintainer="kyawlin.klt4991@gmail.com"

COPY target/*.jar app.jar
 
ENTRYPOINT ["java","-jar","/app.jar"]