package CGA106G3.com.memoitempic.Repository;

import CGA106G3.com.memoitempic.Entity.Memoitempic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoitempicRepository extends JpaRepository<Memoitempic,Integer> {
    Memoitempic findByMino(Integer mino);
}
