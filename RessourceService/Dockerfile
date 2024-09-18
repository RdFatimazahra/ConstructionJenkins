FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/RessourceService-0.0.1-SNAPSHOT.jar ressource.jar
ENTRYPOINT ["java","-jar","/ressource.jar"]