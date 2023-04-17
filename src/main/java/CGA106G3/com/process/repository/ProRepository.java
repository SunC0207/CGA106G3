package CGA106G3.com.process.repository;

import CGA106G3.com.process.Entity.Pro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProRepository extends JpaRepository<Pro, Integer> {

    List<Pro> findByCerNo(@Param("cerNo") Integer cerNo);
}
