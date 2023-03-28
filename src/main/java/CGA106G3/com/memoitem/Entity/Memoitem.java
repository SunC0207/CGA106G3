package CGA106G3.com.memoitem.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Memoitem extends EntityCore {
    @Id
    @GeneratedValue
    private Integer mino;
    @Column(nullable = false,length = 20)
    private String miname;
    @Column(name = "MI_STA",length = 1,nullable = false)
    private Integer mista;
    @Column(nullable = false)
    private Integer miprice;
    @Column(name = "MICATE_NO",nullable = false)
    private Integer micateno;
}
