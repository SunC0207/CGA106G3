package CGA106G3.com.Location.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="location")
@Data
public class Loc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locno;


    @Column( nullable = false,length = 20)
    private String locname;

    @Column( nullable = false , length = 30)
    private String locpur;

    @Column( nullable = false)
    private Integer locsta;
}
