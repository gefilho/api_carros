package carro.com.api.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.DTO.RegisterDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Repositorio.UsuarioRepository;
import carro.com.api.Service.MockEmailService;
import carro.com.api.Service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
    @Autowired
    private UsuarioRepository repositorio;

    @Autowired
    private UsuarioService acao;

    @Autowired
    private MockEmailService email;
    
	@PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody @Valid RegisterDTO data) {
    	if(this.repositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
    	String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    	Usuario novoUsuario = new Usuario(data.nome(), data.email(), encryptedPassword, data.permissao());
    	
    	this.repositorio.save(novoUsuario);
    	
        email.enviarEmail(
                data.email(),
                "Cadastro Realizado",
                "Olá " + data.nome() + ", seu cadastro foi realizado com sucesso!"
        );
    	
    	return ResponseEntity.ok().build();
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
