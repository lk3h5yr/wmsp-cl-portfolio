FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=wmsp-cl-portfolio-0.0.1.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]