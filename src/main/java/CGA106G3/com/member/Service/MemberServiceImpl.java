package CGA106G3.com.member.Service;

import CGA106G3.com.member.DTO.MemberEditDTO;
import CGA106G3.com.member.DTO.MemberVerstaDTO;
import CGA106G3.com.member.DTO.UpdateVerstaDTO;
import org.modelmapper.ModelMapper;
import CGA106G3.com.member.DTO.MemberDTO;
import CGA106G3.com.member.Entity.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CGA106G3.com.member.Repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
//    @Resource
    private MemberRepository memberRepository;
    @Autowired
    private ModelMapper modelMapper;

@Override
public Member insertMember(Member member){

    return  memberRepository.save(member);
}
@Override
public void deleteMember(Integer member){
    memberRepository.deleteById(member);
}
@Override
@Transactional
    public Member updateMember(Member member){
    final Member omember=memberRepository.findByMname(member.getMname());
    member.setMpw(omember.getMpw());
//    member.setUpdater(member.getMname());
    final int resultCount = memberRepository.update(member);
    member.setSuccessful(resultCount>0);
    member.setMessage(resultCount>0?"修改成功":"修改失敗");

    final String password = member.getMpw();
    if(password==null || password.isEmpty()){
        member.setMpw(omember.getMpw());
    }
   return member;
    }

    @Override
    public MemberEditDTO update( MemberEditDTO memberEditDTO) {
        Member member = modelMapper.map(memberEditDTO,Member.class);
        return modelMapper.map(memberRepository.save(member),MemberEditDTO.class);
    }

    public MemberVerstaDTO updateVersta(MemberVerstaDTO memberVerstaDTO){
    Member member =modelMapper.map(memberVerstaDTO,Member.class);
    return modelMapper.map(memberRepository.save(member),MemberVerstaDTO.class);
    }


    @Override
    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }

    @Override
    public Member findMemberById(Integer membno) {
        return  memberRepository.findById(membno).orElse(null);
    }
//    @Override
//    public List<MemberDTO> getMemberBySta(Integer versta){
//     return memberRepository.findMemberbyVersta(versta)
//             .stream()
//             .map(this::EntityToDTO)
//             .collect(Collectors.toList());
//    }


    private MemberDTO EntityToDTO(Member member){
        MemberDTO memberDto = new MemberDTO();
        modelMapper.map(member, MemberDTO.class);//(輸入型態,要轉換的型態.class)
        return memberDto;
    }

    public Member findByMname(String mname) {
    return memberRepository.findByMname(mname);
    }

//    public List<MemberDTO>findByMname(String mname){
//    return memberRepository.findByMname(mname)
//            .stream()
//            .map(this::EntityToDTO)
//            .collect(Collectors.toList());
//    }


    @Transactional
    @Override
    public Member register(Member member) {



//        if (member.getMname() == null) {
//            member.setMessage("帳號未輸入");
//            member.setSuccessful(false);
//            return member;
//        }
//
//        if (member.getMpw() == null) {
//            member.setMessage("密碼未輸入");
//            member.setSuccessful(false);
//            return member;
//        }
//        if (member.getMpic() == null) {
//            member.setMessage("請上傳圖片");
//            member.setSuccessful(false);
//            return member;
//        }
//
//
//        if (member.getMobile() == null) {
//            member.setMessage("請輸入手機號碼");
//            member.setSuccessful(false);
//            return member;
//        }
//
        if (member.getReferenceByEmail(member.getEmail()) != null) {
            member.setMessage("帳號重複");
            member.setSuccessful(false);
            return member;
        }
//        member.setVersta(0);
//        member = memberRepository.save(member);
        memberRepository.save(member);
        member.setMessage("註冊成功");
        member.setSuccessful(true);

        return member;
    }

    public void updateVersta(UpdateVerstaDTO updateVerstaDTO){
        memberRepository.updateVersta(updateVerstaDTO.getMembno(),updateVerstaDTO.getVersta());
    }



}

