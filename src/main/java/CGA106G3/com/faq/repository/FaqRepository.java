package CGA106G3.com.faq.repository;

import CGA106G3.com.faq.Entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {

    Optional<Faq> findByFaqname(String faqname);


}
