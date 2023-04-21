package CGA106G3.com.memoorddetail.Controller;

import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.com.memoorddetail.Service.MemoordDETAILImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
