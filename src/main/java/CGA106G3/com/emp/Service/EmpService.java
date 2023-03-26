package CGA106G3.com.emp.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;
import CGA106G3.com.emp.Entity.Emp;

import java.util.List;

public interface EmpService extends ServiceCore {

    EmpDTO getOne(Integer empno);


    Boolean persistEmp(PersistEmpDTO persistEmpDTO);

    Emp login(Emp emp);



    List<EmpDTO> getAllEmp();

    Integer addEmp(EmpDTO empDTO);

    List<EmpDTO> getEmpByEname(String ename);
    List<EmpDTO> getEmpBySta(Integer empsta);


}
