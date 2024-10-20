package carro.com.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.DTO.AuthenticationDTO;
import carro.com.api.DTO.RegisterDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Repositorio.UsuarioRepository;
import carro.com.api.Security.TokenService;
import carro.com.api.Service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
    @Autowired
    private UsuarioRepository repositorio;
    @Autowired
    private UsuarioService acao;
    @Autowired
    private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
    
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
	    var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
	    var auth = this.authenticationManager.authenticate(emailSenha);
	    
	    var token = tokenService.generateToken((Usuario) auth.getPrincipal());
	    
	    return ResponseEntity.ok(token);
	}
	
	//Teste Registrar
	@PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid RegisterDTO data) {
    	if(this.repositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
    	String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
    	Usuario novoUsuario = new Usuario(data.nome(), data.email(), encryptedPassword, data.permissao());
    	
    	this.repositorio.save(novoUsuario);
    	
    	return ResponseEntity.ok().build();
    }
}
