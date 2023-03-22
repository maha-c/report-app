# Build State, using Maven as our base image:
FROM maven:3.6.0-jdk-11-slim AS build
# Copy the src folder from host machine and move it into the container:
COPY src /home/app/src
# Copy the pom file from host machine and move it into the container:
COPY pom.xml /home/app

# Package the maven app, by specifying where the pom.xml is
RUN mvn -f /home/app/pom.xml clean package

# Once we package the Maven app, we need to execute it using Java:
FROM openjdk:11-jre-slim
# Now that we've packaged the application, it sets in the target folder as a JAR file
# We can inspect the pom.xml to deduce what the name of the jar file is (artifact-id and version)
# Copying the jar file to a new location
COPY --from=build /home/app/target/report-app-0.0.1-SNAPSHOT.jar /usr/local/lib/report-app.jar
# Expose the port from the container, so we can access from our host machine
EXPOSE 8080

# Final command, execute the jar file, using the new location that we moved it to
ENTRYPOINT ["java", "-jar", "/usr/local/lib/report-app.jar"]