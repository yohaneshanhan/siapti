FROM adoptopenjdk/openjdk8:latest
ADD ./target/service-kota-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/service-kota-0.0.1-SNAPSHOT.jar"]
EXPOSE 8194