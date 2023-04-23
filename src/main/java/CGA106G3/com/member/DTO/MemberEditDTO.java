package CGA106G3.com.member.DTO;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class MemberEditDTO {
    private  Integer membno;

    private String mname;

    private String mpw;

    private byte[] mpic;

    private  Integer sex;

    private Integer mobile;

    private String email;

    private Integer versta;
}
