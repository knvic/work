package ru.javabegin.training.vkt7.object_data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.object.SearchCriteria_cust;
import ru.javabegin.training.vkt7.object.SearchCriteria_oper;
import ru.javabegin.training.vkt7.object_modem.SearchCriteria_modem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Николай on 14.06.2017.
 */
@Component
@Scope("singleton")
public class Facade_data {

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Qualifier("jpaOperationService")
    @Autowired
    OperationService operationService;

   /* @Autowired
  */  private SearchCriteria_cust searchCriteria_cust;
    @Autowired
    private SearchCriteria_modem searchCriteria_oper;

    @Autowired
    private SearchCriteria_data searchCriteria_data;
    @Autowired
    private SelectionData selectionData;

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

/////// выборка по операциям
    public List<Operation> getOperations(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id = searchCriteria_data.getId();

        operations= customerService.getOperationsByCustomerId(id);
        return operations;
    }





    public List<Operation> getAllOperationsByCustomer(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id_Customer = searchCriteria_data.getCustomer().getId();
        //Long id_Customer = selectionData.getCust().getId();

        //operations= customerService.getOperationsByCustomerId(id);


        operations=operationService.findOperationByIdCustomer(id_Customer);
        return operations;
    }



    /////////////Measurements ////////////////
    public List<Measurements> getMeasurementsList() {


       /* if (measurementsList==null){
            Long id=searchCriteria_data.getOperation().getId();
            System.out.println("id="+id);
            measurementsList = operationService.getMeasurementsByOperationId(id);
        }*/
        Long id=searchCriteria_data.getOperation().getId();
        System.out.println("id="+id);
        measurementsList = operationService.getMeasurementsByOperationId(id);



        return measurementsList;
    }

    public void setMeasurementsList(List<Measurements> measurementsList) {
        this.measurementsList = measurementsList;
    }




    ////////////////////////////////////////

   //////// выборка по измерениям
    public List<Measurements> getmeasurementsList(){

       /* if (operations==null){
            operations = customerService.findAll();
        }*/

        Long id = searchCriteria_data.getId();

        measurementsList= operationService.getMeasurementsByOperationId(id);
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

    public void deleteCustomer(){
        Customer c= searchCriteria_cust.getCustomer();

            if (!(c.getFirstName()).equals("")) {
                customerService.delete(c);
                searchCriteria_cust.setCustomer(null);
            }

    }
    public List<Customer> findAllCustomers1(Customer customer){

        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);

        return customers;
    }








 /*   public List<Customer> searchCustomerById() {
        customer= searchCriteria_modem.getCustomer();

      //  Long id = c.getId();
        //customer = customerService.findById(id);
      //  customer = customerService.findById(id);
        customers=null;
        customers=new ArrayList<>();
        customers.add(customer);
        return customers;
    }*/


    public Customer searchCustomer_1_ById() {
        customer= searchCriteria_cust.getCustomer();

        //  Long id = c.getId();
        //customer = customerService.findById(id);
        //  customer = customerService.findById(id);

        return customer;
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
