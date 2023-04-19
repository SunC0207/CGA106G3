package CGA106G3.com.religion.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "religion")
@Data
//@ToString(exclude = "ceremonies")
public class Rel extends EntityCore {
    @Id
    @Column(name = "REL_NO",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relNo;
    @Column(name = "REL_NAME",nullable = false,length = 10)
    private String relName;

//    @OneToMany(mappedBy = "rel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    private List<Ceremony> ceremonies = new ArrayList<>();

}
