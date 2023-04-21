package CGA106G3.com.member.Repository;


import CGA106G3.com.emp.Entity.Per;
import org.springframework.data.jpa.repository.JpaRepository;
import CGA106G3.com.member.Entity.Member;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer>,MemberOpreation{

    //	Select * from MEMBER where mname =?
    Member findByMname(String mname);
    @Query(value ="Select * from MEMBER where Email = :email and Mpw = :mpw",
            nativeQuery = true)
    Member selectForlogin(String email, String mpw);

//    Member findVerstaByID(Integer membno);


}

