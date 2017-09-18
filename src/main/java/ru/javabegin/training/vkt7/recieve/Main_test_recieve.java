package ru.javabegin.training.vkt7.recieve;

//import com.sun.istack.internal.NotNull;

import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Николай on 20.08.2017.
 */
public class Main_test_recieve {
public static void hextostr(String hex){
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
    System.out.println( cb.toString());

}

    static int  little2big(int i) {
        return (i&0xff)<<24 | (i&0xff00)<<8 | (i&0xff0000)>>8 | (i>>24)&0xff;
    }

    public static void main(String[] args) throws ParseException {
       Recieve10ServiceImpl recieve10Service=new Recieve10ServiceImpl();
       Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();
   /*     recieve10Service.r_3FFF_n("01");

        System.out.println();
        String str="AC332FE7";

        System.out.println();
        //String hex = "6174656ec3a7c3a36f";
        String hex = "AC332FE7";
        Main_test_recieve.hextostr(hex);
        hex="AAA32FE1AC32";
        Main_test_recieve.hextostr(hex);
        int decnum= 44;
        //String temp = Integer.toBinaryString((decnum | 0x40000000));
        int temp = (0x40000000|decnum);
        //Main_test_recieve.little2big(temp);
        System.out.println(Main_test_recieve.little2big(temp));
        String Hex=Integer.toHexString(Main_test_recieve.little2big(temp));
        System.out.println("Hex="+Hex);


        String str2="0x00 0x10 0x3F 0xFF 0x00 0x00 0x60 0x2C 0x00 0x00 0x40 0x07 0x00" +
                " 0x2D 0x00 0x00 0x40 0x07 0x00 0x2E 0x00 0x00 0x40 0x07 0x00" +
                " 0x2F 0x00 0x00 0x40 0x07 0x00 0x30 0x00 0x00 0x40 0x07 0x00" +
                " 0x35 0x00 0x00 0x40 0x07 0x00 0x37 0x00 0x00 0x40 0x07 0x00" +
                " 0x38 0x00 0x00 0x40 0x07 0x00 0x39 0x00 0x00 0x40 0x01 0x00" +
                " 0x3B 0x00 0x00 0x40 0x01 0x00 0x3C 0x00 0x00 0x40 0x01 0x00" +
                " 0x3D 0x00 0x00 0x40 0x01 0x00 0x42 0x00 0x00 0x40 0x01 0x00" +
                " 0x46 0x00 0x00 0x40 0x01 0x00 0x45 0x00 0x00 0x40 0x01 0x00" +
                " 0x4C 0x00 0x00 0x40 0x01 0x00 0x8C 0x75";
        String str3= "01 10 3F FF 00 00 60 2C 00 00 40 07 00" +
                " 2D 00 00 40 07 00 2E 00 00 40 07 00" +
                " 2F 00 00 40 07 00 30 00 00 40 07 00" +
                " 35 00 00 40 07 00 37 00 00 40 07 00" +
                " 38 00 00 40 07 00 39 00 00 40 01 00" +
                " 3B 00 00 40 01 00 3C 00 00 40 01 00" +
                " 3D 00 00 40 01 00 42 00 00 40 01 00" +
                " 46 00 00 40 01 00 45 00 00 40 01 00" +
                " 4C 00 00 40 01 00 22 58";
        String str4="1 16 63 255 0 0 96 44 0 0 64 7 0" +
                " 45 0 0 64 7 0 46 0 0 64 7 0" +
                " 47 0 0 64 7 0 48 0 0 64 7 0" +
                " 53 0 0 64 7 0 55 0 0 64 7 0" +
                " 56 0 0 64 7 0 57 0 0 64 1 0" +
                " 59 0 0 64 1 0 60 0 0 64 1 0" +
                " 61 0 0 64 1 0 66 0 0 64 1 0" +
                " 70 0 0 64 1 0 69 0 0 64 1 0" +
                " 76 0 0 64 1 0 34 88";



        List<String> resp = new LinkedList<String>(Arrays.asList(str3.split(" ")));
        List<Integer> data1 = new LinkedList<Integer>();
        List<Integer> data2 = new LinkedList<Integer>();
        resp.stream().forEach(w->data1.add(Integer.parseInt(w ,16)));
        System.out.println();
        System.out.println("Выводим data1\n ");
        data1.forEach(p->System.out.print(p+" "));
        resp=null;
        for(int item : data1.subList(7, data1.size()-2)) {
            System.out.print(item);
            data2.add(item);
        }

        recieve03Service.r_3FF6("sdf");


        System.out.println("пришедшая строка 3108"+recieve03Service.hextostr("0D0A4E4F20434152524945520D0A"));*/

        System.out.println("проверяем 3F FE запрос на чтение данных");
        List<Properts> prop =new ArrayList<>();

       // recieve03Service.r_3FFE("sdf",prop);
        recieve03Service.r_3FFC_zapros("sdf");


    }
}
