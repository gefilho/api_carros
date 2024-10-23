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

import carro.com.api.DTO.RegisterDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Security.SecurityConfiguration;
import carro.com.api.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Seção do usuario")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class UsuarioController {
	

    @Autowired
    private UsuarioService acao;

    
	@PostMapping("/registrar")
	@Operation(summary = "Registrar Usuário", description = "Método para registrar um novo usuário no sistema")
	@ApiResponse(responseCode = "201", description = "Usuário registrado com sucesso!")
	@ApiResponse(responseCode = "400", description = "Erro de validação ou dados incorretos fornecidos pelo usuário!")
	@ApiResponse(responseCode = "409", description = "Usuário já existe!")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid RegisterDTO data) {
        return acao.registrarUsuario(data);
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Usuarios", description = "Metodo fazer realizar a listagem de todos os usuarios")
    @ApiResponse(responseCode = "201", description = "Usuarios Listados com sucesso!")
    @ApiResponse(responseCode = "400", description = "Ocorreu um erro por parte do usuario!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public List<Usuario> listar() {
        return (List<Usuario>) acao.findAll();
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Editar Usuário", description = "Método para realizar a edição de um usuário existente")
    @ApiResponse(responseCode = "200", description = "Usuário editado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Ocorreu um erro por parte do usuário!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario UsuarioDetalhe) {
        ResponseEntity<?> updatedUsuario = acao.atualizar(id, UsuarioDetalhe);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Usuário", description = "Método para realizar a exclusão de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Ocorreu um erro por parte do usuário!")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Integer id) {
        acao.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
