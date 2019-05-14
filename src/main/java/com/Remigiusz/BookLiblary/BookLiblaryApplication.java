package com.Remigiusz.BookLiblary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.io.IOException;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BookLiblaryApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(BookLiblaryApplication.class, args);



	}

}

