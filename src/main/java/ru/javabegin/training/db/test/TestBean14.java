package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;
import ru.javabegin.training.security.AddUserSec;
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

    public String test(AddUserSec addUserSec) {
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
        UserDetails details = new User(addUserSec.getName(), addUserSec.getPass(), true, true, true, true, set);
        userDetailsService.createUser(details);
        return "succes";
    }
}








