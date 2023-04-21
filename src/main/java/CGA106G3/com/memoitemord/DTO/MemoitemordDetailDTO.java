package CGA106G3.com.memoitemord.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MemoitemordDetailDTO {

    private Integer mino;

    private String miname;

    private Integer miprice;
    @Column(name = "micate_no")
    private Integer micateno;

    private Integer miqty;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date midate;

    private Integer ordno;
    @Id
    private Integer membno;
    @Column(name = "totlal_pr")
    private Integer totalPr;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orddate;

    private Integer ordsta;
    @Column(name = "pay_sta")
    private Integer paysta;

    private String rec;
    @Column(name = "rec_addr")
    private String recaddr;

}
