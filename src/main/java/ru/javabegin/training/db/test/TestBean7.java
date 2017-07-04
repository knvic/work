package ru.javabegin.training.db.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.*;
import ru.javabegin.training.db.projections.Projection1;

import java.util.List;

@Component
public class TestBean7 {

    @Autowired
    ContactService contactService;


    public void test(){
        System.out.println("Criteria API!!!////////////////////////");

        List<Projection1> contacts = contactService.findByCriteriaQuery7("Марина","Соломинцева");

        System.out.println("");
        System.out.println("Listing contacts :");

        //listContactsWithDetail(contacts);

        for (Projection1 contact: contacts) {
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
