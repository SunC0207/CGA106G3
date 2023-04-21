package CGA106G3.com.memoitemord.Repository;

import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoitemordRepository extends JpaRepository<Memoitemord,Integer> {

    List<Memoitemord> findByMembno(Integer membno);



}
