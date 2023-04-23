package CGA106G3.com.process.Controller;

import CGA106G3.com.process.DTO.ProCereDto;
import CGA106G3.com.process.DTO.ProDTO;
import CGA106G3.com.process.Entity.Pro;
import CGA106G3.com.process.Service.ProService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pro")
public class ProController {
    @Autowired
    private ProService proservice;
    @PostMapping("/add")
    public Pro addPro(@RequestBody Pro pro){
        return proservice.addPro(pro);
    }

    @RequestMapping("/update/{row}")
    public Pro updatePro(@PathVariable("row") int row, @RequestBody Pro pro) {
        pro.setProNo(row);
        return proservice.addPro(pro);
    }

    @RequestMapping("/find")
    public Optional<Pro> findProById(Integer proNo, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return proservice.findProById(proNo);
    }

    @RequestMapping("/findCereName")
    public List<ProCereDto> finaAllJoinCere(){
        return proservice.finaAllJoinCere();
    }

    @RequestMapping("/findAll")
    public Page<ProDTO> getAllPro(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return proservice.findAllProDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) proservice.count();
        return (totalRecords + size - 1) / size;
    }

    @PostMapping("/updateProSta")
    public ResponseEntity<String> updateProSta(@RequestParam("proNo") Integer proNo, @RequestParam("proSta") Boolean proSta){
        Optional<Pro> optionalPro = proservice.findProById(proNo);
        if(optionalPro.isPresent()){
            Pro pro = optionalPro.get();
            pro.setProSta(proSta);
            proservice.updatePro(pro);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.notFound().build();
        }
    }


    @RequestMapping("/proByCerNo")
    public List<Pro> getByCerNo(@Param("cerNo") Integer cerNo){
        return proservice.getByCerNo(cerNo);
    }

    @RequestMapping("/findRNameCName")
    public List<Object[]> findRNameCNameByProNO(Integer proNo){
        return proservice.findRNameCNameByProNO(proNo);
    }

}
