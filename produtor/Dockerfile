# Use JRE17 slim
FROM openjdk:17-jdk-slim

# Add the app jar
ADD target/*.jar redis-stream.jar

ENTRYPOINT java -jar redis-stream.jar