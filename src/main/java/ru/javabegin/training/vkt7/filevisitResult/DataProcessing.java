package ru.javabegin.training.vkt7.filevisitResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.CustomerServiceImpl;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Component
public class DataProcessing implements Serializable{

    @Autowired
    @Qualifier("jpaCustomerService")
CustomerService customerService;


    public void processing(Customer customer, List<Object> objectList){

        List<String> listHigh=(List<String>)objectList.get(0);
        List<String> listZaglavie=(List<String>)objectList.get(1);
        List<String> listData=(List<String>)objectList.get(2);
        for(int i=0; i<listHigh.size(); i++){
            System.out.println(i+" : "+listHigh.get(i));
        }
        System.out.println();
        for(int i=0; i<listData.size(); i++){
            System.out.println(i+" : "+listZaglavie.get(i)+" : "+listData.get(i));
        }


      /*  0 : Заводской
        1 : номер
        2 : 00050719
        3 : ВВОД
        4 : 1
        5 : СХЕМА
        6 : ПОДКЛЮЧЕНИЯ
        7 : 4
        8 : БД
        9 : 1
        10 : ФТ=0
        11 : Т3=2
        12 : КС=0x8E05
        13 : ПО
        14 : 2.7*/

        Operation operation = new Operation();
        operation.setTypeOperation("total_moth");
        operation.setServerVersion(String.valueOf(1));
        operation.setProgrammVersion(listHigh.get(14));
        operation.setShemaTv13Ff9(listHigh.get(7));
       // operation.setTp3Tv1(service_information.get(2));
       // operation.setT5Tv1(service_information.get(3));
        //operation.setShemaTv23Ff9(service_information.get(4));
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








        //measurementsList.forEach(p -> operation.addMeasurements(p));
        //daily_hashMap.get(ts).forEach(p -> operation.addMeasurements(p));
        measurementsList.forEach(p -> operation.addMeasurements(p));
        // List<Measurements> testlist = daily_hashMap.get(ts);
                        for (Measurements measurements :  measurementsList) {
                            System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

                        }

        customer = customerService.findById(121l);
        //Customer customer = customerService.findById(id);
        operation.setIdCustomer(customer.getId());
        operation.setCustomerName(customer.getFirstName());

        customer.addOperation(operation);
        customerService.save(customer);


        System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  МЕСЯЧНЫЕ произведена. ");















    }
}
