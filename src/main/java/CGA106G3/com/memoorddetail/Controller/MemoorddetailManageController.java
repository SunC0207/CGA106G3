package CGA106G3.com.memoorddetail.Controller;

import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.com.memoorddetail.Service.MemoordDETAILImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/MemoorddetailManage")
public class MemoorddetailManageController {
    @Autowired
    private MemoordDETAILImpl service;

    @GetMapping("/getAll")
    @ResponseBody
    public List<MemoorddetailDTO> getAllMemoorddetail(){return service.getAllMemoorddetail();}
    @GetMapping("/SearchByOrdno")
    public List<MemoorddetailDTO> findByOrdno(@RequestParam Integer ordno){return  service.findByOrdno(ordno);}
    @GetMapping("/SearchByMino")
    public List<MemoorddetailDTO> findByMino(@RequestParam Integer mino){return  service.findByMino(mino);}
    @GetMapping("SearchByDate")
    public List<MemoorddetailDTO> findByDate(@RequestParam LocalDateTime midate){return service.findByDate(midate);}
}
