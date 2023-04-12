package CGA106G3.com.member.DTO;

import lombok.Data;

@Data
public class MemberRegisterDTO {
    private String mname;

    private String mpw;

    private byte[] mpic;

    private  Integer sex;

    private Integer mobile;

    private String email;

    private Integer versta;
}
