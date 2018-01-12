package ru.javabegin.training.tv7.LRC;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LRCTest {



    @Test
    public void calculateLRC() throws Exception {
        LRC lrc=new LRC();

        //String hexString="314230333033323630303132";
       // String hexString="314230333033323630303132";
          String hexString="314230333033323630303132";

        HexBinaryAdapter adapter = new HexBinaryAdapter();

        byte[] bytes = adapter.unmarshal(hexString);
       // byte[] bytes = {0x1B,0x03,0x03,0x26,0x00,0x12};
        System.out.println(bytes);

        System.out.println("lrc = "+lrc.calculateLRC(bytes));

        String lrc1=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(lrc.calculateLRC(bytes)),2));
        System.out.println("lrc1 = "+lrc1);

        AuxLrcImpl auxLrc=new AuxLrcImpl();

        byte[] a={lrc.calculateLRC(bytes)};



        System.out.println("LRC to hex = "+auxLrc.bytesToHex(a));

        System.out.println(auxLrc.bytesToHex(bytes));

        System.out.println("- - - - - - - -  - - -");
        byte[] b= auxLrc.hexStringToByteArray(hexString);
        System.out.println("byte array b = "+b);
        System.out.println("LRC = "+lrc.calculateLRC(b));
        System.out.println("LRC1 = "+lrc.getLRCCheckSum(b,0,b.length));
        System.out.println("toHex = "+String.format("%02X ", lrc.calculateLRC(b)));
        System.out.println("- - - - - - - -  - - -");

        String example = "This is an example";
        byte[] bytes1 = example.getBytes();

        System.out.println("Text : " + example);
        System.out.println("Text [Byte Format] : " + bytes1);
        System.out.println("Text [Byte Format] : " + bytes1.toString());

        String s = new String(bytes1);
        System.out.println("Text Decryted : " + s);
        System.out.println("- - - - - - - -  - - -");
        System.out.println("Text [Byte Format] : " + b);
        System.out.println("Text [Byte Format] : " + b.toString());

        String s1 = new String(b);
        System.out.println("Text Decryted : " + s1);


        /*

        >>int a=0xAF;
        >>int b=0x13;

        >Нет, это обычное сложение. Интересует сложение БЕЗ ПЕРЕНОСА(!). В Вашем примере c=0xC2, а при сложении без переноса должно получиться 0xB2.
        >В Вашем примере c=0xC2, а при сложении без переноса должно получиться 0xB2

        Сложение по модулю 16?

        #define MASKADD(a,b,mask) (((a & mask)+(b & mask)) & mask)

        c = MASKADD(a,b,0x0f) | MASKADD(a,b,0xf0)
*/
        int a1=0xAF;
        int b1=0x13;
        int q = MASKADD(a1,b1,0x0f) | MASKADD(a1,b1,0xf0);
        System.out.println("bin = "+q);
        String lrc2=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(q),2));
        System.out.println("lrc2 = "+lrc2);


    }

    @Test
    public void lrcHex() throws Exception {
        int num1 = (Integer.parseInt("1B", 16));
        int num2 = (Integer.parseInt("03", 16));
        int num3 = (Integer.parseInt("03", 16));
        int num4 = (Integer.parseInt("26", 16));
        int num5 = (Integer.parseInt("00", 16));
        int num6 = (Integer.parseInt("12", 16));
        int ff=(Integer.parseInt("FF", 16));

        int sum= num1+num2+num3+num4+num5+num6;


        int t=ff-sum;
        System.out.println("t = "+Integer.toBinaryString(t));
        sum=t+1;
        System.out.println("bin = "+Integer.toBinaryString(sum));
        System.out.println("hex = " + Integer.toHexString(Integer.parseInt(Integer.toBinaryString(sum),2)));

        System.out.println("=====================================");
        int num11 = (Integer.parseInt("11", 16));
        int num12 = (Integer.parseInt("03", 16));
        int num13 = (Integer.parseInt("00", 16));
        int num14 = (Integer.parseInt("6B", 16));
        int num15 = (Integer.parseInt("00", 16));
        int num16 = (Integer.parseInt("03", 16));
        int ff1=(Integer.parseInt("FF", 16));

        sum= num11+num12+num13+num14+num15+num16;


        t=ff-sum;
        System.out.println("t = "+Integer.toBinaryString(t));
        sum=t+1;
        System.out.println("bin = "+Integer.toBinaryString(sum));
        System.out.println("hex = " + Integer.toHexString(Integer.parseInt(Integer.toBinaryString(sum),2)));




    }

    @Test
    public void lrcC() throws Exception {

          /*

        >>int a=0xAF;
        >>int b=0x13;

        >Нет, это обычное сложение. Интересует сложение БЕЗ ПЕРЕНОСА(!). В Вашем примере c=0xC2, а при сложении без переноса должно получиться 0xB2.
        >В Вашем примере c=0xC2, а при сложении без переноса должно получиться 0xB2

        Сложение по модулю 16?

        #define MASKADD(a,b,mask) (((a & mask)+(b & mask)) & mask)

        c = MASKADD(a,b,0x0f) | MASKADD(a,b,0xf0)
*/
        int a1=Integer.parseInt("1B", 16);
        int b1=Integer.parseInt("03", 16);
        int c1=Integer.parseInt("03", 16);
        int d1=Integer.parseInt("26", 16);
        int e1=Integer.parseInt("00", 16);
        int h1=Integer.parseInt("12", 16);
       // int q = MASKADD(a1,b1,0x0f) | MASKADD(a1,b1,0xf0);
        //System.out.println("bin = "+q);
        int q = MASKADD1(a1,b1,c1,d1,e1,h1,0x0f) | MASKADD1(a1,b1,c1,d1,e1,h1,0x0f);
        System.out.println("bin = "+q);

        String lrc2=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(q),2));
        System.out.println("lrc2 = "+lrc2);




        List<String> list=new ArrayList<>();
        list.add("1B");
        list.add("03");
        list.add("03");
        list.add("26");
        list.add("00");
        list.add("12");
        list.forEach(p-> System.out.print(p+" "));
        System.out.println();

        int sum=0;
        for(int i=0; i<list.size();i++){

        sum=MASKADD(sum ,Integer.parseInt(list.get(i), 16),0x0f) | MASKADD(sum,Integer.parseInt(list.get(i), 16),0xf0);
        }
        String lrc3=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(sum),2));
        System.out.println("lrc3 = "+lrc3);

        int qq=add(0xAF,0x13);
        System.out.println("qq = "+qq);
        String lrc4=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(qq),2));
        System.out.println("lrc4 = "+lrc3);
        int sdf=0xAF^0x13;
        System.out.println("sdf = "+sdf);
       lrc4=(String)Integer.toHexString(Integer.parseInt(Integer.toBinaryString(sdf),2));
        System.out.println("lrc4 = "+lrc4);





    }

    public int  MASKADD(int a,int b,int mask){

        int g=(((a & mask)+(b & mask)) & mask);

        return g;
    }

    public int  MASKADD1(int a,int b,int c,int d,int e,int h,int mask){

        int g=(((a & mask)+(b & mask)+(c & mask)+(d & mask)+(e & mask)+(h & mask)) & mask);

        return g;
    }


    public static int add(int a, int b)	{
        if (b == 0) return a;
        int sum = a ^ b;			// добавляем без переноса
        int carry = (a & b) << 1;	// перенос без суммирования
        return add(sum, carry);

    }
}