package CGA106G3.com.Mail.Service;

import CGA106G3.com.Mail.DTO.ForgetDTO;
import CGA106G3.com.Mail.DTO.MailDTO;
import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Repository.MemberRepository;
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
    @Autowired
    private MemberRepository memberRepository;



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


    public boolean sendPassword(ForgetDTO email){
        try {
            Member member = memberRepository.findByEmail(email.getEmail());
            String mailcontent ="親愛的"+member.getMname()+"您好\n"+"這是您的密碼\n"+member.getMpw()+"\n別再弄丟囉";
            MailDTO mailDTO =new MailDTO(email.getEmail(),"找回密碼",mailcontent);
            sendEmail(mailDTO);
            return true;
        }catch (Exception e){
            System.out.println(e.fillInStackTrace());
            return false;
        }



    }

}
