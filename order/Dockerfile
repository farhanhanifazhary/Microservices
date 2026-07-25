FROM openjdk:27-ea-17-jdk-slim-trixie
WORKDIR /app
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
COPY target/order-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]