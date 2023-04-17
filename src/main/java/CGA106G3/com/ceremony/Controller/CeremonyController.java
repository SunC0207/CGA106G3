package CGA106G3.com.ceremony.Controller;

import CGA106G3.com.ceremony.DTO.CeremonyDTO;
import CGA106G3.com.ceremony.Entity.Ceremony;
import CGA106G3.com.ceremony.Service.CeremonyService;
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
@RequestMapping("/ceremony")
public class CeremonyController {
    @Autowired
    private CeremonyService ceremonyService;
    @PostMapping("/add")
    public Ceremony addCeremony(@RequestBody Ceremony ceremony){
        return ceremonyService.addCeremony(ceremony);
    }

    @RequestMapping("/update/{row}")
    public Ceremony updateCeremony(@PathVariable("row") int row, @RequestBody Ceremony ceremony) {
        ceremony.setCerNo(row);
        return ceremonyService.updateCeremony(ceremony);
    }

    @RequestMapping("/find")
    public Optional<Ceremony> findCeremonyById(Integer cerNo, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return ceremonyService.findCeremonyById(cerNo);
    }

    @RequestMapping("/findAll")
    public Page<CeremonyDTO> getAllCeremony(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return ceremonyService.findAllCeremonyDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) ceremonyService.count();
        return (totalRecords + size - 1) / size;
    }

    @PostMapping("/updateCerSta")
    public ResponseEntity<String> updateCerSta(@RequestParam("cerNo") Integer cerNo, @RequestParam("cerSta") Integer cerSta){
        Optional<Ceremony> optionalCeremony = ceremonyService.findCeremonyById(cerNo);
        if(optionalCeremony.isPresent()){
            Ceremony ceremony = optionalCeremony.get();
            ceremony.setCerSta(cerSta);
            ceremonyService.updateCeremony(ceremony);
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/ceremoniesByRelno")
    public List<Ceremony> getCeremoniesByRelNo(@Param("relNo") Integer relNo){
        return ceremonyService.findCeremoniesByRelNo(relNo);
    }

}
