package CGA106G3.com.memoitemord.DTO;


import CGA106G3.com.memoorddetail.Entity.Memoorddetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import java.sql.Date;
import java.util.List;

@Data
public class MemoitemordDTO {
    private Integer ordno;
    private Integer membno;
    private Integer totalpr;
    private LocalDateTime orddate;
    private Integer ordsta;
    private Integer paysta;
    private String rec;
    private String recaddr;
    private List<Memoorddetail> memoorddetails;




}
