package CGA106G3.com.emp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Table
@Data
@Entity
public class Fct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
//    @OneToMany(targetEntity = Per.class)
//    @JoinColumn(name = "fctno")
    private Integer fctno;
    @Column(nullable = false)
    private String fctname;

}
