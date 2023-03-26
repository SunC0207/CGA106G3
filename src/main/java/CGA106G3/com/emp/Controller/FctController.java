package CGA106G3.com.emp.Controller;

import CGA106G3.com.emp.DTO.FctDTO;
import CGA106G3.com.emp.Service.FctServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/fct")
public class FctController {
    @Autowired
    private FctServiceImpl fctServiceImpl;

    @GetMapping("/ls-all")
    @ResponseBody
    public List<FctDTO> getAll(){
        System.out.println(123456);
        return fctServiceImpl.getAllFct();
    }

    @GetMapping("/test")
    public Integer test(){
        return  1;
    }
}
