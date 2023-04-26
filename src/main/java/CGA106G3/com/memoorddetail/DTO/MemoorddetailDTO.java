package CGA106G3.com.memoorddetail.DTO;

import CGA106G3.com.memoitem.Entity.Memoitem;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemoorddetailDTO {
    private Integer ordno;
    private Integer mino;
    private Integer miqty;
    private LocalDateTime midate;
    private Integer miprice;
    private Memoitemord memoitemord;
    private Memoitem memoitem;
}
