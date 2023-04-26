package CGA106G3.com.memoorddetail.Entity;

import CGA106G3.com.memoitem.Entity.Memoitem;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memoord_detail")
@Data
@IdClass(memoorddetailPK.class)
public class Memoorddetail {
    @Id
    @Column(name = "ORDNO")
    private Integer ordno;
    @Id
    @Column(name = "MINO")
    private Integer mino;
    @Column(nullable = false)
    private Integer miqty;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @Column(nullable = false)
    private LocalDateTime midate;
    @Column(nullable = false)
    private Integer miprice;
    @ManyToOne
    @JoinColumn(name = "ORDNO",insertable = false, updatable = false)
    private Memoitemord memoitemord;
    @OneToOne
    @JoinColumn(name = "MINO",insertable = false, updatable = false)
    private Memoitem memoitem;
}
