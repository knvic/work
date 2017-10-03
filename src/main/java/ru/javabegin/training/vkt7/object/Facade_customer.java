package ru.javabegin.training.vkt7.object;

import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.objects.SearchCriteria;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_customer {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Qualifier("jpaOperationService")
    @Autowired
    OperationService operationService;

    @Autowired
    private SearchCriteria_cust searchCriteria_cust;

    @Autowired
    private SearchCriteria_oper searchCriteria_oper;

    private Customer selcustomer;

    private Customer customer;



    private List<Customer> customers;
    private List<Operation> operations;
    private List<Measurements> measurementsList;



    public List<Customer> getCustomers(){

        if (customers==null){
            customers = customerService.findAll();
        }
        return customers;
    }


    public List<Operation> getOperations(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id = searchCriteria_cust.getId();

        operations= customerService.getOperationsByCustomerId(id);
        return operations;
    }
    public List<Measurements> getmeasurementsList(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id = searchCriteria_oper.getId();

        measurementsList= operationService.getMeasurementsByOperatoinId(id);
        return measurementsList;
    }



    public List<Customer> findAllCustomers(){

       customers = customerService.findAll();

        return customers;
    }
    public void saveCustomer(){
        Customer c= searchCriteria_cust.getCustomer();
        if (!(c.getFirstName()).equals("")) {
            customerService.save(c);
        }


    }
    public List<Customer> findAllCustomers1(Customer customer){

        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);

        return customers;
    }








    public List<Customer> searchCustomerById() {
        customer= searchCriteria_cust.getCustomer();

      //  Long id = c.getId();
        //customer = customerService.findById(id);
      //  customer = customerService.findById(id);
        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);
        return customers;
    }

    public List<Operation> findOperationById(){

        Long id = searchCriteria_cust.getId();

        operations= customerService.findOperationByIdCustomer(id);
        return operations;

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
