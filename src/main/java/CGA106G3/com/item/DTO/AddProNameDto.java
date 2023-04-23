package CGA106G3.com.item.DTO;

import lombok.Data;

@Data
public class AddProNameDto {
    private Integer itemNo;
    private String iName;
    private Boolean iSta;
    private Integer proNo;
    private String proName;
    private Integer iPrice;
    private byte[] upFile;
}
