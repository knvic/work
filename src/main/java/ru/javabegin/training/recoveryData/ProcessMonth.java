package ru.javabegin.training.recoveryData;

import ru.javabegin.training.recoveryData.filevisitResult.GetPropertsList;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProcessMonth {

    public void processMonthTv1(Customer customer, CustomerService customerService, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer, List<String> list) {


        System.out.println("вход в подпрограмму");
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
        operation.setProgrammVersion(info.get(14));
        operation.setShemaTv13Ff9(info.get(7));
        // operation.setTp3Tv1(service_information.get(2));
        // operation.setT5Tv1(service_information.get(3));
        //operation.setShemaTv23Ff9(service_information.get(4));
        // operation.setTp3Tv2(service_information.get(5));
        // operation.setT5Tv2(service_information.get(6));
        operation.setIdentificator(info.get(2));
        //operation.setNetNumber(service_information.get(8));
        // operation.setModel(service_information.get(10));
        // operation.setBeginHourDate(date_3ff6.get(0));
        // operation.setCurrentDate3Ff6(date_3ff6.get(1));
        //operation.setBeginDayDate(date_3ff6.get(2));
        //operation.setDateVkt3Ffb();
        // operation.setDateServer(timestamp_date_input);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();



        operation.setChronological(date);
        operation.setShemaTv13Ecd(info.get(7));
        //operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
        operation.setBaseNumber(info.get(9));
        operation.setStatus("OK");
        operation.setError(String.valueOf(0));

        GetPropertsList getPropertsList = new GetPropertsList();
        List<Properts> propertsList= getPropertsList.getList();
        List<Measurements> measurementsList=new ArrayList<>();


        if(info.get(4).equals("1")){
            for(int i=0; i<naimenovaniya.size(); i++){
                for (Properts prop:propertsList){
                    if ((naimenovaniya.get(i)+" Тв1").equals(prop.getText())){
                        measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), list.get(i),"C0","OPC_QUALITY_GOOD 0xC0","00"));
                    }
                }

            }

        }



        measurementsList.forEach(p -> operation.addMeasurements(p));

        for (Measurements measurements :  measurementsList) {
            System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

        }

        long id=customer.getId();
        operation.setIdCustomer(id);

        operation.setCustomerName(customer.getFirstName());

        customer=customerService.findById(id);
        customer.addOperation(operation);
        customerService.save(customer);


        System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  МЕСЯЧНЫЕ произведена. ");





    }


    public void processMonthTv2(Customer customer, CustomerService customerService, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer, List<String> list) {


        System.out.println("вход в подпрограмму");
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

       // Operation operation = new Operation();
        List<Operation> customerList=customerService.findOperation_daily(customer.getId(),date,"total_moth","OK");
        Operation operation=customerList.get(0);


        operation.setShemaTv23Ff9(info.get(7));
        // operation.setTp3Tv1(service_information.get(2));
        // operation.setT5Tv1(service_information.get(3));
        //operation.setShemaTv23Ff9(service_information.get(4));
        // operation.setTp3Tv2(service_information.get(5));
        // operation.setT5Tv2(service_information.get(6));

        //operation.setNetNumber(service_information.get(8));
        // operation.setModel(service_information.get(10));
        // operation.setBeginHourDate(date_3ff6.get(0));
        // operation.setCurrentDate3Ff6(date_3ff6.get(1));
        //operation.setBeginDayDate(date_3ff6.get(2));
        //operation.setDateVkt3Ffb();
        // operation.setDateServer(timestamp_date_input);






        //operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
        operation.setBaseNumber(info.get(9));



        GetPropertsList getPropertsList = new GetPropertsList();
        List<Properts> propertsList= getPropertsList.getList();
        List<Measurements> measurementsList=new ArrayList<>();


        if(info.get(4).equals("2")){
            for(int i=0; i<naimenovaniya.size(); i++){
                for (Properts prop:propertsList){
                    if ((naimenovaniya.get(i)+" Тв2").equals(prop.getText())){
                        measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), list.get(i),"C0","OPC_QUALITY_GOOD 0xC0","00"));
                    }
                }

            }

        }



        measurementsList.forEach(p -> operation.addMeasurements(p));

        for (Measurements measurements :  measurementsList) {
            System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

        }



        final   long id = customer.getId();
        //operation.setIdCustomer(id);
        final long oId=operation.getId();
        System.out.println("ID Operation ="+oId);

        // operation.setCustomerName(customer.getFirstName());

        customer = customerService.findById(id);

        Operation newoper=new Operation();
        newoper=operation;

        Set<Operation> os=customer.getOperationSet();
        Operation operDel=null;
        for (Operation o:os) {
            System.out.println("СРАВНИВАЕМ Id измерения из SET = "+o.getId()+" ID над которым работали - "+oId);

            if (o.getId()==oId){
                System.out.println(" ============================== >>>>>>>>>>>>>>>>>>>>oId ="+oId);
                operDel=o;
            }
        }
        os.remove(operDel);






        customer.addOperation(newoper);
        customerService.save(customer);



        /*long id=customer.getId();

        customer=customerService.findById(id);
        customer.addOperation(operation);
        customerService.save(customer);*/


        System.out.println("Запись ВОССТАНОВЛЕННЫХ значений ТВ2 МЕСЯЧНЫЕ произведена. ");





    }

}
