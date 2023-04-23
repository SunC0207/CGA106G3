package CGA106G3.com.Mail.Service;

import CGA106G3.com.Mail.DTO.MailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;



    public void sendEmail(MailDTO mailDTO){
        MimeMessage mimeMessage
                = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom("cga106g3lycoris@gmail.com");
            mimeMessageHelper.setTo(mailDTO.getReceiverMail());
            mimeMessageHelper.setSubject(mailDTO.getMailSubject());
            mimeMessageHelper.setText(mailDTO.getMailContent());

            mimeMessageHelper.addAttachment("lycris.gif", new File("src/main/resources/static/img/lycris.gif"));



            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

}
