package CGA106G3.com.memoitem.Controller;

import CGA106G3.com.memoitem.DTO.MemoitemDTO;
import CGA106G3.com.memoitem.Service.MemoitemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/memoitemManage")
public class MemoitemController {
    @Autowired
    private MemoitemServiceImpl service;

    @PostMapping("/getAll")
    public List<MemoitemDTO> getAllmemotiem(){return service.getAll();};
}
