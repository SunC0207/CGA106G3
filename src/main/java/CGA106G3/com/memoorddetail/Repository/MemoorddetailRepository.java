package CGA106G3.com.memoorddetail.Repository;

import CGA106G3.com.memoorddetail.Entity.Memoorddetail;
import CGA106G3.com.memoorddetail.Entity.memoorddetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemoorddetailRepository extends JpaRepository<Memoorddetail, memoorddetailPK> {
    public List<Memoorddetail> findByOrdno(Integer ordno);
    public List<Memoorddetail> findByMino(Integer Mino);
    public List<Memoorddetail> findByMidate(LocalDateTime Midate);

}
