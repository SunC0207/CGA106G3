package CGA106G3.com.religion.repository;

import CGA106G3.com.religion.Entity.Rel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelRepository extends JpaRepository<Rel, Integer> {


//    @Query("SELECT new CGA106G3.com.ceremony.DTO.RelToCereDto(c.cerNo, c.cerName) " +
//            "FROM Ceremony c  " +
//            "WHERE c.relNo = :relNo")
//    List<RelToCereDto> findCeremoniesByRelNo(@Param("relNo") Integer relNo);


    Optional<Rel> findByRelName(String relName);


}
