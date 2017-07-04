package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.ContactTelDetail;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.db.projections.Projection1;

import java.util.List;

@Component
public class TestBean8 {

    @Autowired
    ContactService contactService;


    public List<Hobby> test(){
        System.out.println("Criteria API!!!////////////////////////");

        List<Hobby> contacts = contactService.findByCriteriaQuery8();

        System.out.println("");
        System.out.println("Listing contacts :");

        //listContactsWithDetail(contacts);

        for (Hobby contact: contacts) {
            System.out.println(contact);
        }
        return contacts;
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
