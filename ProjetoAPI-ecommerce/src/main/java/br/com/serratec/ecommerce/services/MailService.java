package br.com.serratec.ecommerce.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.domains.MensagemEmail;

@Service
public class MailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(MensagemEmail mensagemEmail){
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {
            helper.setFrom(mensagemEmail.getRemetente());
            helper.setSubject(mensagemEmail.getAssunto());
            helper.setText(mensagemEmail.getMensagem(), true);

            helper.setTo(mensagemEmail.getDestinatario()
                    .toArray(new String[mensagemEmail.getDestinatario().size()]));

            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
