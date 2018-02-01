package ru.javabegin.training.tv7.modem;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.initDataClass.InitData;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.tv7.recieve.ModBusRServiceImpl;
import ru.javabegin.training.tv7.recieve.Tupel_date;
import ru.javabegin.training.tv7.save.SaveServiceImpl;
import ru.javabegin.training.tv7.send.ModBusServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;


/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Modem_cron extends EventListener_tv7 {
 //SerialPort serialPort; /*Создаем объект типа SerialPort*/
 public static AtomicInteger atomicInteger;
    public static volatile boolean end_tv7;

    public static String data;

    public int error= 0;
    public int in_work= 0;
    private String type_to_error = "";


    public static boolean r_3fff;
    public int server_version=0;


    private  List<Date> dateList;
    private  Timestamp tstamp;
    private Timestamp timestamp_daily;
    static EventListener_tv7 eL;
    private File log_revizor;
    private File log_cron;

   private static int requestNum = 0;
   /*private static int[][] requests = {
           {0xFF, 0xFF, 0x01, 0x10, 0x3F, 0xFF, 0x00, 0x00, 0xCC, 0x80, 0x00, 0x00, 0x00, 0x60, 0xA8},
           {0xFF, 0xFF, 0x01, 0x03, 0x3F, 0xFE, 0x00, 0x00, 0x28, 0x2E }, {00, 00, 03}};*/

    private static int[] request=new int[256];



    //public String tv7() throws InterruptedException, TimeoutException, ExecutionException, SerialPortException, IOException {
    public static void tv7_cron(CustomerService customerService) throws InterruptedException, TimeoutException, ExecutionException, SerialPortException, IOException {

        end_tv7=false;

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
        //Customer customer;
        String tel="";
        String status="";
        int moth=0;
        int error=0;
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

         eL= new EventListener_tv7();


        serialPort.addEventListener (eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
        //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

        eL.setSerialPort(serialPort);
           /* eL.setData(data);
           // eL.setData2(data2);
            eL.setRecieve_all_byte(recieve_all_byte);*/


        ExecutorService executor = Executors.newFixedThreadPool(3);





        atomicInteger.addAndGet(1);

        List<Customer> customerList=customerService.findAllWithDetailTv7();

        AuxDateTimeServiceImpl dateTimeService=new AuxDateTimeServiceImpl();
        LocalDateTime ldtime = LocalDateTime.now().minusDays(1);
        LocalDateTime ldtime_month=ldtime;
        List<LocalDateTime> listDate=dateTimeService.from_the_beginning_of_month(ldtime);

        for (Customer customer:customerList) {
            System.out.println("\nКлиент ---------- " + customer.getFirstName());

            tel=customer.getTelModem();



                //
                // Проверяем наличие измерения СУТОЧНЫЕ за указанный день
                // если есть, то пропускаем клиента (для ускорения)

                List<Operationtv7> listtv7 = customerService.findOperationtv7ByDate("day",customer.getId(),ldtime);

                if (listtv7.size()!=0){
                    System.out.println(customer.getFirstName()+ " Измерения за "+ ldtime +" присутствуют" );
                    continue;

                }
                System.out.println(customer.getFirstName()+ " -- Измерений за "+ ldtime +" НЕТ" );

            /**
             * Если измерений за указанный день нет, то подключаемся к клиену
             */


            Thread.sleep(5000);






            stop = true;
            atomicInteger.addAndGet(1);

            while (stop != false) {
                try {


                    step = 100;
                    data2 = "";


                    t = 0;
                    int repeat = 0;
                    serialPort.writeBytes("+++".getBytes());
                    Thread.sleep(1000);

                    serialPort.writeBytes("+++".getBytes());
                    Thread.sleep(1000);
                    executor.submit(callable(3,"посылаем ATH"));
                    System.out.println("\nпосылаем ATH");
                    serialPort.writeBytes("ATH\r".getBytes());

                    atomicInteger.addAndGet(1);
                    while (step == 100 & stop != false) {

                        if (data2.contains("OK")) {
                            t = 1;

                            System.out.println("\n Инициализация модема выполнена. Принятые данные ::  " + data2);
                            step = 0;
                        }
                        if (t == 2) {
                            repeat++;
                            if (repeat == 7) {

                                if (serialPort.isOpened()) {
                                    System.out.println(" Port открыт ");
                                } else {
                                    System.out.println(" Port УЖЕ ЗАКРЫТ ");
                                }

                                System.out.println("\n Закрываем порт ");

                                serialPort.closePort();
                                if (serialPort.isOpened()) {
                                    System.out.println(" Port ЕЩЕ открыт ");
                                } else {
                                    System.out.println(" Port закрыт ");
                                }
                                Thread.sleep(5000);
                                serialPort.openPort(); //Метод открытия порта
                                serialPort.setParams(SerialPort.BAUDRATE_9600,
                                        SerialPort.DATABITS_8,
                                        SerialPort.STOPBITS_1,
                                        SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
                                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                        SerialPort.FLOWCONTROL_RTSCTS_OUT);
                                serialPort.setEventsMask(SerialPort.MASK_RXCHAR);


                            }


                            if (repeat == 6) {
                                step = 0;

                                System.out.println("\n Ответ инициализация модема не поступил. Ошибка по таймауту.");
                                System.out.println("\n error=1.");
                                error = 1;
                                stop = false;

                            }
                            step=300;
                            System.out.println("\n Ответот модема не поступил Ошибка по таймауту. Повторяем запрос");

                            if (serialPort.isOpened()) {
                                System.out.println(" Port открыт ");
                            } else {
                                System.out.println(" Port УЖЕ ЗАКРЫТ ");
                            }

                            System.out.println("\n Закрываем порт ");

                            serialPort.closePort();
                            if (serialPort.isOpened()) {
                                System.out.println(" Port ЕЩЕ открыт ");
                            } else {
                                System.out.println(" Port закрыт ");
                            }
                            Thread.sleep(5000);
                            serialPort.openPort(); //Метод открытия порта
                            serialPort.setParams(SerialPort.BAUDRATE_9600,
                                    SerialPort.DATABITS_8,
                                    SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
                            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
                            serialPort.setEventsMask(SerialPort.MASK_RXCHAR);
                            if (serialPort.isOpened()) {
                                System.out.println(" Port открыт ");
                            } else {
                                System.out.println(" Port ЗАКРЫТ ");
                            }

                            serialPort.addEventListener(eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
                            //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

                            eL.setSerialPort(serialPort);

                            Thread.sleep(1000);
                            t = 0;

                            data2 = "";
                            serialPort.writeBytes("+++".getBytes());

                            System.out.println("\nпосылаем ATH");
                            Thread.sleep(1000);
                            executor.submit(callable(3,"посылаем ATH"));
                            serialPort.writeBytes("ATH\r".getBytes());

                        }


                    }


                    atomicInteger.addAndGet(1);


                    Thread.sleep(1000);
                    if (!stop) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }
                    System.out.println("\n посылвем AT+CREG? ");
                    serialPort.writeBytes("AT+CREG?\r".getBytes());
                    System.out.println("\n Ждем данных от модема ");

                    t = 0;
                    executor.submit(callable(3,"посылвем AT+CREG?"));
                    step = 0;
                    while (data2 == null) {

                        if (t == 2) {
                            System.out.println("\n Ответ от модема не поступил. Ошибка по таймауту.");
                            System.out.println("\n error=2. Ошибка AT+CREG");
                            error = 2;
                            stop = false;
                            break;
                        }
                    }
                    t = 1;
                    System.out.println("\n Ответ от можема получен");
                    System.out.println("\n Проверка регистрации модема");
                    Thread.sleep(1000);

                    t = 0;
                    executor.submit(callable(3,"Проверка регистрации модема"));

                    while (step == 0 & stop == true) {

                        if (data2.contains("+CREG: 1,") || data2.contains("+CREG: 0,1") || data2.contains("+CREG: 2,1")) {
                            t = 1;
                            System.out.println("\n Регистрация в сети произведена = " + data2);

                            step = 1;
                        }


                        if (t == 2) {
                            System.out.println("\n Регистрация модема не произведена. Ошибка по таймауту.");
                            System.out.println("\n error=3. Ошибка регистрации");
                            error = 3;
                            stop = false;
                        }
                    }


                    atomicInteger.addAndGet(1);


                    //Thread.sleep(5000);
                    System.out.println("\nпосылаем AT&FL1E0V1&D2X4S7=120S10=90");
                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }
                    serialPort.writeBytes("AT&FL1E0V1&D2X4S7=120S10=90\r".getBytes());
                    while (step == 1 & stop == true) {

                        if (data2.contains("OK")) {
                            System.out.println("\n Команда AT&FL1E0V1&D2X4S7=120S10=90 прошла" + data2);

                            step = 5555;
                        } else {
                            System.out.println("\n Ошибка команды AT&FL1E0V1&D2X4S7=120S10=90 = " + data2);
                            Thread.sleep(500);
                        }

                    }


                    atomicInteger.addAndGet(1);

                    Thread.sleep(1000);
                    System.out.println("\nНабираем номер");

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                    //serialPort.writeBytes("ATDP+79064427287\r".getBytes());
                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }


        /*   while(repeat!=1000){
                logger.info("Тестовое зависание");
                System.out.println("Тестовое зависание программы");
                Thread.sleep(20000);

            }*/

                    if (tel.equals("")) {
                        tel = "ATDP+79064628305\r"; //Акварель
                        //tel="ATDP+79064427267\r";//ЗАО "Фудстар"
                        // tel="ATDP+79054125173\r";//ООО "Инвестиции "Запод"

                    } else {
                        tel = "ATDP" + tel + "\r";
                    }


                    //serialPort.writeBytes("ATDP+79064426645\r".getBytes());


                    step = 777;
                    data2 = "";
                    temp = "";
                    serialPort.writeBytes(tel.getBytes());
                    //serialPort.writeBytes("ATDP+79064427319\r".getBytes());

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!


                    int c = 0;
                    System.out.print("\n Ждем установки связи :");


                    t = 0;
                    int call = 0;
                    executor.submit(callable(60,"установки связи первое"));

                    while (step == 777 & stop == true) {

                        if (data2.contains("CONNECT 9600/RLP")) {
                            System.out.println("ответ пришел t= " + t);
                            System.out.println("\n Связь Установлена!! Продолжаем работать " + data2);
                            Thread.sleep(2000);


                            t=1;


                            step = 300;
                        } else if (data2.contains("NO CARRIER") & call < 3) {
                            t = 0;
                            data2 = "";
                            temp = "";
                            Thread.sleep(1000);
                            serialPort.writeBytes(tel.getBytes());
                            executor.submit(callable(60,"установки связи NO CARRIER"));
                            call = call + 1;

                        } else if (data2.contains("NO CARRIER") & call == 3) {
                            System.out.println("Ответ  NO CARRIER ответ пришел t= " + t);


                            step = 0;
                            System.out.println("\n Нет Связи!! Закрываем связь" + data2);

                            System.out.println("\n error=5.NO CARRIER");
                            error = 5;
                            stop = false;
                        } else if (data2.contains("NO DIALTONE") & call < 3) {
                            System.out.println("Ответ NO DIALTHONE ответ пришел t= " + t);
                            t = 0;
                            data2 = "";
                            temp = "";
                            modem_init(serialPort, eL);
                            System.out.println("Повторяем набор номера. call = " + call);
                            Thread.sleep(1000);
                            serialPort.writeBytes(tel.getBytes());
                            executor.submit(callable(60, "установка связи после NO DIALTONE"));
                            call = call + 1;

                        } else if (data2.contains("NO DIALTONE") & call == 3) {
                            System.out.println("Ответ NO DIALTHONE ответ пришел t= " + t);

                            step = 0;
                            System.out.println("\n Нет Связи!! Закрываем связь" + data2);
                            System.out.println("\n error=6.NO DIALTONE");
                            error = 6;
                            stop = false;
                        } else if (data2.contains("BUSY") || data2.contains("NO ANSWER")) {
                            System.out.println("Ответ BUSY или NO ANSWER   ответ пришел t= " + t);


                            step = 0;
                            System.out.println("\n Нет Связи!! Закрываем связь" + data2);
                            System.out.println("\n error=7.Ответ BUSY или NO ANSWER");
                            error = 7;
                            stop = false;
                        }

                        if (t == 2 & call < 3) {
                            t = 0;
                            data2 = "";
                            temp = "";
                            Thread.sleep(1000);
                            serialPort.writeBytes(tel.getBytes());
                            executor.submit(callable(60, "t == 2 & call < 3 "));
                            call = call + 1;

                        }


                        if (t == 2 & call == 3) {
                            t = 0;
                            step = 0;
                            System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                            System.out.println("\n error=8.Связь. Ошибка по TimeOut");
                            error = 8;
                            stop = false;

                        }

                    }

                    atomicInteger.addAndGet(1);

                    Thread.sleep(1000);
                    serialPort.setParams(SerialPort.BAUDRATE_9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                            SerialPort.FLOWCONTROL_RTSCTS_OUT);

                    /////// Инициализируем имплементирующие классы ////////////////////////////

                    ModBusServiceImpl modBusService = new ModBusServiceImpl();
                    LrcServiceImpl lrcService = new LrcServiceImpl();
                    AscServiceImpl ascService = new AscServiceImpl();
                    ModBusRServiceImpl modBusRService = new ModBusRServiceImpl();
                    InitData initData = new InitData();
                    AuxDateTimeServiceImpl auxDateTimeService=new AuxDateTimeServiceImpl();
                    SaveServiceImpl saveService=new SaveServiceImpl();


//////////////////////////////////////////////////////////////////////////////////


t=1;


/**
 * Запрос информации об устройстве п.6.1
 */


                    System.out.println("\n Формируем запрос информации об устройстве  п.6.1");
                    System.out.println("\n step= " + step);
                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }
                    List<String> command = modBusService.typeUnit(number);
                   // command.forEach(p -> System.out.print(p + " "));

                    List<String> commandLRC = lrcService.lrcAdd(command);
                    commandLRC.forEach(p -> System.out.print(p + " "));

                    List<String> commandAsc = ascService.enctypt(commandLRC);
                    //commandAsc.forEach(p -> System.out.print(p + " "));

          /*  List<String> commandAsc=modBusService.typeUnit(number);
            commandAsc.forEach(p->System.out.print(p+" "));*/


                    request = null;
                    request = new int[commandAsc.size()];
                    for (int i = 0; i < commandAsc.size(); i++) {
                        request[i] = Integer.parseInt(commandAsc.get(i), 16);
                        // System.out.print(+i+":"+request[i]+" ");
                    }
                    //System.out.println("\n request size = "+ request.length);

                    Thread.sleep(1000);

                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }

                    System.out.println("\nЖдем получения всех данных  информации об устройстве  п.6.1 ");
                    data1.delete(0, data1.length());
                    temp1.delete(0, temp1.length());
                    outTv7 = null;


                    t = 0;
                    repeat = 0;
                    Future<String> task= executor.submit(callable(6,"- - ->>Ждем получения всех данных  информации об устройстве  п.6.1"));
                    recieve_all_byte = 0;
                    step = 255;
                    System.out.println("step= " + step);
                    z = 0;
                    serialPort.writeIntArray(request);


/**
 * Ждем начала приема длинных данных.
 */atomicInteger.addAndGet(1);

                    while (recieve_all_byte == 0 & stop != false) {
                        if (t == 2) {
                            repeat++;
                            if (repeat == 3) {
                                step = 0;

                                System.out.println("\n Ответ информации об устройстве  п.6.1 не поступил  Ошибка по таймауту.");
                                System.out.println("\n error=11. Ошибка по TimeOut");
                                error = 11;
                                stop = false;
                            }
                            System.out.println("\n Ответ информации об устройстве  п.6.1 не поступил. Ошибка по таймауту. Повторяем запрос");
                            data1.delete(0, data1.length());
                            temp1.delete(0, temp1.length());
                            outTv7 = null;
                            t = 0;
                            z = 0;
                            serialPort.writeIntArray(request);

                            executor.submit(callable(20));
                        }

                    }

                    try {
                        System.out.println("-   -   -   -   isCancelled() future " + task.isCancelled());
                        System.out.println("------- -----  Поток есть и не прерван ");

                    } catch (Exception e){
                        System.out.println("Нет еще потока "+e);

                    }

                    t = 1;
                    Thread.sleep(1000);




                    System.out.println("\nДанные по информации об устройстве  п.6.1 поступили.");
                    System.out.println("Ожидаем обработку принятых данных информации об устройстве  п.6.1");

                    atomicInteger.addAndGet(1);


                    System.out.print("Принятая строка информации об устройстве  п.6.1 :: ");
                    outTv7.forEach(p -> System.out.print(p));





    /**
     *
     *           ДАННЫЕ О ДАТАХ
     *Запрос Чтение
     *
     */

                    System.out.println("\n Формируем запрос ДАННЫЕ О ДАТАХ");


                    // LocalDateTime ldt=LocalDateTime.now().minusDays(1);
                    List<String>list = modBusService.infOfDate(0, 2);
                    // list.forEach(p-> System.out.print(p+" "));

                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 01 01 0C 17 12 00 00 00 01 00 00 00 00 E0
                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 02 0C 0C 17 7E 20 00 00 00 10 00 00 00 0 39
                    commandLRC = lrcService.lrcAdd(list);
                    commandLRC.forEach(p -> System.out.print(p + " "));

                    commandAsc = ascService.enctypt(commandLRC);
                    commandAsc.forEach(p -> System.out.print(p + " "));

          /*  List<String> commandAsc=modBusService.typeUnit(number);
            commandAsc.forEach(p->System.out.print(p+" "));*/


                    request = null;
                    request = new int[commandAsc.size()];
                    for (int i = 0; i < commandAsc.size(); i++) {
                        request[i] = Integer.parseInt(commandAsc.get(i), 16);
                        // System.out.print(+i+":"+request[i]+" ");
                    }
                    //System.out.println("\n request size = "+ request.length);

                    // Thread.sleep(2000);

                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }

                    System.out.println("\nЖдем получения ДАННЫЕ О ДАТАХ  ");
                    data1.delete(0, data1.length());
                    temp1.delete(0, temp1.length());
                    outTv7 = null;

                    t = 0;
                    repeat = 0;
                    executor.submit(callable(7," - - - >>>Ждем получения ДАННЫЕ О ДАТАХ "));
                    recieve_all_byte = 0;
                    step = 255;
                    System.out.println("step= " + step);
                    z = 0;
                    serialPort.writeIntArray(request);


                    /**
                     * Ждем начала приема длинных данных.
                     * ДАННЫЕ О ДАТАХ
                     *
                     */
                    atomicInteger.addAndGet(1);

                    while (recieve_all_byte == 0 & stop != false) {
                        if (t == 2) {
                            repeat++;
                            if (repeat == 3) {
                                step = 0;

                                System.out.println("\n Ответ ДАННЫЕ О ДАТАХ не поступил. Ошибка по таймауту.");
                                System.out.println("\n error=11.ДАННЫЕ О ДАТАХ. Ошибка по TimeOut");
                                error = 11;
                                stop = false;
                            }
                            System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                            data1.delete(0, data1.length());
                            temp1.delete(0, temp1.length());
                            outTv7 = null;
                            t = 0;
                            z = 0;
                            serialPort.writeIntArray(request);

                            executor.submit(callable(20));
                        }

                    }

                    t = 1;


                    System.out.println("\nДанные ДАННЫЕ О ДАТАХ поступили.");
                    System.out.println("Ожидаем обработку принятых данных ДАННЫЕ О ДАТАХ");

                    atomicInteger.addAndGet(1);


                    System.out.println("Принятая строка ДАННЫЕ О ДАТАХ :: ");

                    outTv7.forEach(p -> System.out.print(p));
                    Map<String,Tupel_date> infOfDate =modBusRService.infOfDate(outTv7);






////// СУТОЧНЫЕ ////// СУТОЧНЫЕ ////// СУТОЧНЫЕ ////// СУТОЧНЫЕ ////// СУТОЧНЫЕ ////// СУТОЧНЫЕ ////// СУТОЧНЫЕ

                    /**
                     * Проверяем наличие измерений Суточные для всех дат с начала месяца
                     *
                     */



                    /// Проверяем на наличие измерений с начала месяца по СУТОЧНЫЕ!!
                    for (LocalDateTime ldt:listDate ) {

                        listtv7 = customerService.findOperationtv7ByDate("day",customer.getId(),ldt);

                        if (listtv7.size()!=0){
                            System.out.println(customer.getFirstName()+ " Измерения за "+ ldt +" присутствуют" );
                            break;

                        }


                    /// Проверяем попадает запрашиваемая дата после начала записи СУТОЧНЫЕ в счетчике!!
                        if (auxDateTimeService.checkBeginArchive(infOfDate, ldt, "day")==false){
                            System.out.println(customer.getFirstName()+ "начало записи данных СУТОЧНЫЕ находятся после запрашиваемой даты "+ ldt +" Получение данные не возможно" );
                            break;
                        }

                        System.out.println(customer.getFirstName()+ " -- Измерений за "+ ldt +" НЕТ, но Данные в архиве счетчика присутствуют" );

/*
*
 *
 * Запрос Чтение СУТОЧНЫЕ
 *
 *
*/


                    System.out.println("\n Формируем запрос чтения суточных");



                   list = modBusService.archive(0, ldt, 1, 2);

                        System.out.println("\n ::::: ::: Дата для суточных -> "+ldt);

                    list.forEach(p -> System.out.print(p + " "));

                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 01 01 0C 17 12 00 00 00 01 00 00 00 00 E0
                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 02 0C 0C 17 7E 20 00 00 00 10 00 00 00 0 39
                    commandLRC = lrcService.lrcAdd(list);
                    commandLRC.forEach(p -> System.out.print(p + " "));

                    commandAsc = ascService.enctypt(commandLRC);
                    commandAsc.forEach(p -> System.out.print(p + " "));

                  /*  commandAsc=modBusService.typeUnit(number);
                    commandAsc.forEach(p->System.out.print(p+" "));
*/

                    request = null;
                    request = new int[commandAsc.size()];
                    for (int i = 0; i < commandAsc.size(); i++) {
                        request[i] = Integer.parseInt(commandAsc.get(i), 16);
                        // System.out.print(+i+":"+request[i]+" ");
                    }
                    //System.out.println("\n request size = "+ request.length);

                    Thread.sleep(1000);

                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }

                    System.out.println("\nЖдем получения СУТОЧНЫЕ  ");
                    data1.delete(0, data1.length());
                    temp1.delete(0, temp1.length());
                    outTv7 = null;

                    t = 0;
                    repeat = 0;
                    executor.submit(callable(7,"Ждем получения СУТОЧНЫЕ "));
                    recieve_all_byte = 0;
                    step = 255;
                    System.out.println("step= " + step);
                    z = 0;
                    serialPort.writeIntArray(request);


/*
*
 * Чтение СУТОЧНЫЕ
 * Ждем начала приема длинных данных.
 atomicInteger.addAndGet(1);
*/

                    while (recieve_all_byte == 0 & stop != false) {
                        if (t == 2) {
                            repeat++;
                            if (repeat == 3) {
                                step = 0;

                                System.out.println("\n Ответ СУТОЧНЫЕ не поступил. Ошибка по таймауту.");
                                System.out.println("\n error=11.3F FE 65 байт. Ошибка по TimeOut");
                                error = 11;
                                stop = false;
                            }
                            System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                            data1.delete(0, data1.length());
                            temp1.delete(0, temp1.length());
                            outTv7 = null;
                            t = 0;
                            z = 0;
                            serialPort.writeIntArray(request);

                            executor.submit(callable(20));
                        }

                    }
                    t = 1;


                    System.out.println("\nДанные СУТОЧНЫЕ поступили.");
                    System.out.println("Ожидаем обработку принятых данных СУТОЧНЫЕ");

                    atomicInteger.addAndGet(1);


                    System.out.println("Принятая строка СУТОЧНЫЕ :: ");

                   outTv7.forEach(p -> System.out.print(p));
                   String regularExpression1="^(83|90)";

                   if (Pattern.compile(regularExpression1).matcher(outTv7.get(1)).find()){

                       List<String> errList = modBusRService.errorProcessing(outTv7);

                       errList.forEach(p->System.out.println(p));
                       logger.info(customer.getFirstName()+ "  "+ldt+ " " + errList.get(0));

                       continue;

                   }
                   String regularExpression2="^(C8)";

                    if (Pattern.compile(regularExpression2).matcher(outTv7.get(1)).find()){

                        List<String> errList = modBusRService.errorProcessing(outTv7);

                        errList.forEach(p->System.out.println(p));
                        logger.info(customer.getFirstName()+ "  "+ldt+ "  ОШИБКА ЧТЕНИЯ " + errList.get(0)+ "  ОШИБКА ЗАПИСИ " + errList.get(1));

                        continue;

                    }

                   List<Parametr> parametrList = initData.initDay();

                   parametrList= modBusRService.day(outTv7, parametrList, 1);



                        ///
                        customer = customerService.findByIdTv7(customer.getId());


                    if (customer!=null){System.out.println("Кoнтакт найден, customer "+ customer.getFirstName()+" id= "+customer.getId());}

                   saveService=new SaveServiceImpl();
                    Operationtv7 operationtv7=saveService.saveDay(parametrList);
                        operationtv7.setCustomerName(customer.getFirstName());




                    customer.addOperationtv7(operationtv7);
                    customerService.save(customer);

                        ///

                        Thread.sleep(500);






                    }
 /// КОНЕЦ СУТОЧНЫЕ ////  /// КОНЕЦ СУТОЧНЫЕ ////  /// КОНЕЦ СУТОЧНЫЕ ////  /// КОНЕЦ СУТОЧНЫЕ ////


                    /**
                     * Получаем данные за МЕСЯЦ
                     *
                     */

                    //
                    // Проверяем наличие измерения МЕСЯЦ за указанный день
                    // если есть, то пропускаем клиента (для ускорения)
                    t=1;
                    ldtime= ldtime_month;


                   listtv7 = customerService.findOperationtv7ByDate("month",customer.getId(),ldtime);
                    System.out.println("Дата для МЕСЯЧНЫЙ АРХИВ :: :: "+ ldtime);
                    if (listtv7.size()!=0){
                        System.out.println(customer.getFirstName()+ " Измерения  МЕСЯЦ за "+ ldtime +" присутствуют" );
                        continue;

                    }
                    System.out.println(customer.getFirstName()+ " -- Измерений МЕСЯЦ за "+ ldtime +" НЕТ" );


                    /// Проверяем попадает запрашиваемая дата после начала записи МЕСЯЦ в счетчике!!
                    if (auxDateTimeService.checkBeginArchive(infOfDate, ldtime , "month")==false){
                        System.out.println(customer.getFirstName()+ "дата начала АРХИВа СУТОЧНЫЕ находятся после запрашиваемой даты "+ ldtime  +" Получение данные не возможно" );
                        continue;
                    }

                    System.out.println(customer.getFirstName()+ " -- Измерений СУТОЧНЫЕ за "+ ldtime  +" НЕТ, но Данные в архиве счетчика присутствуют" );





//////////////////////  МЕСЯЦ

                    System.out.println("\n Формируем запрос чтения АРХИВ ЗА МЕСЯЦ");


                    ldtime=auxDateTimeService.addTime((ldtime.minusMonths(1)).with(TemporalAdjusters.lastDayOfMonth()),"23");
                    list = modBusService.archive(0, ldtime, 2, 120);
                    list.forEach(p -> System.out.print(p + " "));

                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 01 01 0C 17 12 00 00 00 01 00 00 00 00 E0
                    // 00 48 0A B4 00 6D 00 63 00 06 00 0C 00 02 0C 0C 17 7E 20 00 00 00 10 00 00 00 0 39
                    commandLRC = lrcService.lrcAdd(list);
                    commandLRC.forEach(p -> System.out.print(p + " "));

                    commandAsc = ascService.enctypt(commandLRC);
                    commandAsc.forEach(p -> System.out.print(p + " "));

          /*  List<String> commandAsc=modBusService.typeUnit(number);
            commandAsc.forEach(p->System.out.print(p+" "));*/


                    request = null;
                    request = new int[commandAsc.size()];
                    for (int i = 0; i < commandAsc.size(); i++) {
                        request[i] = Integer.parseInt(commandAsc.get(i), 16);
                        // System.out.print(+i+":"+request[i]+" ");
                    }
                    //System.out.println("\n request size = "+ request.length);

                    Thread.sleep(1000);

                    if (stop == false) {
                        System.out.println("\n Получена команда STOP ");
                        break;
                    }

                    System.out.println("\nЖдем получения АРХИВ ЗА МЕСЯЦ  ");
                    data1.delete(0, data1.length());
                    temp1.delete(0, temp1.length());
                    outTv7 = null;

                    t = 0;
                    repeat = 0;
                    executor.submit(callable(5," - - - >>Ждем получения АРХИВ ЗА МЕСЯЦ"));
                    recieve_all_byte = 0;
                    step = 255;
                    System.out.println("step= " + step);
                    z = 0;
                    serialPort.writeIntArray(request);


/**
 * Чтение АРХИВ ЗА МЕСЯЦ
 * Ждем начала приема длинных данных.
 */atomicInteger.addAndGet(1);

                    while (recieve_all_byte == 0 & stop != false) {
                        if (t == 2) {
                            repeat++;
                            if (repeat == 3) {
                                step = 0;

                                System.out.println("\n Ответ АРХИВ ЗА МЕСЯЦ не поступил. Ошибка по таймауту.");
                                System.out.println("\n error=Ошибка по TimeOut");
                                error = 11;
                                stop = false;
                            }
                            System.out.println("\n Ответ АРХИВ ЗА МЕСЯЦ не поступил. Ошибка по таймауту. Повторяем запрос");
                            data1.delete(0, data1.length());
                            temp1.delete(0, temp1.length());
                            outTv7 = null;
                            t = 0;
                            z = 0;
                            serialPort.writeIntArray(request);

                            executor.submit(callable(20));
                        }

                    }
                    t = 1;


                    System.out.println("\nДанные АРХИВ ЗА МЕСЯЦ поступили.");
                    System.out.println("Ожидаем обработку принятых данных АРХИВ ЗА МЕСЯЦ");

                    atomicInteger.addAndGet(1);


                    System.out.println("Принятая строка АРХИВ ЗА МЕСЯЦ :: ");

                    outTv7.forEach(p -> System.out.print(p));



                    String regularExpression1="^(83|90)";

                    if (Pattern.compile(regularExpression1).matcher(outTv7.get(1)).find()){

                        List<String> errList = modBusRService.errorProcessing(outTv7);

                        errList.forEach(p->System.out.println(p));
                        logger.info(customer.getFirstName()+ "  "+ldtime+ " " + errList.get(0));

                        continue;

                    }
                    String regularExpression2="^(C8)";

                    if (Pattern.compile(regularExpression2).matcher(outTv7.get(1)).find()){

                        List<String> errList = modBusRService.errorProcessing(outTv7);

                        errList.forEach(p->System.out.println(p));
                        logger.info(customer.getFirstName()+ "  "+ldtime+ "  ОШИБКА ЧТЕНИЯ " + errList.get(0)+ "  ОШИБКА ЗАПИСИ " + errList.get(1));

                        continue;

                    }

                   InitData initData11=new InitData();
                    List<Parametr> parametrList = initData11.initDay();

                    parametrList= modBusRService.day(outTv7, parametrList, 1);



                    ///
                    customer = customerService.findByIdTv7(customer.getId());


                    if (customer!=null){System.out.println("Кoнтакт найден, customer "+ customer.getFirstName()+" id= "+customer.getId());}


                    Operationtv7 operationtv7=saveService.saveDay(parametrList);
                    operationtv7.setTypeOperation("month");
                    operationtv7.setCustomerName(customer.getFirstName());




                    customer.addOperationtv7(operationtv7);

                        customerService.save(customer);
                    System.out.println("Данные АРХИВ ЗА МЕСЯЦ записаны");

                    ///

                    Thread.sleep(500);



////////////////////////////







                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
                break;
            }
            atomicInteger.addAndGet(1);

            Thread.sleep(3000);
            System.out.println("\n Переходим в сервисный режим (+++)");
            step = 0;
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
            System.out.println("\n После разрыва связи " + data);

            //   serialPort.closePort();

            if (stop == true) {
                System.out.println("Программа закончила работу. Команды STOP не поступало");
                status = "OK";
            } else {
                status = "ERROR";
            }


            Timestamp timestamp_date_input;

            atomicInteger.addAndGet(1);

            if (error == 0 & status.equals("OK") & moth == 1 & date_before == 1) {

            }


            if (error != 0 & status != "OK") {

            }


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

        end_tv7=true;

       // return "OK";
    }



//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
static Callable callable(long Seconds, String name) {
    return () ->
    {
        final Thread currentThread = Thread.currentThread();
        final String oldName = currentThread.getName();
        currentThread.setName("STREAM ====>>>>" + name);
        System.out.println("STREAM ====>>>> работает поток "+ currentThread.getName());
        try {
            for (int i = 1; i < Seconds + 1; i++) {

                System.out.print(i);
                TimeUnit.SECONDS.sleep(1);
                if (t == 1) {
                    System.out.println("STREAM ====>>>> " + currentThread.getName() + " получен. Таймер остановлен");
                    return 1;
                }
                System.out.print("\r");
            }
            System.out.println("STREAM ====>>>>timeout error " + currentThread.getName());
            t = 2;
            return 0;
        }finally {
            currentThread.setName(oldName);
        }

    };
}



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


    public static void modem_init(SerialPort serialPort, EventListener_tv7 eL) throws SerialPortException, InterruptedException {

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
