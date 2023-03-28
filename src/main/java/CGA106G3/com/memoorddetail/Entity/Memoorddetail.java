package CGA106G3.com.memoorddetail.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "memoord_detail")
@Data
@IdClass(memoorddetailPK.class)
public class Memoorddetail {
    @Id
    @Column(name = "ORD_NO")
    private Integer ordno;
    @Id
    private Integer mino;
    @Column(nullable = false)
    private Integer miqty;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    @Column(nullable = false)
    private Date midate;
    @Column(nullable = false)
    private Integer miprice;
}
