package br.com.api.condomanager.condomanager.sistema.condominios.assembleias;

import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.AssembleiaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.dto.projection.AssembleiaProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<AssembleiaProjection>> listarAssembleias() {
    	return ResponseEntity.ok(this.assembleiaService.buscarAssembleias());
    }

    @GetMapping("/assembleia/{id}")
    public ResponseEntity<AssembleiaProjection> getAssembleia(@PathVariable Long id) {
        return ResponseEntity.ok(this.assembleiaService.getAssembleia(id));
    }

    @PutMapping("/assembleia/editar/{id}")
    public ResponseEntity<AssembleiaResponseDTO> agendarAssembleia(@PathVariable Long id, @RequestBody AssembleiaRequestDTO request) {
        return ResponseEntity.ok(this.assembleiaService.editarAssembleia(id, request));
    }

    @DeleteMapping("/assembleia/deletar/{id}")
    public ResponseEntity<AssembleiaResponseDTO> deletarAssembleia(@PathVariable Long id) {
        return ResponseEntity.ok(this.assembleiaService.deletarAssembleia(id));
    }

}
