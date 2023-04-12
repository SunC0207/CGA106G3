package CGA106G3.com.optpic.DTO;

import lombok.Data;

import java.sql.Blob;

@Data
public class OptPicDTO {
    private Integer picNo;
    private String picName;
    private Integer optNo;
    private Blob upFile;
}
