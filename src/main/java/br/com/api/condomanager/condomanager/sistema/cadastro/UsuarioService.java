package br.com.api.condomanager.condomanager.sistema.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.enums.AcessoEnum;
import br.com.api.condomanager.condomanager.model.UserEntity;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.UserRequestDto;
import br.com.api.condomanager.condomanager.sistema.cadastro.dto.UserResponseDto;
import br.com.api.condomanager.condomanager.sistema.exceptions.DadosPessoaisException;
import br.com.api.condomanager.condomanager.util.Util;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	Util utils;
	
	@Autowired
	PasswordEncoder encoder;
	
	public UserResponseDto cadastrar(UserRequestDto request) throws DadosPessoaisException {
		UserEntity user = new UserEntity();
		user.setCodigo(utils.gerarCodigo("user"));
		user.setName(request.getName());
		
		if(validarEmailExistente(request.getEmail())) {
			user.setEmail(request.getEmail());
		}
		
		if(validarCpfExistente(request.getCpf())) {
			user.setCpf(request.getCpf());
		}
		
		user.setPassword(this.encoder.encode(request.getPassword()));
		user.setPhone(request.getPhone());
		user.setIdAccess(AcessoEnum.ADMINISTRADOR.getNivel());
		user.setNomeAccess(AcessoEnum.ADMINISTRADOR.getDescricao());
		
		usuarioRepository.save(user);
		
		UserResponseDto response = new UserResponseDto();
		response.setCodigo(null);
		response.setNome(user.getName());
		response.setEmail(user.getEmail());
		response.setNivelAcesso(user.getIdAccess());
		
		return response;
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
