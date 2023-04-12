package CGA106G3.com.memoitem.Repository;

import CGA106G3.com.memoitem.Entity.Memoitem;
import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MemoitemRepository extends JpaRepository<Memoitem,Integer> {

    public List<Memoitem> findBymista(Integer mista);
    public List<Memoitem> findByminame(String miname);
    public List<Memoitem> findByMicateno(Integer micateno);
}
