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
import carro.com.api.Security.SecurityConfiguration;
import carro.com.api.Security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Autenticação", description = "Seção de Autenticação")
public class AuthenticationController {
    
    @Autowired
    private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
    
	@PostMapping("/login")
	@Operation(summary = "Login de Usuário", description = "Método para autenticar o usuário no sistema")
	@ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso!")
	@ApiResponse(responseCode = "400", description = "Credenciais incorretas fornecidas pelo usuário!")
	@ApiResponse(responseCode = "401", description = "Usuário não autorizado!")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
	    var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
	    var auth = this.authenticationManager.authenticate(emailSenha);
	    
	    var token = tokenService.generateToken((Usuario) auth.getPrincipal());
	    
	    return ResponseEntity.ok(token);
	}
}
