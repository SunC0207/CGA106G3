package CGA106G3.com.memoitemord.Controller;

import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDetailDTO;
import CGA106G3.com.memoitemord.DTO.MemoritemordOrderDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Service.MemoitemordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/memoitemordManage")
public class MemoitemordController {
    @Autowired
    private MemoitemordServiceImpl service;
    @GetMapping("/getAll")
    public List<MemoitemordDTO> getAllMemoitemord(){return service.getAllMemoitemord();}

    @GetMapping("/findByOrdno")
    public Memoitemord findById(@RequestParam Integer ordno){
        return service.findDetailByOrdno(ordno);
    }
    @GetMapping("/findByMemb")
    public List<MemoitemordDTO> findByMemb(@RequestParam Integer membno){
        return service.findByMemb(membno);
    }
//    會員編號查詢
    @GetMapping("/listMemoitemordDetail")
    public List<MemoritemordOrderDTO>findAllMemoitemordDetailbyMembno(@RequestParam Integer membno){
        return service.findAllMemoitemordDetailbyMembno(membno);
    }
    //    訂單編號查詢
    @GetMapping("/listMemoitemordDetailByOrderno")
        public ResponseEntity<?>findAllMemoitemordDetailbyOrderno(@RequestParam Integer ordno){

        List<MemoritemordOrderDTO> result = service.findAllMemoitemordDetailbyOrderno(ordno);
        if(result.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("查無此訂單");
        }
        return ResponseEntity.ok(result);
    }


}
