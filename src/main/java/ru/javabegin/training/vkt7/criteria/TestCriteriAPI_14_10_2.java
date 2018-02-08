package ru.javabegin.training.vkt7.criteria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.util.List;

/**
 * Created by Николай on 14.10.2017.
 */
@Component
public class TestCriteriAPI_14_10_2 {



    @Autowired
    @Qualifier("jpaCustomerService")
    private CustomerService customerService;

    public int i=0;

        public void test(){
        System.out.println("Criteria API!!!////////////////////////");


            List<Operation> operationList= customerService.findOperationByIdCustomer(36l);
       // List<Customer> customerList =  customerService.findCustomerLikeFirstName("Весн");

        System.out.println("");
        System.out.println("Listing Customer :");

        listContactsWithDetail(operationList);

     /*   for (Customer сustomer: customerList) {
            System.out.println(сustomer);
        }*/
        return ;
    }


    private  void listContactsWithDetail(List<Operation> operationList) {


       System.out.println("");
        System.out.println("Listing operation with details:");
        System.out.println("Размер массива элементов  customerList:"+operationList.size() );

        for (Operation operation: operationList) {
            System.out.println(operation);
            System.out.println("operation= "+operation.getCustomer()+" i= "+i);
            if (operation.getMeasurementsSet() != null) {
                System.out.println("MeasurementsSet() not empty");
          //      System.out.println("Размер MeasurementsSet()  = "+operation.getMeasurementsSet().size());

                for (Measurements measurement: operation.getMeasurementsSet()) {
                    System.out.println(measurement);
                  }
                }
            }
            System.out.println("OperationSet empty!!");
            /*if (contact.getHobbies() != null) {

                for (Hobby hobby: contact.getHobbies()) {
                    System.out.println(hobby);
                }
            }*/



            System.out.println();
        }
    }

