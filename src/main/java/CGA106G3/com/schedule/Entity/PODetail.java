package CGA106G3.com.schedule.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "plan_ord_detail")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PODetail {

    @Id
    @Column(name ="DETAIL_NO")
    private Integer detailno;

    private Integer pono;

    private Integer itemno;

    private Integer locno;

    private Integer iprice;

    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Date date;
}
