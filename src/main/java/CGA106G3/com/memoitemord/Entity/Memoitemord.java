package CGA106G3.com.memoitemord.Entity;

import CGA106G3.Core.Entity.EntityCore;
import CGA106G3.com.memoorddetail.Entity.Memoorddetail;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Data
public class Memoitemord extends EntityCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ordno;
    @Column(nullable = false)
    private Integer membno;

    @Column(name = "TOTLAL_PR",nullable = false)
    private Integer totalpr;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date orddate;
    @Column(nullable = false)
    private Integer ordsta;
    @Column(name= "PAY_STA", nullable = false)
    private Integer paysta;
    @Column(nullable = false, length = 20)
    private String rec;
    @Column(name = "REC_ADDR",nullable = false, length = 100)
    private String recaddr;

    @Transient
    @OneToMany(mappedBy = "memoitemord")
    private List<Memoorddetail> memoorddetails;



}
