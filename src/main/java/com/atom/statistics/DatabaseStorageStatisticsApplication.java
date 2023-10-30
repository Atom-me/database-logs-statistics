package com.atom.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atom.statistics.mapper")
public class DatabaseStorageStatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseStorageStatisticsApplication.class, args);
    }

}
