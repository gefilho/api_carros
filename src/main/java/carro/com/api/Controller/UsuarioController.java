package carro.com.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.Model.Usuario;
import carro.com.api.Service.MockEmailService;
import carro.com.api.Service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService acao;

    @Autowired
    private MockEmailService email;

    @PostMapping("/criar")
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario Usuario) {
        Usuario newUsuario = acao.criar(Usuario);

        email.enviarEmail(
                Usuario.getEmail(),
                "Cadastro Realizado",
                "Olá " + Usuario.getNome() + ", seu cadastro foi realizado com sucesso!"
        );
        return ResponseEntity.ok(newUsuario);
    }

    @GetMapping("/listar")
    public List<Usuario> listar() {
        return (List<Usuario>) acao.findAll();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario UsuarioDetalhe) {
        Usuario updatedUsuario = acao.atualizar(id, UsuarioDetalhe);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        acao.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
