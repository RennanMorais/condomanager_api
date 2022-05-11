package br.com.api.condomanager.condomanager.service;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.model.User;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.dto.request.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.dto.response.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	public UserResponseDto cadastrar(UserRequestDto request) throws DadosPessoaisException {
		User user = new User();
		user.setNome(request.getNome());
		
		if(validarEmailExistente(request.getEmail())) {
			user.setEmail(request.getEmail());
		}
		
		if(validarCpfExistente(request.getCpf())) {
			user.setCpf(request.getCpf());
		}
		
		user.setSenha(this.encoder.encode(request.getSenha()));
		user.setTelefone(request.getTelefone());
		user.setNivelAcesso(BigInteger.valueOf(1));
		
		usuarioRepository.save(user);
		
		return new UserResponseDto(request.getNome(), request.getEmail(), BigInteger.valueOf(1));
	}
	
	public boolean validarEmailExistente(String email) {
		
		if(usuarioRepository.findByEmail(email) != null) {
			throw new DadosPessoaisException("E-mail já cadastrado!");
		}
		
		return true;
	}
	
	public boolean validarCpfExistente(String cpf) {
		
		if(usuarioRepository.findByCpf(cpf) != null) {
			throw new DadosPessoaisException("CPF já cadastrado!");
		}
		
		return true;
	}
}
