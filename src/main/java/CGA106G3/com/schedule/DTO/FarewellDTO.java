package CGA106G3.com.schedule.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FarewellDTO {
    @Id
    private Integer pono;
    private String dname;
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Timestamp date;
    private String locname;
}
