FROM openjdk:8-jdk-alpine


# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 7070 available to the world outside this container
EXPOSE 7070

# The application's jar file
ARG JAR_FILE=target/employee-service-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} employee-data-service.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/employee-data-service.jar"]