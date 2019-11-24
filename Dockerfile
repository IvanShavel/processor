FROM openjdk:8-jre-alpine
RUN \
# Update
apt-get update -y && \
# Install Java
apt-get install default-jre -y

RUN mvn clean install


# copy WAR into image
COPY --from=builder /opt/proc/target/processor.jar /app.jar
# run application with this command line
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]