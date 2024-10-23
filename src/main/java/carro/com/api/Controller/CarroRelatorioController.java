package carro.com.api.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.Model.Carro;
import carro.com.api.Security.SecurityConfiguration;
import carro.com.api.Service.CarroRelatorioService;
import carro.com.api.Service.CarroService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatorio", description = "Seção de Relatorio")
@SecurityRequirement(name = SecurityConfiguration.SECURITY)
public class CarroRelatorioController {
	
    @Autowired
    private CarroService acao;

    private final CarroRelatorioService relatorioService = new CarroRelatorioService();

    @GetMapping("/carros/download")
    public ResponseEntity<InputStreamResource> downloadRelatorioCarros() throws IOException {
        List<Carro> carros = acao.findAll();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        relatorioService.gerarRelatorioPdf(carros, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=relatorio_carros.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(inputStream));
    }
}
