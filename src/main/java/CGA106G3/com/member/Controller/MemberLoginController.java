package CGA106G3.com.member.Controller;

import CGA106G3.com.member.DTO.MemberDTO;
import CGA106G3.com.member.Service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Memberlogin")
public class MemberLoginController {


    private MemberServiceImpl memberServiceImpl;
    @Autowired
    private MemberServiceImpl service;

}



