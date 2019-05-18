package org.formacion;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaBasicApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(JpaBasicApplication.class, args);
	}
}
