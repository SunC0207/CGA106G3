package CGA106G3.com.info.Controller;

import CGA106G3.com.info.DTO.InfoDTO;
import CGA106G3.com.info.Service.InfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/info")

public class InfoController {
    @Autowired
    private InfoServiceImpl infoService;

    @GetMapping("/findall") //localhost:8080/info/findall
    public List<InfoDTO> findAll (){
        return infoService.getAllinfo();
    }

    @PostMapping("/update") //localhost:8080/info/update
    public boolean update(@RequestBody InfoDTO infoDTO){return infoService.updateInfo(infoDTO); }

    @PostMapping("/add") //localhost:8080/info/add
    public boolean add (@RequestBody InfoDTO infoDTO){return infoService.insertInfo(infoDTO);}

    @GetMapping("/delete") //localhost:8080/info/delete?infono=7
    public void delete(@RequestParam Integer infono){
         infoService.deleteInfo(infono);}

    @GetMapping("/getByName")
    public InfoDTO getByName(@RequestParam String infoname){
        return infoService.findByName(infoname);
    }

}

