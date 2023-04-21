package CGA106G3.com.optpic.Controller;

import CGA106G3.com.optpic.Entity.OptPic;
import CGA106G3.com.optpic.Service.OptPicService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/optPic")
public class OptPicController  {
    @Autowired
    private OptPicService optPicservice;
    @PostMapping("/add")
    public OptPic addOptPic(OptPic optPic){
        return optPicservice.addOptPic(optPic);
    }

    @RequestMapping("/update")
    public OptPic updateOptPic(OptPic optPic) {
        return optPicservice.updateOptPic(optPic);
    }

    @RequestMapping("/find")
    public Optional<OptPic> findOicById(Integer optPicNo, HttpServletResponse hsr){
        hsr.addHeader("Access-Control-Allow-Origin","*");
        return optPicservice.findOptPicById(optPicNo);
    }

    @GetMapping("/findAll")
    public Page<OptPic> findAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page -1, size);
        return optPicservice.findAll(pageable);
    }

    @RequestMapping("/getTotalPages")
    public int getTotalPages(@RequestParam int size){
        int totalRecords = (int) optPicservice.count();
        return (totalRecords + size - 1) / size;
    }

}
