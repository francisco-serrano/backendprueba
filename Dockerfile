FROM openjdk:10
RUN mkdir -p /devops/backend-prueba
ADD target/backendprueba-0.0.1-SNAPSHOT.jar /devops/backend-prueba
EXPOSE 8080
CMD ["java", "-jar", "/devops/backend-prueba/backendprueba-0.0.1-SNAPSHOT.jar"]
