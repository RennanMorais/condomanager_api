package br.com.api.condomanager.condomanager.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.api.condomanager.condomanager.autenticacao.dto.request.LoginRequestDto;
import br.com.api.condomanager.condomanager.autenticacao.dto.response.LoginResponseDto;
import br.com.api.condomanager.condomanager.autenticacao.security.jwt.JwtUtils;
import br.com.api.condomanager.condomanager.repository.UsuarioRepository;

@Service
public class AutenticacaoService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	public LoginResponseDto autenticar(LoginRequestDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		LoginResponseDto response = new LoginResponseDto();
		response.setToken(jwt);
		
		return response;
	}

}
