package ru.javabegin.training.recoveryData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.recoveryData.filevisitResult.DataProcessingTotal;
import ru.javabegin.training.recoveryData.filevisitResult.GetPropertsList;
import ru.javabegin.training.recoveryData.filevisitResult.TestVisitResult;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RecoveryServiceVktImpl implements RecoveryService {

    @Autowired
    AuxiliaryService auxiliaryService;

    @Autowired
    CustomerService customerService;

    @Override
    public void Recovery_month(String customerName)  {

        String date=auxiliaryService.forUpdateMoth();

        TestVisitResult visitResult=new TestVisitResult();
        List<Object> objectList= null;
        try {
            objectList = visitResult.searchMothTxt(customerName,date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> listHigh=(List<String>)objectList.get(0);
        List<String> listZaglavie=(List<String>)objectList.get(1);
        List<String> listData=(List<String>)objectList.get(2);
        List<String> listHigh2=null;
        List<String> listZaglavie2=null;
        List<String> listData2=null;

        if (objectList.size() > 3) {
            listHigh2=(List<String>)objectList.get(3);
             listZaglavie2=(List<String>)objectList.get(4);
             listData2=(List<String>)objectList.get(5);
        }
        System.out.println("\n\n");
       /* listHigh.forEach((p)-> System.out.print(p+" "));
        System.out.println();
        listZaglavie.forEach((p)-> System.out.print(p+" "));
        System.out.println();
        listData.forEach((p)-> System.out.print(p+" "));
        System.out.println();
        listHigh2.forEach((p)-> System.out.print(p+" "));
        System.out.println();
        listZaglavie2.forEach((p)-> System.out.print(p+" "));
        System.out.println();
        listData2.forEach((p)-> System.out.print(p+" "));
*/


        Operation operation = new Operation();
        operation.setTypeOperation("total_moth");
        //operation.setServerVersion(String.valueOf(1));
        operation.setProgrammVersion(listHigh.get(14));
        operation.setShemaTv13Ff9(listHigh.get(7));
        // operation.setTp3Tv1(service_information.get(2));
        // operation.setT5Tv1(service_information.get(3));

        // operation.setTp3Tv2(service_information.get(5));
        // operation.setT5Tv2(service_information.get(6));
        operation.setIdentificator(listHigh.get(2));
        //operation.setNetNumber(service_information.get(8));
        // operation.setModel(service_information.get(10));
        // operation.setBeginHourDate(date_3ff6.get(0));
        // operation.setCurrentDate3Ff6(date_3ff6.get(1));
        //operation.setBeginDayDate(date_3ff6.get(2));
        //operation.setDateVkt3Ffb();
        // operation.setDateServer(timestamp_date_input);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();


        Timestamp ts=auxiliaryService.stringDate_to_TimeStamp_forUpdateMoth(listData.get(0));
        operation.setChronological(ts);
        operation.setShemaTv13Ecd(listHigh.get(7));
        //operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
        operation.setBaseNumber(listHigh.get(8));
        operation.setStatus("OK");
        operation.setError(String.valueOf(0));





        GetPropertsList getPropertsList = new GetPropertsList();
        List<Properts> propertsList= getPropertsList.getList();
        List<Measurements> measurementsList=new ArrayList<>();


        if(listHigh.get(4).equals("1")){
            for(int i=0; i<listZaglavie.size(); i++){
                for (Properts prop:propertsList){
                    if ((listZaglavie.get(i)+" Тв1").equals(prop.getText())){
                        measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), listData.get(i),"C0","OPC_QUALITY_GOOD 0xC0","00"));
                    }
                }

            }

        }
        if(objectList.size()>3){
            operation.setShemaTv23Ff9(listHigh2.get(7));


            if(listHigh2.get(4).equals("2")){
                for(int i=0; i<listZaglavie2.size(); i++){
                    for (Properts prop:propertsList){
                        System.out.println("prop = "+prop.getText()+" "+listZaglavie2.get(i)+" Тв2");
                        if ((listZaglavie2.get(i)+" Тв2").equals(prop.getText())){
                            System.out.println(listZaglavie2.get(i)+" Тв2" +" после совпадения с "+ prop.getText());
                            System.out.println("prop.getId() ="+prop.getId());
                            System.out.println("prop.getName() ="+prop.getName());


                            try {
                                System.out.println("listData2.get(i) ="+listData2.get(i));
                                measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), listData2.get(i),"C0","OPC_QUALITY_GOOD 0xC0","00"));

                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }

                }

            }




        }







        //measurementsList.forEach(p -> operation.addMeasurements(p));
        //daily_hashMap.get(ts).forEach(p -> operation.addMeasurements(p));
        measurementsList.forEach(p -> operation.addMeasurements(p));
        // List<Measurements> testlist = daily_hashMap.get(ts);
        for (Measurements measurements :  measurementsList) {
            System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

        }

        //customer = customerService.findById(168l);
        //Customer customer = customerService.findById(id);

        Customer customer=customerService.findCustomerLikeFirstName(customerName).get(0);
        operation.setIdCustomer(customer.getId());
        operation.setCustomerName(customer.getFirstName());

        customer.addOperation(operation);
        customerService.save(customer);

        if (objectList.size()>3){
            System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  ТВ1 и ТВ2 МЕСЯЧНЫЕ произведена. ");}
        else {
            System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  ТВ1  МЕСЯЧНЫЕ произведена. ");
        }







    }
}
