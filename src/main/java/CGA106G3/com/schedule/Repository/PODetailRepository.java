package CGA106G3.com.schedule.Repository;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.Entity.PODetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PODetailRepository extends JpaRepository<PODetail,Integer> {

    @Query(value = "SELECT * FROM plan_ord_detail WHERE date BETWEEN (:startDate) AND ADDDATE(:endDate, 1)", nativeQuery = true)
    List<PODetail> findByDateRange(Date startDate, Date endDate);
}
