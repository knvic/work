package ru.javabegin.training.vkt7.object;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.objects.SearchCriteria;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.util.List;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_t {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Autowired
    private SearchCriteria searchCriteria;

    private Customer selcustomer;

    private List<Customer> customers;

    public List<Customer> getCustomers(){

        if (customers==null){
            customers = customerService.findAll();
        }
        return customers;
    }
    public List<Customer> findAllCustomers(){

       customers = customerService.findAll();

        return customers;
    }

/*    public void searchContactByLetter() {
        String letter = searchCriteria.getLetter();
        contacts = contactService.findByCriteriaQuery4(letter);
    }

    public void searchContactByHobby() {
        Hobby hobby = searchCriteria.getHobby();
        contacts = contactService.findByCriteriaQuery10(hobby);
    }*/

/*

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
*/




 /*   public byte[] getContent(long id){

        return (byte[])contactService.getContent(id);
    }*/

}
