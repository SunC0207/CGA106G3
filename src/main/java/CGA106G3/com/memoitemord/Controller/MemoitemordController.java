package CGA106G3.com.memoitemord.Controller;

import CGA106G3.com.memoitemord.DTO.CartDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.Service.MemoitemordServiceImpl;
import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.ecpay.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
