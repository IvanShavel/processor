FROM openjdk:8u212-jre-alpine
VOLUME /tmp
RUN mkdir /opt/stock
WORKDIR /opt/stock
COPY target/processor.jar processor.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar /opt/stock/processor.jar
