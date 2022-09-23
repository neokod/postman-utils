package com.neokod.postman;

import com.neokod.postman.context.PostmanProcessRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PostmanApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PostmanApplication.class, args);
		final PostmanProcessRunner processRunner = context.getBean(PostmanProcessRunner.class);

		processRunner.runProcess();
	}

}
