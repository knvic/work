package ru.javabegin.training.test_modem;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by Николай on 03.09.2017.
 */

@Component
public class EventListener_test implements SerialPortEventListener   { /*Слушатель срабатывающий по появлению данных на COM-порте*/
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
    static SerialPort serialPort;

    String data;



    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }


    public  String hextostr(String hex){
        int len = hex.length();
        byte[] cStr = new byte[len/2];
        for( int i = 0; i < len; i+=2 ) {
            cStr[i/2] = (byte)Integer.parseInt( hex.substring( i, i+2 ), 16 );
        }
        CharsetDecoder decoder = Charset.forName( "Cp866" ).newDecoder();

        CharBuffer cb = null;
        try {
            cb = decoder.decode( ByteBuffer.wrap( cStr ));
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        //System.out.println( cb.toString());
        return cb.toString();

    }


    public void serialEvent(SerialPortEvent event) {


        if (event.isRXCHAR() && event.getEventValue() > 0) { /*Если происходит событие установленной маски и количество байтов в буфере более 0*/
            try {



                if (step == 100) {
                   // data = serialPort.readHexString(event.getEventValue());
                    System.out.println("temp= "+temp);
                    temp = temp + serialPort.readHexString(event.getEventValue());
                    if (temp.replace(" ", "").contains("0D0A4F4B0D0A")){
                       System.out.println("конеуц овета перевод строки");
                      System.out.println("temp= "+temp);
                      String data1 = temp.replace(" ", "");
                        System.out.println("data1="+data1);
                        data1=data1.substring(0,data1.length()-12);
                        System.out.println("data1="+data1);
                        temp="";
                        data2 = hextostr(data1);
                        System.out.println("Data hextostr:: " + data2);
                    }


                }



            } catch (SerialPortException ex) {
                System.out.println(ex);

            }
        }
    }
}