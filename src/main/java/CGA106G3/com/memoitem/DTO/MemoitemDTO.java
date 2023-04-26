package CGA106G3.com.memoitem.DTO;

import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import CGA106G3.com.memoitempic.Entity.Memoitempic;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemoitemDTO {
    private Integer mino;
    private String miname;
    private Integer mista;
    private Integer miprice;
    private Integer micateno;
    private MultipartFile mipicno;
    private Memoitempic memoitempic;
    private Memoitemcate memoitemcate;
}
