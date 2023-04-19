package CGA106G3.com.item.repository;

import CGA106G3.com.item.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    //SELECT * FROM Item WHERE proNo = :proNo
    public List<Item> findByProNo(@Param("proNo") Integer proNo);



    @Query(value = "SELECT i.ITEMNO, i.INAME, i.IPRICE, i.PRONO, p.PRONAME, c.CERNO, c.CERNAME ,r.rel_no, r.rel_name " +
            "FROM Item i " +
            "JOIN process p ON i.proNo = p.proNo " +
            "JOIN Ceremony c ON p.cerNo = c.cerNo " +
            "JOIN Religion r ON c.REL_NO = r.REL_NO " +
            "WHERE i.PRONO = :proNo "
            , nativeQuery = true)
    List<Object[]> findAllItemsWithProAndCeremonyAndRel(@Param("proNo") Integer proNo);
}
