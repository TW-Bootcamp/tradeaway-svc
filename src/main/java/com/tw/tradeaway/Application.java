package com.tw.tradeaway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by prateeks on 1/13/17.
 */

@SpringBootApplication(scanBasePackages = "com.tw.tradeaway")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
