package br.com.api.condomanager.condomanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CondomanagerApplicationTests {
	
	@Test
	void contextLoads() {
		
		try {
			CondomanagerApplication.main(new String[] {});
		} catch (Exception e) {
			Assertions.fail(e);
		}
		
	}
 
}
