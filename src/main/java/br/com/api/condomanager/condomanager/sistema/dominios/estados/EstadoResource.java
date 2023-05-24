package br.com.api.condomanager.condomanager.sistema.dominios.estados;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.projection.EstadoProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class EstadoResource {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/dominio/estados")
    public ResponseEntity<List<EstadoProjection>> listarEstados() {
        return ResponseEntity.ok(this.estadoService.listarEstados());
    }
}
