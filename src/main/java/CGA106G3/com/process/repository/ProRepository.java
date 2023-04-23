package CGA106G3.com.process.repository;

import CGA106G3.com.process.Entity.Pro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProRepository extends JpaRepository<Pro, Integer> {

    List<Pro> findByCerNo(@Param("cerNo") Integer cerNo);

    @Query(value ="SELECT p.PROSEQ, p.PRONO, p.PRONAME, c.CERNAME, p.PROSTA , p.CERNO " +
            " FROM process p " +
            " JOIN ceremony c ON p.CERNO = c.CERNO " +
            " ORDER BY p.PROSEQ" , nativeQuery = true)
    List<Object[]> finaAllJoinCere();

    @Query(value = "SELECT r.REL_NAME, c.CERNAME " +
            "FROM process p " +
            "JOIN ceremony c ON p.CERNO = c.CERNO " +
            "JOIN religion r ON c.REL_NO = r.REL_NO " +
            "WHERE p.PRONO = :proNo " , nativeQuery = true)
    List<Object[]> findRNameCNameByProNO (Integer proNo);
}
