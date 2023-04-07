package CGA106G3.com.optpic.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "optpic")
@Data
public class Opic extends EntityCore {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picno;
    @Column(nullable = false)
    private Integer optno;

    @Column(nullable = false, length = 10)
    private String picname;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] upfile;

    @Transient
    private String upfileBase64;

}
