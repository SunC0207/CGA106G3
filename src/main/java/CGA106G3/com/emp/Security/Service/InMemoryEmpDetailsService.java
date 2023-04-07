//package CGA106G3.com.emp.Security.Service;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.List;
//
//public class InMemoryEmpDetailsService implements EmpDetailsService{
//    private final List<UserDetails> users;
//
//    public InMemoryEmpDetailsService(List<UserDetails> users) {
//        this.users = users;
//    }
//
//    @Override
//    public UserDetails loadEmpByEname(String ename) throws UsernameNotFoundException {
//        return users.stream()
//                .filter(u->u.getUsername().equals(ename))
//                .findFirst()
//                .orElseThrow(
//                        ()->new UsernameNotFoundException("User Not Found")
//                );
//    }
//}
