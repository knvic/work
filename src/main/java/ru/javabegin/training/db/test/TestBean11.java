package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.Content;

import java.io.Serializable;
import java.util.List;

@Component
public class TestBean11 implements Serializable {

    @Autowired
    ContactService contactService;

    private Contact selcontact;

    public Contact getSelcontact() {
        return selcontact;
    }

    public void setSelcontact(Contact selcontact) {
        this.selcontact = selcontact;
    }

private String uname;

    public String test(){
        System.out.println("Spring Security ContextHolder");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            uname = ((UserDetails)principal).getUsername();
        } else {
             uname = principal.toString();
        }

        System.out.println("");
        System.out.println("Username :" );
        return uname;

    }


}
