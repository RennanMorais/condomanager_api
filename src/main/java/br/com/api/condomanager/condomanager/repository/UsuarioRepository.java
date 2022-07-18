package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
	User findByCpf(String cpf);
	
}
