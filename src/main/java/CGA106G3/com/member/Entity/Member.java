package CGA106G3.com.member.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class Member extends EntityCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer membno;

    @Column(nullable = false, length = 20)
    private String mname;
    @Column(nullable = false, length = 20)
    private String mpw;
    private byte[] mpic;
    @Column(nullable = false, length = 1)
    private Integer sex;
    @Column(nullable = false, length = 10)
    private Integer mobile;
    @Column(nullable = false, length = 30)
    private String email;
    @Column(name = "ver_sta", length = 1, nullable = false)
    private Integer versta;


}
