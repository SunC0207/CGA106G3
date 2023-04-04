package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.DTO.EmpLoginDTO;
import CGA106G3.com.emp.DTO.PersistEmpDTO;
import CGA106G3.com.emp.Repository.EmpRepository;
import CGA106G3.com.emp.Service.EmpLoginServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/empLogin")
public class EmpLoginController {
    @Autowired
    EmpLoginServiceImpl empLoginService;

    @PostMapping(value = "/login")
    public String EmpLogin(HttpServletRequest request, @RequestBody EmpLoginDTO empLoginDTO) {
        PersistEmpDTO persistEmpDTO = empLoginService.login(empLoginDTO);
        if (persistEmpDTO != null) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            HttpSession session = request.getSession();
            session.setAttribute("loginOrNot",true);

        } ;

        return null;
    }

}
