package ms.com.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    LerConteudoCelula leitor = new LerConteudoCelula();
    public void enviarEmail(String destinatario, String assunto, String corpo, String caminhoAnexo) {
        MimeMessage mensagem = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);
            helper.setFrom("viniciustoldojaeger@outlook.com");
            helper.setTo(destinatario);
            helper.setSubject(assunto);

            String conteudoTabela = leitor.lerConteudoTabela("C:\\Users\\Pichau\\Desktop\\teste.xlsx");

            String corpoComTabela = corpo + "\n\nConteúdo da Tabela:\n\n" + conteudoTabela;
            helper.setText(corpoComTabela);

            javaMailSender.send(mensagem);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar o e-mail: " + e.getMessage());
        }
    }
}



