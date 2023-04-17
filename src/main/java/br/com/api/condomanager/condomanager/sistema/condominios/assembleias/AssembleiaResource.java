package br.com.api.condomanager.condomanager.sistema.condominios.assembleias;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaResponseDTO;

@RequestMapping("/condomanager/sistema")
@RestController
public class AssembleiaResource {

	@Autowired
    private AssembleiaService assembleiaService;

    @PostMapping("/assembleia/agendar")
    public ResponseEntity<AssembleiaResponseDTO> agendarAssembleia(@RequestBody AssembleiaRequestDTO request) {
        return ResponseEntity.ok(this.assembleiaService.agendarAssembleia(request));
    }
    
    @GetMapping("/assembleia/listar")
    public ResponseEntity<List<AssembleiaResponseDTO>> buscarAssembleias() {
    	return ResponseEntity.ok(this.assembleiaService.buscarAssembleias());
    }

}
