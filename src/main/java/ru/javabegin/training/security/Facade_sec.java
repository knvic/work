package ru.javabegin.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.Contact;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.Hobby;
import ru.javabegin.training.objects.SearchCriteria;

import java.util.List;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_sec {

    @Autowired
    ContactService contactService;

    @Autowired
    private SearchCriteria searchCriteria;

    private Contact selcontact;

    private List<Contact> contacts;

    public List<Contact> getContacts(){

        if (contacts==null){
            contacts = contactService.findByCriteriaQuery9();
        }
        return contacts;
    }

    public void searchContactByLetter() {
        String letter = searchCriteria.getLetter();
        contacts = contactService.findByCriteriaQuery4(letter);
        }

    public void searchContactByHobby() {
        Hobby hobby = searchCriteria.getHobby();
        contacts = contactService.findByCriteriaQuery10(hobby);
    }


    public void searchContactByText() {
        String text = searchCriteria.getText();
        switch (searchCriteria.getSearchType()){
            case TITLE:
                contacts = contactService.findByCriteriaQuery11(text);
                break;
            case AUTHOR:
                contacts = contactService.findByCriteriaQuery12(text);
                break;
        }



    }

    public byte[] getContent(long id){

        return (byte[])contactService.getContent(id);
    }

}
