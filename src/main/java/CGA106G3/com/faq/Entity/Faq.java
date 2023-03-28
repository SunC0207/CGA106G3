package CGA106G3.com.faq.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "Faq")
@Data
public class Faq{

    @Id
    @Column(name = "FAQNO", nullable = false)
    private int faqno;
    @Column(name = "FAQ_NAME", nullable = false)
    private String faqname;
    @Column(name = "FAQ_ANS", nullable = false)
    private String faqans;
    @Column(name = "FAQ_TAG", nullable = false)
    private String tag;

}
