package CGA106G3.com.item.DTO;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer itemNo;
    private String iName;
    private Integer iPrice;
    private Integer proNo;
    private String proName;
    private Integer cerNo;
    private String cerName;
    private Integer relNo;
    private String relName;
    private byte[] upFile;

    public OrderDetailDto(Integer ITEMNO, String INAME, Integer IPRICE, Integer PRONO, String PRONAME
            , Integer CERNO, String CERNAME, Integer REL_NO, String REL_NAME, byte[] UpFile) {

        this.itemNo = ITEMNO;
        this.iName = INAME;
        this.iPrice = IPRICE;
        this.proNo = PRONO;
        this.proName = PRONAME;
        this.cerNo = CERNO;
        this.relNo = REL_NO;
        this.relName = REL_NAME;
        this.cerName = CERNAME;
        this.upFile = UpFile;

    }

    public OrderDetailDto() {

    }

}
