package CGA106G3.com.memoitemcate.DTO;

import CGA106G3.com.memoitem.Entity.Memoitem;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class MemoitemcateDTO {

    private Integer micateno;
    private String micname;
    private Integer micatesta;
    private Date micatedate;
    private List<Memoitem> memoitem;

}
