package br.com.api.condomanager.condomanager.sistema.condominios.ocorrencias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.FinalizarOcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.OcorrenciaResponseDTO;

@RestController
@RequestMapping("/condomanager/sistema")
public class OcorrenciaResource {

	@Autowired
	OcorrenciaService service;
	
	@PostMapping("/ocorrencia/registrar")
	public OcorrenciaResponseDTO registrarOcorrencia(@RequestBody OcorrenciaRequestDTO request) {
		return this.service.registrarOcorrencia(request);
	}
	
	@PostMapping("/ocorrencia/atender/{id}")
	public OcorrenciaResponseDTO registrarOcorrencia(@PathVariable Long codigo) {
		return this.service.atenderOcorrencia(codigo);
	}
	
	@PostMapping("/ocorrencia/finalizar/{id}")
	public OcorrenciaResponseDTO finalizarOcorrencia(@PathVariable Long codigo, @RequestBody FinalizarOcorrenciaRequestDTO request) {
		return this.service.finalizarOcorrencia(codigo, request);
	}
	
}
