package CGA106G3.com.ceremony.DTO;

import lombok.Data;


@Data
public class RelToCereDto {
    private Integer cerNo;

    private String cerName;

    public RelToCereDto(Integer cerNo, String cerName){
        this.cerNo = cerNo;
        this.cerName = cerName;
    }
}
