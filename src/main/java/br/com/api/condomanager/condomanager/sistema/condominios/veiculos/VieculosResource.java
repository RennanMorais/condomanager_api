package br.com.api.condomanager.condomanager.sistema.condominios.veiculos;

import br.com.api.condomanager.condomanager.sistema.dto.VeiculoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.VeiculoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.VeiculoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/condomanager/sistema")
@RestController
public class VieculosResource {

	@Autowired
	VeiculoService veiculoService;
	
	@PostMapping("/veiculo/cadastrar")
	public VeiculoResponseDTO cadastrarVeiculo(@Valid @RequestBody VeiculoRequestDTO request) {
		return this.veiculoService.cadastrarVeiculo(request);
	}

	@GetMapping("/veiculo/listar")
	public List<VeiculoProjection> listarVeiculos() {
		return this.veiculoService.listarVeiculos();
	}
	
	@GetMapping("/veiculo/{id}")
	public VeiculoProjection buscarVeiculo(@PathVariable Long id) {
		return this.veiculoService.buscarVeiculo(id);
	}
	
	@PutMapping("/veiculo/editar/{id}")
	public VeiculoResponseDTO edittarVeiculo(@Valid @RequestBody VeiculoRequestDTO request, @PathVariable Long id) {
		return this.veiculoService.editarVeiculo(id, request);
	}
	
	@DeleteMapping("/veiculo/deletar/{id}")
	public VeiculoResponseDTO deletarVeiculo(@PathVariable Long id) {
		return this.veiculoService.deletarVeiculo(id);
	}
	
}
