package CGA106G3.com.emp.Service;

import CGA106G3.com.emp.DTO.FctDTO;
import CGA106G3.com.emp.Entity.Fct;

import java.util.List;

public interface FctService {
    public List<FctDTO> getAllFct();
    public FctDTO EntityToDTO(Fct fct);

}
