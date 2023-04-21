package CGA106G3.com.Mail.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {

    private String receiverMail;

    private String mailSubject;

    private String mailContent;




}
