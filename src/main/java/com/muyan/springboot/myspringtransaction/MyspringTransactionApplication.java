package com.muyan.springboot.myspringtransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MyspringTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run( MyspringTransactionApplication.class, args );
    }

}
