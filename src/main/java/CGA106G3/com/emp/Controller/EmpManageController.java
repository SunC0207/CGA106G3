package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;
import CGA106G3.com.emp.Service.EmpServiceImpl;
import CGA106G3.com.emp.Service.PerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/empManage")
public class EmpManageController {
    @Autowired
    private EmpServiceImpl empService;
    @Autowired
    private PerServiceImpl perService;

    @GetMapping("/getOne")
    public EmpDTO getOne(@RequestParam Integer empno){
        return empService.getOne(empno);
    }

    @GetMapping("/getAll")
    public List<EmpDTO> getAllEmp() {
        return empService.getAllEmp();
    }

    @PostMapping("/persistEmp")
    public Boolean persistEmp(@RequestBody PersistEmpDTO persistEmpDTO) {

        return empService.persistEmp(persistEmpDTO);
    }

    @PostMapping("/addEmp")
    public Integer addEmp(@RequestBody EmpDTO empDTO) {

        return empService.addEmp(empDTO);
    }

    @GetMapping("/searchByEname")
    public List<EmpDTO> getEmpByEname(@RequestParam String ename){
        return empService.getEmpByEname(ename);
    }
    @GetMapping("/searchBySta")
    public List<EmpDTO> getEmpBySta(@RequestParam Integer empsta){
        return empService.getEmpBySta(empsta);
    }


}
