package CGA106G3.com.schedule.Controller;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;
import CGA106G3.com.schedule.Service.PODetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/PODetail")
public class PODetailController {

    @Autowired
    private PODetailServiceImpl poDetailService;

    @GetMapping("/getAll")
    public List<PODetailDTO> getAll() {
        return poDetailService.getAll();
    }

    @GetMapping("/getOne")
    public PODetailDTO getOne(@RequestParam Integer pono) {
        return poDetailService.getOne(pono);
    }

    @PostMapping("/findByRange")
    public List<PODetailDTO> searchDateRange(@RequestBody PODetailRangeDTO poDetailRangeDTO) {
        return poDetailService.getByDateRange(poDetailRangeDTO);
    }

    @RequestMapping("/findAll")
    public Page<PODetailDTO> getAllPODetail(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return poDetailService.findAllPODetailDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) poDetailService.count();
        return (totalRecords + size - 1) / size;
    }

}
