package com.example.ecommercebackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class ECommerceBackendApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
    }
}
