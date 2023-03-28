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
import java.sql.Timestamp;

@Entity
@Table(name = "plan_ord")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class POrd {
    @Id
    private Integer pono;
    @Column
    private Integer membno;
    @Column
    private Integer empno;
    @Column(name = "TOTAL_PR")
    private Integer tPrice;
    @Column(name = "PODATE")
    @JsonFormat(pattern = "yyyy-MM-dd kk:mm", timezone = "GMT+8")
    private Timestamp poDate;
    @Column(name = "PO_STA")
    private Integer posta;
    @Column(name = "PAY_STA")
    private Integer paysta;
    @Column(name = "PO_TYPE")
    private Integer poType;
    @Column
    private String dname;

    @Column(name = "DBIRTH")
    private Date dBirth;
    @Column(name = "DDATE")
    private Date dDate;


}
