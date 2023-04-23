package CGA106G3.com.member.Controller;

import CGA106G3.com.member.Core.Core;
import CGA106G3.com.member.DTO.MemberEditDTO;
import CGA106G3.com.member.DTO.UpdateVerstaDTO;
import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RequestMapping("/memberEdit")
@RestController
//@SessionAttributes({"member"})
public class MemberEditController {

    @Autowired
    private MemberServiceImpl memberService;


    @PostMapping("/updateMember")
    public MemberEditDTO updateMember (@RequestBody MemberEditDTO memberEditDTO){
        return memberService.update(memberEditDTO);

    }





    //    @GetMapping
//    public Member getInfo(@SessionAttribute Member member){
//        if(member == null){
//            member = new Member();
//            member.setMessage("無會員資訊");
//            member.setSuccessful(false);
//        }else {
//            member.setSuccessful(true);
//        }
//        return  member;
//    }
//    @GetMapping("{password}")
//    public Core checkPassword(@PathVariable String password ,
//                              @SessionAttribute Member member){
//        final Core core = new Core();
//        if(member==null){
//            core.setMessage("無會員資訊");
//            core.setSuccessful(false);
//
//        }else{
//            final String currentPassword = member.getMpw();
//            if(Objects.equals(password,currentPassword)){
//                core.setSuccessful(true);
//        }else {
//                core.setMessage("舊密碼錯誤");
//                core.setSuccessful(false);
//            }
//        }
//        return  core;
//    }
//
//@PutMapping("edit")
//    public Member edit (Model model ,@RequestBody Member member
//,@SessionAttribute("member") Member oMember){
//    final String memberName = oMember.getMname();
//    member.setMname(memberName);
//    member= memberService.updateMember(member);
//    if(member.isSuccessful()){
//        model.addAttribute("member",member);
//    }
//    return  member;
//}
    @PostMapping("/updateVersta")
    public void updateVersta(@RequestBody UpdateVerstaDTO updateVerstaDTO){
        memberService.updateVersta(updateVerstaDTO);
    }
}
