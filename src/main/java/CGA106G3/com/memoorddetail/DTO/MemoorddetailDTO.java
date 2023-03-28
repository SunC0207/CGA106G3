package CGA106G3.com.memoorddetail.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class MemoorddetailDTO {
    private Integer ordno;
    private Integer mino;
    private Integer miqty;
    private Date midate;
    private Integer miprice;

}
