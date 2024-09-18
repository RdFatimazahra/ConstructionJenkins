FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/GatewayService-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java","-jar","/gateway.jar"]