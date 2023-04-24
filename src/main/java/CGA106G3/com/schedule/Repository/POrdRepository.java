package CGA106G3.com.schedule.Repository;

import CGA106G3.com.schedule.Entity.POrd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface POrdRepository extends JpaRepository<POrd, Integer> {
    List<POrd> findByEmpno(Integer empno);

    @Query(value = "SELECT pl.PODATE, pl.PONO, r.REL_NAME, pl.DNAME, pl.DBIRTH, pl.DDATE, " +
            "pod.DATE, i.INAME, i.IPRICE, l.LOCNAME, pl.TOTAL_PR, pl.PAY_STA, pl.PO_STA, " +
            "pod.DETAIL_NO, i.ITEMNO, pod.LOCNO " +
            "FROM plan_ord pl " +
            "LEFT JOIN plan_ord_detail pod ON pl.pono = pod.pono " +
            "LEFT JOIN location l ON pod.LOCNO = l.LOCNO " +
            "LEFT JOIN item i ON pod.ITEMNO = i.ITEMNO " +
            "LEFT JOIN process p ON i.PRONO = p.PRONO " +
            "LEFT JOIN ceremony c ON p.CERNO = c.CERNO " +
            "LEFT JOIN religion r ON c.REL_NO = r.REL_NO " +
            "WHERE pl.pono = :pono " , nativeQuery = true)
    List<Object[]> detailByPoNO(Integer pono);

    List<POrd> findByMembno(Integer membno);

}
