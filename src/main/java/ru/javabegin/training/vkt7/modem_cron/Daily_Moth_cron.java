package ru.javabegin.training.vkt7.modem_cron;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.Crc16.Crc16ServiceImpl;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.dao.OperationService;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.measurements.MeasurementsServiceImpl;
import ru.javabegin.training.vkt7.propert.Properts_ready_Impl;
import ru.javabegin.training.vkt7.propert.entities.Properts;
import ru.javabegin.training.vkt7.recieve.Recieve03ServiceImpl;
import ru.javabegin.training.vkt7.recieve.Recieve10ServiceImpl;
import ru.javabegin.training.vkt7.send.Send03ServiceImpl;
import ru.javabegin.training.vkt7.send.Send10ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.m;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;


/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Daily_Moth_cron extends ru.javabegin.training.vkt7.modem_cron.EventListener_cron {
 //SerialPort serialPort; /*Создаем объект типа SerialPort*/
 public static AtomicInteger atomicInteger;
 public static volatile boolean end;


    public static String data;

    public int error= 0;
    public int in_work= 0;
    private String type_to_error = "";


    public static boolean r_3fff;
    public int server_version=0;
    private int version_oper=1;
    private int version_oper_moth=1;
    private Map<Timestamp, List<Measurements> > daily_hashMap = new HashMap<>();
    private Map<Timestamp, List<Measurements> > total_moth_hashMap=new HashMap<>();

    private  List<Date> dateList;
    private  Timestamp tstamp;
    private Timestamp timestamp_daily;
    ru.javabegin.training.vkt7.modem_cron.EventListener_cron eL;
    private File log_revizor;
    private File log_cron;

   private static int requestNum = 0;
   /*private static int[][] requests = {
           {0xFF, 0xFF, 0x01, 0x10, 0x3F, 0xFF, 0x00, 0x00, 0xCC, 0x80, 0x00, 0x00, 0x00, 0x60, 0xA8},
           {0xFF, 0xFF, 0x01, 0x03, 0x3F, 0xFE, 0x00, 0x00, 0x28, 0x2E }, {00, 00, 03}};*/

    private static int[] request=new int[256];



    public String daily_all_cycle(List<Customer> customerList, CustomerService customerService, AuxiliaryService auxiliaryService, Date date, int type ) throws InterruptedException, TimeoutException, ExecutionException, SerialPortException, IOException {

      end=false;
        log_revizor = new File("C:\\Work\\Java\\work\\logRevizor.txt");
        log_cron=new File("C:\\Work\\Java\\work\\logCron.txt");
        atomicInteger=new AtomicInteger();
        atomicInteger.addAndGet(1);
        oldString="";

        List<String> service_information =new ArrayList<>();
        List<Timestamp> date_3ff6= new ArrayList<>();
        List<Measurements> measurementsList=new ArrayList<>();
        List<Measurements> measurementsList_moth=new ArrayList<>();

        Map<Timestamp, List<Measurements> > hashMap = new HashMap<>();
        Logger logger = Logger.getRootLogger();

        int shema_Tb1=0;
        int shema_Tb2=0;
        int number_active_base=0;
        int number=0;
        Customer customer;
        String tel="";
        String status="";
        int moth=0;
        tstamp = null;
        int date_before = 0;



        String[] portNames = SerialPortList.getPortNames();
        String port="";
        for(int i = 0; i < portNames.length; i++){
            port=portNames[i];
            System.out.println(port);

        }






        if(serialPort!=null){
            serialPort=null;
        }
        serialPort = new SerialPort (port); /*Передаем в конструктор суперкласса имя порта с которым будем работать*/



        serialPort.openPort (); //Метод открытия порта
        serialPort.setParams (SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                SerialPort.FLOWCONTROL_RTSCTS_OUT);
        serialPort.setEventsMask (SerialPort.MASK_RXCHAR); //Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта

        if (eL!=null){
            eL=null;
        }

         eL= new ru.javabegin.training.vkt7.modem_cron.EventListener_cron();


        serialPort.addEventListener (eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
        //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

        eL.setSerialPort(serialPort);
           /* eL.setData(data);
           // eL.setData2(data2);
            eL.setRecieve_all_byte(recieve_all_byte);*/


        ExecutorService executor = Executors.newFixedThreadPool(3);



        //// Удаляем повторы DAILY ////

        atomicInteger.addAndGet(1);

        for (int countCustomer=0;countCustomer<customerList.size();countCustomer++){

    moth=0;


    System.out.println("countCustomer = "+ countCustomer);
    Long id_c=customerList.get(countCustomer).getId();

    customer=customerList.get(countCustomer);
    System.out.println("FirstName()= = "+ customer.getFirstName());

    number=customer.getUnitNumber();
    System.out.println("FirstName()= = "+ customer.getFirstName());
    tel=customer.getTelModem();


    ///// Проверяем есть ли измерение daily за эту дату //////////////////
  /*  System.out.println("Запрашиваемая дата Date = "+date);
    LocalDate date_loc = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    System.out.println("Перевели в  LocalDate = "+date_loc);
    LocalDateTime ldt_oper = LocalDateTime.of(date_loc, LocalTime.of(23,0));

    System.out.println("Запрашиваемая дата LocalDataTime = "+ldt_oper);
    Timestamp ts_oper = Timestamp.valueOf(ldt_oper);
    System.out.println("Запрашиваемая дата TimeStamp = "+ts_oper);
    System.out.println("\n Проверяем есть ли успешное  OK измерение за указанную дату");*/


    Timestamp ts_oper=auxiliaryService.date_TimeStamp(auxiliaryService.addTime(date,"23"));

    List<Operation> l_ok= customerService.findOperation_daily(id_c, ts_oper, "daily", "OK");
    if (l_ok.size()>0){
        System.out.println("Измерение  ОК присутствует");
        System.out.println("Размер массива = "+ l_ok.size());
        System.out.println("Пропускаем запрос");
        continue;
    } else{
        System.out.println("Измерение  ОК  нет");
    }






        stop=true;
            atomicInteger.addAndGet(1);

        while (stop!=false){
        try {

type_to_error="daily";




            step=100;
            data2="";

            if(!stop){
                System.out.println("\n Получена команда STOP ");
                break;
            }





            t=0;
            int repeat=0;
            serialPort.writeBytes("+++".getBytes());
            Thread.sleep(1000);
            executor.submit(callable(8));
            serialPort.writeBytes("+++".getBytes());
            Thread.sleep(1000);
            System.out.println("\nпосылаем ATH");
            serialPort.writeBytes("ATH\r".getBytes());

            atomicInteger.addAndGet(1);
            while(step==100&stop!=false){

                if (data2.contains("OK")){
                    t=1;

                    System.out.println("\n Инициализация модема выполнена. Принятые данные ::  " + data2);
                    step=0;
                   }
               if (t==2){
                    repeat++;
                    if(repeat==7) {

                        if (serialPort.isOpened())
                        {System.out.println(" Port открыт ");}
                        else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}

                        System.out.println("\n Закрываем порт ");

                        serialPort.closePort();
                        if (serialPort.isOpened()) {System.out.println(" Port ЕЩЕ открыт ");}
                        else {System.out.println(" Port закрыт ");}
                        Thread.sleep(5000);
                        serialPort.openPort (); //Метод открытия порта
                        serialPort.setParams (SerialPort.BAUDRATE_9600,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        serialPort.setEventsMask (SerialPort.MASK_RXCHAR);




                   }


                    if (repeat==6){
                        step=0;

                        System.out.println("\n Ответ инициализация модема не поступил. Ошибка по таймауту.");
                        System.out.println("\n error=1.");
                        error=1;
                        stop=false;

                    }
                    System.out.println("\n Ответот модема не поступил Ошибка по таймауту. Повторяем запрос");

                   if (serialPort.isOpened())
                   {System.out.println(" Port открыт ");}
                   else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}

                   System.out.println("\n Закрываем порт ");

                   serialPort.closePort();
                   if (serialPort.isOpened()) {System.out.println(" Port ЕЩЕ открыт ");}
                   else {System.out.println(" Port закрыт ");}
                   Thread.sleep(5000);
                   serialPort.openPort (); //Метод открытия порта
                   serialPort.setParams (SerialPort.BAUDRATE_9600,
                           SerialPort.DATABITS_8,
                           SerialPort.STOPBITS_1,
                           SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
                   serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                           SerialPort.FLOWCONTROL_RTSCTS_OUT);
                   serialPort.setEventsMask (SerialPort.MASK_RXCHAR);
                   if (serialPort.isOpened())
                   {System.out.println(" Port открыт ");}
                   else {System.out.println(" Port ЗАКРЫТ ");}

                   serialPort.addEventListener (eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
                   //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

                   eL.setSerialPort(serialPort);
                   Thread.sleep(1000);
                    t=0;
                    executor.submit(callable(5));
                   data2="";
                   serialPort.writeBytes("+++".getBytes());
                   System.out.println("\nпосылаем ATH");
                   Thread.sleep(1000);
                   serialPort.writeBytes("ATH\r".getBytes());

                }


            }


            atomicInteger.addAndGet(1);






            Thread.sleep(1000);
            if(!stop){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeBytes("AT+CREG?\r".getBytes());
            System.out.println("\n Ждем данных от модема ");

            t=0;
            executor.submit(callable(10));
            step=0;
            while(data2==null) {

                if (t==2){
                    System.out.println("\n Ответ от модема не поступил. Ошибка по таймауту.");
                    System.out.println("\n error=2. Ошибка AT+CREG");
                    error=2;
                    stop=false;
                    break;
                }
            }
            t=1;
            System.out.println("\n Ответ от можема получен");
            System.out.println("\n Проверка регистрации модема");



            t=0;
            executor.submit(callable(10));

            while(step==0&stop==true){

                if (data2.contains("+CREG: 1,")||data2.contains("+CREG: 0,1")||data2.contains("+CREG: 2,1")){
                    t=1;
                    System.out.println("\n Регистрация в сети произведена = "+data2);

                    step=1;
                }


                if (t==2){
                    System.out.println("\n Регистрация модема не произведена. Ошибка по таймауту.");
                    System.out.println("\n error=3. Ошибка регистрации");
                    error=3;
                    stop=false;
                }
            }




            atomicInteger.addAndGet(1);



            //Thread.sleep(5000);
            System.out.println("\nпосылаем AT&FL1E0V1&D2X4S7=120S10=90");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeBytes("AT&FL1E0V1&D2X4S7=120S10=90\r".getBytes());
            while(step==1&stop==true){

                if (data2.contains("OK")){
                    System.out.println("\n Команда AT&FL1E0V1&D2X4S7=120S10=90 прошла"+data2);

                    step=5555;
                }
                else{
                    System.out.println("\n Ошибка команды AT&FL1E0V1&D2X4S7=120S10=90 = "+data2);
                    Thread.sleep(500);
                }

            }



            atomicInteger.addAndGet(1);

            Thread.sleep(1000);
            System.out.println("\nНабираем номер");

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            //serialPort.writeBytes("ATDP+79064427287\r".getBytes());
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }


        /*   while(repeat!=1000){
                logger.info("Тестовое зависание");
                System.out.println("Тестовое зависание программы");
                Thread.sleep(20000);

            }*/

            if (tel.equals("")){
                tel="ATDP+79064426645\r"; //Весна
                //tel="ATDP+79064427287\r";
                //tel="ATDP+79064427319\r";

            }
            else {
                tel="ATDP"+tel+"\r";
            }


            //serialPort.writeBytes("ATDP+79064426645\r".getBytes());



            step=777;
            data2="";
            temp="";
            serialPort.writeBytes(tel.getBytes());
            //serialPort.writeBytes("ATDP+79064427319\r".getBytes());

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!


            int c=0;
            System.out.print("\n Ждем установки связи :");


            t=0;
            int call=0;
            executor.submit(callable(60));

            while(step==777&stop==true){

                if (data2.contains("CONNECT 9600/RLP")){
                    System.out.println("ответ пришел t= "+t);
                    System.out.println("\n Связь Установлена!! Продолжаем работать "+data2);
                    Thread.sleep(2000);

                    step=300;
                }

                else if (data2.contains("NO CARRIER")&call<3){
                    t=0;
                    data2="";
                    temp="";
                    Thread.sleep(1000);
                    serialPort.writeBytes(tel.getBytes());
                    executor.submit(callable(60));
                    call=call+1;

                }
                else if (data2.contains("NO CARRIER")&call==3){
                    System.out.println("Ответ  NO CARRIER ответ пришел t= "+t);


                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);

                    System.out.println("\n error=5.NO CARRIER");
                    error=5;
                    stop=false;
                }
                else if (data2.contains("NO DIALTONE")&call<3){
                    System.out.println("Ответ NO DIALTHONE ответ пришел t= "+t);
                    t=0;
                    data2="";
                    temp="";
                    modem_init(serialPort,eL);
                    System.out.println("Повторяем набор номера. call = "+ call);
                    Thread.sleep(1000);
                    serialPort.writeBytes(tel.getBytes());
                    executor.submit(callable(60));
                    call=call+1;

                }
                else if (data2.contains("NO DIALTONE")&call==3){
                    System.out.println("Ответ NO DIALTHONE ответ пришел t= "+t);

                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);
                    System.out.println("\n error=6.NO DIALTONE");
                    error=6;
                    stop=false;
                }
                else if (data2.contains("BUSY")||data2.contains("NO ANSWER")){
                    System.out.println("Ответ BUSY или NO ANSWER   ответ пришел t= "+t);


                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);
                    System.out.println("\n error=7.Ответ BUSY или NO ANSWER");
                    error=7;
                    stop=false;
                }

                if (t==2&call<3){
                    t=0;
                    data2="";
                    temp="";
                    Thread.sleep(1000);
                    serialPort.writeBytes(tel.getBytes());
                    executor.submit(callable(60));
                   call=call+1;

                }


                if (t==2&call==3){
                    t=0;
                    step=0;
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                    System.out.println("\n error=8.Связь. Ошибка по TimeOut");
                    error=8;
                    stop=false;

                }

            }

            atomicInteger.addAndGet(1);

            Thread.sleep(1000);
            serialPort.setParams (SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_2,
                    SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);

 /////// Инициализируем имплементирующие классы ////////////////////////////
            Send10ServiceImpl send10Service=new Send10ServiceImpl();
            Send03ServiceImpl send03Service=new Send03ServiceImpl();
            Recieve10ServiceImpl recieve10Service = new Recieve10ServiceImpl();
            Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();
            Crc16ServiceImpl crc16Service=new Crc16ServiceImpl();
//////////////////////////////////////////////////////////////////////////////////



/**
 * 3F FF Начало сеанса
 */
            List<Integer> data_send=new ArrayList<>();
            List<String> crc=new ArrayList<>();

            List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем 3F FF_n (Начало связи)");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n=send10Service.s_3FFF_n(number);
            ff_n.forEach(System.out::print);
            System.out.println("\nПолучили массив команды");
            System.out.println("Добавили CRC");
            crc=crc16Service.crc16_t(ff_n);


            System.out.println();

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            atomicInteger.addAndGet(1);
            request=null;
            int[] request=new int[crc.size()];
          /*  for(int i=0;i<crc.size();i++ ){
                System.out.print(crc.get(i)+" ");
            }*/
           // System.out.println();
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                //System.out.print(i+":"+request[i]+" ");
            }


            System.out.println("\n посылаем запрос 3F FF_n");


            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }



            t=0;
            repeat=0;
            data2="";
            executor.submit(callable(7));
            r_3fff=false;
            data="";
            step=5;
            serialPort.writeIntArray(request);
            //Thread.sleep(1000);

            while(step==5&stop!=false){
                if (data2.contains("3F FF ")){
                    t=1;

                System.out.println("Запрос 3F FF_n; data2 = "+data2);
                r_3fff = recieve10Service.r_3FFF_n(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FF_n прошла. Принятые данные ::  " + data2);
                    oldString=data2;
                    step=300;

                    temp="";
                    data="";
                    data2="";



                }

                if (t==2){
                      repeat++;
                    if (repeat==6){
                        step=0;

                    System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                        System.out.println("\n error=10.3F FF_n TimeOut");
                        error=10;
                        stop=false;
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                   // Thread.sleep(2000);
                    step=300;
                    temp="";
                    data="";
                    data2="";
                    step=5;
                    t=0;
                    executor.submit(callable(4));
                    serialPort.writeIntArray(request);

                }


            }

            //Thread.sleep(2000);
            atomicInteger.addAndGet(1);

/**
 * 3F FE 1-й проход определение версии
  */



            System.out.println("\n Формируем запрос 3F FE (Версия сервера 65 байт)");
            System.out.println("\n step= "+step);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n=send03Service.s_3FFE(number);
            crc=crc16Service.crc16_t(ff_n);
            request=null;
            request=new int[crc.size()];
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
               // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);

            Thread.sleep(2000);

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            System.out.println("\nЖдем получения всех данных после команды 3FF FE (Версия сервера 65 байт) ");
           data="";
           temp="";
           data2="";

            t=0;
            repeat=0;
            executor.submit(callable(15));
            recieve_all_byte=0;
            step=6;
            z=0;
            serialPort.writeIntArray(request);



/**
 * Ждем начала приема длинных данных.
 */atomicInteger.addAndGet(1);

            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==3){
                        step=0;

                        System.out.println("\n Ответ не поступил 3F FE (Версия сервера 65 байт). Ошибка по таймауту.");
                        System.out.println("\n error=11.3F FE 65 байт. Ошибка по TimeOut");
                        error=11;
                        stop=false;
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    data="";

                    temp = "";
                    data2="";
                    t=0;
                    z=0;
                    serialPort.writeIntArray(request);

                    executor.submit(callable(20));
                }

            }
            t=1;


            System.out.println("\nДанные 3F FE (Версия сервера) поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FE (Версия сервера 65 байт)");

            atomicInteger.addAndGet(1);
                step=7;
              while(step==7&stop!=false){

                 System.out.println("Принятая строка 3F FE (Версия сервера) :: " + data2);
                 if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
                     step = 0;

                     System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                     System.out.println("\n error=12.3F FE CRC16 Error");
                     error=12;
                     stop=false;
                     break;

                  }
                  System.out.println("\n Контрольная сумма верна ");

                server_version = recieve03Service.r_3FFE_1( data2);
                System.out.println("server_version = "+server_version);

                  if(server_version==0&stop!=false){
                      step = 0;

                      System.out.println("\n Версия сервера=0!! ");
                      System.out.println("\n error=1000.3F FE ServerVersion=0");
                      error=1000;
                      stop=false;
                      break;
                  }

                  if (server_version>=0&stop!=false) {
                    System.out.println(" Команда 3F FE (Версия сервера 65 байт) прошла");

                    Thread.sleep(500);
                    step=300;
                    System.out.println();
                }

                else{
                    System.out.println("\n Ошибка приема команды 3F FE (Версия сервера 65 байт). Посылаем повторно ");
                    //serialPort.writeIntArray(request);
                    Thread.sleep(500);

                }

            }
/**  //////////////////////////////////////////////////
 * 3F F9  (S8) Запрос на чтение служебной информации ////
 */  //////////////////////////////////////////////////
            atomicInteger.addAndGet(1);

            //Thread.sleep(2000);
            System.out.println("\n Фoрмируем запрос 3F F9 Запрос на чтение служебной информации");

            ff_n=null;
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n= send03Service.s_3FF9(number);

           //Получаем массив с контрольной суммой
            crc=crc16Service.crc16_t(ff_n);
            request=null;
            request=new int[crc.size()];
           System.out.println("\n crc size = "+ crc.size());

//Формируем массив int для отправки в порт
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);
           // Thread.sleep(2000);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            atomicInteger.addAndGet(1);

            System.out.println("\nЖдем получения всех данных после команды 3F F9 Запрос на чтение служебной информации");

            t=0;
            repeat=0;
            data="";
            temp="";
            data2="";

            executor.submit(callable(6));
            step=6;
            z=0;
            serialPort.writeIntArray(request);
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false) {
                if (t == 2) {
                    repeat++;
                    if (repeat == 2) {
                        step = 0;

                        System.out.println("\n Ответ не поступил 3F F9. Ошибка по таймауту.");
                        System.out.println("\n error=13.3F F9 TimeOut");
                        error=13;
                        stop=false;
                    }
                    System.out.println("\n Ответ не поступил 3F F9. Ошибка по таймауту. Повторяем запрос");
                    data="";
                    temp="";
                    data2="";
                    count=0;
                    serialPort.writeIntArray(request);
                    t = 0;
                    z=0;
                    executor.submit(callable(8));

                }
            }
            atomicInteger.addAndGet(1);
t=1;
            System.out.println(" 3FF9 data2=="+ data2);

            System.out.println("\nДанные 3FF F9 поступили.Запрос на чтение служебной информации");
            System.out.println("Ожидаем обработку принятых данных 3FF F9");

            step=9;
            while(step==9&stop!=false){

                System.out.println("Запрос 3F F9 Запрос на чтение служебной информации");
                System.out.println("Принятая строка" + data2);
                System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));

/**
 * Получаем массив со служебной информацией - service_information
 * {
 * номер версии ПО
 * схема измерения по ТВ1×2
 * схема измерения по ТВ2×2
 * идентификатор абонента
 * сетевой номер прибора
 * дата отчета
 * модель исполнения
 * }
 */
                service_information = recieve03Service.r_3FF9( data2);
                System.out.println("\n Принятый массив 3F F9 :: ");
                service_information.forEach(p->System.out.print(p+" "));
                step=300;
                System.out.println("");
            }



/**  ////////////////////////////////////////////////////////////////////////////
 * 3F FF  (s10->11) Запрос на запись перечня для чтения. Требует подтверждения.//////
 */  ////////////////////////////////////////////////////////////////////////////
            atomicInteger.addAndGet(1);
            //List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем 3F FF (Запрос на запись перечня для чтение)");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            /**
             *
             * @param number номер узла
             * Получаем массив объектов со стокой команды и массивом (prop_common) общих свойств с названиями
             * @return Массив из двух объектов {LinkedList<Properts> prop_specification, List<String> command,LinkedList<Properts> prop_common}
             * 1 объект
             */
            List<Object> ff = send10Service.s_3FFF(number);


            //ff_n.forEach(System.out::print);
            System.out.println("\nПолучили массив с командой и массив свойств \n LinkedList<Properts> prop_session, List<String> command,LinkedList<Properts> prop_common");

// Массив свойств для запроса единиц измерений и количества знаков
            List<Properts>prop_session=(List<Properts>)ff.get(0);
// Команда
            List<String> command=(List<String>)ff.get(1);
// Общий список свойств
            List<Properts>prop_common=(List<Properts>)ff.get(2);

            System.out.println("\n command = ");
          /*  command
                    .forEach(p->System.out.print(p+" "));*/


            System.out.println();
            crc=crc16Service.crc16_t(command);
            System.out.println("\nДобавили CRC");


            //crc16Service.crc16_t(ff_n).stream().forEach(w->data_send.add(Integer.parseInt(w ,16)));

           /* IntStream.range(0,crc.size())
                    .map(p->Integer.parseInt(crc.get(p) ,16)=requests[p]);*/

            System.out.println("\n command +CRC = ");
            for(int i=0;i<crc.size();i++ ){
                System.out.print(crc.get(i)+" ");
            }
            System.out.println();


            request=null;
            request= new int[crc.size()];
            //System.out.println("\n Проверяем значение масива request :: "+request.length);
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
               // System.out.print(request[i]+" ");
            }
            //System.out.println();
           // System.out.println("\n request после наполнения :: "+request.length);
           // System.out.println(request);


            System.out.println("\n посылаем запрос 3F FF (Запрос на запись перечня для чтение");
            //Thread.sleep(500);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }


            atomicInteger.addAndGet(1);

            t=0;
            repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
            data="";
            data2="";
            step=10;
            serialPort.writeIntArray(request);
            while(step==10&stop!=false){
                if (data2.contains("3F FF ")){
                    t=1;
                    //System.out.println("После запроса 3F FF_n ");
                    System.out.println("Запрс 3F FF; data2 = "+data2);
                    r_3fff = recieve10Service.r_3FFF(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FF (Запрос на запись перечня для чтение) прошла. Принятые данные ::  " + data2);
                    Thread.sleep(1000);
                    data2="";
                    temp="";
                    step=300;
                    System.out.println();
                }

                if (t==2){
                    repeat++;
                    if (repeat==6){
                        step=0;

                        System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                        System.out.println("\n error=14.3F FF TimeOut");
                        error=14;
                        stop=false;
                    }

                   System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp="";
                    t=0;
                    data="";
                    data2="";
                    step=10;
                    executor.submit(callable(5));
                    serialPort.writeIntArray(request);
                }





            }

            Thread.sleep(500);

            atomicInteger.addAndGet(1);

/**       //////////////////////////////////////////////////////////////////////
 * 3F FE  (03)  s11 -> s12 -> 13 Запрос на чтение данных  ////////////////////////////////////////
 */       //////////////////////////////////////////////////////////////////////
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            System.out.println("\n Формируем запрос 3F FE Запрос на чтение данных");
            ff=null;
            List<String> ff_2=send03Service.s_3FFE(number);
            //System.out.println();
            crc=crc16Service.crc16_t(ff_2);
            request=null;
           request=new int[crc.size()];
            //System.out.println("\n crc size = "+ crc.size());
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);
            Thread.sleep(500);
            System.out.println("\nЖдем получения всех данных после команды 3F FE (Запрос на чтение данных)  ");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }




            t=0;
            repeat=0;
            temp="";
            data="";
            data2="";
            step=6;
            z=0;
            executor.submit(callable(10));
            serialPort.writeIntArray(request);
            atomicInteger.addAndGet(1);

/**
 * Ждем начала приема длинных данных.
 */
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==4){
                        step=0;

                        System.out.println("\n Ответ не поступил 3F FE. Ошибка по таймауту.");
                        System.out.println("\n error=15.3F FE TimeOut");
                        error=15;
                        stop=false;
                    }
                    System.out.println("\n Ответ на 3F FE не поступил. Ошибка по таймауту. Повторяем запрос");
                    count=0;
                    z=0;

                    t=0;
                    temp="";
                    data="";
                    data2="";
                    executor.submit(callable(4));
                    serialPort.writeIntArray(request);
                }

            }
            t=1;

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            System.out.println("\nДанные 3F FE поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FE ");
            atomicInteger.addAndGet(1);
            List<Properts> prop_specification=new ArrayList<>();
            step=12;
            while(step==12&stop!=false){


                System.out.println("Принятая строка 3F FE :: " + data2);
                //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                //Проверяем контрольную сумму. Если !=true то закрываем порт
                if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
                    step = 0;
                    System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                    System.out.println("\n error=16.3F FE CRC16");
                    error=16;
                    stop=false;
                }

                System.out.println("Контрольная сумма верна ");

             // Получаем массив типа <Properts> с единицами измерения и количеством знаков ранее в ранее посланном запросе
             //свойств на переменных для чтения

                prop_specification = recieve03Service.r_3FFE(data2,prop_session,server_version);
                step=300;

            }

            atomicInteger.addAndGet(1);

/**  ////////////////////Класс  Properts_ready_Impl///////////////////////////////////
 * Формируем из общего массива свойств и массива единиц измерения и количества знаков
 * конечный масиив свойств с указанием единиц измерения и количества знаков
 * Входные массивы
 *          prop_common - общий список свойств
 *          prop_specification
 * Выходной массив
 *          prop_completed массив свойств с единицами измерения и количеством знаков
 *
 */  ////////////////////////////////////////////////////////////////////////////////
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            System.out.println("\n Запускаем обработку массивов и форматирование массива свойств");
            List<Properts> prop_completed=new ArrayList<>();
            Properts_ready_Impl propertsReadyImp=new  Properts_ready_Impl();
             prop_completed= propertsReadyImp.prop_ready(prop_common, prop_specification);

          /*  prop_completed
                    .stream()
                    .forEach(p->System.out.println(p.getId()+"  "+ p.getName() + "  "+ p.getText()+ "  "+ p.getEd()+ "  "+ p.getZnak()));
            System.out.println();*/

  //////////////////////////////////////////////////////////////////////////////////////



            atomicInteger.addAndGet(1);
/**       //////////////////////////////////////////////////////////////////////
 * 3F F6  (03)  s13 -> s14 -> 15 Запрос «Чтение интервала дат»  //////////////////////
 */       //////////////////////////////////////////////////////////////////////

            System.out.println("\n Формируем запрос 3F F6 Запрос «Чтение интервала дат»");
            ff_2=null;
            List<String> f6= send03Service.s_3FF6(number);
            //System.out.println();
            crc=crc16Service.crc16_t(f6);
            request=null;
            request=new int[crc.size()];
            //System.out.println("\n crc size = "+ crc.size());
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                 }
            Thread.sleep(500);
            System.out.println("\nЖдем получения всех данных после команды 3F F6 Запрос «Чтение интервала дат»");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            t=0;
            temp="";
            data2="";
            repeat=0;
            executor.submit(callable(10));
            step=6;
            z=0;
            serialPort.writeIntArray(request);


            atomicInteger.addAndGet(1);
/**
 * Ждем начала приема длинных данных.
 */
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==2){
                        step=0;

                        System.out.println("\n Ответ не поступил 3F F6. Ошибка по таймауту.");
                        System.out.println("\n error=17.3F F6 TimeOut");
                        error=17;
                        stop=false;
                    }
                    step=300;
                    System.out.println("\n Ответ на 3F F6 не поступил. Ошибка по таймауту. Повторяем запрос");

                    t=0;
                    temp="";
                    count=0;
                    data2="";
                    executor.submit(callable(8));
                    step=6;
                    z=0;
                    serialPort.writeIntArray(request);
                }

            }
            t=1;


            System.out.println("\nДанные 3F F6 поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F F6 ");
            atomicInteger.addAndGet(1);
            step=14;
            while(step==14&stop!=false){


                System.out.println("Принятая строка 3F F6 :: " + data2);
                //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                //Проверяем контрольную сумму. Если !=true то закрываем порт
                if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
                    step = 0;
                    System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                    System.out.println("\n error=18.3F F6 CRC");
                    error=18;
                    stop=false;
                }

                System.out.println("Контрольная сумма верна ");

                date_3ff6=recieve03Service.r_3FF6(data2);
                System.out.println("Выводим данные даты и времени счетчика");
                date_3ff6
                        .stream()
                        .forEach(p->System.out.println(p+" "));
                System.out.println("Выводим данные даты и времени сервера");
                //System.out.println(new Date(System.currentTimeMillis()));
                step=300;


            }

           /* List<Integer> version_serv = new ArrayList<>(Arrays.asList(server_version));
            List<SerialPort> serialPortList = new ArrayList<>(Arrays.asList(serialPort));
            List<EventListener> eventListeners = new ArrayList<>(Arrays.asList(eL));

            connect.add(version_serv);
            connect.add(service_information);
            connect.add(prop_common);
            connect.add(prop_completed);
            connect.add(date);
            connect.add(serialPortList);
            connect.add(eventListeners);*/

        /////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
* Фаза подключения закончена /////////////////////////////////////////////////////////////////////////////////////////////
* Получаем текущие зачения ///////////////////////////////////////////////////////////////////////////////////////////////
*/
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



         //////////////////////////////////////////////////////////////////////////////




/**  //////////////////////////////////////////////////
 * 3F FC (S17->18->19) Запрос на чтение перечня активных элементов данных ////
 */  //////////////////////////////////////////////////
            atomicInteger.addAndGet(1);
            Thread.sleep(500);
            System.out.println("\n Фoрмируем запрос 3F FC перечнь активных элементов данных");
            ff_n=null;

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n= send03Service.s_3FFC(number);

            //Получаем массив с контрольной суммой
            crc=crc16Service.crc16_t(ff_n);
            request=null;
            request=new int[crc.size()];
            //System.out.println("\n crc size = "+ crc.size());

//Формируем массив int для отправки в порт
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);
           // Thread.sleep(2000);




            System.out.println("\nЖдем получения всех данных после команды 3F FC");

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            t=0;
            repeat=0;

            executor.submit(callable(6));
            temp="";
            count=0;
            data2="";
            step=6;
            z=0;
            serialPort.writeIntArray(request);
            recieve_all_byte=0;
            while (recieve_all_byte==0) {
                if (t == 2) {
                    repeat++;
                    if (repeat == 2) {
                        step = 0;

                        System.out.println("\n Ответ не поступил 3F FC. Ошибка по таймауту.");
                        System.out.println("\n error=19.3F FC TimeOut");
                        error=19;
                        stop=false;
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    step=300;
                    t = 0;
                    data="";
                    temp="";
                    count=0;
                    data2="";
                    executor.submit(callable(8));
                    step=6;
                    z=0;
                    serialPort.writeIntArray(request);

                }
            }
            atomicInteger.addAndGet(1);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            t=1;

            System.out.println("\nДанные 3F FC поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FC");
            System.out.println("Step= "+step);
            System.out.println("data2 = "+data2);


            List<Properts> active_items=new ArrayList<>();



                System.out.println("Принятая строка 3F FC " + data2);
                System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));


                active_items=recieve03Service.r_3FFC(data2,prop_completed);
               /* System.out.println("\n Принятый массив 3F FC :: ");
                list.forEach(p->System.out.print(p+" "));*/


            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            atomicInteger.addAndGet(1);
/**  ////////////////////Класс  MeasurementsServiceImpl///////////////////////////////////
 *                      MeasurementsServiceImpl
 * Формируем массивы для измерений
 *
 *          active_items - ассив с активными элементами тепловычислителя с ед. изм., количеством знаков и размером
 *
 *          current - массив OBJECT!!
 *          [0] - массив List<String> команды "текущие", полученный извыбора из массива активный элементов, элемнтов, онтосящихся к измерениям "текущие".
 *          [1] - массив List<Measurements> элементов и свойств относящийся к измерению "текущие"
 *
 */  ////////////////////////////////////////////////////////////////////////////////


            MeasurementsServiceImpl measurementsService=new MeasurementsServiceImpl();
            System.out.println();
            List<Object> current= measurementsService.archive_command(active_items);
            List<String> command_send = (List<String>)current.get(0);
            List<Measurements> current_measur =(List<Measurements>) current.get(1);
            System.out.println("\n Полученная команда для отправки");
            command_send.forEach(p->System.out.print(p+" "));
            System.out.println("\n Полученный массив элементов");
            current_measur.forEach(p->System.out.println(p.getId()+ " "+p.getName()+ " "+p.getText()+ " "+p.getEd()+ " "+p.getZnak()+ " "+p.getSize()));

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }


            /**  ////////////////////////////////////////////////////////////////////////////
             * 3F FF  10  (s19 - >) Запрос на запись перечня для чтение. Требует подтверждения.//////
             */  ////////////////////////////////////////////////////////////////////////////

            //List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем 3F FF  Запрос на запись перечня для чтение");
            atomicInteger.addAndGet(1);

            /**
             *
             * @param number номер узла ДОЛЖЕН БЫТЬ INTEGER
             * Получаем массив объектов со стокой команды и массивом (prop_common) общих свойств с названиями
             * @return Массив из двух объектов {LinkedList<Properts> prop_specification, List<String> command,LinkedList<Properts> prop_common}
             * 1 объект
             */
            List<String> command_3FFF = send10Service.s_3FFF_end(number, command_send);

            System.out.println("\n Полученная команда после добавления всего");

            command_3FFF.forEach(p->System.out.print(p+" "));
            /**
             * Получили строку для получения данных по измерению "текущие"
             * добавляем контрольную сумму CRC
             */
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            crc=crc16Service.crc16_t(command_3FFF);
            System.out.println("\n Добавили контрольную сумму");
            crc.forEach(p->System.out.print(p+" "));



            System.out.println("\n command +CRC = ");
            for(int i=0;i<crc.size();i++ ){
                System.out.print(crc.get(i)+" ");
            }
            System.out.println();


            request=null;
            request= new int[crc.size()];
            //System.out.println("\n Проверяем значение масива request :: "+request.length);
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                // System.out.print(request[i]+" ");
            }
            //System.out.println();
            // System.out.println("\n request после наполнения :: "+request.length);
            // System.out.println(request);


            System.out.println("\n посылаем запрос 3F FF (Запрос  на запись перечня для чтение");
           // Thread.sleep(5000);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            atomicInteger.addAndGet(1);

            t=0;
            repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
            data="";
            data2="";
            step=19;
            serialPort.writeIntArray(request);
            while(step==19){
                if (data2.contains("3F FF ")){
                    t=1;
                    //System.out.println("После запроса 3F FF_n ");
                    System.out.println("Запрос 3F FF; data2 = "+data2);
                    r_3fff = recieve10Service.r_3FFF(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FF (Запрос на запись) прошла. Принятые данные ::  " + data2);
                    Thread.sleep(500);
                    step=300;
                    data2="";

                }

                if (t==2){
                    repeat++;
                    if (repeat==6){
                        step=0;

                        System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                        System.out.println("\n error=20.3F FF TimeOut");
                        error=20;
                        stop=false;
                    }

                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    t=0;
                    data="";
                    data2="";
                    executor.submit(callable(4));
                    serialPort.writeIntArray(request);
                }


            }

            Thread.sleep(500);
            atomicInteger.addAndGet(1);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }

            List<String> command_3FFD=new ArrayList<>();

            /**
             * Подготавливаем время для разбивки на часы
             * приходит в программу
             * Date data
             * Конвертируем в LocalDataTime
             * Переводим в строку вида dd:MM:uu
             */
         /*   Instant date_time = date.toInstant();
            LocalDateTime ldt = LocalDateTime.ofInstant(date_time, ZoneId.systemDefault());
            System.out.println("Перевели Date в  LocalDateTime =" + ldt);
            // String date_str=ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
            String date_str = ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu"));
            System.out.println("Строка даты для формирования  массива часовых данных = " + date_str);

            List<String> date_dd_MM_UU_HH = new ArrayList<String>();


            for (int i = 0; i < 24; i++) {
                date_dd_MM_UU_HH.add(date_str + ":" + String.valueOf(i));
            }

*/

            atomicInteger.addAndGet(1);



        if (type==1) {
////(!)(!)(!)(!)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////(!)(!)(!)(!)//////////////////////Меняем тип запрашиваемго архива на суточный////////////////////////////////////////////////
////(!)(!)(!)(!)//////////////////////type=1 /////////////////////////////////////////////////////////////////////////////////////////
            type_to_error="daily";
            atomicInteger.addAndGet(1);

            /**  ////////////////////////////////////////////////////////////////////////////
             * 3F FD  10  (s20 - >21) Запрос на запись типа значения. Требует подтверждения.//////
             */  ////////////////////////////////////////////////////////////////////////////

            //List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем новый (type=1) 3F FD Запрос на запись ТИПА значений");


            /**
             *
             * @param number номер узла ДОЛЖЕН БЫТЬ INTEGER
             * @param type тип измерений. (в данный момент "Суточный" - 1) ДОЛЖЕН БЫТЬ INTEGER
             * @return Массив строки команды
             * 1 объект
             */
            command_3FFD = null;
            command_3FFD = send10Service.s_3FFD(number, 1);

            System.out.println("\n Полученная команда после добавления всего");

            command_3FFD.forEach(p -> System.out.print(p + " "));

            if (stop == false) {
                System.out.println("\n Получена команда STOP ");
                break;
            }

            /**
             * Получили строку для получения данных по измерению "текущие"
             * добавляем контрольную сумму CRC
             * получаем массив crc - полную строку с контрольной cуммой
             */

            crc = crc16Service.crc16_t(command_3FFD);
            System.out.println("\n Добавили контрольную сумму");
            crc.forEach(p -> System.out.print(p + " "));
            System.out.println("\n command +CRC = ");
            for (int i = 0; i < crc.size(); i++) {
                System.out.print(crc.get(i) + " ");
            }
            System.out.println();


            request = null;
            request = new int[crc.size()];

            for (int i = 0; i < crc.size(); i++) {
                request[i] = Integer.parseInt(crc.get(i), 16);
            }


            System.out.println("\n посылаем запрос 3F FD Запрос на запись ТИПА значений.");
            //Thread.sleep(5000);
            if (stop == false) {
                System.out.println("\n Получена команда STOP ");
                break;
            }
            atomicInteger.addAndGet(1);

            step = 20;
            temp = "";
            data2 = "";

            t = 0;
            repeat = 0;
            executor.submit(callable(3));
            r_3fff = false;
            serialPort.writeIntArray(request);
            while (step == 20) {
                if (data2.contains("3F FD ")) {
                    t = 1;
                    //System.out.println("После запроса 3F FF_n ");
                    System.out.println("Запрс 3F FD; data2 = " + data2);
                    r_3fff = recieve10Service.r_3FFD(data2);
                }
                if (r_3fff) {
                    System.out.println("\n Команда 3F FD (Запрос на запись ТИПА прошла. Принятые данные ::  " + data2);
                    //Thread.sleep(1000);
                    data2 = "";
                    step = 21;
                    System.out.println();
                }

                if (t == 2) {
                    repeat++;
                    if (repeat == 6) {
                        step = 0;

                        System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                        System.out.println("\n error=21.3F FD TimeOut");
                        error = 21;
                        stop = false;
                    }

                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    serialPort.writeIntArray(request);
                    t = 0;
                    executor.submit(callable(4));
                }

            }
            atomicInteger.addAndGet(1);

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            /**
             * Делаем общую проверку  измерений от начала месяца
             *
             */
            Date date1=new Date();
            System.out.println("Текущая дата = "+ date1);
            //  AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

            dateList= auxiliaryService.from_the_beginning_of_month(date1);
            dateList.forEach(p->  System.out.println(p));

            List<String> dateListStr=auxiliaryService.from_the_beginning_of_month_str(date1);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Начинаем перебор по датам
 * Получили массив дат в формате List<String>
 *
 *
 */
            for(String strData:dateListStr) {



                // Сравниваем рассматриваемую дату с датой начала записи суточных значений в теплосчетчике




                LocalDateTime begin_arhive =  auxiliaryService.timestamp_to_localDateTime(date_3ff6.get(2));
                System.out.println("Переводим в LocalData Time --- "+begin_arhive);




                LocalDateTime target_data=auxiliaryService.stringDate_to_LocalDateTime(strData);
                date_before = 0;
                if (begin_arhive.isBefore(target_data)||begin_arhive.isEqual(target_data)){

                    System.out.println("Запрашиваемая дата "+ strData+ " после даты начала работы устройства "+ begin_arhive+ ". Запрос  СУТОЧНЫЕ возможен");
                    auxiliaryService.saveMessage(log_cron, "Customer "+ customer.getFirstName()+ " СУТОЧНЫЕ МОЖНО ПРОВОДИТЬ ИЗМЕРЕНИЯ.  Дата "+ strData +" до начала работы устройства " + begin_arhive );
                    date_before = 1;
                }
                else{
                    System.out.println("Запрос СУТОЧНЫЕ НЕ ВОЗМОЖЕН!.  Дата "+ strData +" до начала работы устройства " + begin_arhive  );
                    auxiliaryService.saveMessage(log_cron, "Customer "+ customer.getFirstName()+ " Запрос СУТОЧНЫЕ НЕ ВОЗМОЖЕН!.  Дата "+ strData +" до начала работы устройства " + begin_arhive );
                    continue;
                }




                //проверяем есть ли измерение
                Date day = auxiliaryService.stringDate_to_Date(strData);


                Long customerID = customerList.get(countCustomer).getId();
                List<Operation> operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "daily", "OK");
                if (operationList.size() > 0) {

                    System.out.println("CUSTOMER " + customer.getFirstName() + " Измерения за " + day + " есть. Размер массива = " + operationList.size());
                    auxiliaryService.saveMessage(log_cron,"CUSTOMER " + customer.getFirstName() + " Измерения за " + day + " есть. Размер массива = " + operationList.size());

                    for (Operation o : operationList) {
                        System.out.println("getChronological() = " + o.getChronological() + "getCustomer() = " + o.getCustomer() + "getStatus() = " + o.getStatus());
                    }

                   if(operationList.size()>1){ auxiliaryService.saveMessage(log_cron," Есть дублированные измерения " + day + " размер массива = " + operationList.size());}
                    for (int i = 1; i < operationList.size(); i++) {
                        if (operationList.get(i) != null) {
                            System.out.println(" " + day + " размер массива ДО удаления= " + operationList.size());
                            operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "daily", "OK");
                            auxiliaryService.saveMessage(log_cron," " + day + " размер массива ДО удаления= " + operationList.size());
                            customerService.deleteOperation(customerID, operationList.get(i).getId());
                            System.out.println(" " + day + " размер массива ПОСЛЕ удаления= " + operationList.size());
                            operationList = customerService.findOperation_daily(customerID, auxiliaryService.date_TimeStamp(day), "daily", "OK");
                            auxiliaryService.saveMessage(log_cron," " + day + " размер массива ПОСЛЕ удаления= " + operationList.size());


                        } else {
                            System.out.println("operationList.get(i)== NULL . ДУБЛИРОВАНИЕ ОТСУТСТВУЕТ");
                        }
                    }


                } else {
                    System.out.println("Измерений за " + day + " НЕТ. Размер массива = " + operationList.size());
                    System.out.println("CUSTOMER " + customer.getFirstName() + " Дата " + day + " <<<----- НУЖНО ПРОВОДИТЬ ИЗМЕРЕНИЯ!!!!!!!!!!!!!");
                    auxiliaryService.saveMessage(log_cron,"CUSTOMER " + customer.getFirstName() + " Дата " + day + " <<<----- НУЖНО ПРОВОДИТЬ ИЗМЕРЕНИЯ!!!!!!!!!!!!!");





                /**  ////////////////////////////////////////////////////////////////////////////
                 * 3F FB  10  (s30 - >31) 4.4 Запрос на запись даты. Требует подтверждения.//////
                 */  ////////////////////////////////////////////////////////////////////////////


                System.out.println("\nФормируем 3F FB  " + strData + "      4.4 Запрос на запись даты");


                List<String> command_3FFB = send10Service.s_3FFB(number, strData);

                System.out.println("\n Полученная команда после добавления всего");

                command_3FFB.forEach(p -> System.out.print(p + " "));
                /**
                 * Получили строку для получения данных по измерению "текущие"
                 * добавляем контрольную сумму CRC
                 * получаем массив crc - полную строку с контрольной cуммой
                 */
                crc = crc16Service.crc16_t(command_3FFB);
                System.out.println("\n Добавили контрольную сумму");
                //crc.forEach(p -> System.out.print(p + " "));
                System.out.println("\n command +CRC = ");
                for (int i = 0; i < crc.size(); i++) {
                    System.out.print(crc.get(i) + " ");
                }
                System.out.println();


                request = null;
                request = new int[crc.size()];

                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                }


                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                System.out.println("\n посылаем запрос 3F FB" + strData + "  4.4 Запрос на запись даты.");
                Thread.sleep(1000);


                atomicInteger.addAndGet(1);
                data2 = "";
                temp = "";

                t = 0;
                repeat = 0;

                executor.submit(callable(6));

                step = 20;
                serialPort.writeIntArray(request);


                r_3fff = false;
                while (step == 20) {
                    if (data2.contains("3F FB ")) {
                        t = 1;
                        //System.out.println("После запроса 3F FF_n ");
                        System.out.println("Запрс 3F FB;" +strData + " data2 = " + data2);
                        r_3fff = recieve10Service.r_3FFB(data2);
                    }
                    if (r_3fff) {
                        System.out.println("\n Команда 3F FB (4.4 Запрос на запись даты) Принятые данные ::  " + data2);
                        //Thread.sleep(1000);
                        data2 = "";
                        step = 150;
                        System.out.println();
                    }

                    if (t == 2) {
                        repeat++;
                        if (repeat == 6) {
                            step = 0;

                            System.out.println("\n Ответ не поступил." +strData + " Ошибка по таймауту.");
                            System.out.println("\n error=22.3F FB TimeOut");
                            error = 22;
                            stop = false;
                        }

                        System.out.println("\n Ответ не поступил." + strData + " Ошибка по таймауту. Повторяем запрос");

                        t = 0;
                        data = "";
                        data2 = "";
                        temp = "";
                        executor.submit(callable(6));

                        serialPort.writeIntArray(request);
                    }

                }

                Thread.sleep(2000);
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                /**       //////////////////////////////////////////////////////////////////////
                 * 3F FE  (03)  s11 -> s12 Запрос на чтение данных  ////////////////////////////////////////
                 */
                //////////////////////////////////////////////////////////////////////

                System.out.println("\n Формируем запрос 3F FE СУТОЧНЫЕ чтение " + strData);
                ff_2 = null;
                ff_2 = send03Service.s_3FFE(number);
                //System.out.println();
                crc = crc16Service.crc16_t(ff_2);
                request = null;
                request = new int[crc.size()];
                //System.out.println("\n crc size = "+ crc.size());
                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                    // System.out.print(+i+":"+request[i]+" ");
                }
                //System.out.println("\n request size = "+ request.length);
                //Thread.sleep(5000);
                System.out.println("\nЖдем получения данных СУТОЧНЫЕ после команды 3F FE   " + strData);
                data2 = "";
                step = 21;
                recieve_all_byte = 0;
                t = 0;
                repeat = 0;
                executor.submit(callable(5));



                serialPort.writeIntArray(request);

                atomicInteger.addAndGet(1);
/**
 * Ждем начала приема длинных данных.
 */

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                while (recieve_all_byte == 0) {
                    if (t == 2) {
                        repeat++;
                        if (repeat == 6) {
                            step = 0;

                            System.out.println("\n Ответ не поступил 3F FE(СУТОЧНЫЕ). Ошибка по таймауту.");
                            System.out.println("\n error=23.3F FE TimeOut");
                            error = 23;
                            stop = false;
                        }
                        System.out.println("\n Ответ на 3F FE  " + strData + " не поступил. Ошибка по таймауту. Повторяем запрос");
                        temp = "";
                        z = 0;
                        t = 0;
                        data2 = "";
                        serialPort.writeIntArray(request);

                        executor.submit(callable(4));
                    }

                }
                t = 1;

                atomicInteger.addAndGet(1);
                System.out.println("\nДанные 3F FE (СУТОЧЫЕ ) поступили.");
                System.out.println("Ожидаем обработку принятых данных 3F FE ");

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);
                while (step == 22) {


                    System.out.println("Принятая строка 3F FE :: " + data2);
                    //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                    //Проверяем контрольную сумму. Если !=true то закрываем порт
                    if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList(data2.replace(" ", "").split("(?<=\\G.{2})")))) != true) {
                        step = 0;
                        System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт" + data2);
                        serialPort.closePort();
                    }

                    System.out.println("Контрольная сумма верна ");

                    // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                    //свойств на переменных для чтения

                    //prop_specification = recieve03Service.r_3FFE(data2,prop_session);

                    System.out.println("размер measurementsList до =" + measurementsList.size());
                    measurementsList = null;
                    measurementsList = recieve03Service.r_3FFE_Measurements(data2, current_measur);
                    measurementsList.forEach(p -> System.out.println(p));

                    System.out.println("обработка завершена");
                    System.out.println("размер measurementsList после =" + measurementsList.size());
                    //Thread.sleep(1000);
                    System.out.println("размер measurementsList после через 1с=" + measurementsList.size());
                    step = 60;

                }
                atomicInteger.addAndGet(1);

                System.out.println("Дата для суточного архива= " + strData);

                // формируем timestamp из строки для записи в хеш с полученными часовыми данными

                /*List<String> d = new ArrayList<String>(Arrays.asList(date_dd_MM_UU_HH.get(23).split(":")));
                LocalDateTime ldt2 = LocalDateTime.of(2000 + Integer.parseInt(d.get(2)), Integer.parseInt(d.get(1)), Integer.parseInt(d.get(0)), Integer.parseInt(d.get(3)), 0, 0);
                System.out.println("LocalDateTime ldt2 = " + ldt2);
                timestamp_daily = Timestamp.valueOf(ldt2);*/

                timestamp_daily=auxiliaryService.stringDate_to_TimeStamp(strData);
                System.out.println(" timestamp для суточного измерения = " + timestamp_daily);

                measurementsList.forEach(p -> System.out.println(p));
                daily_hashMap.put(timestamp_daily, measurementsList);

                System.out.println("//////////////////////////////////////////////////////////////////");



                System.out.println("//////////////////////////////////////////////////////////////////");
                System.out.println("Суточные из daily_hashMap");
                System.out.println("При записи Суточные в daily_hashMap, timestamp_daily == " + timestamp_daily);
                List<Measurements> testlist1 = daily_hashMap.get(timestamp_daily);
                for (Measurements measurements : testlist1) {
                    System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

                }
                System.out.println("//////////////////////////////////////////////////////////////////");


                atomicInteger.addAndGet(1);


                /**  //////////////////////////////////////////////////////////////////////
                 * 3E CD  (03)  s11 -> s12 Чтение номера схемы измерений Тв1 ////////////////////////////////////////
                 */
                //////////////////////////////////////////////////////////////////////
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);
                System.out.println("\n Формируем запрос 3E CD Чтение номера схемы измерений Тв1");
                ff_2 = null;
                ff_2 = send03Service.s_3ECD(number);
                //System.out.println();
                crc = crc16Service.crc16_t(ff_2);
                request = null;
                request = new int[crc.size()];
                //System.out.println("\n crc size = "+ crc.size());
                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                    // System.out.print(+i+":"+request[i]+" ");
                }
                //System.out.println("\n request size = "+ request.length);
               // Thread.sleep(5000);
                System.out.println("\nЖдем получения всех данных  3E CD Чтение номера схемы измерений Тв1");


                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }


                t = 0;
                repeat = 0;
                //Thread.sleep(5000);
                executor.submit(callable(6));
                step = 300;
                recieve_all_byte = 0;
                data = "";
                data2 = "";
                temp = "";
                step = 6;
                z = 0;
                serialPort.writeIntArray(request);


/**
 * Ждем начала приема длинных данных.
 */
                atomicInteger.addAndGet(1);
                while (recieve_all_byte == 0) {
                    if (t == 2) {
                        repeat++;
                        if (repeat == 4) {
                            step = 0;

                            System.out.println("\n Ответ не поступил 3E CD Чтение номера схемы измерений Тв1. Ошибка по таймауту.");
                            System.out.println("\n error=24.3F CD TimeOut");
                            error = 24;
                            stop = false;
                            break;
                        }
                        System.out.println("\n Ответ на 3E CD не поступил. Ошибка по таймауту. Повторяем запрос");
                        //Thread.sleep(5000);
                        data = "";
                        data2 = "";
                        temp = "";
                        t = 0;
                        z = 0;
                        executor.submit(callable(6));
                        serialPort.writeIntArray(request);
                    }

                }
                t = 1;
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                System.out.println("\nДанные 3E CD(Тв1) Чтение номера схемы измерений Тв1. поступили.");
                System.out.println("Ожидаем обработку принятых данных 3E CD(Тв1) ");
                step = 22;
                atomicInteger.addAndGet(1);
                while (step == 22) {


                    System.out.println("Принятая строка 3E CD(Тв1) :: " + data2);
                    //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                    //Проверяем контрольную сумму. Если !=true то закрываем порт
                    if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList(data2.replace(" ", "").split("(?<=\\G.{2})")))) != true) {
                        step = 0;
                        System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                        System.out.println("\n error=25.3E CD CRC");
                        error = 25;
                        stop = false;
                    }

                    System.out.println("Контрольная сумма верна ");

                    // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                    //свойств на переменных для чтения

                    //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                    shema_Tb1 = recieve03Service.r_3ECD(data2);
                    System.out.println("Схема измерений по Тв1 - " + shema_Tb1);


                    step = 300;

                }
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);

                /**       //////////////////////////////////////////////////////////////////////
                 * 3F 5B  (03)   Чтение номера схемы измерений Тв2 ////////////////////////////////////////
                 */
                //////////////////////////////////////////////////////////////////////

                System.out.println("\n Формируем запрос 3F 5B Чтение номера схемы измерений Тв2");
                ff_2 = null;
                ff_2 = send03Service.s_3F5B(number);
                //System.out.println();
                crc = crc16Service.crc16_t(ff_2);
                request = null;
                request = new int[crc.size()];
                //System.out.println("\n crc size = "+ crc.size());
                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                    // System.out.print(+i+":"+request[i]+" ");
                }
                //System.out.println("\n request size = "+ request.length);
                //Thread.sleep(5000);
                System.out.println("\nЖдем получения всех данных  3F 5B Чтение номера схемы измерений Тв2");
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                recieve_all_byte = 0;
                data = "";
                temp = "";
                data2 = "";
                t = 0;
                repeat = 0;
                executor.submit(callable(6));
                step = 6;
                z = 0;
                serialPort.writeIntArray(request);

/**
 * Ждем начала приема длинных данных.
 */atomicInteger.addAndGet(1);

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }


                while (recieve_all_byte == 0) {
                    if (t == 2) {
                        repeat++;
                        if (repeat == 4) {
                            step = 0;

                            System.out.println("\n Ответ не поступил 3F 5B Чтение номера схемы измерений Тв2. Ошибка по таймауту.");
                            System.out.println("\n error=26.3F 5B TimeOut");
                            error = 26;
                            stop = false;
                        }
                        System.out.println("\n Ответ на 3F 5B не поступил. Ошибка по таймауту. Повторяем запрос");
                        step = 300;
                        data = "";
                        temp = "";
                        data2 = "";
                        t = 0;
                        executor.submit(callable(6));
                        step = 6;
                        z = 0;
                        serialPort.writeIntArray(request);
                    }

                }
                t = 1;
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                System.out.println("\nДанные 3F 5B(Тв2) Чтение номера схемы измерений Тв2. поступили.");
                System.out.println("Ожидаем обработку принятых данных 3F 5B(Тв2)  ");
                atomicInteger.addAndGet(1);
                step = 22;
                while (step == 22) {


                    System.out.println("Принятая строка 3F 5B(Тв2)  :: " + data2);
                    //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                    //Проверяем контрольную сумму. Если !=true то закрываем порт
                    if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList(data2.replace(" ", "").split("(?<=\\G.{2})")))) != true) {
                        step = 0;
                        System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                        System.out.println("\n error=27.3F 5B TimeOut");
                        error = 27;
                        stop = false;
                    }

                    System.out.println("Контрольная сумма верна ");

                    // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                    //свойств на переменных для чтения

                    //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                    shema_Tb2 = recieve03Service.r_3F5B(data2);
                    System.out.println("Схема измерений по Тв2 - " + shema_Tb2);


                    step = 300;

                }
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);

                /**       //////////////////////////////////////////////////////////////////////
                 * 3F E9  (03)   Чтение номера активной базы данных ////////////////////////////////////////
                 */
                //////////////////////////////////////////////////////////////////////

                System.out.println("\n Формируем запрос 3F E9  Чтение номера активной базы данных");
                ff_2 = null;
                ff_2 = send03Service.s_3FE9(number);
                //System.out.println();
                crc = crc16Service.crc16_t(ff_2);
                request = null;
                request = new int[crc.size()];
                //System.out.println("\n crc size = "+ crc.size());
                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                    // System.out.print(+i+":"+request[i]+" ");
                }
                //System.out.println("\n request size = "+ request.length);
                Thread.sleep(5000);
                System.out.println("\nЖдем получения всех данных  3F E9  Чтение номера активной базы данных");


                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }


                atomicInteger.addAndGet(1);


                data = "";
                temp = "";
                data2 = "";
                t = 0;
                repeat = 0;
                executor.submit(callable(4));

                z = 0;
                    step = 6;
                    recieve_all_byte = 0;
                serialPort.writeIntArray(request);


/**
 * Ждем начала приема длинных данных.
 */

                while (recieve_all_byte == 0) {
                    if (t == 2) {
                        repeat++;
                        if (repeat == 5) {
                            step = 0;

                            System.out.println("\n Ответ не поступил 3F E9  Чтение номера активной базы данных. Ошибка по таймауту.");
                            System.out.println("\n error=28.3F E9 TimeOut");
                            error = 28;
                            stop = false;
                        }
                        System.out.println("\n Ответ на 3F E9  Чтение номера активной базы данных не поступил. Ошибка по таймауту. Повторяем запрос");
                        step = 300;
                        data = "";

                        temp = "";
                        data2 = "";
                        t = 0;
                        executor.submit(callable(4));
                        step = 6;
                        z = 0;
                        serialPort.writeIntArray(request);
                    }

                }
                t = 1;
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                atomicInteger.addAndGet(1);
                System.out.println("\nДанные 3F E9  Чтение номера активной базы данных поступили.");
                System.out.println("Ожидаем обработку принятых данных 3F E9 .");
                step = 22;
                while (step == 22) {


                    System.out.println("Принятая строка 3F E9   :: " + data2);
                    //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                    //Проверяем контрольную сумму. Если !=true то закрываем порт
                    if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList(data2.replace(" ", "").split("(?<=\\G.{2})")))) != true) {
                        step = 0;
                        System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                        System.out.println("\n error=28.3F E9 CRC");
                        error = 28;
                        stop = false;
                    }

                    System.out.println("Контрольная сумма верна ");

                    // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                    //свойств на переменных для чтения

                    //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                    number_active_base = recieve03Service.r_3FE9(data2);
                    System.out.println("Активная база - " + number_active_base);


                    step = 300;

                }

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                atomicInteger.addAndGet(1);
                /////////////////////////////////////////////////////////////////


                //////////////////////Производим запись СУТОЧНЫЕ ///////////////////////////
                ////////////////////////////////////////////////////////////////////////////
                ///////////////////////////////////////////////////////////////////////////
                if (stop == true) {
                    System.out.println("Суточные получены без ошибок. Команды STOP не поступало");
                    status = "OK";
                } else {
                    status = "ERROR";
                }

                if (error == 0 & status.equals("OK") & type == 1) {


                    System.out.println("Начинаем запись в базу суточный архив");

                    // Получили времы сервера для записиси в базу

                    Timestamp timestamp_date_input=auxiliaryService.date_TimeStamp(new Date());

                    //for (Timestamp ts : daily_hashMap.keySet()) {



                        Operation operation = new Operation();
                        operation.setTypeOperation("daily");
                        operation.setServerVersion(String.valueOf(server_version));
                        operation.setProgrammVersion(service_information.get(0));
                        operation.setShemaTv13Ff9(service_information.get(1));
                        operation.setTp3Tv1(service_information.get(2));
                        operation.setT5Tv1(service_information.get(3));
                        operation.setShemaTv23Ff9(service_information.get(4));
                        operation.setTp3Tv2(service_information.get(5));
                        operation.setT5Tv2(service_information.get(6));
                        operation.setIdentificator(service_information.get(7));
                        operation.setNetNumber(service_information.get(8));
                        operation.setModel(service_information.get(10));
                        operation.setBeginHourDate(date_3ff6.get(0));
                        operation.setCurrentDate3Ff6(date_3ff6.get(1));
                        operation.setBeginDayDate(date_3ff6.get(2));
                        //operation.setDateVkt3Ffb();
                        operation.setDateServer(timestamp_date_input);
                        //operation.setChronological(ts);
                        operation.setChronological(timestamp_daily);
                        operation.setShemaTv13Ecd(String.valueOf(shema_Tb1));
                        operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
                        operation.setBaseNumber(String.valueOf(number_active_base));
                        operation.setStatus(status);
                        operation.setError(String.valueOf(error));


                        if (version_oper > 1) {
                            operation.setVersion(version_oper);
                        }


                        //measurementsList.forEach(p -> operation.addMeasurements(p));
                        //daily_hashMap.get(ts).forEach(p -> operation.addMeasurements(p));
                        measurementsList.forEach(p -> operation.addMeasurements(p));
                       // List<Measurements> testlist = daily_hashMap.get(ts);
                       /* for (Measurements measurements :  measurementsList) {
                            System.out.println(measurements.getText() + " = " + measurements.getMeasurText() + " байт качества -" + measurements.getQualityText() + "NS -" + measurements.getNs());

                        }*/

                        customer = customerService.findById(id_c);
                        //Customer customer = customerService.findById(id);
                        operation.setIdCustomer(customer.getId());
                        operation.setCustomerName(customer.getFirstName());

                        customer.addOperation(operation);
                        customerService.save(customer);



                    System.out.println("Запись значений СУТОЧНОГО архива произведена. ");
                    auxiliaryService.saveMessage(log_cron, " Запись СУТОЧНЫЕ "+ customer.getFirstName()+ " Дата "+ timestamp_daily);

                }


            }


            }

            ///////////// закрываем if проверки цикла type ==1 //////
        }





     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////Работа с СУТОЧНЫЕ закончена//////////////////////////////////////////////////////////////////////////////////////////////
            ///////////////////////////////////////////////////////////////////////////////////////////////




            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////









            atomicInteger.addAndGet(1);

////////////////////////////////////////////////////////////////////////////////////
/**
 *проверяем наличие измерения ИТОГОВЫЕ МЕСЯЦ. измерения нет, то выполняем его.
 */
////////////////////////////////////////////////////////////////////////////////////


            tstamp = Timestamp.valueOf(LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()), LocalTime.of(23,00)));
            System.out.println("Последний день предыдущего месяца 23-00 переведенный в ");
            System.out.println("TimeStamp для запроса базы данных= "+  tstamp);

            System.out.println("дата для записи  в 3FFB type=3 "+ LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00)));
            LocalDateTime data_type3 =LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00));

            System.out.println(data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")));



            List<Operation> totalMoth=customerService.findOperation_total_moth( customer.getId(),tstamp,"total_moth", "OK");


            /////// Проверяем на наличие измерений total_moth сщ статусом ERROR /////////////////////////////


            System.out.println("\n Проверяем есть ли TOTAL_MOTH ERROR измерение за указанную дату");

            List<Operation> total_moth_error= customerService.findOperation_daily(customer.getId(), tstamp, "total_moth", "ERROR");
            if (total_moth_error.size()>0){
                System.out.println("Измерение TOTAL_MOTH ERROR присутствует");
                System.out.println("Размер массива TOTAL_MOTH = "+ total_moth_error.size());
                System.out.println("Удаляем ошибочное измерение и повторяем запрос ");
                /// Удаление
                Customer cust=customerService.findById(customer.getId());
                Set<Operation> operationSet=cust.getOperationSet();
                Operation toDelOperation=null;
                for( Operation operation:operationSet){
                    if (operation.getId().equals(total_moth_error.get(0).getId())){
                        toDelOperation=operation;
                        version_oper_moth=toDelOperation.getVersion()+1;
                    }
                }
                operationSet.remove(toDelOperation);
                customerService.save(cust);
                /// конец удаления
                System.out.println("проверяем результаты удаления ");
                total_moth_error= customerService.findOperation_daily(customer.getId(), tstamp, "total_moth", "ERROR");
                System.out.println("Размер массива TOTAL_MOTH после удаления =  "+  total_moth_error.size());



            }else{
                System.out.println("Измерения ERROR нет");
            }


            /////////// конец проверки на наличие измерений total_moth сщ статусом ERROR /////////////////////////////




// Если ни одного измерения итоговых за месяц нет, то выполняем итоговые за месяц type=3
            atomicInteger.addAndGet(1);

            //// Выполняем проверку на наличие записи итогового арзива в теплосчетчике. Проверяем дату начала суточного архтва и сравниваем с нашей датой
            Timestamp begin_work = date_3ff6.get(2);
            System.out.println("Дата начала записи суточных архивов (TimeStamp) --- "+begin_work);

            LocalDateTime begin_arhive =  begin_work.toLocalDateTime();
            System.out.println("Переводим в LocalData Time --- "+begin_arhive);




            date_before = 0;
            if (begin_arhive.isBefore(data_type3.with(TemporalAdjusters.firstDayOfMonth()))){

                System.out.println("Дата начала работы до запрашиваемой даты. Запрос ИТОГОВОГО АРХИВА возможен");
                date_before = 1;
            }
            else{
                System.out.println("ИТОГОВЫЙЙ АРХИВ за "+ data_type3 +" отсутствует, но запрос не возможен. Начало записи " + begin_arhive );
            }


            System.out.println("Проверяем наличие измерения total_moth "+  tstamp);
            System.out.println("customer.getId()"+ customer.getId());
            System.out.println("tstamp "+  tstamp);
            System.out.println("date_before "+  date_before);

            System.out.println("Результат размер массива ===== "+  totalMoth.size());



            if (totalMoth.size()==0&date_before==1){

                moth=1;


////(!)(!)(!)(!)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////(!)(!)(!)(!)//////////////////////Меняем тип запрашиваемго архива на ИТОГОВЫЙ АРХИВ////////////////////////////////////////////////
////(!)(!)(!)(!)//////////////////////type=3 /////////////////////////////////////////////////////////////////////////////////////////
                atomicInteger.addAndGet(1);

                /**  ////////////////////////////////////////////////////////////////////////////
                 * 3F FD  10  (s20 - >21) Запрос на запись типа значения. Требует подтверждения.//////
                 */  ////////////////////////////////////////////////////////////////////////////

                //List<String> ff_n=new ArrayList<>();
                System.out.println("\nФормируем новый type= 3 (ИТОГОВЫЙ АРХИВ) 3F FD Запрос на запись ТИПА значений");
                type_to_error="total_moth";


                /**
                 *
                 * @param number номер узла ДОЛЖЕН БЫТЬ INTEGER
                 * @param type тип измерений.  ДОЛЖЕН БЫТЬ INTEGER
                 * @return Массив строки команды
                 * 1 объект
                 */
                command_3FFD = null;
                command_3FFD = send10Service.s_3FFD(number, 3);

                System.out.println("\n Полученная команда после добавления всего");

                command_3FFD.forEach(p -> System.out.print(p + " "));

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                /**
                 * Получили строку для получения данных по измерению "текущие"
                 * добавляем контрольную сумму CRC
                 * получаем массив crc - полную строку с контрольной cуммой
                 */

                crc = crc16Service.crc16_t(command_3FFD);
                System.out.println("\n Добавили контрольную сумму");
                crc.forEach(p -> System.out.print(p + " "));
                System.out.println("\n command +CRC = ");
                for (int i = 0; i < crc.size(); i++) {
                    System.out.print(crc.get(i) + " ");
                }
                System.out.println();


                request = null;
                request = new int[crc.size()];

                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                }

                atomicInteger.addAndGet(1);
                System.out.println("\n посылаем запрос 3F FD Запрос на запись ТИПА значений.");
                Thread.sleep(5000);
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                temp = "";
                data2 = "";
                step = 20;
                serialPort.writeIntArray(request);



                t = 0;
                repeat = 0;
                executor.submit(callable(3));
                r_3fff = false;
                while (step == 20) {
                    if (data2.contains("3F FD ")) {
                        t = 1;
                        //System.out.println("После запроса 3F FF_n ");
                        System.out.println("Запрс 3F FD; data2 = " + data2);
                        r_3fff = recieve10Service.r_3FFD(data2);
                    }
                    if (r_3fff) {
                        System.out.println("\n Команда 3F FD (Запрос на запись ТИПА прошла. Принятые данные ::  " + data2);
                        Thread.sleep(1000);
                        data2 = "";
                        step = 21;
                        System.out.println();
                    }

                    if (t == 2) {
                        repeat++;
                        if (repeat == 6) {
                            step = 0;

                            System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                            System.out.println("\n error=21.3F FD TimeOut");
                            error = 21;
                            stop = false;
                        }

                        System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                        serialPort.writeIntArray(request);
                        t = 0;
                        executor.submit(callable(4));
                    }

                }

                atomicInteger.addAndGet(1);

                /**  ////////////////////////////////////////////////////////////////////////////
                 * 3F FB  10  (s30 - >31) 4.4 Запрос на запись даты. Требует подтверждения.//////
                 */  ////////////////////////////////////////////////////////////////////////////



             data_type3 =LocalDateTime.of(LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()),LocalTime.of(23,00));

             System.out.println(data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")));


             System.out.println("\nФормируем 3F FB  " + data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")) +" 4.4 Запрос на запись даты");



                atomicInteger.addAndGet(1);

                List<String> command_3FFB = send10Service.s_3FFB(number, data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")));

                System.out.println("\n Полученная команда после добавления всего");

                command_3FFB.forEach(p -> System.out.print(p + " "));
                /**
                 * Получили строку для получения данных по измерению "текущие"
                 * добавляем контрольную сумму CRC
                 * получаем массив crc - полную строку с контрольной cуммой
                 */
                crc = crc16Service.crc16_t(command_3FFB);
                System.out.println("\n Добавили контрольную сумму");
                //crc.forEach(p -> System.out.print(p + " "));
                System.out.println("\n command +CRC = ");
                for (int i = 0; i < crc.size(); i++) {
                    System.out.print(crc.get(i) + " ");
                }
                System.out.println();


                request = null;
                request = new int[crc.size()];

                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                }
                step = 20;
                data2 = "";
                temp = "";
                atomicInteger.addAndGet(1);
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                System.out.println("\n посылаем запрос 3F FB 4.4 Запрос на запись даты."+ data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")));
                Thread.sleep(5000);
                serialPort.writeIntArray(request);


                t = 0;
                repeat = 0;

                executor.submit(callable(6));
                r_3fff = false;
                while (step == 20) {
                    if (data2.contains("3F FB ")) {
                        t = 1;
                        //System.out.println("После запроса 3F FF_n ");
                        System.out.println("Запрс 3F FB;" + data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")) + " data2 = " + data2);
                        r_3fff = recieve10Service.r_3FFB(data2);
                    }
                    if (r_3fff) {
                        System.out.println("\n Команда 3F FB (4.4 Запрос на запись даты Принятые данные ::  " + data2);
                        Thread.sleep(1000);
                        data2 = "";
                        step = 150;
                        System.out.println();
                    }

                    if (t == 2) {
                        repeat++;
                        if (repeat == 6) {
                            step = 0;

                            System.out.println("\n Ответ не поступил." + data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")) + " Ошибка по таймауту.");
                            System.out.println("\n error=22.3F FB TimeOut");
                            error = 22;
                            stop = false;
                        }

                        System.out.println("\n Ответ не поступил." + data_type3.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH")) + " Ошибка по таймауту. Повторяем запрос");
                        serialPort.writeIntArray(request);
                        t = 0;
                        executor.submit(callable(6));
                    }

                }
                atomicInteger.addAndGet(1);
                Thread.sleep(2000);
                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                atomicInteger.addAndGet(1);

                /**       //////////////////////////////////////////////////////////////////////
                 * 3F FE  (03)  s11 -> s12 Запрос на чтение данных  ////////////////////////////////////////
                 */
                //////////////////////////////////////////////////////////////////////

                System.out.println("\n Формируем запрос 3F FE ИТОГОВЫЕ ТЕКУЩИЕ чтение ");
                ff_2 = null;
                ff_2 = send03Service.s_3FFE(number);
                //System.out.println();
                crc = crc16Service.crc16_t(ff_2);
                request = null;
                request = new int[crc.size()];
                //System.out.println("\n crc size = "+ crc.size());
                for (int i = 0; i < crc.size(); i++) {
                    request[i] = Integer.parseInt(crc.get(i), 16);
                    // System.out.print(+i+":"+request[i]+" ");
                }
                //System.out.println("\n request size = "+ request.length);
                Thread.sleep(5000);
                System.out.println("\nЖдем получения данных ИТОГОВЫЕ ТЕКУЩИЕ после команды 3F FE   " );
                data2 = "";
                step = 21;
                recieve_all_byte = 0;
                t = 0;
                repeat = 0;
                executor.submit(callable(5));
                serialPort.writeIntArray(request);

                atomicInteger.addAndGet(1);
/**
 * Ждем начала приема длинных данных.
 */

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }

                while (recieve_all_byte == 0) {
                    if (t == 2) {
                        repeat++;
                        if (repeat == 6) {
                            step = 0;

                            System.out.println("\n Ответ не поступил 3F FE(ИТОГОВЫЕ ТЕКУЩИЕ). Ошибка по таймауту.");
                            System.out.println("\n error=23.3F FE TimeOut");
                            error = 23;
                            stop = false;
                        }
                        System.out.println("\n Ответ на 3F FE   не поступил. Ошибка по таймауту. Повторяем запрос");
                        temp = "";
                        z = 0;
                        t = 0;
                        data2 = "";
                        serialPort.writeIntArray(request);

                        executor.submit(callable(4));
                    }

                }
                t = 1;
                atomicInteger.addAndGet(1);

                System.out.println("\nДанные 3F FE (ИТОГОВЫЕ ТЕКУЩИЕ) поступили.");
                System.out.println("Ожидаем обработку принятых данных 3F FE ");

                if (stop == false) {
                    System.out.println("\n Получена команда STOP ");
                    break;
                }
                while (step == 22) {


                    System.out.println("Принятая строка 3F FE :: " + data2);
                    //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                    //Проверяем контрольную сумму. Если !=true то закрываем порт
                    if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList(data2.replace(" ", "").split("(?<=\\G.{2})")))) != true) {
                        step = 0;
                        System.out.println("\n Ошибочная контрольная сумма CRC16 ");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт" + data2);
                        serialPort.closePort();
                    }

                    System.out.println("Контрольная сумма верна ");



                    atomicInteger.addAndGet(1);

                    measurementsList_moth = recieve03Service.r_3FFE_Measurements(data2, current_measur);

                    System.out.println("обработка завершена");
                    System.out.println("размер measurementsList после =" + measurementsList.size());
                    Thread.sleep(1000);
                    System.out.println("размер measurementsList после через 1с=" + measurementsList.size());
                    step = 60;

                }

                atomicInteger.addAndGet(1);

                System.out.println("Записываем ИТОГОВЫЕ ТЕКУЩИЕ !!! Проверяем даты");

                total_moth_hashMap.put(tstamp, measurementsList_moth);




                System.out.println("//////////////////////////////////////////////////////////////////");
                System.out.println("Итоговые после подпрограммы");
                for(Measurements measurements:measurementsList){
                    System.out.println(measurements.getText()+" = " +measurements.getMeasurText()+" байт качества -"+measurements.getQualityText()+"NS -"+measurements.getNs());

                }

                System.out.println("//////////////////////////////////////////////////////////////////");

                System.out.println("Итоговые из total_moth_hashMap");

                System.out.println("tstamp для итогового== "+tstamp);
                List<Measurements> testlist=total_moth_hashMap.get(tstamp);
                for(Measurements measurements:testlist){
                    System.out.println(measurements.getText()+" = " +measurements.getMeasurText()+" байт качества -"+measurements.getQualityText()+"NS -"+measurements.getNs());

                }


                System.out.println("//////////////////////////////////////////////////////////////////");




                atomicInteger.addAndGet(1);


            }
//// конец проверки по наличию итоговых за месяц type=3







///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 * Фаза получения данных АРХИВНЫЕ (Суточные) (Часовые) ИТОГОВЫЙ АРХИВ закончена /////////////////////////////////////////////////////////////////////////////////////////////
 * Закрываем соединение  ///////////////////////////////////////////////////////////////////////////////////////////////
 */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            //////////////////////////////////////////////////////////////////////////////



        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        break;
        }
            atomicInteger.addAndGet(1);

        Thread.sleep(3000);
        System.out.println("\n Переходим в сервисный режим (+++)");
        step=0;
        serialPort.writeBytes("+++".getBytes());
        Thread.sleep(1000);
        serialPort.writeBytes("+++".getBytes());
       // System.out.println("\n Закрываем порт "+data);
      //  serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
       //         SerialPort.FLOWCONTROL_RTSCTS_OUT);
      //  Thread.sleep(500);
        System.out.println("\n Разрываем связь");
        serialPort.writeBytes("ATH\r".getBytes());
        Thread.sleep(5000);
        System.out.println("\n После разрыва связи "+data);

     //   serialPort.closePort();

        if (stop==true){
            System.out.println ("Программа закончила работу. Команды STOP не поступало");
            status="OK";
        }
        else
        {status="ERROR";}



        Timestamp timestamp_date_input;

            atomicInteger.addAndGet(1);

    if(error==0&status.equals("OK")&moth==1&date_before==1) {
        System.out.println("В базе отсутствовал Итоговый за мессяц. Начинаем запись");
        Thread.sleep(2000);
        // Получили времы сервера для записиси в базу
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);


        Instant date_input = date.toInstant();
        System.out.println("Смотрим пришедшую дату для действий (тип Date) " + date);
        LocalDateTime date_input_ldt = LocalDateTime.ofInstant(date_input, ZoneId.systemDefault());
        System.out.println("перевели пришедшую дату (тип LocalDataTime ) " + date_input_ldt);
        timestamp_date_input = Timestamp.valueOf(date_input_ldt);

/*
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());*/

        for (Timestamp ts : total_moth_hashMap.keySet()) {



            Operation operation = new Operation();
            operation.setTypeOperation("total_moth");
            operation.setServerVersion(String.valueOf(server_version));
            operation.setProgrammVersion(service_information.get(0));
            operation.setShemaTv13Ff9(service_information.get(1));
            operation.setTp3Tv1(service_information.get(2));
            operation.setT5Tv1(service_information.get(3));
            operation.setShemaTv23Ff9(service_information.get(4));
            operation.setTp3Tv2(service_information.get(5));
            operation.setT5Tv2(service_information.get(6));
            operation.setIdentificator(service_information.get(7));
            operation.setNetNumber(service_information.get(8));
            operation.setModel(service_information.get(10));
            operation.setBeginHourDate(date_3ff6.get(0));
            operation.setCurrentDate3Ff6(date_3ff6.get(1));
            operation.setBeginDayDate(date_3ff6.get(2));
            //operation.setDateVkt3Ffb();
            operation.setDateServer(timestamp);
            operation.setChronological(tstamp);
            operation.setShemaTv13Ecd(String.valueOf(shema_Tb1));
            operation.setShemaTv23F5B(String.valueOf(shema_Tb2));
            operation.setBaseNumber(String.valueOf(number_active_base));
            operation.setStatus(status);
            operation.setError(String.valueOf(error));
            if(version_oper_moth>0){
                operation.setVersion(version_oper_moth);
            }


            //measurementsList.forEach(p -> operation.addMeasurements(p));
            total_moth_hashMap.get(ts).forEach(p -> operation.addMeasurements(p));
            List<Measurements> testlist=total_moth_hashMap.get(ts);
            for(Measurements measurements:testlist){
                System.out.println(measurements.getText()+" = " +measurements.getMeasurText()+" байт качества -"+measurements.getQualityText()+"NS -"+measurements.getNs());

            }

            customer=customerService.findById(id_c);
            //Customer customer = customerService.findById(id);
            operation.setIdCustomer(customer.getId());
            operation.setCustomerName(customer.getFirstName());

            customer.addOperation(operation);
            customerService.save(customer);
        }


        System.out.println("Запись значений ИТОГОВОГО архива произведена. ");

    }

            atomicInteger.addAndGet(1);







    if(error!=0&status!="OK") {


    /**
     * При возникновении ошибки делвем запись в базу
     * дата операции
     * дата требуемого дня
     * код ошибки
     * статус
     */
    System.out.println("Работа прервана либо выполнена с ошибкой. Ошибка =" + error);
    Operation operation = new Operation();
    operation.setTypeOperation(type_to_error);

        System.out.println("Запрашиваемая дата Date = "+date);

        System.out.println("Запрашиваемая дата TimeStamp = "+ts_oper);

        ts_oper=auxiliaryService.date_TimeStamp(auxiliaryService.addTime(date,"23"));
        System.out.println("Запрашиваемая дата TimeStamp = "+ts_oper);
    LocalDateTime localDateTime = LocalDateTime.now();
    Timestamp timestamp = Timestamp.valueOf(localDateTime);

    operation.setDateServer(timestamp);
    operation.setChronological(ts_oper);

    if(error==0&status.equals("ERROR")){
        error=5555;
    }



   // operation.setError(String.valueOf(error));
    operation.setError(error(error));

    operation.setStatus(status);
        if(version_oper>1){
            operation.setVersion(version_oper);
        }
    //Customer customer = customerService.findById(id);
    System.out.println("FirstName()= "+customer.getFirstName());
    operation.setIdCustomer(customer.getId());
    operation.setCustomerName(customer.getFirstName());
    System.out.println("FirstName()= "+operation.getCustomerName());

    customer.addOperation(operation);
    customerService.save(customer);



}
    System.out.println("Запись произведена");



    stop=true;
    error=0;
////// закрываем цикл for перебора Customer
}

        executor.shutdownNow();


        System.out.println("\n Поток отработал. Закрываем соединение и PORT");
        logger.info("\n Поток отработал. Закрываем соединение и PORT");
        Thread.sleep(1000);
        System.out.println("\n Переходим в сервисный режим (+++)");
        step=0;
        serialPort.writeBytes("+++".getBytes());
        Thread.sleep(2000);
        System.out.println("\n Разрываем связь");
        serialPort.writeBytes("ATH\r".getBytes());
        Thread.sleep(5000);
        System.out.println("\n После разрыва связи "+data);
        System.out.println("\n Проверяем открыт ли порт  ");

        if (serialPort.isOpened())
        {System.out.println(" Port открыт ");}
            else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}

         System.out.println("\n Закрываем порт ");

        serialPort.closePort();
            if (serialPort.isOpened()) {System.out.println(" Port ЕЩЕ открыт ");}
            else {System.out.println(" Port закрыт ");}
        end=true;


        return "OK";
    }



//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {
                   if(stop==false){
                       System.out.println("Ответ из вспомогательного потока. Поступила команда STOP "+ stop);
                       return 1;}
                System.out.print(i);
                    TimeUnit.SECONDS.sleep(1);
                    if (t == 1) {
                        System.out.println("Ответ получен. Таймер остановлен");
                        return 1;
                    }
                System.out.print("\r");
                }
                System.out.println("timeout error");
                t = 2;
                return 0;


        };
    }


    public void listOperationWithDetail(List<Operation> operationList) {
        System.out.println("");
        System.out.println("Listing operation with details:");

        for (Operation oper: operationList) {
            System.out.println(oper);
            if (oper.getMeasurementsSet() != null) {
                for (Measurements measurements:
                        oper.getMeasurementsSet()) {
                    System.out.println(measurements);
                }
            }

            System.out.println();
        }
    }

///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

    public String error(int error){
        String error_str="";
        if (error == 1){
            error_str="1. Ошибка ATH ";
        }
        if (error == 2){
            error_str="2. Ошибка AT+CREG. Нет данных";
        }
        if (error == 3){
            error_str="3. Ошибка регистрации";
        }
        if (error == 4){
            error_str="";
        }
        if (error == 5){
            error_str="5.NO CARRIER";
        }
        if (error == 6){
            error_str="6.NO DIALTONE";
        }
        if (error == 7){
            error_str="7.Ответ BUSY или NO ANSWER";
        }
        if (error == 8){
            error_str="8.Связь. Ошибка по TimeOut";
        }
        if (error == 9){
            error_str="";
        }
        if (error == 10){
            error_str="10.3F FF_n TimeOut";
        }
        if (error == 11){
            error_str="11.3F FE 65 байт.";
        }
        if (error == 12){
            error_str="12.3F FE CRC16 Error";
        }
        if (error == 13){
            error_str="13.3F F9 TimeOut";
        }
        if (error == 14){
            error_str="14.3F FF TimeOut";
        }
        if (error == 15){
            error_str="15.3F FE TimeOut";
        }
        if (error == 16){
            error_str="16.3F FE CRC16";
        }
        if (error == 17){
            error_str="17.3F F6 TimeOut";
        }
        if (error == 18){
            error_str="18.3F F6 CRC";
        }
        if (error == 19){
            error_str="19.3F FC TimeOut";
        }
        if (error == 20){
            error_str="20.3F FF TimeOut";
        }
        if (error == 21){
            error_str="21.3F FD TimeOut";
        }
        if (error == 22){
            error_str="22.3F FB TimeOut";
        }
        if (error == 23){
            error_str="23.3F FE TimeOut";
        }
        if (error == 24){
            error_str=" 24.3F CD TimeOut";
        }
        if (error == 25){
            error_str="25.3F CD CRC";
        }
        if (error == 26){
            error_str="26.3F 5B TimeOut";
        }
        if (error == 27){
            error_str="27.3F 5B TimeOut";
        }
        if (error == 28){
            error_str="28.3F E9 TimeOut";
        }

        if (error == 1000){
            error_str="error=1000.3F FE ServerVersion=0";
        }

        if (error == 5555){
            error_str="Прервано пользователем";
        }

        return error_str;
    }


    public void modem_init(SerialPort serialPort, ru.javabegin.training.vkt7.modem_cron.EventListener_cron eL) throws SerialPortException, InterruptedException {

        if (serialPort.isOpened())
        {System.out.println(" Port открыт ");}
        else {System.out.println(" Port УЖЕ ЗАКРЫТ ");}

        System.out.println("\n Закрываем порт ");

        serialPort.closePort();
        if (serialPort.isOpened()) {System.out.println(" Port ЕЩЕ открыт ");}
        else {System.out.println(" Port закрыт ");}
        Thread.sleep(5000);
        serialPort.openPort (); //Метод открытия порта
        serialPort.setParams (SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                SerialPort.FLOWCONTROL_RTSCTS_OUT);
        serialPort.setEventsMask (SerialPort.MASK_RXCHAR);
        if (serialPort.isOpened())
        {System.out.println(" Port открыт ");}
        else {System.out.println(" Port ЗАКРЫТ ");}

        serialPort.addEventListener (eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
        //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

        eL.setSerialPort(serialPort);
        Thread.sleep(1000);

        serialPort.writeBytes("+++".getBytes());
        System.out.println("\nпосылаем ATH");
        Thread.sleep(1000);
        serialPort.writeBytes("ATH\r".getBytes());
        System.out.println("\nпосылаем AT&FL1E0V1&D2X4S7=120S10=90");
        Thread.sleep(1000);
        serialPort.writeBytes("AT&FL1E0V1&D2X4S7=120S10=90\r".getBytes());

    }

}
