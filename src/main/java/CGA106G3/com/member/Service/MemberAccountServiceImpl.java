package CGA106G3.com.member.Service;


import CGA106G3.com.member.DTO.MemberLoginDTO;
import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberAccountServiceImpl implements MemberAccountService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;


//    @Override
//    public MemberLoginDTO login(MemberLoginDTO memberLoginDTO) {
//    final String email = memberLoginDTO.getEmail();
//    final String mpw = memberLoginDTO.getMpw();
//
//        if(email==null){
//        memberLoginDTO.setMessage("帳號未輸入");
//        memberLoginDTO.setSuccessful(false);
//            return memberLoginDTO;
//        }
//        if(mpw==null){
//            memberLoginDTO.setMessage("密碼未輸入");
//            memberLoginDTO.setSuccessful(false);
//            return memberLoginDTO;
//        }
//        memberLoginDTO=memberRepository.selectForlogin(email,mpw);
//        if(memberLoginDTO==null){
//            memberLoginDTO=new MemberLoginDTO();
//            memberLoginDTO.setMessage("帳號或密碼錯誤");
//            memberLoginDTO.setSuccessful(false);
//            return memberLoginDTO;
//        }
//
//        memberLoginDTO.setMessage("登入成功");
//        memberLoginDTO.setSuccessful(true);
//        return memberLoginDTO;
//    }

    @Override
    public Member login(Member member) {
        final String email= member.getEmail();
        final String mpw = member.getMpw();

        //前台寫了require 這裡不需要
//        if(email==null){
//        member.setMessage("帳號未輸入");
//        member.setSuccessful(false);
//            return member;
//        }
//
//        if(mpw==null){
//            member.setMessage("密碼未輸入");
//            member.setSuccessful(false);
//            return member;
//        }

        member=memberRepository.selectForlogin(email,mpw);

        if(member == null){}
        if(member==null){
            member=new Member();
//            member.setMessage("帳號或密碼錯誤");
            member.setSuccessful(false);

            return member;
        }
        member.setMessage("登入成功");
        member.setSuccessful(true);


        return member;
    }

//    public Boolean login(MemberLoginDTO memberLoginDTO) {
//
//        return ;
//    }
}


        //檢查帳號是否存在
//        Member member =memberRepository.getReferenceByEmail(memberLoginDTO.getEmail());
//        if(memberLoginDTO.getEmail() == null){
//            System.out.println("請輸入email");
//            return false;
//        }
        //檢查密碼是否正確
//        if(memberLoginDTO.getMpw().equals(member.getMpw())){
//            System.out.println("密碼相同");
//            return  true;
//            //登入失敗計數器
//        }else{
//            System.out.println("登入失敗");
            //登入失敗紀錄器
//            if(member.getLoginAttempts()==null){
//                member.setLoginAttempts(1);
//            }else{
//                member.setLoginAttempts(member.getLoginAttempts()+1);
//            }
//            if(member.getLoginAttempts()>=3){
//                member.setLoginAttempts(0);
//                out.print("嘗試寫出輸入三次錯誤  請重新輸入");
//                return false;
//            }else{
//                return false;
//            }

//        }


//}
//}