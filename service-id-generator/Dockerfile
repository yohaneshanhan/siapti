FROM adoptopenjdk/openjdk8:latest
ADD ./target/service-id-generator-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-id-generator-0.0.1-SNAPSHOT.jar"]
EXPOSE 8189