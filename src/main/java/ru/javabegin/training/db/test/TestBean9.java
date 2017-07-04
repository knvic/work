package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.ContactTelDetail;
import ru.javabegin.training.db.Hobby;

import java.io.Serializable;
import java.util.List;

@Component
public class TestBean9 implements Serializable {

    @Autowired
    ContactService contactService;

    private Contact selcontact;

    public Contact getSelcontact() {
        return selcontact;
    }

    public void setSelcontact(Contact selcontact) {
        this.selcontact = selcontact;
    }



    public List<Contact> test(){
        System.out.println("Criteria API!!!////////////////////////");

        List<Contact> contacts = contactService.findByCriteriaQuery9();

        System.out.println("");
        System.out.println("Listing contacts :");

        //listContactsWithDetail(contacts);

        for (Contact contact: contacts) {
            System.out.println(contact);
        }
        return contacts;
    }


}
