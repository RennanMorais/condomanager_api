package br.com.api.condomanager.condomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CondomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondomanagerApplication.class, args);
	}
}
