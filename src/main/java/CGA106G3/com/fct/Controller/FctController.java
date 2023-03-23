package CGA106G3.com.fct.Controller;

import CGA106G3.com.fct.DTO.FctDTO;
import CGA106G3.com.fct.Service.FctService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fct")
public class FctController {
    @Autowired
    private FctService fctService;

    @GetMapping("/ls-all")
    @ResponseBody
    public List<FctDTO> getAll(){
        System.out.println(123456);
        return fctService.getAllFct();
    }

    @GetMapping("/test")
    public Integer test(){
        return  1;
    }
}
