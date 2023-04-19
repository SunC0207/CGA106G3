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
    @Column(name="ITEMNO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemNo;

    @Column(name="INAME", nullable = false, length = 20)
    private String iName;

    @Column(name="ISTA", nullable = false, length = 3)
    private Integer iSta;

    @Column(name="IPRICE", nullable = false, length = 10)
    private Integer iPrice;

    @Column(name="PRONO", nullable = false, length = 10) //,insertable=false, updatable=false)
    private Integer proNo;



//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "PRONO", referencedColumnName = "PRONO", insertable = false, updatable = false)
//    @JsonIgnore
//    private Pro pro;

//    @JoinColumns({
//            @JoinColumn(name = "CERNO", referencedColumnName = "CERNO", insertable = false, updatable = false),
//    })

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumns({
//            @JoinColumn(name = "CERNO", referencedColumnName = "CERNO", insertable = false, updatable = false),
//            @JoinColumn(name = "REL_NO", referencedColumnName = "REL_NO", insertable = false, updatable = false)
//    })
//    private Ceremony ceremony;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "REL_NO", referencedColumnName = "REL_NO", insertable = false, updatable = false)
//    private Rel rel;



}
