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
public class Rel extends EntityCore {
    @Id
    @Column(name = "REL_NO",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relno;
    @Column(name = "REL_NAME",nullable = false,length = 10)
    private String relname;

}
