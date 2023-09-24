package com.test.sea.lucas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LucasApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucasApplication.class, args);
	}

}
