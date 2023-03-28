package CGA106G3.com.memoitemcate.Controller;

import CGA106G3.com.memoitemcate.DTO.MemoitemcateDTO;
import CGA106G3.com.memoitemcate.Service.MemoitemcateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memoitemcateManage")
public class MemoitemcateController {
    @Autowired
    private MemoitemcateServiceImpl memoitemcateService;

    @PostMapping("/getAll")
    public List<MemoitemcateDTO> getAll(){
        return memoitemcateService.getAllMemoitemcate();}

    @GetMapping("/test")
    public Integer test(){
        return  1;
    }
}
