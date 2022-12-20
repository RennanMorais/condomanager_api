package br.com.api.condomanager.condomanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.condomanager.condomanager.model.UserEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String usuario);

	Boolean existsByUsername(String usuario);

	Boolean existsByEmail(String email);

}
