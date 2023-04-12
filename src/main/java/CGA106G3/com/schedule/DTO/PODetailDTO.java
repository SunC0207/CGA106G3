package CGA106G3.com.schedule.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class PODetailDTO {
    private Integer detailno;

    private Integer pono;

    private Integer itemno;

    private Integer locno;

    private Integer iprice;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Date date;

}
