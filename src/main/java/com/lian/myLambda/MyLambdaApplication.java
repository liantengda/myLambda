package com.lian.myLambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MyLambdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyLambdaApplication.class, args);
    }

}
