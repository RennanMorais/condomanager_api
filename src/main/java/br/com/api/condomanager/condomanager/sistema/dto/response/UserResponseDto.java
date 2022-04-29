package br.com.api.condomanager.condomanager.sistema.dto.response;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

	private String nome;
	
	private String email;
	
	private BigInteger nivelAcesso;
}
