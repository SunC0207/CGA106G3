package CGA106G3.com.memoitempic.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.memoitem.Entity.Memoitem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Data
public class Memoitempic extends EntityCore {
    @Id
    @GeneratedValue
    private Integer mino;
    @Column(nullable = false)
    private byte[] mipicno;
    @Column(nullable = false,length = 20)
    private String mipicname;

}
