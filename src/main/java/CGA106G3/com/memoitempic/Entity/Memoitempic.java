package CGA106G3.com.memoitempic.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMOITEMPIC")
@Data
public class Memoitempic {
    @Id
    private Integer mino;
    @Column(columnDefinition = "blob")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] mipicno;
    @Column(nullable = false,length = 20)
    private String mipicname;

}
