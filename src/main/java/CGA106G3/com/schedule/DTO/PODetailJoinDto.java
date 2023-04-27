package CGA106G3.com.schedule.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;


@Data
public class PODetailJoinDto {
    private Date poDate;
    private Integer poNo;
    private String relName;
    private String dName;
    private Date dBirth;
    private Date dDate;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Timestamp date;
    private String iName;
    private Integer iPrice;
    private String locName;
    private Integer totalPr;
    private Integer paySta;
    private Integer poSta;
    private Integer detailNo;
    private Integer itemNo;
    private Integer locNo;
    private String cerName;


}
