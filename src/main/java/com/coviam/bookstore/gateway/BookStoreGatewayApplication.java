package com.coviam.bookstore.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BookStoreGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreGatewayApplication.class, args);
	}

}
