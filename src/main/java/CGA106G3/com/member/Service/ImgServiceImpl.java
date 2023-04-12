package CGA106G3.com.member.Service;

import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements  ImgService {
    private MemberRepository memberRepository;

    public ImgServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
@Override
    public Member findMpicById(Integer membno){
        return memberRepository.findById(membno).orElseThrow(()-> new RuntimeException(+membno+"圖片沒找到"));
    }
}
