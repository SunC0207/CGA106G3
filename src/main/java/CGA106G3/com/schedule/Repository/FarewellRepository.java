package CGA106G3.com.schedule.Repository;

import CGA106G3.com.schedule.DTO.FarewellDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface FarewellRepository extends JpaRepository<FarewellDTO,Integer> {

    @Query(value = "SELECT DISTINCT pd.PONO, pd.DNAME, d.`DATE` , l.LOCNAME\n" +
            "FROM plan_ord_detail d\n" +
            "JOIN plan_ord pd ON d.pono = pd.pono\n" +
            "JOIN location l ON d.LOCNO = l.LOCNO\n" +
            "JOIN item i ON d.ITEMNO = i.ITEMNO\n" +
            "JOIN `process` por ON i.PRONO = por.PRONO\n" +
            "JOIN ceremony c ON c.CERNO = por.CERNO\n" +
            "WHERE c.CERNO IN (8007 , 8015 , 8025) AND d.`DATE` > SUBDATE(CURDATE(),1) AND pd.DNAME LIKE %:dname%",
            nativeQuery = true)
    Collection<FarewellDTO> findByDname(String dname);
}
