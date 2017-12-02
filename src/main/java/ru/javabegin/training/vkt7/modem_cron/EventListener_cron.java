package ru.javabegin.training.vkt7.modem_cron;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.springframework.stereotype.Component;
import ru.javabegin.training.vkt7.recieve.Recieve03ServiceImpl;
import ru.javabegin.training.vkt7.recieve.Recieve10ServiceImpl;
import org.apache.log4j.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Николай on 03.09.2017.
 */

@Component
public class EventListener_cron implements SerialPortEventListener   { /*Слушатель срабатывающий по появлению данных на COM-порте*/
    public int size_byte;
    int count_data = 1;
    public static String temp = "";
    long data_res;
    int count = 0;
    static int z = 0;

    public volatile static String data2;
    public volatile static int step;
    public volatile static int recieve_all_byte;
    public static volatile int t;
    public static SerialPort serialPort;
    public static volatile  String oldString;

    String data;


    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }


    public void serialEvent(SerialPortEvent event) {
        Logger logger = Logger.getRootLogger();
       // logger.info("hello world");

        oldString="";


        if (event.isRXCHAR() && event.getEventValue() > 0) { /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
            try {

                Recieve03ServiceImpl recieve03Service = new Recieve03ServiceImpl();
                Recieve10ServiceImpl recieve10Service = new Recieve10ServiceImpl();

                if (step == 100) {
                    /*data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Data :: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data hextostr:: " + data2);*/

                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                       // System.out.println("конеуц овета перевод строки");
                       // System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                        System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                      //  System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }



                }
                if (step == 1000) {
                   /* data = serialPort.readHexString(event.getEventValue());
                    System.out.println("SOPPPP!!");
                    System.out.println("Data :: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data hextostr:: " + data2);
                      */
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    //System.out.println("Пришедшая строка "+temp);
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                       // System.out.println("конеуц овета перевод строки");
                       // System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                      //  System.out.println("data1="+data1);
                       // data1=data1.substring(0,data1.length()-12);
                      //  System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }


                }



                if (step == 0) {

                   /* data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Data dddddd:->: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("data2 dddddd:->: " + data2);*/
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                      //  System.out.println("конеуц овета перевод строки");
                      //  System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                        System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                      //  System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }


                }

                if (step == 1) {
                    t = 1;
                  /*  data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Data s1:->: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data s1 ->:->: " + data2);*/
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                      //  System.out.println("конеуц овета перевод строки");
                      //  System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                    //    System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                        System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }



                }
                if (step == 2) {
                    t = 1;
                   /* data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Data s2:->: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data s2 ->:->: " + data2);*/
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                 //       System.out.println("конеуц овета перевод строки");
                   //     System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                  //      System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                        System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }


                }
                if (step == 3) {
                    t = 1;
                  /*  data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Datas3 :->: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data s3:->: " + data2);*/
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                  //      System.out.println("конеуц овета перевод строки");
                  //      System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                   //     System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                        System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }



                }
                if (step == 777) {
                    t = 1;
                   /* data = serialPort.readHexString(event.getEventValue());
                    System.out.println("Datas4 :->: " + data);
                    String data1 = data.replace(" ", "");
                    data2 = recieve03Service.hextostr(data1);
                    System.out.println("Data :->: " + data2);*/

                    data = serialPort.readHexString(event.getEventValue());
                    //System.out.println("Datas4 :->: " + data);
                    temp = temp + data;
                   // System.out.println("temp s4 :->: " + temp);
                    /*if (temp.replace(" ", "").contains("0D0A")){
                        System.out.println("конеуц овета перевод строки");
                        System.out.println("temp= "+temp);
                        String data1 = temp.replace(" ", "");
                        System.out.println("data1="+data1);
                        //data1=data1.substring(0,data1.length()-12);
                        System.out.println("data1="+data1);
                        temp="";
                        data2 = recieve03Service.hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }*/

                        //String regularExpression="\f\n";
                        String regularExpression="0D0A$";
                    String str_temp=temp.replace(" ", "");
                    //System.out.println("temp_split =  " + str_temp);
                        Pattern pattern = Pattern.compile(regularExpression);
                        Matcher match = pattern.matcher(str_temp);
                        int matchCounter = 0;
                        while (match.find()){
                            matchCounter++;
                            //System.out.println("start(): "  + match.start());
                            //System.out.println("end(): " + match.end());
                           // System.out.println("Number of match: " + matchCounter);
                        }
                        if (matchCounter==1) {
                           // System.out.println("конец овета перевод строки step 4");
                           // System.out.println("temp= "+temp);
                            String data1 = temp.replace(" ", "");
                           // System.out.println("data1="+data1);
                            //data1=data1.substring(0,data1.length()-12);
                            System.out.println("data1="+data1);
                            temp="";
                            data2 = recieve03Service.hextostr(data1);
                            temp="";
                            data="";
                            data1="";
                            System.out.println("Data hextostr step 4:: " + data2);

                        }



                    }









                if (step == 5) {
                    data = serialPort.readHexString(event.getEventValue());
                    System.out.print("Data (step " + step + "):->: " + data);
                    data2 = data;
                    oldString=data2;

                }
            //    System.out.println();
                //temp="";


                if (step == 6) {  //// 3F FE 1

                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 6 == " + temp);
                    System.out.println("z= " + z);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        System.out.println("z= " + z);
                        temp = temp.replace(" ", "");
                        System.out.println("temp при ,больше 11  и без пробелов" + temp);
                        //String temp_substr = temp.substring(4,6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;
                    }
                    temp = temp.replace(" ", "");
                    ///Проверка данных Если попали старые данны и обработка их не возможна//////////////
                    if(temp.length()>count&z==1){
                        System.out.println("count == " +count+" count_read == " +temp.length() );
                        System.out.println("z= " + z);
                        System.out.println("temp.length()>count= "+step);


                        count = 0;

                        System.out.println("Ждем окончание тамера и новой отправки запроса");
                    }

                    System.out.println("step=6. OldString= "+oldString );
                    /////////////////////////////////////////////
                    if(z==1&oldString.length()>8&temp.contains(oldString.substring(2,7))){

                        System.out.println("Старые данные!!!!!!!!!!!!" );
                        System.out.println("Старая строка"+ oldString);
                        System.out.println("Принимаемая строка"+ temp);
                        logger.info("Старые данные!!!!!!!!!!!!");

                        count = 0;

                        System.out.println("Ждем окончание тамера и новой отправки запроса");
                    }


                    System.out.println("count == " +count+" count_read == " +temp.length() );
                    if (temp.length() == count) {

                        count = 0;
                        z = 0;
                        recieve_all_byte = 1;

                        step = 300;
                        data2 = temp;
                        oldString=temp;
                        System.out.println(" На выходе step=6 temp= "+ data2);
                    }

                }

                // count=0;
                // z=0;
                if (step == 8) {    //// 3F F9
                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 8 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 11  и без пробелов" + temp);
                        String temp_substr = temp.substring(4, 6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;

                    }
                    temp = temp.replace(" ", "");
                    if (temp.length() == count) {

                        data2 = temp;
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 9;
                        temp = "";
                    }

                }


                if (step == 10) {

                    data = serialPort.readHexString(event.getEventValue());
                    System.out.print("Data (step " + step + "):->: " + data);
                    data2 = data;

                }

                if (step == 11) {    //// 3F FE  Запрос на чтение данных
                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 11 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 11  и без пробелов" + temp);
                        String temp_substr = temp.substring(4, 6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;

                    }
                    temp = temp.replace(" ", "");
                    System.out.println("count == " +count+" count_read == " +temp.length() );
                    if (temp.length() == count) {

                        data2 = temp;
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 12;
                        temp = "";
                    }

                }

                if (step == 13) {    //// 3F F6 Запрос на чтение данных
                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 13 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 13  и без пробелов" + temp);
                        String temp_substr = temp.substring(4, 6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;

                    }
                    temp = temp.replace(" ", "");
                    if (temp.length() == count) {

                        data2 = temp;
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 14;
                        temp = "";
                    }

                }


                if (step == 15) {    //// 3F F9 (2) Чтение служебной информации
                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 15 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 15  и без пробелов" + temp);
                        //String temp_substr = temp.substring(4,6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;

                    }
                    temp = temp.replace(" ", "");
                    System.out.println("count = " + count);
                    System.out.println("temp.length() = " + temp.length());
                    if (temp.length() == count) {
                        data2 = temp;
                        System.out.println("data2 = " + data2);
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 16;
                        System.out.println("step = " + step);
                        temp = "";
                    }

                }



                if (step == 17) {    ////
                    data = serialPort.readHexString(event.getEventValue());
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 15 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 15  и без пробелов" + temp);
                        //String temp_substr = temp.substring(4,6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);
                        z = 1;

                    }
                    temp = temp.replace(" ", "");
                    System.out.println("count = " + count);
                    System.out.println("temp.length() = " + temp.length());
                    if (temp.length() == count) {
                        data2 = temp;
                        System.out.println("data2 = " + data2);
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 18;
                        System.out.println("step = " + step);
                        temp = "";
                    }

                }

                if (step == 19) {

                    data = serialPort.readHexString(event.getEventValue());
                    System.out.print("Data (step " + step + "):->: " + data);
                    data2 = data;

                }
                if (step == 20) {

                    data = serialPort.readHexString(event.getEventValue());
                    System.out.print("Data (step " + step + "):->: " + data+"\n");
                    data2 = data;

                }


                if (step == 21) {    //// 3F FE  ТЕКУЩИЕ Запрос на чтение данных
                    data = serialPort.readHexString(event.getEventValue());
                    System.out.println("data step21 пришедшая =="+data);
                    temp = temp + data;//System.out.print ("Data :: "+data);
                    System.out.println("Дата при step 21 == " + temp);
                    //List<String> list = new ArrayList<>(Arrays.asList( temp.replace(" ","").split("(?<=\\G.{2})")));
                    if (temp.length() > 11 & z == 0) {
                        temp = temp.replace(" ", "");
                        System.out.println("temp при меньше 11  и без пробелов" + temp);
                        String temp_substr = temp.substring(4, 6);
                        count = Integer.parseInt(temp.substring(4, 6), 16);
                        count = count * 2 + 10;
                        System.out.println("count == " + count);

                        z = 1;
                    }
                    temp = temp.replace(" ", "");
                    System.out.println("temp промежуточный = "+temp);
                    System.out.println("temp length() = "+temp.length());
                    System.out.println("count = "+count);
///Проверка данных Если попали старые данны и обработка их не возможна//////////////
                    if(temp.length()>count&z==1){

                        System.out.println("temp.length()>count= "+step);

                        count = 0;

                        System.out.println("Ждем окончание тамера и новой отправки запроса");
                    }
     /////////////////////////////////////////////


                    if (temp.length() == count) {
                        System.out.println("вошли в выход  step= "+step);
                        data2 = temp;
                        System.out.println("вошли в цикл выхода. Data2= "+data2);
                        recieve_all_byte = 2;
                        count = 0;
                        z = 0;
                        step = 22;
                        temp = "";

                        System.out.println("вошли в цикл выхода. Data2= "+data2);
                    }
                    System.out.println("при проверке(пора выходить step= "+step);
                }

////////////////////////////////////////////////////////////
//////////// Закрываем порт ///////////////////////////////



                if (step == 500) {

                    data = serialPort.readHexString(event.getEventValue());
                    System.out.print("Data (step " + step + "):->: " + data);
                    data2 = data;

                }




            } catch (SerialPortException ex) {
                System.out.println(ex);

            }
        }
    }
}