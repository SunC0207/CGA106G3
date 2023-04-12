package CGA106G3.com.religion.repository;

import CGA106G3.com.religion.Entity.Rel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RelRepository extends JpaRepository<Rel, Integer> {

    Optional<Rel> findByRelName(String relName);

    List<Rel> findByRelNo(Integer relNo);
}
