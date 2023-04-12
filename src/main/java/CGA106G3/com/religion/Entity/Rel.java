package CGA106G3.com.religion.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.ceremony.Entity.Ceremony;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "religion")
@Data
public class Rel extends EntityCore {
    @Id
    @Column(name = "REL_NO",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relNo;
    @Column(name = "REL_NAME",nullable = false,length = 10)
    private String relName;

    @OneToMany(mappedBy = "rel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ceremony> ceremonies = new ArrayList<>();
}
