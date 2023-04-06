package CGA106G3.com.process.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "process")
@Data
public class Pro extends EntityCore {
    @Id
    @Column(nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer prono;

    @Column(nullable = false,length = 20)
    private String proname;

    @Column(nullable = false,length = 3)
    private Integer prosta;

    @Column(nullable = false,length = 10)
    private Integer proseq;

    @Column(nullable = false,length = 10)
    private Integer cerno;
}
