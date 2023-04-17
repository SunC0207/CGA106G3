package CGA106G3.com.ceremony.repository;

import CGA106G3.com.ceremony.Entity.Ceremony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CeremonyRepository extends JpaRepository<Ceremony, Integer> {

//    @Query("SELECT c FROM Ceremony c WHERE c.relNo = :relNo")
    List<Ceremony> findByRelNo(@Param("relNo") Integer relNo);
}
