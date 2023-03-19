FROM openjdk
MAINTAINER baeldung.com

ARG JAR_FILE=*.jar
COPY target/${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]