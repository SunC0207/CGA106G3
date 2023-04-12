package CGA106G3.com.religion.Controller;

import CGA106G3.com.religion.DTO.RelDTO;
import CGA106G3.com.religion.Entity.Rel;
import CGA106G3.com.religion.Service.RelService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rel")
public class RelController {
    @Autowired
    private RelService relservice;

    @PostMapping("/add")
    public Rel addRel(@RequestBody Rel rel){

        return relservice.addRel(rel);
    }

    @GetMapping("/{relName}")
    public Optional<Rel> findByRelName(@PathVariable String relName) {
        return relservice.findByRelName(relName);
    }

    @RequestMapping("/update/{row}")
    public Rel updateRel(@PathVariable("row") int row, @RequestBody Rel rel) {
        rel.setRelNo(row);
        return relservice.addRel(rel);
    }

    @RequestMapping("/findById")
    public Optional<Rel> findRelById(Integer relNo, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return relservice.findRelById(relNo);
    }

    @RequestMapping("/findAll")
    public List<RelDTO> getAllRel(){
        return relservice.getAllRel();
    }

//    @RequestMapping("/findCereName")
//    public List<String> getCeremonyName(Integer relNo){
//        return relservice.getCeremonyName(relNo);
//    }

}
