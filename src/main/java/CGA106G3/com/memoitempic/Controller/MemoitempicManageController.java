package CGA106G3.com.memoitempic.Controller;

import CGA106G3.com.memoitempic.DTO.MemoitempicDTO;
import CGA106G3.com.memoitempic.Entity.Memoitempic;
import CGA106G3.com.memoitempic.Service.MemoitempicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/MemoitempicManage")
public class MemoitempicManageController {

    @Autowired
    private MemoitempicServiceImpl service;
    @PostMapping("/getAll")
    public List<MemoitempicDTO> getAllMemoitempic(){return service.getAllMemoitempic();}
    @PostMapping("/add")
    public Memoitempic addOptPic(@RequestParam("mino") Integer mino,
                                 @RequestParam("mipicname") String mipicname,
                                 @RequestParam("mipicno") MultipartFile mipicno) throws IOException {
        // 檢查該細項是否已有圖片
        Memoitempic existingPic = service.getByMino(mino);
        if (existingPic != null) {
            // 如果已有圖片 則更新圖片內容
            byte[] fileBytes = mipicno.getBytes();
            existingPic.setMipicno(fileBytes);
            return service.addPic(existingPic);
        } else {
            // 處理檔案上傳
            byte[] fileBytes = mipicno.getBytes();

            // 建立 OptPic 物件
            Memoitempic memoitempic = new Memoitempic();
            memoitempic.setMino(mino);
            memoitempic.setMipicno(fileBytes);
            memoitempic.setMipicname(mipicname);

            return service.addPic(memoitempic);
        }

    }
}
