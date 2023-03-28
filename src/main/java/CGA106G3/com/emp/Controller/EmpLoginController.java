package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.Repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empLogin")
public class EmpLoginController {
    @Autowired
    EmpRepository empRepository;
    @PostMapping(value = "/login")
    public String EmpLogin(@RequestBody String empno,String password){

    return "index";
    }

}
