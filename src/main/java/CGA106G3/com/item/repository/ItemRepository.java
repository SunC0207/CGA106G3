package CGA106G3.com.item.repository;

import CGA106G3.com.item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    public List<Item> findByProNo(@Param("proNo") Integer proNo);
}
