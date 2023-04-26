package CGA106G3.com.schedule.Controller;

import CGA106G3.com.schedule.DTO.PODetailByMemberDTO;
import CGA106G3.com.schedule.DTO.PODetailJoinDto;

import CGA106G3.com.schedule.DTO.POrdDTO;
import CGA106G3.com.schedule.Entity.POrd;
import CGA106G3.com.schedule.Service.POrdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/schedule")
public class POrdController {

    @Autowired
    private POrdServiceImpl pOrdService;

    @RequestMapping("/update/{row}")
    public POrd update(@PathVariable("row") int row, @RequestBody POrd pOrd) {
        pOrd.setPono(row);
        return pOrdService.updatePOrd(pOrd);
    }

    @GetMapping("/getAll")
    public List<POrdDTO> getAll() {
        return pOrdService.getAll();
    }

    @GetMapping("*")
    public POrdDTO getOne(Integer pono){
        return pOrdService.getOne(pono);
    }

    @RequestMapping("/findAll")
    public Page<POrdDTO> getAllPOrd(@RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page -1, size);
        return pOrdService.findAllPOrdDTO(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) pOrdService.count();
        return (totalRecords + size - 1) / size;
    }

    @RequestMapping("/addPOrd")
    public POrd addPOrd(@RequestBody POrd pOrd){

        return pOrdService.addPOrd(pOrd);
    }

    @RequestMapping("/detailJoin")
    public List<PODetailJoinDto> detailByPoNO(Integer pono){
        return pOrdService.detailByPoNO(pono);
    }
    @RequestMapping("/detailByMembno")
    public List<PODetailByMemberDTO>detailByMembno(Integer membno){
        return pOrdService.detailByMembno(membno);
    }


//    @GetMapping("/listPOrdByMembno")
//    public List<MemberPOrdDTO>findPordByMembno(@RequestParam Integer membno){
//        return pOrdService.findPordByMembno(membno);
//    }
@GetMapping("/findOrdbyMembno")
    public List<POrdDTO>findOrdbyMembno(@RequestParam Integer membno){
        return pOrdService.findByMembno(membno);
}

}
