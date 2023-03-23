package CGA106G3.com.emp.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.Entity.Emp;

import java.util.List;

public interface EmpService extends ServiceCore {


    Emp register(Emp emp);

    Emp login(Emp emp);

    Emp edit(Emp emp);

    List<EmpDTO> getAllEmp();

    boolean update(Emp emp);
}
