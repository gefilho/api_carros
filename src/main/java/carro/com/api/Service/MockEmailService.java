package carro.com.api.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MockEmailService {

    private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);

    public void enviarEmail(String para, String assunto, String corpo) {
        logger.info("enviando email: {}", para);
        logger.info("Assunto: {}", assunto);
        logger.info("Corpo: {}", corpo);
    }
}
