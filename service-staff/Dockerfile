FROM adoptopenjdk/openjdk8:latest
ADD ./target/service-staff-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-staff-0.0.1-SNAPSHOT.jar"]
EXPOSE 8181