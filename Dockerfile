FROM openjdk:jre-13.0.2_8-alpine
VOLUME /tmp
RUN mkdir /opt/stock
WORKDIR /opt/stock
COPY target/processor.jar processor.jar
EXPOSE 8080/tcp
ENTRYPOINT java -jar /opt/stock/processor.jar
