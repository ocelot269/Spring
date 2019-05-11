package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(JpaBasicApplication.class, args);
		
		Util util = ctx.getBean(Util.class);
		
		System.out.println("----- creant persona --------");
		util.alta(1L, "Aina");
		
		
	}
}
