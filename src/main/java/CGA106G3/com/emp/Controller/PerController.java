package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.DTO.PerDTO;
import CGA106G3.com.emp.Service.PerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Per")
public class PerController {

    @Autowired
    private PerServiceImpl perService;

    @PostMapping("/addPer")
    public Boolean addPer(@RequestBody ArrayList<PerDTO> perDTOList) {


        return perService.updatePer(perDTOList);
    }

    @GetMapping("/getPerByEmpno")
    public List<PerDTO> getPerByEmpno(Integer empno){
        return perService.getPerByEmpno(empno);
    }
}
