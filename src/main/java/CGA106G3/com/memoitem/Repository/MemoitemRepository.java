package CGA106G3.com.memoitem.Repository;

import CGA106G3.com.memoitem.Entity.Memoitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoitemRepository extends JpaRepository<Memoitem,Integer> {
}
