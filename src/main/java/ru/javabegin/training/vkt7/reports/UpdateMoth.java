package ru.javabegin.training.vkt7.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.filevisitResult.DataProcessing;
import ru.javabegin.training.vkt7.filevisitResult.TestVisitResult;
import ru.javabegin.training.vkt7.reports.DataCustomer;
import ru.javabegin.training.vkt7.reports.DataCustomerList;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateMoth implements Serializable {

    @Autowired
    AuxiliaryService auxiliaryService;
    @Autowired
    DataProcessing processing;

public void update() throws IOException {
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getRootLogger();
    List<DataCustomer> list= DataCustomerList.dataCustomerList;
    System.out.println("размер list= "+list.size());
    List<Customer> customerList=new ArrayList<>();

          /*  list.stream()
                    .filter((p)->p.getStatus().contains("OK")).collect(Collectors.toList())*/

    for(DataCustomer dk:list){
        if(dk.getDaily_all().equals("OK")&&dk.getMoth().equals("Отсутствуют")){
            customerList.add(dk.getCustomer());
            System.out.println(dk.getCustomer().getFirstName());

        }
    }

    System.out.println("Размер найденных= "+customerList.size());

    //String customerName="Долина нарзанов тп-1";
    String customerName="Елзарова Атриумф";

    String date=auxiliaryService.forUpdateMoth();

    TestVisitResult visitResult=new TestVisitResult();
    List<Object> objectList= visitResult.searchMothTxt(customerName,date);
    List<String> listHigh=(List<String>)objectList.get(0);
    List<String> listZaglavie=(List<String>)objectList.get(1);
    List<String> listData=(List<String>)objectList.get(2);
   // DataProcessing processing=new DataProcessing();
    processing.processing(customerList.get(0), objectList);





}

}
