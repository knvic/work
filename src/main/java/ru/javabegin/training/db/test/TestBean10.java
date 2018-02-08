package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.Content;

import java.io.Serializable;
import java.util.List;

@Component
public class TestBean10 implements Serializable {

    @Autowired
    ContactService contactService;

    private Contact selcontact;

    public Contact getSelcontact() {
        return selcontact;
    }

    public void setSelcontact(Contact selcontact) {
        this.selcontact = selcontact;
    }



    public List<Content> test(){
        System.out.println("Criteria API!!!////////////////////////");

        List<Content> contacts = contactService.findByfirstname("Мари");

        System.out.println("");
        System.out.println("Listing contacts :");

        //listContactsWithDetail(contacts);

        for (Content contact: contacts) {
            System.out.println(contact);
        }
        return contacts;
    }


}
