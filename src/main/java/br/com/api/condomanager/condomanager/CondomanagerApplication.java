package br.com.api.condomanager.condomanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CondomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondomanagerApplication.class, args);
	}
	
	@Bean
	public PasswordEncoder passordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
