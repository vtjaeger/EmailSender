package ms.com.email.controllers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import ms.com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/enviar")
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    @PostMapping
    public String enviar(@RequestParam("arquivo") MultipartFile arquivo) throws MessagingException, IOException {
        String dadosPlanilha = emailService.convertExcelToString(arquivo);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("jaegerzinho@gmail.com");
        helper.setSubject("dsadfdsfsdas");
        helper.setText(dadosPlanilha);

        javaMailSender.send(message);

        return "ok";
    }
}