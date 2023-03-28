package CGA106G3.com.memoitemcate.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
