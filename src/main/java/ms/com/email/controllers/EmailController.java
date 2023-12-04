package ms.com.email.controllers;

import ms.com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enviar")
public class EmailController {
    @Autowired
    private EmailService emailService;
    @PostMapping
    public String enviarEmail() {
        try {
            SimpleMailMessage mensagem = new SimpleMailMessage();
            emailService.enviarEmail("viniciustjaeger@gmail.com", "Assunto", "Corpo",
                    "C:\\Users\\Pichau\\Desktop\\teste.xlsx");

            return "E-mail enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }
}
