package CGA106G3.com.member.Repository;


import CGA106G3.com.emp.Entity.Per;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import CGA106G3.com.member.Entity.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Integer>,MemberOpreation{


//    List<Member> findMemberbyVersta(Integer versta);
    //	Select * from MEMBER where mname =?
    Member findByMname(String mname);
    @Query(value ="Select * from MEMBER where Email = :email and Mpw = :mpw",
            nativeQuery = true)
    Member selectForlogin(String email, String mpw);

//    Member findVerstaByID(Integer membno);

    @Query(value = "SELECT * FROM member WHERE email = :email",nativeQuery = true)
    Member findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE member SET ver_sta = :versta WHERE membno = :membno",nativeQuery = true)
    void updateVersta(Integer membno, Integer versta);
}

