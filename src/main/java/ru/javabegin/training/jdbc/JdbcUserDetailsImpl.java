package ru.javabegin.training.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Николай on 21.07.2017.
 */
public class JdbcUserDetailsImpl {


    @Autowired
    private UserDetailsManager userDetailsService;
   /* @Autowired
    private PasswordEncoder passwordEncoder;
*/




    public void test() {
        /*GrantedAuthority auth = new GrantedAuthority() {
            private static final long serialVersionUID = 1L;

            public String getAuthority() {
                return "ROLE_USER,ROLE_ADMIN";
            }
        };*/

        GrantedAuthority auth = new GrantedAuthority() {
            private static final long serialVersionUID = 1L;

            public String getAuthority() {
                return "ROLE_USER";
            }
        };
        GrantedAuthority auth1 = new GrantedAuthority() {
            private static final long serialVersionUID = 1L;

            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        };




        Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
        set.add(auth);
        set.add(auth1);



//        UserDetails details = new User("root",  passwordEncoder.encodePassword("root", new Object()), true, true, true, true, set);
        UserDetails details = new User("root",  "root", true, true, true, true, set);
        userDetailsService.createUser(details);
    }
}
