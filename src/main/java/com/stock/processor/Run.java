package com.stock.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.datastore.repository.config.EnableDatastoreRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDatastoreRepositories
@EnableAsync
public class Run {
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}
