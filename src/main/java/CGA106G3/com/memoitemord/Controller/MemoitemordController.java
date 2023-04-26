package CGA106G3.com.memoitemord.Controller;

import CGA106G3.com.memoitemord.DTO.CartDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.DTO.MemoritemordOrderDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Service.MemoitemordServiceImpl;
import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.ecpay.service.OrderService;
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
    @Autowired
    private OrderService orderService;
    @GetMapping("/getAll")
    public List<MemoitemordDTO> getAllMemoitemord(){return service.getAllMemoitemord();}
    @PostMapping("/addMemoitemord")
    @ResponseBody
    public String addMemoitemord(@RequestBody CartDTO cartDTO){
        return orderService.Checkout(service.addMemoitemord(cartDTO).getOrdno());
    }
    @GetMapping("/GetOrderDetailsByID")
    public List<MemoorddetailDTO> getDetailByOrdno(@RequestParam  Integer ordno){
        return service.getDetailByordno(ordno);
    }

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
