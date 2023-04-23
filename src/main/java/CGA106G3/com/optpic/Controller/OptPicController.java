package CGA106G3.com.optpic.Controller;

import CGA106G3.com.optpic.Entity.OptPic;
import CGA106G3.com.optpic.Service.OptPicService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/optPic")
public class OptPicController  {
    @Autowired
    private OptPicService optPicservice;

    @RequestMapping("/delete")
    public void deleteByOptNo(Integer optNo){
        optPicservice.deleteByOptNo(optNo);
    }

    @PostMapping("/add")
    public OptPic addOptPic(@RequestParam("optNo") Integer optNo,
                            @RequestParam("picName") String picName,
                            @RequestParam("upFile") MultipartFile upFile) throws IOException {
        // 檢查該細項是否已有圖片
        OptPic existingPic = optPicservice.findByOptNo(optNo);

        if(existingPic != null){
            // 如果已有圖片 則更新圖片內容
            byte[] fileBytes = upFile.getBytes();
            existingPic.setUpFile(fileBytes);
            return optPicservice.addOptPic(existingPic);
        }else{
            // 處理檔案上傳
            byte[] fileBytes = upFile.getBytes();

            // 建立 OptPic 物件
            OptPic optPic = new OptPic();
            optPic.setOptNo(optNo);
            optPic.setPicName(picName);
            optPic.setUpFile(fileBytes);

            return optPicservice.addOptPic(optPic);
        }

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
