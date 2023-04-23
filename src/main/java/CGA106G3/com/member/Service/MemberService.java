package CGA106G3.com.member.Service;

import CGA106G3.Core.Service.ServiceCore;
import CGA106G3.com.member.DTO.MemberEditDTO;
import CGA106G3.com.member.Entity.Member;

import java.util.List;


public interface MemberService extends ServiceCore {
    Member insertMember(Member member);



    void deleteMember(Integer membno);

    Member updateMember(Member member);

    MemberEditDTO update(MemberEditDTO memberEditDTO);

//    MemberEditDTO update(Integer membno, MemberEditDTO memberEditDTO);

    List<Member> findAllMember();

    Member findMemberById(Integer membno);

//    Member findMemberByEmail(String email);

    Member register(Member member);

//   Member findVerstaByID(Integer membno);

}
