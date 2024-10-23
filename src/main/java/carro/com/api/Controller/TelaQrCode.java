package carro.com.api.Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import carro.com.api.DTO.CarroResumoDTO;
import carro.com.api.Model.Usuario;
import carro.com.api.Security.SecurityConfiguration;
import carro.com.api.Service.CarroService;
import io.nayuki.qrcodegen.QrCode;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = SecurityConfiguration.SECURITY)
@RequestMapping("/QrCode")
@RestController
public class TelaQrCode {

    @Autowired
    private CarroService carroService;

    @GetMapping("/CarroQrCode/{id}")
    public ResponseEntity<byte[]> generateQrCode(@PathVariable Integer id) throws IOException {
        CarroResumoDTO carroResumo = carroService.resumoID(id);
        String json = String.format(
            "{\"nome\": \"%s\", \"preco\": %.2f, \"ano\": %d, \"marca\": \"%s\"}",
            carroResumo.nome(), carroResumo.preco(), carroResumo.ano(), carroResumo.marca()
        );

        QrCode qrCode = QrCode.encodeText(json, QrCode.Ecc.MEDIUM);
        
        int qrSize = qrCode.size;
        int scale = 10;

        BufferedImage qrImage = new BufferedImage(qrSize * scale, qrSize * scale, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < qrSize; x++) {
            for (int y = 0; y < qrSize; y++) {
                Color color = qrCode.getModule(x, y) ? Color.BLACK : Color.WHITE;
                Graphics2D g = qrImage.createGraphics();
                g.setColor(color);
                g.fillRect(x * scale, y * scale, scale, scale);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
    
    @GetMapping("/QrCodeUsuario")
    public ResponseEntity<byte[]> generateUserQrCode() throws IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        String nome = ((Usuario) userDetails).getNome();
        
        String json = String.format("{\"Nome\": \"%s\", \"Email\": \"%s\"}", 
        		nome, email
        );

        QrCode qrCode = QrCode.encodeText(json, QrCode.Ecc.MEDIUM);
        
        int qrSize = qrCode.size;
        int scale = 10;

        BufferedImage qrImage = new BufferedImage(qrSize * scale, qrSize * scale, BufferedImage.TYPE_INT_RGB);
        
        for (int x = 0; x < qrSize; x++) {
            for (int y = 0; y < qrSize; y++) {
                Color color = qrCode.getModule(x, y) ? Color.BLACK : Color.WHITE;
                Graphics2D g = qrImage.createGraphics();
                g.setColor(color);
                g.fillRect(x * scale, y * scale, scale, scale);
            }
        }

        // escreve a imagem do qrcode
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();

        // Configur os headers da reposta
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.IMAGE_PNG);

        // Retornar a figura do qrcode como ResponseEntity
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
