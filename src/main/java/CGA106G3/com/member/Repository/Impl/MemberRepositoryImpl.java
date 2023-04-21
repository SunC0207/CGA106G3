package CGA106G3.com.member.Repository.Impl;

import CGA106G3.com.member.Entity.Member;
import CGA106G3.com.member.Repository.MemberOpreation;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

public class MemberRepositoryImpl implements MemberOpreation {

    @PersistenceContext
    private Session session;

@Transactional
    public int update(Member member) {
        final StringBuilder hql = new StringBuilder()
                .append("UPDATE Member Set");
        final String password = member.getMpw();
        if(password!=null&&!password.isEmpty()){}{
            hql.append("password=:password,");
    }
        final byte[] img = member.getMpic();
        if(img!=null && img.length!=0){
            hql.append("img = :img,");
        }
        hql.append("mname=:mname,")
                .append("mobile=:mobile,")
                .append("email = :email")
                .append(" WHERE membno=:membno");
    Query query = session.createQuery(hql.toString())
            .setParameter("membno",member.getMembno())
            .setParameter("mname",member.getMname())
            .setParameter("mobile",member.getMobile())
            .setParameter("email",member.getEmail());


    if(password!=null&&!password.isEmpty()){
        query.setParameter("member", member.getMpw());
    }
    if(img!=null && img.length!=0){
        query.setParameter("mpic",img);

    }
    return query.executeUpdate();
    }
}
