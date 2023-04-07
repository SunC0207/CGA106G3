//package CGA106G3.com.emp.Security.Entity;
//
//import CGA106G3.com.emp.Entity.Emp;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//public class SecurityEmp implements UserDetails {
//
//    private final Emp emp;
//
//    public SecurityEmp(Emp emp) {
//        this.emp = emp;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return emp.getEmppw();
//    }
//
//    @Override
//    public String getUsername() {
//        return emp.getEname();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
