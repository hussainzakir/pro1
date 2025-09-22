# FROM tomcat:9.0-jdk11-openjdk
#
# # Set the working directory inside the container
# WORKDIR /usr/local/tomcat/webapps
#
# # Copy the built WAR file from the target directory
# COPY target/*.war ROOT.war
#
# # Expose port 8080
# EXPOSE 8080



# FROM maven AS build
# WORKDIR /app
# COPY . .
# RUN mvn package
#
# FROM tomcat
# COPY --from=build /app/target/file.war /usr/local/tomcat/webapps

# Stage 1: Build the application
FROM maven:3.8.6-jdk-11 AS build
WORKDIR /app
COPY . .
#RUN echo "jenkins-build=$BUILD_ID" >> csweb2.0-api/src/main/resources/configs/dev.properties
#RUN echo "jenkins-build=$BUILD_ID" >> csweb2.0-api/src/main/resources/configs/prod.properties
#RUN echo "jenkins-build=$BUILD_ID" >> csweb2.0-api/src/main/resources/configs/test.properties
RUN mvn clean install -P full-build -Dskiptests -Dbuild.number=1.0 -Dbuild.env=Dev

# Stage 2: Deploy to Tomcat
FROM tomcat:9.0
COPY --from=build /app/csweb2.0-api/target/*.war /usr/local/tomcat/webapps/ROOT.war

# # Expose port 8080
EXPOSE 8080
ARG BUILD_ID
ENV jenkins-build=${BUILD_ID}
CMD ["export AWS_CONTAINER_CREDENTIALS_RELATIVE_URI"]

# Specify the command to run on container start
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]