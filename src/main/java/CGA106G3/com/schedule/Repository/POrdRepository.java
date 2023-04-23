package CGA106G3.com.schedule.Repository;

import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Entity.POrd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface POrdRepository extends JpaRepository<POrd, Integer> {
    List<POrd> findByEmpno(Integer empno);

    List<POrd> findByMembno(Integer membno);

}
