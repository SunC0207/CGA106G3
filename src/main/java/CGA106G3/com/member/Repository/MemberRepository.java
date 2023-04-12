package CGA106G3.com.member.Repository;





import org.springframework.data.jpa.repository.JpaRepository;
import CGA106G3.com.member.Entity.Member;
import org.springframework.data.jpa.repository.Query;




public interface MemberRepository extends JpaRepository<Member,Integer>,MemberOpreation{




    @Query(value ="Select * from MEMBER where Email = :email and Mpw = :mpw",
            nativeQuery = true)
    Member selectForlogin(String email, String mpw);







}

