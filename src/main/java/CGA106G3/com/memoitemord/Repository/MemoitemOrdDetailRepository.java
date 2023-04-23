package CGA106G3.com.memoitemord.Repository;

import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemoitemOrdDetailRepository extends JpaRepository<MemoitemordDetailDTO,Integer> {
    @Query(value = "SELECT m.MINO, m.MINAME, m.MIPRICE, m.MICATE_NO, d.MIQTY, d.MIDATE, o.ORDNO, o.membno, o.TOTLAL_PR, o.ORDDATE, o.ORDSTA, o.PAY_STA, o.REC, o.REC_ADDR " +
            "FROM memoitem m " +
            "JOIN memoord_detail d ON m.MINO = d.MINO " +
            "JOIN memoitemord o ON d.ORDNO = o.ORDNO " +
            "WHERE o.membno = :membno", nativeQuery = true)
    List<Object[]> findAllMemoitemordDetailbyMembno(Integer membno);

    @Query(value = "SELECT m.MINO, m.MINAME, m.MIPRICE, m.MICATE_NO, d.MIQTY, d.MIDATE, o.ORDNO, o.membno, o.TOTLAL_PR, o.ORDDATE, o.ORDSTA, o.PAY_STA, o.REC, o.REC_ADDR " +
            "FROM memoitem m " +
            "JOIN memoord_detail d ON m.MINO = d.MINO " +
            "JOIN memoitemord o ON d.ORDNO = o.ORDNO " +
            "WHERE o.ordno = :ordno", nativeQuery = true)
    List<Object[]> findAllMemoitemordDetailbyOrderno(Integer ordno);

}
