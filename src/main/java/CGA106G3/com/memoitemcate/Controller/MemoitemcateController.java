package CGA106G3.com.memoitemcate.Controller;

import CGA106G3.com.memoitemcate.DTO.MemoitemcateDTO;
import CGA106G3.com.memoitemcate.Service.MemoitemcateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
    @RequestMapping("/memoitemcateManage")
public class MemoitemcateController {
    @Autowired
    private MemoitemcateServiceImpl memoitemcateService;

    @GetMapping("/getAll")
    public List<MemoitemcateDTO> getAll(){
        return memoitemcateService.getAllMemoitemcate();}
    @GetMapping("/getOne")
    public MemoitemcateDTO getOne(@RequestParam Integer micateno){
        return memoitemcateService.getOne(micateno);
    }

    @PostMapping("/addMemoitemcate")
    public void addMemoitemcate(@RequestBody MemoitemcateDTO memoitemcate){
        memoitemcateService.addMemoitemcate(memoitemcate);
    }
    @PostMapping("/updateMemoitemcate")
    public MemoitemcateDTO updateMemoitemcate(@RequestBody MemoitemcateDTO memoitemcateDTO){

        return memoitemcateService.addMemoitemcate(memoitemcateDTO);
    }
    @GetMapping("/searchBySTA")
    public List<MemoitemcateDTO> getByMemoitemcateSTA(@RequestParam Integer micatesta){return  memoitemcateService.getBySTA(micatesta);}
    @GetMapping("/searchByName")
    public  List<MemoitemcateDTO> getByMemoitemcateName(@RequestParam String micname){return  memoitemcateService.getByName(micname);}
}
