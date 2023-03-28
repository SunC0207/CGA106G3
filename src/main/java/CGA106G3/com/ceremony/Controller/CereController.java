package CGA106G3.com.ceremony.Controller;

import CGA106G3.com.ceremony.DTO.CereDTO;
import CGA106G3.com.ceremony.Entity.Cere;
import CGA106G3.com.ceremony.Service.CereService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cere")
public class CereController {
    @Autowired
    private CereService cereservice;
    @PostMapping("/add")
    public Cere addCere(Cere cere){
        return cereservice.addCere(cere);
    }

    @RequestMapping("/update")
    public Cere updateCere(Cere cere) {
        return cereservice.addCere(cere);
    }

    @RequestMapping("/find")
    public Optional<Cere> findCereById(Integer cereno, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return cereservice.findCereById(cereno);
    }

    @RequestMapping("/findAll")
    public List<CereDTO> getAllCere(){
        return cereservice.getAllCere();
    }

}
