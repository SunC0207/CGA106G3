package CGA106G3.com.member.Controller;



import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Service.MemberAccountServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/memberlogin")
public class MemberLoginController {
//    http://localhost:8080/memberlogin/login?email=12345@hotmail.com&mpw=112233
    @Autowired
    MemberAccountServiceImpl memberAccountService;

    @GetMapping("/login")
    public Member MemberLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String mpw) {
        Member member = new Member();
        if (email == null || mpw == null) {
            member.setMessage("無會員資訊");
            member.setSuccessful(false);
            return member;
        }

//        if (!memberAccountService.emailExists(email)){
//            member.setMessage("登入失敗");
//            return member;
//        }
        member.setEmail(email);
        member.setMpw(mpw);
        member=memberAccountService.login(member);

        if (!member.isSuccessful()) {
            if (request.getSession(false) != null) {
                request.changeSessionId();
            }
            final HttpSession session = request.getSession();
            session.setAttribute("loggedin", true);
            session.setAttribute("member", member);
        }
        return member;

//        if(memberAccountService.login(memberLoginDTO)) {
//            return "登入成功";
//        }else {
//             return "登入失敗";
//        }

    }
//


}



