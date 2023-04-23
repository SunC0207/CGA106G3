package CGA106G3.com.member.Controller;

import CGA106G3.com.member.DTO.MemberDTO;
import CGA106G3.com.member.DTO.MemberVerstaDTO;
import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Service.MemberServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memb")
public class MemberController {
    @Resource
    private MemberServiceImpl memberServiceImpl;

    @GetMapping("/findById")
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
    @GetMapping("/getMemberByMname")
    public Member getMemberByMname (@RequestParam String mname){
        return memberServiceImpl.findByMname(mname);
    }

@PostMapping("/{membno}/versta/{versta}")
public ResponseEntity<MemberVerstaDTO> updateMemberVersta(@PathVariable("membno") Integer membno,
                                                          @PathVariable("versta") Integer versta){
        MemberVerstaDTO memberVerstaDTO=new MemberVerstaDTO();
        memberVerstaDTO.setMembno(membno);
        memberVerstaDTO.setVersta(versta);

        MemberVerstaDTO updatedMemberVersta = memberServiceImpl.updateVersta(memberVerstaDTO);
        return ResponseEntity.ok(updatedMemberVersta);
}

//    @GetMapping("/getMembByVersta")
//    public List<MemberDTO> getMembByVersta(@RequestParam Integer versta){
//        return memberServiceImpl.getMemberBySta(versta);
//
//    }
    @GetMapping("/getAllMember")
    public List<Member> getAllMember() {
        return memberServiceImpl.findAllMember();
    }


}
