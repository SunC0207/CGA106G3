package CGA106G3.com.schedule.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDTO {
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd'T'kk:mm:ss", timezone = "GMT+8")
    private Date start;
}
