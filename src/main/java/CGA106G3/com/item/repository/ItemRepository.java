package CGA106G3.com.item.repository;

import CGA106G3.com.item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
