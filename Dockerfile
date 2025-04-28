FROM openjdk:17
COPY target/mappedsuperclass-0.0.1-SNAPSHOT.jar .


ENTRYPOINT ["java","-jar","app.jar"]



