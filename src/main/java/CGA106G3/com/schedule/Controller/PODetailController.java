package CGA106G3.com.schedule.Controller;

import CGA106G3.com.schedule.DTO.PODetailDTO;
import CGA106G3.com.schedule.DTO.PODetailRangeDTO;
import CGA106G3.com.schedule.DTO.ScheduleDTO;
import CGA106G3.com.schedule.Service.PODetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/findByRangeAndEmpno")
    public List<ScheduleDTO> findByDateRangeAndEmpno(@RequestBody PODetailRangeDTO poDetailRangeDTO) {
        return poDetailService.getByDateRangeAndPono(poDetailRangeDTO);
    }

}
