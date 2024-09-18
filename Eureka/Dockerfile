FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/Eureka-0.0.1-SNAPSHOT.jar eureka.jar
ENTRYPOINT ["java","-jar","/eureka.jar"]