package CGA106G3.com.ceremony.repository;

import CGA106G3.com.ceremony.Entity.Ceremony;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeremonyRepository extends JpaRepository<Ceremony, Integer> {
}
