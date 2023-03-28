package CGA106G3.com.emp.Repository;

import CGA106G3.com.emp.Entity.Per;
import CGA106G3.com.emp.Id.PerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerRepository extends JpaRepository<Per, PerId> {
    List<Per> findPerByEmpno(Integer empno);

    void removePerByEmpno(Integer empno);
}
