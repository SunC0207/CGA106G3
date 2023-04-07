//package CGA106G3.config;
//
//import CGA106G3.com.emp.Security.Service.EmpDetailsService;
//import CGA106G3.com.emp.Security.Service.InMemoryEmpDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.List;
//
//@Configuration
//public class WebAuthorizationConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(
//                        (authz) -> authz
//                                .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//    //指定所有請求都須經過驗證
//
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user = User
////                .withUsername("SungC")
////                .password("123456")
////                .roles("admin")
////                .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//    //建立新的使用者
//
//    public EmpDetailsService empDetailsService(){
//        UserDetails u =new User("SungC","654321",);
//        List<UserDetails> users = List.of(u);
//        return new InMemoryEmpDetailsService(users);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//    //設置密碼編碼方式
//
//}
