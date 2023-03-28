package CGA106G3.com.schedule.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class POrdDTO {

    private Integer pono;

    private Integer membno;

    private Integer empno;

    private Integer tPrice;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Timestamp poDate;

    private Integer posta;

    private Integer paysta;

    private Integer poType;

    private String dname;

    private Date dBirth;

    private Date dDate;
}
