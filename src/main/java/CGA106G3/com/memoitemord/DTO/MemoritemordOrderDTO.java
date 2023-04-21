package CGA106G3.com.memoitemord.DTO;
//會員訂單號碼查詢

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
public class MemoritemordOrderDTO {
    private Integer mino;

    private String miname;

    private Integer miprice;
//    @Column(name = "micate_no")
    private Integer micateno;

    private Integer miqty;

//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date midate;

    private Integer ordno;
//    @Id
    private Integer membno;
//    @Column(name = "totlal_pr")
    private Integer totalPr;

//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orddate;

    private Integer ordsta;
//    @Column(name = "pay_sta")
    private Integer paysta;

    private String rec;
//    @Column(name = "rec_addr")
    private String recaddr;

//    public MemoritemordOrderDTO(Integer MINO, String MINAME, Integer MIPRICE, Integer MICATE_NO, Integer MIQTY, Date MIDATE, Integer ORDNO, Integer membno, Integer TOTLAL_PR, Date ORDDATE, Integer ORDSTA, Integer PAY_STA, String REC, String REC_ADDR) {
//        this.mino = MINO;
//        this.miname = MINAME;
//        this.miprice = MIPRICE;
//        this.micateno = MICATE_NO;
//        this.miqty = MIQTY;
//        this.midate = MIDATE;
//        this.ordno = ORDNO;
//        this.membno = membno;
//        this.totalPr = TOTLAL_PR;
//        this.orddate = ORDDATE;
//        this.ordsta = ORDSTA;
//        this.paysta = PAY_STA;
//        this.rec = REC;
//        this.recaddr = REC_ADDR;
//    }

    public MemoritemordOrderDTO() {

    }
//    public MemoritemordOrderDTO(){
//
//    }
}
