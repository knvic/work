package ru.javabegin.training.test_modem;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import ru.javabegin.training.test_modem.EventListener_test;

import java.util.EventListener;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;



/**
 * Created by Николай on 31.10.2017.
 */
public class TectModemLikoil extends EventListener_test{

    public static void main(String args[]) throws SerialPortException, InterruptedException {


        String[] portNames = SerialPortList.getPortNames();
        String port = "";
        for (int i = 0; i < portNames.length; i++) {
            port = portNames[i];
            System.out.println(port);

        }


        SerialPort serialPort = new SerialPort(port);
        serialPort.openPort (); //Метод открытия порта
        serialPort.setParams (SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                SerialPort.FLOWCONTROL_RTSCTS_OUT);
        serialPort.setEventsMask (SerialPort.MASK_RXCHAR); //Устанавливаем маску или список события на которые будет происходить реакция. В данном случае это приход данных в буффер порта

        EventListener_test eL= new EventListener_test();

        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
        }

        serialPort.addEventListener(eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
        //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

        eL.setSerialPort(serialPort);

        System.out.println("\nпосылаем +++");
        step=100;

        serialPort.writeBytes("+++".getBytes());
        Thread.sleep(5000);


        System.out.println("\nпосылаем ATH");
        serialPort.writeBytes("ATH\r".getBytes());
        Thread.sleep(1000);
        System.out.println("\nпосылаем ati1");
        serialPort.writeBytes("ati1\r".getBytes());


        Thread.sleep(5000);
        System.out.println("посылаем  AT+CREG?");
        serialPort.writeBytes("AT+CREG?\r".getBytes());
        System.out.println("\n Ждем данных от модема ");
        Thread.sleep(1000);
        if(data2.contains("+CREG: 2,1,")){
            System.out.println("регистрация произведена ");
        }


        System.out.println("посылаем   at+cops?");
        serialPort.writeBytes(" at+cops?\r".getBytes());
        System.out.println("\n Ждем данных от модема ");

        Thread.sleep(1000);

        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
            System.out.println("Закрываем");
        }
        serialPort.closePort();
        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
            System.out.println("Закрываем");
        }
        else{
            System.out.println("порт закрыт");
        }


        serialPort.openPort (); //Метод открытия порта
        serialPort.setParams (SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE); //Задаем основные параметры протокола UART
        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                SerialPort.FLOWCONTROL_RTSCTS_OUT);
        serialPort.setEventsMask (SerialPort.MASK_RXCHAR);
        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
         }
         else{System.out.println("порт закрыт");}
        serialPort.addEventListener(eL); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс
        //serialPort.addEventListener (new EventListener ()); //Передаем экземпляр класса EventListener порту, где будет обрабатываться события. Ниже описан класс

        eL.setSerialPort(serialPort);


            serialPort.writeBytes("+++".getBytes());
            Thread.sleep(5000);


            System.out.println("\nпосылаем ATH");
            serialPort.writeBytes("ATH\r".getBytes());
            Thread.sleep(1000);
            System.out.println("\nпосылаем ati1");
            serialPort.writeBytes("ati1\r".getBytes());
        Thread.sleep(3000);

        System.out.println("Набираем номер");

        serialPort.writeBytes("ATDP+79064427319\r".getBytes());



        serialPort.writeBytes("+++".getBytes());
        Thread.sleep(5000);


        System.out.println("\nпосылаем ATH");
        serialPort.writeBytes("ATH\r".getBytes());

        Thread.sleep(5000);

        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
            System.out.println("Закрываем");
        }
        serialPort.closePort();
        if(serialPort.isOpened()){
            System.out.println("порт "+ serialPort.getPortName()+ "открыт");
            System.out.println("Закрываем");
        }
        else{
            System.out.println("порт закрыт");
        }


        }



    //////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
    static Callable callable(long Seconds) {
        return () ->
        {
            for (int i = 1; i < Seconds + 1; i++) {

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
