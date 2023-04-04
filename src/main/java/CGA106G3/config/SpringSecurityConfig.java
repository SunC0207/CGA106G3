//package CGA106G3.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class SpringSecurityConfig {
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        var user = User.withUsername("SungC")
//                .password("123456")
//                .authorities("read")
//                .build();
//
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//
//}
