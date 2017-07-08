package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.security.SecurityService;

import java.io.Serializable;

@Component
public class TestBean12 implements Serializable {


    @Autowired
    SecurityService securityService;



private String uname;

    public void test(){
        System.out.println("Spring Security ContextHolder");
        Object principal = securityService.getAllPrincipals();

      return;
    }


}
