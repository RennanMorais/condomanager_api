package br.com.api.condomanager.condomanager.service;

import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
	
	public UserResponseDto cadastrar(UserRequestDto request) throws DadosPessoaisException , MethodArgumentNotValidException {
		User user = new User();
		user.setNome(request.getNome());
		
		if(validarEmailExistente(request.getEmail())) {
			user.setEmail(request.getEmail());
		}
		
		if(validarCpfExistente(request.getCpf())) {
			user.setCpf(request.getCpf());
		}
		
		user.setSenha(encoder.encode(request.getSenha()));
		user.setTelefone(request.getTelefone());
		user.setNivelAcesso(BigInteger.valueOf(1));
		
		usuarioRepository.save(user);
		
		UserResponseDto dto = new UserResponseDto(request.getNome(), request.getEmail(), BigInteger.valueOf(1));
		return dto;
	}
	
	private boolean validarEmailExistente(String email) {
		
		if(usuarioRepository.findByEmail(email) == null) {
			throw new DadosPessoaisException("E-mail já cadastrado!");
		}
		
		return true;
	}
	
	private boolean validarCpfExistente(String cpf) {
		
		if(usuarioRepository.findByCpf(cpf) == null) {
			throw new DadosPessoaisException("CPF já cadastrado!");
		}
		
		return true;
	}
}
