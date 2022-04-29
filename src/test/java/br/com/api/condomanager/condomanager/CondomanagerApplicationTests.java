package br.com.api.condomanager.condomanager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.api.condomanager.condomanager.model.User;
import br.com.api.condomanager.condomanager.service.UsuarioService;

@SpringBootTest
class CondomanagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	UsuarioService usuarioService;

}
