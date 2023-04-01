package CGA106G3.com.schedule.DTO;

import lombok.Data;

import java.sql.Date;

@Data
public class PODetailRangeDTO {

    private Date startDate;

    private Date EndDate;

    private Integer empno;
}
