package CGA106G3.com.schedule.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class POrdDTO {

    private Integer pono;

    private Integer membno;

    private Integer empno;

    private Integer tPrice;
//    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Date poDate;

    private Integer posta;

    private Integer paysta;

    private Integer poType;

    private String dname;

    private Date dBirth;

    private Date dDate;
}
