package CGA106G3.com.schedule.Controller;

import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Service.POrdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/schedule")
public class POrdController {

    @Autowired
    private POrdServiceImpl pOrdService;


    @GetMapping("/getAll")
    public List<POrdDTO> getAll() {
        return pOrdService.getAll();
    }

    @GetMapping("*")
    public POrdDTO getOne(Integer pono){
        return pOrdService.getOne(pono);
    }
}
