package br.com.api.condomanager.condomanager.sistema.condominios.assembleias;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.autenticacao.security.MyUserDetails;
import br.com.api.condomanager.condomanager.sistema.dto.AssembleiaRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.AssembleiaResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.AssembleiaProjection;
import br.com.api.condomanager.condomanager.util.Util;

@RequestMapping("/condomanager/sistema")
@RestController
public class AssembleiaResource {

	@Autowired
    private AssembleiaService assembleiaService;

    @Autowired
    private MyUserDetails userDetails;

    @Autowired
    private Util util;

    @PostMapping("/assembleia/agendar")
    public ResponseEntity<AssembleiaResponseDTO> agendarAssembleia(@Valid @RequestBody AssembleiaRequestDTO request) {
        return ResponseEntity.ok(this.assembleiaService.agendarAssembleia(request));
    }
    
    @GetMapping("/assembleia/listar")
    public ResponseEntity<List<AssembleiaProjection>> listarAssembleias() {
    	return ResponseEntity.ok(this.assembleiaService.buscarAssembleias());
    }

    @GetMapping("/assembleia/{id}")
    public ResponseEntity<AssembleiaProjection> getAssembleia(@PathVariable Long id) {
        util.validarAdmin(userDetails.getLoginUser().trim());
        return ResponseEntity.ok(this.assembleiaService.getAssembleia(id));
    }

    @PutMapping("/assembleia/editar/{id}")
    public ResponseEntity<AssembleiaResponseDTO> agendarAssembleia(@PathVariable Long id, @Valid @RequestBody AssembleiaRequestDTO request) {
        util.validarAdmin(userDetails.getLoginUser().trim());
        return ResponseEntity.ok(this.assembleiaService.editarAssembleia(id, request));
    }

    @DeleteMapping("/assembleia/deletar/{id}")
    public ResponseEntity<AssembleiaResponseDTO> deletarAssembleia(@PathVariable Long id) {
        util.validarAdmin(userDetails.getLoginUser().trim());
        return ResponseEntity.ok(this.assembleiaService.deletarAssembleia(id));
    }

}
