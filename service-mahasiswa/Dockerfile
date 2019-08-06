FROM adoptopenjdk/openjdk8:latest
ADD ./target/service-mahasiswa-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-mahasiswa-0.0.1-SNAPSHOT.jar"]
EXPOSE 8184