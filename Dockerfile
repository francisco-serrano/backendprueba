FROM openjdk:10
ADD target/backend.jar backend.jar
EXPOSE 8080
CMD ["java", "-jar", "backend.jar"]