package CGA106G3.com.member.Repository;

import CGA106G3.com.member.Service.MemberService;
import org.springframework.data.jpa.repository.JpaRepository;
import CGA106G3.com.member.Entity.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Integer>{


}
