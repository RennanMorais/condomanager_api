package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.VeiculoResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class VieculosResource {

	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping("/veiculo/cadastrar")
	public VeiculoResponseDTO cadastrarVeiculo(@RequestBody VeiculoRequestDTO request) {
		return this.veiculoService.cadastrar(request);
	}
	
}
