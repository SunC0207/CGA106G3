package CGA106G3.com.member.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;


@RestController
@RequestMapping("/member/logout")
public class MemberLogoutController {
    @GetMapping
    public void logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
    }
}
