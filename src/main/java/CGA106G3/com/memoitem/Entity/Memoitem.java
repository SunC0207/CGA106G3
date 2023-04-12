package CGA106G3.com.memoitem.Entity;

import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import CGA106G3.com.memoitempic.Entity.Memoitempic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Memoitem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mino;
    @Column(nullable = false,length = 20)
    private String miname;
    @Column(name = "MI_STA",length = 1,nullable = false)
    private Integer mista;
    @Column(nullable = false)
    private Integer miprice;
    @Column(name = "MICATE_NO")
    private Integer micateno;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mino",insertable = false,updatable = false)
    private Memoitempic memoitempic;
    @ManyToOne
    @JoinColumn(name = "MICATE_NO",insertable = false, updatable = false)
    private Memoitemcate memoitemcate;
}


