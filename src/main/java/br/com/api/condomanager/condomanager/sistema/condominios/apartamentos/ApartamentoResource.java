package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.dto.ApartamentoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class ApartamentoResource {

    @Autowired
    ApartamentoService service;

    @PostMapping("/apartamento/cadastrar")
    public ResponseEntity<ApartamentoResponseDTO> cadastrarApartamento(@RequestBody ApartamentoRequestDTO request) {
        return ResponseEntity.ok(this.service.cadastrarApartamento(request));
    }

    @GetMapping("/apartamento/listar/{id}")
    public ResponseEntity<List<ApartamentoProjection>> listarApartamentos(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.listarApartamentos(id));
    }

}
