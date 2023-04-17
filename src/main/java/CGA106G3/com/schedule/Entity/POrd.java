package CGA106G3.com.schedule.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "plan_ord")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class POrd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pono;
    @Column
    private Integer membno;
    @Column
    private Integer empno;
    @Column(name = "TOTAL_PR")
    private Integer tPrice;
    @Column(name = "PODATE")
    private Date poDate;
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
