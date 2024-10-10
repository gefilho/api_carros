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

import carro.com.api.Model.Admin;
import carro.com.api.Service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService acao;

    @PostMapping("/criar")
    public ResponseEntity<Admin> criar(@Valid @RequestBody Admin admin) {
        Admin newAdmin = acao.criar(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @GetMapping("/listar")
    public List<Admin> listar() {
        return (List<Admin>) acao.findAll();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Admin> atualizarAdmin(@PathVariable Integer id, @Valid @RequestBody Admin adminDetalhe) {
        Admin updatedAdmin = acao.atualizar(id, adminDetalhe);
        return ResponseEntity.ok(updatedAdmin);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAdmin(@PathVariable Integer id) {
        acao.deletar(id);
        return ResponseEntity.noContent().build();
    }
    

    /*@PutMapping("/editar/{nome}")
    public ResponseEntity<Admin> atualizar(@PathVariable String nome, @Valid @RequestBody Admin adminDetalhe) {
        Admin updatedAdmin = acao.editar(nome, adminDetalhe);
        return ResponseEntity.ok(updatedAdmin);
    }*/

    /*@DeleteMapping("/deletar/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        acao.deletar(nome);
        return ResponseEntity.noContent().build();
    }*/
}
