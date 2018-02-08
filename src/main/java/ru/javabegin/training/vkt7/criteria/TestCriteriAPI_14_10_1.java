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
public class TestCriteriAPI_14_10_1 {



    @Autowired
    @Qualifier("jpaCustomerService")
    private CustomerService customerService;

    public int i=0;

        public void test(){
        System.out.println("Criteria API!!!////////////////////////");



        List<Customer> customerList =  customerService.findCustomerLikeFirstName("Весн");

        System.out.println("");
        System.out.println("Listing Customer :");

        listContactsWithDetail(customerList);

     /*   for (Customer сustomer: customerList) {
            System.out.println(сustomer);
        }*/
        return ;
    }


    private  void listContactsWithDetail(List<Customer> customerList) {


       System.out.println("");
        System.out.println("Listing contacts with details:");
        System.out.println("Размер массива элементов  customerList:"+customerList.size() );

        for (Customer сustomer: customerList) {
            System.out.println(сustomer);
            System.out.println("сustomerid= "+сustomer.getFirstName()+" i= "+i);
            if (сustomer.getOperationSet() != null) {
                System.out.println("OperationSet not empty");
                System.out.println("Размер OperationSet  = "+сustomer.getOperationSet().size());

                for (Operation operation: сustomer.getOperationSet()) {
                    System.out.println(operation);
                    if (operation.getMeasurementsSet() != null){
                        System.out.println("MeasurementsSet() not empty");
                       // System.out.println("Размер MeasurementsSet()="+operation.getMeasurementsSet().size());
                       /* for (Measurements measurement: operation.getMeasurementsSet()) {
                            System.out.println(measurement);
                        }*/
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
}
