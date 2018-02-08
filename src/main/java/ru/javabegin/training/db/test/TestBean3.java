package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.ContactTelDetail;
import ru.javabegin.training.db.Hobby;

import java.util.List;

@Component
public class TestBean3 {

    @Autowired
    ContactService contactService;


    public void test(){
        System.out.println("Criteria API!!!////////////////////////");
        List<Contact> contacts = contactService.findByCriteriaQuery3("Марина");

        System.out.println("");
        System.out.println("Listing contacts :");

        //listContactsWithDetail(contacts);

        for (Contact contact: contacts) {
            System.out.println(contact);
        }
    }


    private static void listContactsWithDetail(List<Contact> contacts) {
        System.out.println("");
        System.out.println("Listing contacts with details:");

        for (Contact contact: contacts) {
            System.out.println(contact);
            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail:
                        contact.getContactTelDetails()) {
                    System.out.println(contactTelDetail);
                }
            }

            if (contact.getHobbies() != null) {
                for (Hobby hobby: contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }

            System.out.println();
        }
    }


}
