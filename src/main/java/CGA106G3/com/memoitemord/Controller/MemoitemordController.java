package CGA106G3.com.memoitemord.Controller;

import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.Service.MemoitemordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memoitemordManage")
public class MemoitemordController {
    @Autowired
    private MemoitemordServiceImpl service;
    @GetMapping("/getAll")
    public List<MemoitemordDTO> getAllMemoitemord(){return service.getAllMemoitemord();}
}
