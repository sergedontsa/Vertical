FROM openjdk:8
ADD /build/libs/Vertical-0.0.1-SNAPSHOT.jar  Vertical-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "Vertical-0.0.1-SNAPSHOT.jar"]