package CGA106G3.com.faq.repository;

import CGA106G3.com.faq.Entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

//    static String findByFaqLike(String faqtag) {
//        return null;
//    };

    List<Faq> findByFaqtagContaining(String faqtag);

    Optional<Faq> findByFaqname(String faqname);

//    Optional<Faq> findByFaqno(Integer faqno);

    Optional<Faq> findById(Integer faqno);



}
