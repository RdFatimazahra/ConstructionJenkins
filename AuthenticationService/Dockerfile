FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/AuthenticationService-0.0.1-SNAPSHOT.jar authentication.jar
ENTRYPOINT ["java","-jar","/authentication.jar"]