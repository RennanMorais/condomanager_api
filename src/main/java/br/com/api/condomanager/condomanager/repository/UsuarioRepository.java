package br.com.api.condomanager.condomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.UserEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Long> {

	boolean existsByEmail(String email);
	
	boolean existsByCpf(String cpf);
	
	boolean existsByCodigo(String codigo);
	
	UserEntity findByCodigo(String codigo);

	UserEntity findByEmail(String email);
	
	UserEntity findByCpf(String cpf);

}
