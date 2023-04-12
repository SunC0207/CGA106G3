package CGA106G3.com.member.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Data
public class MemberLoginDTO {
   private String email;
   private String mpw;

    private  boolean successful;
    private  String message;
    public MemberLoginDTO(){}

    public MemberLoginDTO(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

}
