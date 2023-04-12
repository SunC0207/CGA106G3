package CGA106G3.com.member.Controller;

import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Repository.MemberRepository;
import CGA106G3.com.member.Service.ImgService;
import CGA106G3.com.member.Service.MemberService;
import CGA106G3.com.member.Service.MemberServiceImpl;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.System.out;
//第一版
@Controller
@RequestMapping(value = "/pictureReader")
//第二版
//@RestController
//@RequestMapping("/pictureReader")
public class pictureReaderController {


    //第一版

    private final MemberService memberService;

    public pictureReaderController(MemberService memberService){
        this.memberService=memberService;
    }
    @GetMapping("/pictureReader")
    public void pictureReader(@RequestParam("membno") String membno, HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        res.setContentType("image/png");
        ServletOutputStream out = res.getOutputStream();
        try {
          Member member = memberService.findMemberById(Integer.valueOf(membno));
          res.getOutputStream().write(member.getMpic());

        } catch (Exception e) {
            InputStream in = req.getServletContext().getResourceAsStream("/static/NoData/nopic.jpg");
            byte[] buf = new byte[in.available()];
            in.read(buf);
            out.write(buf);
            in.close();
        }
    }
}
//第二版
//    private final ImgService imgService;
//
//    public pictureReaderController(ImgService imgService){
//        this.imgService=imgService;
//    }

//    @GetMapping("/{membno}")
//    public ResponseEntity<byte[]>findMpicById(@PathVariable Integer membno){
//        Member image = imgService.findMpicById(membno);
//        return ResponseEntity.ok()
//                .contentType(MediaType.valueOf(image.getType()))
//                .body(image.getData());
//    }
//}
