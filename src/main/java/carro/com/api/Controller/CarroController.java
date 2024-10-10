package carro.com.api.Controller;

import java.util.List;

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

import carro.com.api.Model.Carro;
import carro.com.api.Service.CarroService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService acao;

    @GetMapping("/listar")
    public List<Carro> listar() {
        return acao.findAll();
    }

    @PostMapping("/criar")
    public ResponseEntity<Carro> criar(@Valid @RequestBody Carro carro) {
        Carro novoCarro = acao.criar(carro);
        return ResponseEntity.ok(novoCarro);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Carro> editar(@PathVariable Integer id, @Valid @RequestBody Carro carroDetalhe) {
        Carro carroAtualizado = acao.atualizar(id, carroDetalhe);
        return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        acao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
