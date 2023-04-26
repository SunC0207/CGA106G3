package CGA106G3.com.memoitemord.Repository;

import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MemoitemordRepository extends JpaRepository<Memoitemord,Integer> {

    List<Memoitemord> findByMembno(Integer membno);




     Memoitemord findByOrdno(Integer ordno);

     @Query(value = "UPDATE memoitemord SET pay_sta = 1 WHERE ordno = :ordno",nativeQuery = true)
     @Modifying
     @Transactional
     void setstaToOne(Integer ordno);
}
