package CGA106G3.com.emp.repository;

import CGA106G3.com.emp.DTO.EmpDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import CGA106G3.com.emp.Entity.Emp;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
    List<Emp> findEmpByEname(String ename);
    List<Emp> findEmpByEmpsta(Integer empsta);



}
