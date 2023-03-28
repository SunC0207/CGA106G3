package CGA106G3.com.ceremony.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ceremony")
@Data
public class Cere extends EntityCore {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cerno;
    @Column(nullable = false,length = 20)
    private String cername;
    @Column(nullable = false,length = 3)
    private Integer cersta;
    @Column(name = "REL_NO",nullable = false,length = 10)
    private Integer relno;



}
