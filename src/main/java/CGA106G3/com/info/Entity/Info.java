package CGA106G3.com.info.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "system_info")
@Data
public class Info extends EntityCore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer infono;
    @Column(nullable = false, length = 20 ,name = "info_name")
    private String infoname;
    @Column(nullable = false, length = 1000, name = "info_con")
    private String infocon;
}
