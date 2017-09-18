package ru.javabegin.training.vkt7.modem;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import ru.javabegin.training.vkt7.Crc16.Crc16ServiceImpl;
import ru.javabegin.training.vkt7.measurements.Measurements;
import ru.javabegin.training.vkt7.measurements.MeasurementsServiceImpl;
import ru.javabegin.training.vkt7.propert.entities.Properts;
import ru.javabegin.training.vkt7.recieve.Recieve03ServiceImpl;
import ru.javabegin.training.vkt7.recieve.Recieve10ServiceImpl;
import ru.javabegin.training.vkt7.send.Send03ServiceImpl;
import ru.javabegin.training.vkt7.send.Send10ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Николай on 12.08.2017.
 */
public class Current extends EventListener{
        public static String data;
    public static boolean r_3fff;
    EventListener eventListener;
    SerialPort serialPort;




    private static int[] request=new int[256];


    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }


    public void cur(List<Properts> prop_completed) throws InterruptedException, TimeoutException, ExecutionException, SerialPortException {  /* Точка входа в программу*/
    int shema_Tb1;
    int shema_Tb2;
    int number_active_base;
        List<Object> connect =new ArrayList<>();


        System.out.println("\n Начинает работать вторая задача");



        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
            System.out.println(portNames[i]);
        }


        try {




            /////// Инициализируем имплементирующие классы ////////////////////////////
            Send10ServiceImpl send10Service=new Send10ServiceImpl();
            Send03ServiceImpl send03Service=new Send03ServiceImpl();
            Recieve10ServiceImpl recieve10Service = new Recieve10ServiceImpl();
            Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();
            Crc16ServiceImpl crc16Service=new Crc16ServiceImpl();
//////////////////////////////////////////////////////////////////////////////////


/**  //////////////////////////////////////////////////
 * 3F FC (S17->18->19) Запрос на чтение перечня активных элементов данных ////
 */  //////////////////////////////////////////////////
            step=17;
            Thread.sleep(3000);
            System.out.println("\n Фoрмируем запрос 3F FC");
            List<String> ff_n=null;
            ff_n= send03Service.s_3FFC("01");

            //Получаем массив с контрольной суммой
            List<String> crc=crc16Service.crc16_t(ff_n);
            request=null;
            request=new int[crc.size()];
            //System.out.println("\n crc size = "+ crc.size());

//Формируем массив int для отправки в порт
            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                // System.out.print(+i+":"+request[i]+" ");
            }
            //System.out.println("\n request size = "+ request.length);
            Thread.sleep(2000);
            serialPort.writeIntArray(request);


            System.out.println("\nЖдем получения всех данных после команды 3F FC");
           ExecutorService executor = Executors.newFixedThreadPool(2);
            t=0;
             int repeat=0;
            executor.submit(callable(6));
            recieve_all_byte=1;
            while (recieve_all_byte==1) {
                if (t == 2) {
                    repeat++;
                    if (repeat == 2) {
                        step = 0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F FC. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт" + data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ не поступил. Ошибка по таймауту. Повторяем запрос");
                    serialPort.writeIntArray(request);
                    t = 0;
                    executor.submit(callable(8));

                }
            }

            t=1;

            executor.shutdown();
            System.out.println("\nДанные 3F FC поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FC");
            System.out.println("Step= "+step);
            System.out.println("data2 = "+data2);


            List<Properts> active_items=new ArrayList<>();
            while(step==18){

                System.out.println("Принятая строка 3F FC " + data2);
                System.out.println("\n Проверяем контрольную сумму :: " +crc16Service.crc16_valid(new ArrayList<>(Arrays.asList( data2.replace(" ","").split("(?<=\\G.{2})")))));


                active_items=recieve03Service.r_3FFC(data2,prop_completed);
               /* System.out.println("\n Принятый массив 3F FC :: ");
                list.forEach(p->System.out.print(p+" "));*/
                step=19;
                System.out.println("");
            }


            executor.shutdownNow();
/**  ////////////////////Класс  MeasurementsServiceImpl///////////////////////////////////
 *                      MeasurementsServiceImpl
 * Формируем массивы для измерений
 *
 *          active_items - ассив сактивными элементами тепловычислителя с ед. изм., количеством знаков и размером
 *
 *          current - массив OBJECT!!
 *          [0] - массив List<String> команды "текущие", полученный извыбора из массива активный элементов, элемнтов, онтосящихся к измерениям "текущие".
 *          [1] - массив List<Measurements> элементов и свойств относящийся к измерению "текущие"
 *
 */  ////////////////////////////////////////////////////////////////////////////////


            MeasurementsServiceImpl measurementsService=new MeasurementsServiceImpl();
            System.out.println();
            List<Object> current= measurementsService.current_command(active_items);
            List<String> command_send = (List<String>)current.get(0);
            List<Measurements> current_measur =(List<Measurements>) current.get(1);
            System.out.println("\n Полученная команда для отправки");
            command_send.forEach(p->System.out.print(p+" "));
            System.out.println("\n Полученный массив текущих элементов");
            current_measur.forEach(p->System.out.println(p.getId()+ " "+p.getName()+ " "+p.getText()+ " "+p.getEd()+ " "+p.getZnak()+ " "+p.getSize()));












            /**  ////////////////////////////////////////////////////////////////////////////
             * 3F FF  10  (s19 - >) Запрос на запись перечня для чтение. Требует подтверждения.//////
             */  ////////////////////////////////////////////////////////////////////////////

            //List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем 3F FF (\"текущие\") Запрос на запись перечня для чтение");


            /**
             *
             * @param number номер узла ДОЛЖЕН БЫТЬ INTEGER
             * Получаем массив объектов со стокой команды и массивом (prop_common) общих свойств с названиями
             * @return Массив из двух объектов {LinkedList<Properts> prop_specification, List<String> command,LinkedList<Properts> prop_common}
             * 1 объект
             */
            List<String> command_3FFF = send10Service.s_3FFF_end(1, command_send);

            System.out.println("\n Полученная команда после добавления всего");

            command_3FFF.forEach(p->System.out.print(p+" "));
            /**
             * Получили строку для получения данных по измерению "текущие"
             * добавляем контрольную сумму CRC
             */

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


            System.out.println("\n посылаем запрос 3F FF (Запрос \"ТЕКУЩИЕ\" на запись перечня для чтение");
            Thread.sleep(5000);
            serialPort.writeIntArray(request);

            //Thread.sleep(3000);
            executor = Executors.newFixedThreadPool(1);
            t=0;
            repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
            while(step==19){
                if (data2.contains("3F FF ")){
                    t=1;
                    //System.out.println("После запроса 3F FF_n ");
                    System.out.println("Запрс 3F FF; data2 = "+data2);
                    r_3fff = recieve10Service.r_3FFF(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FF (Запрос на запись\"ТЕКУЩИЕ\") прошла. Принятые данные ::  " + data2);
                    Thread.sleep(1000);
                    step=20;
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
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(4));
                }





            }
            executor.shutdown();
            Thread.sleep(2000);




            /**  ////////////////////////////////////////////////////////////////////////////
             * 3F FD  10  (s20 - >21) Запрос на запись типа значения. Требует подтверждения.//////
             */  ////////////////////////////////////////////////////////////////////////////

            //List<String> ff_n=new ArrayList<>();
            System.out.println("\nФормируем 3F FD Запрос на запись ТИПА значений");


            /**
             *
             * @param number номер узла ДОЛЖЕН БЫТЬ INTEGER
             * @param type тип измерений. (в данный момент "текущие" - 4) ДОЛЖЕН БЫТЬ INTEGER
             * @return Массив строки команды
             * 1 объект
             */
            List<String> command_3FFD = send10Service.s_3FFD(1, 4);

            System.out.println("\n Полученная команда после добавления всего");

            command_3FFD.forEach(p->System.out.print(p+" "));
            /**
             * Получили строку для получения данных по измерению "текущие"
             * добавляем контрольную сумму CRC
             * получаем массив crc - полную строку с контрольной cуммой
             */
            crc=crc16Service.crc16_t(command_3FFD);
            System.out.println("\n Добавили контрольную сумму");
            crc.forEach(p->System.out.print(p+" "));
            System.out.println("\n command +CRC = ");
            for(int i=0;i<crc.size();i++ ){
                System.out.print(crc.get(i)+" ");
            }
            System.out.println();


            request=null;
            request= new int[crc.size()];

            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                }



            System.out.println("\n посылаем запрос 3F FD Запрос на запись ТИПА значений.");
            Thread.sleep(5000);
            serialPort.writeIntArray(request);

            //Thread.sleep(3000);
            executor = Executors.newFixedThreadPool(1);
            t=0;
            repeat=0;
            executor.submit(callable(3));
            r_3fff=false;
            while(step==20){
                if (data2.contains("3F FD ")){
                    t=1;
                    //System.out.println("После запроса 3F FF_n ");
                    System.out.println("Запрс 3F FD; data2 = "+data2);
                    r_3fff = recieve10Service.r_3FFD(data2);}
                if (r_3fff) {
                    System.out.println("\n Команда 3F FD (Запрос на запись ТИПА прошла. Принятые данные ::  " + data2);
                    Thread.sleep(1000);
                    step=21;
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
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(4));
                }

            }
            executor.shutdown();
            Thread.sleep(2000);











/**       //////////////////////////////////////////////////////////////////////
 * 3F FE  (03)  s11 -> s12 Запрос на чтение данных  ////////////////////////////////////////
 */
       //////////////////////////////////////////////////////////////////////

            System.out.println("\n Формируем запрос 3F FE Запрос на чтение данных ТЕКУЩИЕ");
            List<String> ff_2=null;
            ff_2=send03Service.s_3FFE("01");
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
            System.out.println("\nЖдем получения всех данных после команды 3F FE (ТЕКУЩИЕ)  ");
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(10));


/**
 * Ждем начала приема длинных данных.
 */

            recieve_all_byte=0;
            while (recieve_all_byte==0){
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
                    temp = "";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(4));
                }

            }
            t=1;


            System.out.println("\nДанные 3F FE (ТЕКУЩИЕ) поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F FE ");
            executor.shutdown();
List<Measurements> measurementsList=new ArrayList<>();
            while(step==22){


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

                // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                //свойств на переменных для чтения

                //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                measurementsList=recieve03Service.r_3FFE_Measurements(data2,current_measur);
                step=23;

            }



            /**       //////////////////////////////////////////////////////////////////////
             * 3E CD  (03)  s11 -> s12 Чтение номера схемы измерений Тв1 ////////////////////////////////////////
             */
            //////////////////////////////////////////////////////////////////////

            System.out.println("\n Формируем запрос 3E CD Чтение номера схемы измерений Тв1");
            ff_2=null;
            ff_2=send03Service.s_3ECD("01");
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
            System.out.println("\nЖдем получения всех данных  3E CD Чтение номера схемы измерений Тв1");

            recieve_all_byte=0;
            step=21;
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(3));


/**
 * Ждем начала приема длинных данных.
 */

            while (recieve_all_byte==0){
                if (t==2){
                    repeat++;
                    if (repeat==4){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3E CD Чтение номера схемы измерений Тв1. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ на 3E CD не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp = "";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(2));
                }

            }
            t=1;


            System.out.println("\nДанные 3E CD(Тв1) Чтение номера схемы измерений Тв1. поступили.");
            System.out.println("Ожидаем обработку принятых данных 3E CD(Тв1) ");
            executor.shutdown();
            while(step==22){


                System.out.println("Принятая строка 3E CD(Тв1) :: " + data2);
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

                // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                //свойств на переменных для чтения

                //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                shema_Tb1=recieve03Service.r_3ECD(data2);
                System.out.println("Схема измерений по Тв1 - "+shema_Tb1);


                step=23;

            }



            /**       //////////////////////////////////////////////////////////////////////
             * 3F 5B  (03)   Чтение номера схемы измерений Тв2 ////////////////////////////////////////
             */
            //////////////////////////////////////////////////////////////////////

            System.out.println("\n Формируем запрос 3F 5B Чтение номера схемы измерений Тв2");
            ff_2=null;
            ff_2=send03Service.s_3F5B("01");
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
            System.out.println("\nЖдем получения всех данных  3F 5B Чтение номера схемы измерений Тв2");

            recieve_all_byte=0;
            step=21;
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(3));


/**
 * Ждем начала приема длинных данных.
 */

            while (recieve_all_byte==0){
                if (t==2){
                    repeat++;
                    if (repeat==4){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F 5B Чтение номера схемы измерений Тв2. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ на 3F 5B не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp = "";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(2));
                }

            }
            t=1;


            System.out.println("\nДанные 3F 5B(Тв2) Чтение номера схемы измерений Тв2. поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F 5B(Тв2)  ");
            executor.shutdown();
            while(step==22){


                System.out.println("Принятая строка 3F 5B(Тв2)  :: " + data2);
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

                // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                //свойств на переменных для чтения

                //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                shema_Tb2=recieve03Service.r_3F5B(data2);
                System.out.println("Схема измерений по Тв2 - "+shema_Tb2);


                step=23;

            }



            /**       //////////////////////////////////////////////////////////////////////
             * 3F E9  (03)   Чтение номера активной базы данных ////////////////////////////////////////
             */
            //////////////////////////////////////////////////////////////////////

            System.out.println("\n Формируем запрос 3F E9  Чтение номера активной базы данных");
            ff_2=null;
            ff_2=send03Service.s_3FE9("01");
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
            System.out.println("\nЖдем получения всех данных  3F E9  Чтение номера активной базы данных");

            recieve_all_byte=0;
            step=21;
            serialPort.writeIntArray(request);
            executor = Executors.newFixedThreadPool(2);
            t=0;
            repeat=0;
            executor.submit(callable(3));


/**
 * Ждем начала приема длинных данных.
 */

            while (recieve_all_byte==0){
                if (t==2){
                    repeat++;
                    if (repeat==4){
                        step=0;
                        executor.shutdown();
                        System.out.println("\n Ответ не поступил 3F E9  Чтение номера активной базы данных. Ошибка по таймауту.");
                        Thread.sleep(2000);
                        serialPort.writeBytes("+++".getBytes());
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                                SerialPort.FLOWCONTROL_RTSCTS_OUT);
                        Thread.sleep(1000);
                        serialPort.writeBytes("ATH\r".getBytes());
                        System.out.println("\n Закрываем порт"+data2);
                        serialPort.closePort();
                    }
                    System.out.println("\n Ответ на 3F E9  Чтение номера активной базы данных не поступил. Ошибка по таймауту. Повторяем запрос");
                    temp = "";
                    serialPort.writeIntArray(request);
                    t=0;
                    executor.submit(callable(2));
                }

            }
            t=1;


            System.out.println("\nДанные 3F E9  Чтение номера активной базы данных поступили.");
            System.out.println("Ожидаем обработку принятых данных 3F E9 .");
            executor.shutdown();
            while(step==22){


                System.out.println("Принятая строка 3F E9   :: " + data2);
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

                // Получаем массив типа <Measurements> с единицами измерения и количеством знаков ранее в ранее посланном запросе
                //свойств на переменных для чтения

                //prop_specification = recieve03Service.r_3FFE(data2,prop_session);
                number_active_base=recieve03Service.r_3FE9(data2);
                System.out.println("Активная база - "+number_active_base);


                step=23;

            }













/*

            Thread.sleep(5000);
            ff_n=send03Service.s_3FF9("01");
            crc=crc16Service.crc16_t(ff_n);
            request=null;
           crc
                   .stream()
                   .forEach(p->System.out.print(p+" "));
            System.out.println();

            for(int i=0;i<crc.size();i++ ){
                request[i]=Integer.parseInt(crc.get(i) ,16);
                System.out.print(i+":"+request[i]+" ");
            }
            serialPort.writeIntArray(request);

*/


           // serialPort.writeIntArray(requests[requestNum]);

        /*    serialPort.writeIntArray(requests[requestNum]);
            Thread.sleep(7000);
            serialPort.writeIntArray(requests[requestNum]);
*/

/*

        Thread.sleep(5000);
            System.out.println("\n Переходим в сервисный режим (+++)");
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

            serialPort.closePort();

*/


        }
        catch (SerialPortException ex) {
            System.out.println (ex);
        }


    }


//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () -> {
            for (int i=1;i< Seconds+1;i++)
            {
                TimeUnit.SECONDS.sleep(1);
                if (t==1){
                    System.out.println("Ответ получен. Таймер остановлен");
                    return 1;
                }
                System.out.print(i+ " ");
            }
            System.out.println("timeout error");
            t=2;
            return 0;
        };
    }
///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////




}
