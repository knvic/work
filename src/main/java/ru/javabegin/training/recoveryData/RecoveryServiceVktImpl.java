package ru.javabegin.training.recoveryData;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.recoveryData.auxrecovery.AuxRecovery;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

@Component
public class RecoveryServiceVktImpl implements RecoveryService {

    @Autowired
    AuxiliaryService auxiliaryService;

    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;

    @Autowired
    AuxRecovery auxRecovery;



    @Override
    public String recoveryDay(String name) throws IOException {
        Logger logger = Logger.getRootLogger();

        ExecutorService serviceVKT1 = Executors.newFixedThreadPool(2);
        Customer customer=null;
        Long customerId=null;
        FileVisitorCommon fileVisitorCommon=new FileVisitorCommon();

        List<String> lines= fileVisitorCommon.searchMothTxt(name, "28/02/18 24:00");
        if (lines.size()>0){


            /*List<Customer> customers=customerService.findCustomerLikeFirstName(name);
              customer=customers.get(0);
              customerId=customers.get(0).getId();*/

             customer=customerService.findByName(name);
            customerId=customer.getId();
        }

        lines.forEach(p->System.out.println(p));

        String dannie="";
        String naim="";
        String edIzm="";
        String search="";
        String dannie2="";
        String naim2="";
        String edIzm2="";
        String search2="";

        int count_d =1;
        int count_n =1;
        int count_s =1;
        int count_e =1;
        int count_month =1;


        List<String> info1=null;
        List<String> info2=null;
        List<String> naimenovaniya1=null;
        List<String> naimenovaniya2=null;
        List<String> edIzmer1=null;
        List<String> edIzmer2=null;


        String regularExpression="\\d{2}/\\d{2}/\\d{2}\\z";
        String regularExpression1="\\d{2}/\\d{2}/\\d{2}24:00";

boolean tv2=false;
        for (String line:lines) {


                if (line.contains("Заводской номер")) {
                    System.out.println("Найдена строка информации" + line);
                    if (count_d == 1) {
                        dannie = line;
                         info1=info(dannie);

                    }
                    if (count_d == 2) {
                        dannie2 = line;
                        info2=info(dannie2);
                        tv2=true;
                    }
                    count_d++;
                }
                if (line.contains("Дата")) {
                    System.out.println("Найдена строка наименований" + line);


                    if (count_n == 1) {
                        naim = line;
                        naimenovaniya1=naimenovaniya(naim);
                    }

                    if (count_n ==2){
                        naim = line;
                        naimenovaniya1=naimenovaniya(naim);
                    }

                    if (count_n == 3) {
                        naim2 = line;
                        naimenovaniya2=naimenovaniya(naim2);
                    }


                    if (count_n == 4) {
                        naim2 = line;
                        naimenovaniya2=naimenovaniya(naim2);
                    }
                    count_n++;
                }
            if (line.contains(" м3 ")) {
                System.out.println("Найдена строка единиц измерений" + line);


                if (count_e == 1) {
                    edIzm = line;
                    edIzmer1=edIzmer(edIzm);
                }


                if (count_e == 2) {
                    edIzm = line;
                    edIzmer1=edIzmer(edIzm);
                }

                if (count_e == 3) {
                    edIzm2 = line;

                    edIzmer2=edIzmer(edIzm2);
                }


                if (count_e == 4) {
                    edIzm2 = line;

                    edIzmer2=edIzmer(edIzm2);
                }
                count_e++;
            }


            line=line.replace(" ","");
            List<String> list = new ArrayList<>(Arrays.asList(line.split("\\|")));
            for (String s:list) {

                if (Pattern.compile(regularExpression).matcher(s).find()&&!tv2) {
                    System.out.println("найдено day = " + s);
                    List<Operation> customerList=customerService.findOperation_daily(customerId,auxRecovery.stringDate_to_TimeStamp_forDay(s),"daily","OK");

                    if (customerList.size()==0){

                            System.out.println("ВРЕМЯ ЖАТВЫ TV1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                        ProcessDay processDay=new ProcessDay();

                        List<String> finalInfo = info1;
                        Customer finalCustomer = customer;
                        List<String> finalNaimenovaniya = naimenovaniya1;
                        List<String> finalEdIzmer = edIzmer1;

                        Callable taskTv1 = () -> {
                            System.out.println("работает поток "+ Thread.currentThread().getName());
                            processDay.processDayTv1(finalCustomer,customerService, auxRecovery.stringDate_to_TimeStamp_forDay(s) , finalInfo, finalNaimenovaniya, finalEdIzmer,list);
                            return "123";
                        };
                            Future<String> future1 = serviceVKT1.submit(taskTv1);
                            System.out.println(customer.getFirstName()+" Данные ТВ1 за " +s +" записываются");
                            logger.info(customer.getFirstName()+" Данные ТВ1 за " +s +" записываются");
                    }

                }

                /// СУТОЧНЫЕ Для ТВ2

                if (Pattern.compile(regularExpression).matcher(s).find()&&tv2) {
                    System.out.println("найдено day = " + s);


                    System.out.println("ВРЕМЯ ЖАТВЫ TV2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                        ProcessDay processDay=new ProcessDay();

                        List<String> finalInfo = info2;
                        Customer finalCustomer = customer;
                        List<String> finalNaimenovaniya = naimenovaniya2;
                        List<String> finalEdIzmer = edIzmer2;


                       /* Callable taskTv2 = () -> {
                            System.out.println("работает поток "+ Thread.currentThread().getName());
                            processDay.processDayTv2(finalCustomer,customerService, auxRecovery.stringDate_to_TimeStamp_forDay(s) , finalInfo, finalNaimenovaniya, finalEdIzmer,list);
                            return "123";
                        };


                            Future<String> future1 = serviceVKT1.submit(taskTv2);
                            System.out.println(customer.getFirstName()+" Данные ТВ2 за " +s +" записываются");
                            logger.info(customer.getFirstName()+" Данные ТВ2 за " +s +" записываются");
*/

                    System.out.println("работает поток "+ Thread.currentThread().getName());
                    processDay.processDayTv2(finalCustomer,customerService, auxRecovery.stringDate_to_TimeStamp_forDay(s) , finalInfo, finalNaimenovaniya, finalEdIzmer,list);




                }





                if (Pattern.compile(regularExpression1).matcher(s).find()&&!tv2) {
                    System.out.println("найдено month TV1= " + s);

                    List<Operation> customerList=customerService.findOperation_daily(customerId,auxRecovery.stringDate_to_TimeStamp_forMonth(s),"total_moth","OK");
                    System.out.println("размер массива Месяц за дату "+s+" = "+customerList.size());
                    System.out.println("Проверка даны на конец месяца "+auxRecovery.checkMonthDay(auxRecovery.stringDate_to_TimeStamp_forMonth(s)));
                    if (customerList.size()==0&&auxRecovery.checkMonthDay(auxRecovery.stringDate_to_TimeStamp_forMonth(s))) {
                        System.out.println("ВРЕМЯ ЖАТВЫ MONTH TV1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                        ProcessMonth processMonth=new ProcessMonth();

                        List<String> finalInfo = info1;
                        Customer finalCustomer = customer;
                        List<String> finalNaimenovaniya = naimenovaniya1;
                        List<String> finalEdIzmer = edIzmer1;


                        Callable task1 = () -> {
                            System.out.println("работает поток "+ Thread.currentThread().getName());

                            try {
                                processMonth.processMonthTv1(finalCustomer,customerService, auxRecovery.stringDate_to_TimeStamp_forMonth(s) , finalInfo, finalNaimenovaniya, finalEdIzmer,list);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "123";
                        };

                        Future<String> futureMonth = serviceVKT1.submit(task1);
                        System.out.println(customer.getFirstName()+" Данные МЕСЯЦ за " +s +" записываются");
                        logger.info(customer.getFirstName()+" Данные за МЕСЯЦ" +s +" записываются");


                    }


                    count_month++;

                }

                if (Pattern.compile(regularExpression1).matcher(s).find()&&tv2) {
                    System.out.println("найдено month TV2 = " + s);



                    System.out.println("Проверка даны на конец месяца "+auxRecovery.checkMonthDay(auxRecovery.stringDate_to_TimeStamp_forMonth(s)));
                    if (auxRecovery.checkMonthDay(auxRecovery.stringDate_to_TimeStamp_forMonth(s))) {
                        System.out.println("ВРЕМЯ ЖАТВЫ MONTH TV2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                        ProcessMonth processMonth=new ProcessMonth();

                        List<String> finalInfo = info2;
                        Customer finalCustomer = customer;
                        List<String> finalNaimenovaniya = naimenovaniya2;
                        List<String> finalEdIzmer = edIzmer2;


                        Callable taskMonthTV2 = () -> {
                            System.out.println("работает поток "+ Thread.currentThread().getName());

                            try {
                                processMonth.processMonthTv2(finalCustomer,customerService, auxRecovery.stringDate_to_TimeStamp_forMonth(s) , finalInfo, finalNaimenovaniya, finalEdIzmer,list);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            return "123";
                        };

                        Future<String> futureMonth = serviceVKT1.submit(taskMonthTV2);
                        System.out.println(customer.getFirstName()+" Данные МЕСЯЦ за " +s +" записываются");
                        logger.info(customer.getFirstName()+" Данные за МЕСЯЦ" +s +" записываются");


                    }


                    count_month++;

                }




            }





        }


        serviceVKT1.shutdown();
        return "Данные востановлены";

    }

    @Override
    public List<String> info(String line) {
        line=line.replaceAll("[\\s]{2,}", " ").trim();
        List<String> d_list = new ArrayList<>(Arrays.asList(line.split(" ")));
        d_list.forEach(p->System.out.print(p+" "));

        return d_list;
    }

    @Override
    public List<String> naimenovaniya(String line) {

        line=line.replace(" ","");
        List<String> n_list = new ArrayList<>(Arrays.asList(line.split("\\|")));
        n_list.forEach(p->System.out.print(p+" "));
        System.out.print("\n");
        return  n_list;
    }

    @Override
    public List<String> edIzmer(String line) {

        line=line.replace(" ","");
        List<String> izm_list = new ArrayList<>(Arrays.asList(line.split("\\|")));
        izm_list.forEach(p->System.out.print(p+" "));
        System.out.print("\n");
        return izm_list;
    }

    @Override
    public void processDay(Customer customer, Timestamp date, List<String> info, List<String> naimenovaniya, List<String> edIzmer,List<String> list) {

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
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();



        operation.setChronological(date);
        operation.setShemaTv13Ecd(info.get(7));
        //operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
        operation.setBaseNumber(info.get(8));
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


        operation.setIdCustomer(customer.getId());
        operation.setCustomerName(customer.getFirstName());

        customer.addOperation(operation);
        customerService.save(customer);


        System.out.println("Запись ВОССТАНОВЛЕННЫХ значений  МЕСЯЧНЫЕ произведена. ");





    }


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
