package ru.javabegin.training.vkt7.modem;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.primefaces.push.annotation.Singleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.db.ContactService;
import ru.javabegin.training.db.ContactServiceImpl;
import ru.javabegin.training.vkt7.Crc16.Crc16ServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.db.CustomerServiceImpl;
import ru.javabegin.training.vkt7.db.ResultService;
import ru.javabegin.training.vkt7.db.ResultServiceImpl;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Result;
import ru.javabegin.training.vkt7.modem_run.ModemServiceImpl;
import ru.javabegin.training.vkt7.propert.Properts_ready_Impl;
import ru.javabegin.training.vkt7.propert.entities.Properts;
import ru.javabegin.training.vkt7.recieve.Recieve03ServiceImpl;
import ru.javabegin.training.vkt7.recieve.Recieve10ServiceImpl;
import ru.javabegin.training.vkt7.send.Send03ServiceImpl;
import ru.javabegin.training.vkt7.send.Send10ServiceImpl;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.m;
import static ru.javabegin.training.vkt7.modem_run.ModemServiceImpl.stop;

/**
 * Created by Николай on 12.08.2017.
 */
@Component
public class Connect extends EventListener{
 //SerialPort serialPort; /*Создаем объект типа SerialPort*/
    public static String data;



   /* @Autowired
    @Qualifier("jpaCustomerService")
    private CustomerService customerService;
*/


/* public volatile static String data2;
    public volatile static int step;
    public volatile static int recieve_all_byte;
    public static volatile int t;*/


    public static boolean r_3fff;
    public static int server_version=0;

   private static int requestNum = 0;
   /*private static int[][] requests = {
           {0xFF, 0xFF, 0x01, 0x10, 0x3F, 0xFF, 0x00, 0x00, 0xCC, 0x80, 0x00, 0x00, 0x00, 0x60, 0xA8},
           {0xFF, 0xFF, 0x01, 0x03, 0x3F, 0xFE, 0x00, 0x00, 0x28, 0x2E }, {00, 00, 03}};*/

    private static int[] request=new int[256];



    public List<Object> connect(CustomerService customerService) throws InterruptedException, TimeoutException, ExecutionException {


        List<Object> connect =new ArrayList<>();

        stop=true;
        String[] portNames = SerialPortList.getPortNames();
        String port="";
        for(int i = 0; i < portNames.length; i++){
            port=portNames[i];
            System.out.println(port);

        }



        serialPort = new SerialPort (port); /*Передаем в конструктор суперкласса имя порта с которым будем работать*/
            System.out.println("\n m======= "+m);
        System.out.println("\n STOP = =  "+stop);

        stop=false;
        while (stop!=false){
        try {
            System.out.println("\n  Открытие порта STOP = =  "+stop);
            serialPort.openPort (); //Метод открытия порта
            serialPort.setParams (SerialPort.BAUDRATE_9600,
                                  SerialPort.DATABITS_8,
                                  SerialPort.STOPBITS_1,
                                  SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.setEventsMask (SerialPort.MASK_RXCHAR); //Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта

            EventListener eL= new EventListener ();


            serialPort.addEventListener (eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
            //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

            eL.setSerialPort(serialPort);
           /* eL.setData(data);
           // eL.setData2(data2);
            eL.setRecieve_all_byte(recieve_all_byte);*/


            step=100;
            System.out.println("\nпосылаем +++");
            System.out.println("\n посылаем +++ STOP = =  "+stop);
            if(!stop){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeBytes("+++".getBytes());
            System.out.println("\nпосылаем ATH");
            serialPort.writeBytes("ATH\r".getBytes());
            serialPort.writeBytes("+++".getBytes());

            Thread.sleep(500);
            if(!stop){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeBytes("AT+CREG?\r".getBytes());
            System.out.println("\n Ждем данных от модема ");
            ExecutorService executor = Executors.newFixedThreadPool(2);
            t=0;
            executor.submit(callable(10));
            step=0;
            while(data2==null) {

                if (t==2){
                    System.out.println("\n Ответ от модема не поступил. Ошибка по таймауту.");
                    System.out.println("\n Закрываем порт"+data2);
                    System.exit(0);
                    serialPort.closePort();
                }
            }
            t=1;
            System.out.println("\n Ответ от можема получен");
            System.out.println("\n Проверка регистрации модема");
            executor.shutdownNow();

            executor = Executors.newFixedThreadPool(1);
            t=0;
            executor.submit(callable(10));
            System.out.println("\n перед while(step==0&stop!=false) STOP = =  "+stop);
            while(step==0&stop==true){
              //  System.out.println("\n зашли в  while(step==0&stop!=false) STOP = =  "+stop);
                if (data2.contains("+CREG: 1,")||data2.contains("+CREG: 0,1")||data2.contains("+CREG: 2,1")){
                    t=1;
                    System.out.println("\n Регистрация в сети произведена = "+data2);
                    /*Thread.sleep(2000);
                    serialPort.writeBytes("AT+CSQ\r".getBytes());
                    Thread.sleep(2000);
                    System.out.println("\n Уровень сигнала = "+data2);*/
                    step=1;
                }
               /* else{
                    System.out.println("\n Запуск запрещен !Data2 = "+data2);
                    serialPort.writeBytes("AT+CREG?\r".getBytes());
                    Thread.sleep(2000);
                }*/

                if (t==2){
                    System.out.println("\n Регистрация модема не произведена. Ошибка по таймауту.");
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();
                }
            }


           // Thread.sleep(30000);
            System.out.println("\nпосылаем +++");
            serialPort.writeBytes("+++".getBytes());

            Thread.sleep(500);




            step=2;
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Thread.sleep(5000);
            System.out.println("\nпосылаем ATH");
            serialPort.writeBytes("ATH\r".getBytes());

            while(step==2&stop==true){
                Thread.sleep(500);

                if (data2.contains("OK")){
                    System.out.println("\n Команда ATH прошла"+data2);
                    Thread.sleep(1000);
                    step=3;
                }
                else{
                    System.out.println("\n Ошибка команды ATH = "+data2);
                    Thread.sleep(1000);
                }


            }

            //Thread.sleep(5000);
            System.out.println("\nпосылаем AT&FL1E0V1&D2X4S7=120S10=90");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeBytes("AT&FL1E0V1&D2X4S7=120S10=90\r".getBytes());
            while(step==3&stop==true){

                if (data2.contains("OK")){
                    System.out.println("\n Команда AT&FL1E0V1&D2X4S7=120S10=90 прошла"+data2);

                    step=4;
                }
                else{
                    System.out.println("\n Ошибка команды AT&FL1E0V1&D2X4S7=120S10=90 = "+data2);
                    Thread.sleep(000);
                }

            }



            //Thread.sleep(3000);

            executor.shutdownNow();
            System.out.println("\n m======= "+m);
            Thread.sleep(1000);
            System.out.println("\nНабираем номер");

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            //serialPort.writeBytes("ATDP+79064427287\r".getBytes());
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
           serialPort.writeBytes("ATDP+79064426645\r".getBytes());
            //serialPort.writeBytes("ATDP+79064427319\r".getBytes());

///////////// Набор номера!!!!!!!!!!!!!!!!!!!!!!!!!!!!


            int c=0;
            System.out.print("\n Ждем установки связи :");

            executor = Executors.newFixedThreadPool(2);
            t=0;
            executor.submit(callable(60));

            while(step==4&stop==true){

                if (data2.contains("CONNECT 9600/RLP")){
                    System.out.println("ответ пришел t= "+t);
                    System.out.println("\n Связь Установлена!! Продолжаем работать "+data2);
                    Thread.sleep(2000);

                    step=5;
                }

                else if (data2.contains("NO CARRIER")){
                    System.out.println("Ответ  NO CARRIER ответ пришел t= "+t);
                    executor.shutdown();

                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);
                    Thread.sleep(2000);
                    serialPort.writeBytes("+++".getBytes());
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                            SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    Thread.sleep(1000);
                    serialPort.writeBytes("ATH\r".getBytes());
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();
                }
                else if (data2.contains("NO DIALTONE")){
                    System.out.println("Ответ NO DIALTHONE ответ пришел t= "+t);
                    executor.shutdown();

                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);
                    Thread.sleep(2000);
                    serialPort.writeBytes("+++".getBytes());
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                            SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    Thread.sleep(1000);
                    serialPort.writeBytes("ATH\r".getBytes());
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();
                }
                else if (data2.contains("BUSY")||data2.contains("NO ANSWER")){
                    System.out.println("Ответ BUSY или NO ANSWER   ответ пришел t= "+t);
                    executor.shutdown();

                    step=0;
                    System.out.println("\n Нет Связи!! Закрываем связь"+data2);
                    Thread.sleep(2000);
                    serialPort.writeBytes("+++".getBytes());
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                            SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    Thread.sleep(1000);
                    serialPort.writeBytes("ATH\r".getBytes());
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();
                }
                if (t==2){

                    step=0;
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                    //Thread.sleep(2000);
                    serialPort.writeBytes("+++".getBytes());
                    serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                            SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    Thread.sleep(1000);
                    serialPort.writeBytes("ATH\r".getBytes());
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();

                }

            }

            executor.shutdown();
            System.out.println("\n  после набора номера Stop== "+stop);
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
            System.out.println("\nФормируем 3F FF_n (Начало связи");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n=send10Service.s_3FFF_n("01");
            ff_n.forEach(System.out::print);
            System.out.println("\nПолучили массив команды");
            //System.out.println();
            crc=crc16Service.crc16_t(ff_n);
            System.out.println("\nДобавили CRC");
           // System.out.println();

            //crc16Service.crc16_t(ff_n).stream().forEach(w->data_send.add(Integer.parseInt(w ,16)));

           /* IntStream.range(0,crc.size())
                    .map(p->Integer.parseInt(crc.get(p) ,16)=requests[p]);*/
            System.out.println();
            for(int i=0;i<crc.size();i++ ){
                System.out.print(crc.get(i)+" ");
            }
           // System.out.println();
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                //System.out.print(i+":"+request[i]+" ");
            }


            System.out.println("\n посылаем запрос 3F FF_n");
            System.out.println("\n Перед посылаем запрос 3F FF_n  STOP== "+stop );

            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);

            //Thread.sleep(3000);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            int repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
            while(step==5&stop!=false){
                //System.out.println("\n  step  после while(step==5&stop!=false){  STOP== "+stop );
                if (data2.contains("3F FF ")){
                    t=1;
                //System.out.println("После запроса 3F FF_n ");
                System.out.println("Запрс 3F FF_n; data2 = "+data2);
                r_3fff = recieve10Service.r_3FFF_n(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FF_n прошла. Принятые данные ::  " + data2);
                    Thread.sleep(1000);
                    data2="";
                    step=6;
                    System.out.println();
                }

                if (t==2){
                      repeat++;
                    if (repeat==6){
                        step=0;
                        executor.shutdown();
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                    Thread.sleep(2000);
                    serialPort.writeBytes("+++".getBytes());

                    Thread.sleep(1000);
                    serialPort.writeBytes("ATH\r".getBytes());
                        Thread.sleep(1000);
                    System.out.println("\n Закрываем порт"+data2);
                    serialPort.closePort();
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(2));
                }



               /* else{
                    System.out.println("\n Ответ на 3F FF(Начало связи) не принят. ");
                    Thread.sleep(2000);
                    serialPort.writeIntArray(request);
                }*/

            }
            executor.shutdown();
            Thread.sleep(2000);


/**
 * 3F FE 1-й проход определение версии
  */
            System.out.println("\n m======= "+m);
            System.out.println("\n Формируем запрос 3F FE (Версия сервера 65 байт)");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n=send03Service.s_3FFE("01");
            //System.out.println();
            crc=crc16Service.crc16_t(ff_n);
            request=null;
            int[] request=new int[100];
            //System.out.println("\n crc size = "+ crc.size());
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
               // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);
            System.out.println("\n m======= "+m);
            Thread.sleep(5000);
            System.out.println("\nЖдем получения всех данных после команды 3FF FE (Версия сервера 65 байт) ");
            temp="";
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(15));

/**
 * Ждем начала приема длинных данных.
 */
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==3){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F FE (Версия сервера 65 байт). Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp = "";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(10));
                }

            }
            t=1;


            System.out.println("\nДанные 3F FE (Версия сервера) поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FE (Версия сервера 65 байт)");
            executor.shutdown();

              while(step==7&stop!=false){

                 System.out.println("Принятая строка 3F FE (Версия сервера) :: " + data2);
                 if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
                     step = 0;
                     executor.shutdown();
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
                  System.out.println("\n Контрольная сумма верна ");

                server_version = recieve03Service.r_3FFE_1( data2);
                System.out.println("server_version = "+server_version);
                if (server_version>0&stop!=false) {
                    System.out.println(" Команда 3F FE (Версия сервера 65 байт) прошла");

                    Thread.sleep(2000);
                    step=8;
                    System.out.println();
                }

                else{
                    System.out.println("\n Ошибка приема команды 3F FE (Версия сервера 65 байт). Посылаем повторно ");
                    //serialPort.writeIntArray(request);
                    Thread.sleep(5000);

                }

            }
/**  //////////////////////////////////////////////////
 * 3F F9  (S8) Запрос на чтение служебной информации ////
 */  //////////////////////////////////////////////////


            Thread.sleep(5000);
            System.out.println("\n Фoрмируем запрос 3F F9 Запрос на чтение служебной информации");
            List<String> service_information =new ArrayList<>();
            ff_n=null;
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            ff_n= send03Service.s_3FF9("01");

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
            Thread.sleep(2000);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);


            System.out.println("\nЖдем получения всех данных после команды 3F F9 Запрос на чтение служебной информации");
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(6));
            while (recieve_all_byte==1&stop!=false) {
                if (t == 2) {
                    repeat++;
                    if (repeat == 2) {
                        step = 0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F F9. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт" + data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ не поступил 3F F9. Ошибка по таймауту. Повторяем запрос");
                    serialPort.writeIntArray(request);
                    t = 0;
                    executor.submit(callable(8));

                }
            }

t=1;


            System.out.println("\nДанные 3FF F9 поступили.Запрос на чтение служебной информации");
            System.out.println("Ожидаем обработку принятых данных 3FF F9");
            executor.shutdown();

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
                step=10;
                System.out.println("");
            }



/**  ////////////////////////////////////////////////////////////////////////////
 * 3F FF  (s10->11) Запрос на запись перечня для чтение. Требует подтверждения.//////
 */  ////////////////////////////////////////////////////////////////////////////

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
            List<Object> ff = send10Service.s_3FFF("01");


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
            Thread.sleep(5000);
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);

            //Thread.sleep(3000);
            executor = Executors.newFixedThreadPool(1);
            t=0;
            repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
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
                    step=11;
                    System.out.println();
                }

                if (t==2){
                    repeat++;
                    if (repeat==6){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        Thread.sleep(2000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }

                   System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp="";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(4));
                }





            }
            executor.shutdown();
            Thread.sleep(2000);



/**       //////////////////////////////////////////////////////////////////////
 * 3F FE  (03)  s11 -> s12 -> 13 Запрос на чтение данных  ////////////////////////////////////////
 */       //////////////////////////////////////////////////////////////////////
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            System.out.println("\n Формируем запрос 3F FE Запрос на чтение данных");
            ff=null;
            List<String> ff_2=send03Service.s_3FFE("01");
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
            Thread.sleep(5000);
            System.out.println("\nЖдем получения всех данных после команды 3F FE (Запрос на чтение данных)  ");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(10));

/**
 * Ждем начала приема длинных данных.
 */
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==4){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F FE. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ на 3F FE не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp="";
                    z=0;
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(4));
                }

            }
            t=1;


            System.out.println("\nДанные 3F FE поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FE ");
            executor.shutdown();
            List<Properts> prop_specification=new ArrayList<>();
            while(step==12&stop!=false){


                System.out.println("Принятая строка 3F FE :: " + data2);
                //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                //Проверяем контрольную сумму. Если !=true то закрываем порт
                if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
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

             // Получаем массив типа <Properts> с единицами измерения и количеством знаков ранее в ранее посланном запросе
             //свойств на переменных для чтения

                prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                step=13;

            }


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
            System.out.println("\n Запускаем обработку массивов и формаирование массива свойств");
            List<Properts> prop_completed=new ArrayList<>();
            Properts_ready_Impl propertsReadyImp=new  Properts_ready_Impl();
             prop_completed= propertsReadyImp.prop_ready(prop_common, prop_specification);

            prop_completed
                    .stream()
                    .forEach(p->System.out.println(p.getId()+"  "+ p.getName() + "  "+ p.getText()+ "  "+ p.getEd()+ "  "+ p.getZnak()));
            System.out.println();

  //////////////////////////////////////////////////////////////////////////////////////




/**       //////////////////////////////////////////////////////////////////////
 * 3F F6  (03)  s13 -> s14 -> 15 Запрос «Чтение интервала дат»  //////////////////////
 */       //////////////////////////////////////////////////////////////////////
            List<Date> date= new ArrayList<>();
            System.out.println("\n Формируем запрос 3F F6 Запрос «Чтение интервала дат»");
            ff_2=null;
            List<String> f6= send03Service.s_3FF6("01");
            //System.out.println();
            crc=crc16Service.crc16_t(f6);
            request=null;
            request=new int[crc.size()];
            //System.out.println("\n crc size = "+ crc.size());
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                 }
            Thread.sleep(5000);
            System.out.println("\nЖдем получения всех данных после команды 3F F6 Запрос «Чтение интервала дат»");
            if(stop==false){
                System.out.println("\n Получена команда STOP ");
                break;
            }
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(10));

/**
 * Ждем начала приема длинных данных.
 */
            recieve_all_byte=0;
            while (recieve_all_byte==0&stop!=false){
                if (t==2){
                    repeat++;
                    if (repeat==2){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F F6. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ на 3F F6 не поступил. Ошибка по таймауту. Повторяем запрос");
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(8));
                }

            }
            t=1;


            System.out.println("\nДанные 3F F6 поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F F6 ");
            executor.shutdown();

            while(step==14&stop!=false){


                System.out.println("Принятая строка 3F F6 :: " + data2);
                //System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));
                //Проверяем контрольную сумму. Если !=true то закрываем порт
                if (crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})"))))!=true){
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

                 date=recieve03Service.r_3FF6(data2);
                System.out.println("Выводим данные даты и времени счетчика");
                date
                        .stream()
                        .forEach(p->System.out.println(p+" "));
                System.out.println("Выводим данные даты и времени сервера");
                System.out.println(new Date(System.currentTimeMillis()));
                step=15;


            }
/*






        Thread.sleep(5000);
          /*  System.out.println("\n Переходим в сервисный режим (+++)");
            step=0;
            serialPort.writeBytes("+++".getBytes());
            Thread.sleep(2000);
            System.out.println("\n Закрываем порт "+data);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            Thread.sleep(500);
            System.out.println("\n Разрываем связь");
            serialPort.writeBytes("ATH\r".getBytes());
            Thread.sleep(5000);
            System.out.println("\n После разрыва связи "+data);

            serialPort.closePort();*/

            List<Integer> version_serv = new ArrayList<>(Arrays.asList(server_version));
            List<SerialPort> serialPortList = new ArrayList<>(Arrays.asList(serialPort));
            List<EventListener> eventListeners = new ArrayList<>(Arrays.asList(eL));

            connect.add(version_serv);
            connect.add(service_information);
            connect.add(prop_common);
            connect.add(prop_completed);
            connect.add(date);
            connect.add(serialPortList);
            connect.add(eventListeners);


        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        break;
        }

        if (stop==true){
            System.out.println ("Программа закончила работу. Команды STOP не поступало");
        }

        System.out.println ("Начинаем запись в базу");
        Thread.sleep(2000);
        Customer cu=new Customer();
        cu.setFirstName("Очень новый");
        cu.setTelModem("345345");
        customerService.save(cu);
        List<Customer> l_cu=customerService.findAll();
        l_cu.forEach(p->System.out.println(p.getFirstName()+" "+p.getLastName()));




        return connect;
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
                    TimeUnit.SECONDS.sleep(1);
                    if (t == 1) {
                        System.out.println("Ответ получен. Таймер остановлен");
                        return 1;
                    }
                    System.out.print(i + " ");
                }
                System.out.println("timeout error");
                t = 2;
                return 0;


        };
    }
///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////




}
