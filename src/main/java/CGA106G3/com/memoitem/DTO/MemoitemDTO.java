package CGA106G3.com.memoitem.DTO;

import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import CGA106G3.com.memoitempic.Entity.Memoitempic;
import lombok.Data;

import java.sql.Blob;
import java.util.List;

@Data
public class MemoitemDTO {
    private Integer mino;
    private String miname;
    private Integer mista;
    private Integer miprice;
    private Integer micateno;
    private Memoitempic memoitempic;

    private Memoitemcate memoitemcate;
}
