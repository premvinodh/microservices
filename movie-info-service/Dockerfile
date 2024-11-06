# Start with the base image containing Java runtime
# For Java 8, try this
FROM openjdk:8-jdk-alpine 					

# Information about who maintains the image
MAINTAINER premvinodh.com

# Add the application's jar to the image
COPY target/movie-info-service-0.0.1-SNAPSHOT.jar movie-info-service-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "movie-info-service-0.0.1-SNAPSHOT.jar"]