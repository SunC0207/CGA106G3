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

    @GetMapping("/{relname}")
    public Optional<Rel> findByRelname(@PathVariable String relname) {
        return relservice.findByRelname(relname);
    }

    @RequestMapping("/update/{row}")
    public Rel updateRel(@PathVariable("row") int row, @RequestBody Rel rel) {
        rel.setRelno(row);
        return relservice.addRel(rel);
    }

    @RequestMapping("/findbyid")
    public Optional<Rel> findRelById(Integer relno, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return relservice.findRelById(relno);
    }

    @RequestMapping("/findAll")
    public List<RelDTO> getAllRel(){
        return relservice.getAllRel();
    }

}
