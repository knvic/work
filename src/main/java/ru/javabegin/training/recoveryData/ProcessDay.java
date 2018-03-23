package ru.javabegin.training.recoveryData;

import ru.javabegin.training.recoveryData.filevisitResult.GetPropertsList;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ProcessDay {

    public void processDayTv1(Customer customer, CustomerService customerService, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer, List<String> list) {

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
        operation.setTypeOperation("daily");
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


        System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  ТВ1 СУТОЧНЫЕ произведена. ");





    }
    public void processDayTv2(Customer customer, CustomerService customerService, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer, List<String> list) {




       List<Operation> operationList=customerService.findOperation_daily(customer.getId(),date,"daily","OK");

        System.out.println("размер массива = "+operationList.size());

        Operation operation = operationList.get(0);
        //operation.setTypeOperation("daily");

        operation.setShemaTv23Ff9(info.get(7));


        GetPropertsList getPropertsList = new GetPropertsList();
        List<Properts> propertsList= getPropertsList.getList();
        List<Measurements> measurementsList=new ArrayList<>(operation.getMeasurementsSet());

        boolean save = true;
        if(info.get(4).equals("2")){
            for(int i=0; i<naimenovaniya.size(); i++){
                for (Properts prop:propertsList){
                    if ((naimenovaniya.get(i)+" Тв2").equals(prop.getText())){

                        System.out.println("добавляем ТВ2. Проверяем есть ли значения ТВ2 в списке.");
                        System.out.println("naimenovaniya.get(i)+ Тв2 = "+naimenovaniya.get(i)+" Тв2");
                        System.out.println("prop.getText() = "+prop.getText());
                        String qgtv2=naimenovaniya.get(i)+" Тв2";
                        if (qgtv2.contains("Qг Тв2")){
                            System.out.println("Параметр найден");
                        }


                        Optional<Measurements> m1= null;
                        boolean exist=measurementsList.stream().filter(p->p.getText().contains(prop.getText())).findFirst().isPresent();
                        boolean exist1=measurementsList.stream().filter(p->p.getText().contains(qgtv2)).findFirst().isPresent();

                        if(exist){
                            System.out.println("Проверка наличия = измерение есть "+prop.getText());
                        }else{
                            System.out.println("Проверка наличия = НЕТ"+prop.getText());
                        }
                        if(exist1){
                            System.out.println("Проверка наличия = измерение есть "+qgtv2);
                        }else{
                            System.out.println("Проверка наличия = НЕТ"+qgtv2);
                        }

                        if(!exist) {
                            if (prop.getText().contains("Qо Тв2")) {

                                try {
                                    BigDecimal test = new BigDecimal(list.get(i));
                                    measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), list.get(i), "C0", "OPC_QUALITY_GOOD 0xC0", "00"));
                                    System.out.println("Qo преобразыется  :" + test.toString());
                                } catch (Exception e) {
                                    System.out.println("Qo Не преобразуется. Запись не производится!!  ");
                                    save = false;
                                }

                            } else {

                                try {
                                    BigDecimal test = new BigDecimal(list.get(i));
                                    measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), list.get(i), "C0", "OPC_QUALITY_GOOD 0xC0", "00"));
                                } catch (Exception e) {
                                    System.out.println(prop.getText()+" Не преобразуется. Запись не производится!!  ");
                                }
                            }

                        }



                        if(exist) {
                            m1 = measurementsList.stream().filter(p->p.getText().contains(prop.getText())).findFirst();
                            if (prop.getText().contains("Qо Тв2")) {

                                try {
                                    BigDecimal test = new BigDecimal(list.get(i));
                                    //measurementsList.add(new Measurements(prop.getId(), prop.getName(), prop.getText(), list.get(i), "C0", "OPC_QUALITY_GOOD 0xC0", "00"));

                                    m1.get().setMeasurInt(Integer.parseInt(test.toString()));
                                    m1.get().setMeasurText(test.toString());

                                    System.out.println("Qo преобразыется  :" + test.toString());
                                } catch (Exception e) {
                                    System.out.println("Qo Не преобразуется. Запись не производится!!  ");
                                    m1.get().setMeasurInt(null);
                                    m1.get().setMeasurText(null);


                                }

                            } else {

                                try {
                                    BigDecimal test = new BigDecimal(list.get(i));
                                    m1.get().setMeasurInt(Integer.parseInt(test.toString()));
                                    m1.get().setMeasurText(test.toString());

                                } catch (Exception e) {

                                    m1.get().setMeasurInt(null);
                                    m1.get().setMeasurText(null);
                                }
                            }

                        }


                    }
                }

            }

        }





        measurementsList.forEach(p -> operation.addMeasurements(p));

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


            System.out.println("Запись ВОССТАНОВЛЕННЫХ значений ТВ2 СУТОЧНЫЕ произведена. ");





        /*if (save) {
            measurementsList.forEach(p -> operation.addMeasurements(p));

            for (Measurements measurements : measurementsList) {
                System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

            }

            long id = customer.getId();
            operation.setIdCustomer(id);

           // operation.setCustomerName(customer.getFirstName());

            customer = customerService.findById(id);
            customer.addOperation(operation);
            customerService.save(customer);


            System.out.println("Запись ВОССТАНОВЛЕННЫХ значений ТВ2 СУТОЧНЫЕ произведена. ");

        }
*/


    }

}
