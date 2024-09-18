FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/TacheService-0.0.1-SNAPSHOT.jar tache.jar
ENTRYPOINT ["java","-jar","/tache.jar"]