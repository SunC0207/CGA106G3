package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.EmpLoginDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;

public interface EmpLoginService {

    PersistEmpDTO login(EmpLoginDTO empLoginDTO);
}
