package carro.com.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.DTO.AuthenticationDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
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
}
