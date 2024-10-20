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
import carro.com.api.Security.SecurityConfiguration;
import carro.com.api.Service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carro")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@Tag(name = "Carro", description = "Seção dos carros")
public class CarroController {

    @Autowired
    private CarroService acao;

    @GetMapping("/listar")
    @Operation(summary = "Listar Carros", description = "Método para listar todos os carros cadastrados")
    @ApiResponse(responseCode = "200", description = "Carros listados com sucesso!")
    @ApiResponse(responseCode = "400", description = "Ocorreu um erro por parte do usuário!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public List<Carro> listar() {
        return acao.findAll();
    }

    @PostMapping("/criar")
    @Operation(summary = "Registrar Carro", description = "Método para registrar um novo carro no sistema")
    @ApiResponse(responseCode = "201", description = "Carro registrado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Erro de validação ou dados incorretos fornecidos!")
    @ApiResponse(responseCode = "409", description = "Carro já existe!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Carro> criar(@Valid @RequestBody Carro carro) {
        Carro novoCarro = acao.criar(carro);
        return ResponseEntity.ok(novoCarro);
    }

    @PutMapping("/editar/{id}")
    @Operation(summary = "Atualizar Carro", description = "Método para atualizar informações de um carro existente")
    @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Erro de validação ou dados incorretos fornecidos!")
    @ApiResponse(responseCode = "404", description = "Carro não encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Carro> editar(@PathVariable Integer id, @Valid @RequestBody Carro carroDetalhe) {
        Carro carroAtualizado = acao.atualizar(id, carroDetalhe);
        return ResponseEntity.ok(carroAtualizado);
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Carro", description = "Método para deletar um carro do sistema")
    @ApiResponse(responseCode = "200", description = "Carro deletado com sucesso!")
    @ApiResponse(responseCode = "400", description = "ID fornecido inválido!")
    @ApiResponse(responseCode = "404", description = "Carro não encontrado!")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        acao.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
