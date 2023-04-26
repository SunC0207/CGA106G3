package CGA106G3.com.ceremony.repository;

import CGA106G3.com.ceremony.Entity.Ceremony;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CeremonyRepository extends JpaRepository<Ceremony, Integer> {

//    @Query("SELECT c FROM Ceremony c WHERE c.relNo = :relNo")
    List<Ceremony> findByRelNo(@RequestParam("relNo") Integer relNo);

    @Query(value = "SELECT r.REL_NAME " +
            "FROM ceremony c " +
            "JOIN religion r ON c.REL_NO = r.REL_NO " +
            "WHERE CERNO = :cerNo"
            ,nativeQuery = true)
    List<Object> findRelNameByCerno(Integer cerNo);

    @Query(value = "SELECT  c.CERNO, r.REL_NO, r.REL_NAME, c.CERNAME, c.CERSTA " +
            "FROM ceremony c " +
            "JOIN religion r ON c.REL_NO = r.REL_NO " +
            "ORDER BY c.REL_NO "
            ,nativeQuery = true)
    List<Object[]> findAllJoinRel();
}
