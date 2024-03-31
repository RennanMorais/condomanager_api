package br.com.api.condomanager.condomanager.sistema.condominios.apartamentos;

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

import br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain.ApartamentoRequestDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain.ApartamentoResponseDTO;
import br.com.api.condomanager.condomanager.sistema.condominios.apartamentos.domain.DisponibilidadeAluguelDTO;
import br.com.api.condomanager.condomanager.sistema.dto.projection.ApartamentoProjection;

@RestController
@RequestMapping("/condomanager/sistema")
public class ApartamentoResource {

    @Autowired
    ApartamentoService service;

    @PostMapping("/apartamento/cadastrar")
    public ResponseEntity<ApartamentoResponseDTO> cadastrarApartamento(@Valid @RequestBody ApartamentoRequestDTO request) {
        return ResponseEntity.ok(this.service.cadastrarApartamento(request));
    }

    @GetMapping("/apartamento/listar/{id}")
    public ResponseEntity<List<ApartamentoProjection>> listarApartamentos(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.listarApartamentos(id));
    }

    @GetMapping("/apartamento/{id}")
    public ResponseEntity<ApartamentoProjection> buscarApartamentoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.buscarAptoPorId(id));
    }

    @PutMapping("/apartamento/editar/{id}")
    public ResponseEntity<ApartamentoResponseDTO> editarApartamento(@PathVariable Long id, @RequestBody ApartamentoRequestDTO request) {
        return ResponseEntity.ok(this.service.editarApartamento(id, request));
    }

    @DeleteMapping("/apartamento/deletar/{id}")
    public ResponseEntity<ApartamentoResponseDTO> deletarApartamento(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.deletarApartamento(id));
    }
    
    @PostMapping("/apartamento/aluguel")
    public ResponseEntity<ApartamentoResponseDTO> alterarDisponibilidadeAluguel(@Valid @RequestBody DisponibilidadeAluguelDTO request) {
        return ResponseEntity.ok(this.service.alterarDisponibilidadeAluguel(request));
    }

}
