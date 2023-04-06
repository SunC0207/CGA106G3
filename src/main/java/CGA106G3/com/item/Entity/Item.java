package CGA106G3.com.item.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
@Data
public class Item extends EntityCore {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemno;
    @Column(nullable = false,length = 20)
    private String iname;
    @Column(nullable = false,length = 3)
    private Integer ista;
    @Column(nullable = false,length = 10)
    private Integer iprice;
    @Column(nullable = false,length = 10)
    private Integer prono;



}
