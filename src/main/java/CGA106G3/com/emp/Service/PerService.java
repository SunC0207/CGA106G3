package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.PerDTO;

import java.util.ArrayList;
import java.util.List;

public interface PerService {

    Boolean updatePer(ArrayList<PerDTO> perDTOList);

    List<PerDTO> getPerByEmpno(Integer empno);

    void deletePer(Integer empno);
}
