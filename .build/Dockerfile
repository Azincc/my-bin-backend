FROM openjdk:17-alpine
COPY target/PasteBin-0.0.1-SNAPSHOT.jar /app/
ENV PATH $PATH:/opt/openjdk-17/bin
CMD ["java", "-jar", "/app/PasteBin-0.0.1-SNAPSHOT.jar"]
