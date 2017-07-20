package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.javabegin.training.security.SecurityService;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestBean14 implements Serializable {



    @Autowired
    private UserDetailsManager userDetailsService;
   /* @Autowired
    private PasswordEncoder passwordEncoder;
*/

    public void test() {
        GrantedAuthority auth = new GrantedAuthority() {
            private static final long serialVersionUID = 1L;

            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        set.add(auth);

//        UserDetails details = new User("root",  passwordEncoder.encodePassword("root", new Object()), true, true, true, true, set);
        UserDetails details = new User("root",  "root", true, true, true, true, set);
        userDetailsService.createUser(details);
    }
}






   /* public void test(){
        System.out.println("Spring Security ContextHolder");
        Object principal = securityService.getAllPrincipals();

      return;*/




