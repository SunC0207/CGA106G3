package CGA106G3.com.memoitemord.DTO;


import lombok.Data;

import java.sql.Date;
@Data
public class MemoitemordDTO {
    private Integer ordno;
    private Integer membno;
    private Integer totalpr;
    private Date orddate;
    private Integer ordsta;
    private Integer paysta;
    private String rec;
    private String recaddr;
}
