package ms.com.email.controllers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import ms.com.email.dtos.EmailDto;
import ms.com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/enviar")
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;

    @PostMapping
    public String enviar(@RequestParam("caminhoArquivo") String caminhoArquivo) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("jaegerzinho@gmail.com");
        helper.setSubject("dsadfdsfsdas");
        helper.setText("'fdsfsdf");

        FileSystemResource arquivo = new FileSystemResource(caminhoArquivo);

        helper.addAttachment(Objects.requireNonNull(arquivo.getFilename()), arquivo);

        javaMailSender.send(message);

        return "ok";
    }
}