package CGA106G3.com.faq.Entity;

import CGA106G3.Core.Entity.EntityCore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faq")
@Data
public class Faq extends EntityCore {

    @Id
    @Column(name = "FAQNO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faqno;
    @Column(name = "FAQ_NAME", nullable = false)
    private String faqname;
    @Column(name = "FAQ_ANS", nullable = false)
    private String faqans;
    @Column(name = "FAQ_TAG", nullable = false)
    private String faqtag;

}
