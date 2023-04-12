package CGA106G3.com.info.Respository;


import CGA106G3.com.info.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {

    Info findByInfoname (String infoname);
}