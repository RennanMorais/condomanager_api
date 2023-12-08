package br.com.api.condomanager.condomanager.sistema.dominios.cidades;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.projection.CidadeProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/dominio/cidades")
    public ResponseEntity<List<CidadeProjection>> listarCidades() {
        return ResponseEntity.ok(this.cidadeService.listarCidades());
    }
    
    @GetMapping("/dominio/cidades/estado/{id}")
    public ResponseEntity<List<CidadeProjection>> listarCidadePorEstado(@PathVariable Long id) {
        return ResponseEntity.ok(this.cidadeService.listarCidadesPorEstado(id));
    }

}
