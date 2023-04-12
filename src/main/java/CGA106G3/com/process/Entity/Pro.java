package CGA106G3.com.process.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.ceremony.Entity.Ceremony;
import CGA106G3.com.item.Entity.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "process")
@Data
public class Pro extends EntityCore {
    @Id
    @Column(name = "PRONO", nullable = false, length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proNo;

    @Column(name = "PRONAME",nullable = false,length = 20)
    private String proName;

    @Column(name = "PROSTA",nullable = false,length = 3)
    private Integer proSta;

    @Column(name = "PROSEQ",nullable = false,length = 10)
    private Integer proSeq;

    @Column(name = "CERNO",nullable = false,length = 10, insertable=false, updatable=false)
    private Integer cerNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CERNO")
    private Ceremony ceremony;

    @OneToMany(mappedBy = "pro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();


    public List<Item> getItems() {
        return items;
    }
}
