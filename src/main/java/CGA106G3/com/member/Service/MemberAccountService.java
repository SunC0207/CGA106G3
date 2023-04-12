package CGA106G3.com.member.Service;


import CGA106G3.Core.CoreService;
import CGA106G3.com.member.Entity.Member;

public interface MemberAccountService extends CoreService {


//    Boolean login(MemberLoginDTO memberLoginDTO);



    Member login(Member member);
}
