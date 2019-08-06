FROM adoptopenjdk/openjdk8:latest
ADD ./target/service-reset-password-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-reset-password-0.0.1-SNAPSHOT.jar"]
EXPOSE 8188