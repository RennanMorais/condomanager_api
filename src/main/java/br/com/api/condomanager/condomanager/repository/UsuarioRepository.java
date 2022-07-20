package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.UserEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);
	UserEntity findByCpf(String cpf);
	UserEntity findByToken(String token);
	
}
