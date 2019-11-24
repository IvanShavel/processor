FROM node as builder

RUN git clone https://github.com/IvanShavel/processor.git /opt/proc
WORKDIR /opt/proc
RUN npm install

# Alpine Linux with OpenJDK JRE
FROM openjdk:8-jre-alpine
# copy WAR into image
COPY --from=builder /opt/proc/target/processor.jar /app.jar
# run application with this command line
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.jar"]