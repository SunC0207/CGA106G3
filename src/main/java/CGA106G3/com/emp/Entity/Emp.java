package CGA106G3.com.emp.Entity;

import CGA106G3.Core.Entity.EntityCore;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Emp extends EntityCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empno;
    @Column(nullable = false,length = 10)
    private String ename;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date hiredate;
    @Column(name = "emp_sta",length = 1,nullable = false)
    private Integer empsta;
    @Column(name = "emp_pw",length = 20,nullable = false)
    private String emppw;

}
