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


//

    @Override
    public Member login(Member member) {
        final String email = member.getEmail();
        final String mpw = member.getMpw();

        member = memberRepository.selectForlogin(email, mpw);

        if (member == null) {
            member = new Member();
//            member.setMessage("帳號或密碼錯誤");
            member.setSuccessful(false);

            return member;
        }
        member.setMessage("登入成功");
        member.setSuccessful(true);


        return member;
    }
}
