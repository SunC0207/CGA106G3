package CGA106G3.com.ceremony.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.item.Entity.Item;
import CGA106G3.com.process.Entity.Pro;
import CGA106G3.com.religion.Entity.Rel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ceremony")
@Data
public class Ceremony extends EntityCore {
    @Id
    @Column(name="CERNO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cerNo;

    @Column(name="CERNAME", nullable = false, length = 20)
    private String cerName;

    @Column(name="CERSTA", nullable = false, length = 3)
    private Integer cerSta;

    @Column(name="REL_NO", nullable = false, length = 10, insertable=false, updatable=false)
    private Integer relNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REL_NO")
    private Rel rel;

    @OneToMany(mappedBy = "ceremony", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pro> pros = new ArrayList<>();



    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        for(Pro pro : pros){
            items.addAll(pro.getItems());
        }
        return items;
    }
}
