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
public class OptPic extends EntityCore {
    @Id
    @Column(name="PICNO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picNo;
    @Column(name="OPTNO",nullable = false)
    private Integer optNo;

    @Column(name="PICNAME",nullable = false, length = 10)
    private String picName;

    @Lob
    @Column(name="UPFILE",columnDefinition = "BLOB")
    private byte[] upFile;


}
