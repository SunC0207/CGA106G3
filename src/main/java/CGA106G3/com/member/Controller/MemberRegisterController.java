package CGA106G3.com.member.Controller;

import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Service.MemberService;
import CGA106G3.com.member.Service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = {"/memberRegister"})
public class MemberRegisterController {

    @Autowired
    private MemberServiceImpl memberService;

    @PostMapping(value = "/register")
    public Member register(@RequestBody Member member) {
        String email = member.getEmail();
        if(memberService.findMemberByEmail(email)!=null){
            member.setMessage("Email已經註冊");
            member.setSuccessful(false);
            return member;
        }


        if (member == null) {
            member = new Member();
            member.setMessage("無會員資訊");
            member.setSuccessful(false);
            return member;

        }
        return memberService.register(member);
    }
}