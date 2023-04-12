package CGA106G3.com.item.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.process.Entity.Pro;
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
    @Column(name="ITEMNO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemNo;

    @Column(name="INAME", nullable = false, length = 20)
    private String iName;

    @Column(name="ISTA", nullable = false, length = 3)
    private Integer iSta;

    @Column(name="IPRICE", nullable = false, length = 10)
    private Integer iPrice;

    @Column(name="PRONO", nullable = false, length = 10,insertable=false, updatable=false)
    private Integer proNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRONO")
    private Pro pro;


}
