package CGA106G3.com.memoitem.Repository;

import CGA106G3.com.memoitem.Entity.Memoitem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoitemRepository extends JpaRepository<Memoitem,Integer> {
    public Memoitem getByMino(Integer mino);
    public List<Memoitem> findBymista(Integer mista);
    public List<Memoitem> findByminame(String miname);
    public List<Memoitem> findByMicateno(Integer micateno);
    @Query("from Memoitem where memoitemcate.micname = :micname")
    public List<Memoitem> findBymemoitemcatemicName(String micname);
}
