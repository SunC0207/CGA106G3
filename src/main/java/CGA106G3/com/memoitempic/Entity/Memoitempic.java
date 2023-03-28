package CGA106G3.com.memoitempic.Entity;

import CGA106G3.Core.Entity.EntityCore;
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
    private Integer mipicno;
    @Lob
    @Column(nullable = false)
    private Blob mino;
    @Column(nullable = false,length = 20)
    private String mipicname;
}
