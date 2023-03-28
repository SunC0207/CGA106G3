package CGA106G3.com.emp.DTO;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PersistEmpDTO {
    private EmpDTO empDTO;
    private ArrayList<PerDTO> perDTOS;
}
