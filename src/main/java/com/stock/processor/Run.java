package com.stock.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
//@EnableDatastoreRepositories
public class Run {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Run.class, args);

    }

/*
   datasource:
    url: jdbc:postgresql://35.235.45.205:5432/postgres
    username: postgres
    password: stockdb
    socketFactory: com.google.cloud.sql.postgres.SocketFactory
    cloudSqlInstance: true-server-259316:europe-west3:stockdb
  datasource:
    url: jdbc:postgresql://google/postgres
    username: postgres
    password: stockdb
    socketFactory: com.google.cloud.sql.postgres.SocketFactory
    cloudSqlInstance: true-server-259316:europe-west3:stockdb
      datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: root

    */


}
