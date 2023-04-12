package CGA106G3.com.memoitemcate.repository;

import CGA106G3.com.memoitemcate.Entity.Memoitemcate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoitemcateRepository extends JpaRepository<Memoitemcate,Integer> {

    List<Memoitemcate> findByMicatesta(Integer micatesta);

    List<Memoitemcate> findByMicname(String micname);

}
