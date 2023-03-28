package CGA106G3.com.member.Service;

import jakarta.annotation.Resource;
import org.modelmapper.ModelMapper;
import CGA106G3.com.member.DTO.MemberDTO;
import CGA106G3.com.member.Entity.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CGA106G3.com.member.Repository.MemberRepository;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    @Resource
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
    public Member updateMember(Member member){

    return memberRepository.save(member);
    }


@Override
    public List<MemberDTO> getAllMember(){
        return memberRepository.findAll().stream()
                .map(this::EntityToDTO).
                collect(Collectors.toList());
    }

    @Override
    public Member findMemberById(Integer membno) {
        return  memberRepository.findById(membno).orElse(null);
    }

    private MemberDTO EntityToDTO(Member member){

        MemberDTO memberDto = new MemberDTO();
        modelMapper.map(member, MemberDTO.class);
        return memberDto;
    }










}

