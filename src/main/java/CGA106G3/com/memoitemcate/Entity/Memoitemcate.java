package CGA106G3.com.memoitemcate.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.memoitem.Entity.Memoitem;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Data
public class Memoitemcate extends EntityCore{
    @Id
    @Column(name = "MICATE_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer micateno;

    @Column(name = "MIC_NAME", length = 10, nullable = false)
    private String micname;

    @Column(name = "micate_STA", nullable = false)
    private  Integer micatesta;

    @Column(name = "micate_Date", nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date micatedate;
    @Transient
    @OneToMany(mappedBy = "memoitemcate")
    private List<Memoitem> memoitem;
}
