package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.DTO.EmpDTO;
import CGA106G3.com.emp.DTO.EmpRegisterDTO;
import CGA106G3.com.emp.Service.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empManage")
public class EmpManageController {
    @Autowired
    private EmpServiceImpl service;

    @PostMapping("/getAll")
    public List<EmpDTO> getAllEmp(){
      return service.getAllEmp();
    }

    @PostMapping("/addOrUpdateEmp")
    public Boolean addNewEmp(@RequestBody EmpRegisterDTO empRegisterDTO){
        return service.register(empRegisterDTO);
    }
}
