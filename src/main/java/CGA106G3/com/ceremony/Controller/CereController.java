package CGA106G3.com.ceremony.Controller;

import CGA106G3.com.ceremony.DTO.CereDTO;
import CGA106G3.com.ceremony.Entity.Cere;
import CGA106G3.com.ceremony.Service.CereService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cere")
public class CereController {
    @Autowired
    private CereService cereservice;
    @PostMapping("/add")
    public Cere addCere(@RequestBody Cere cere){
        return cereservice.addCere(cere);
    }

    @RequestMapping("/update/{row}")
    public Cere updateCere(@PathVariable("row") int row, @RequestBody Cere cere) {
        cere.setCerno(row);
        return cereservice.addCere(cere);
    }

    @RequestMapping("/find")
    public Optional<Cere> findCereById(Integer cerno, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return cereservice.findCereById(cerno);
    }

    @RequestMapping("/findAll")
    public Page<CereDTO> getAllCere(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return cereservice.findAllCereDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) cereservice.count();
        return (totalRecords + size - 1) / size;
    }

    @PostMapping("/updateCersta")
    public ResponseEntity<String> updateCersta(@RequestParam("cerno") Integer cerno, @RequestParam("cersta") Integer cersta){
        Optional<Cere> optionalCere = cereservice.findCereById(cerno);
        if(optionalCere.isPresent()){
            Cere cere = optionalCere.get();
            cere.setCersta(cersta);
            cereservice.updateCere(cere);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
