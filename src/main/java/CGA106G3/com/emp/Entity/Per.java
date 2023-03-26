package CGA106G3.com.emp.Entity;

import CGA106G3.com.emp.Id.PerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@IdClass(PerId.class)
@Data
public class Per {
    @Id
    private Integer empno;
    @Id
//    @ManyToOne
    private Integer fctno;
}
