package CGA106G3.com.member.Controller;

import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Service.MemberServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memb")
public class MemberController {
    @Resource
    private MemberServiceImpl memberServiceImpl;

    @GetMapping("/membno")
    public Member findById(@RequestParam Integer membno) {
        return memberServiceImpl.findMemberById(membno);
    }

    @PostMapping("/addMember")
    public Member addMember(@RequestBody Member member) {
        return memberServiceImpl.insertMember(member);
    }

    @DeleteMapping("/deleteMember/{membno}")
    public void deleteMember(@PathVariable Integer membno) {

        memberServiceImpl.deleteMember(membno);
//        localhost:8080/memb/deleteMember/6
    }

    @PutMapping("/updateMember")
    public Member updateMember(@RequestBody Member member) {
        return memberServiceImpl.updateMember(member);
    }

}
