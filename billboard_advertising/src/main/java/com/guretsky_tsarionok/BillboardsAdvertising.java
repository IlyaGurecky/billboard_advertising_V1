package com.guretsky_tsarionok;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BillboardsAdvertising {
    public static void main(String[] args) {
        SpringApplication.run(BillboardsAdvertising.class, args);
    }
}
