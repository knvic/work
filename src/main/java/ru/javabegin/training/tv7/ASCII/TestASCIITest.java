package ru.javabegin.training.tv7.ASCII;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class TestASCIITest {
    @Test
    public void send_ASCII() throws Exception {
        int a=116;
        String a_s=Integer.toString(a);
        String i_to_h=format("%02X", a);
        System.out.println(" i_to_h= "+ i_to_h);


        String u1=i_to_h.substring(0,1);
        System.out.println("u1= "+u1);


        String u2=i_to_h.substring(1);
        System.out.println("u2= "+u2);

        List<String> list = new ArrayList<>();
        //list.add()
        System.out.println("вапвап= "+ asciiToHex(u1));
        System.out.println("вапвап= "+ asciiToHex(u2));


    }

    @Test
    public void asc_dec_sumbol() throws Exception {
        //String oct = "3A";
       // String oct = "3740";  㝀
        String oct = "37";

        int dec = Integer.parseInt(oct, 16);
        System.out.println("В десятичной с.с. будет " + dec);
        System.out.println("Знак ASCII будет " + (char) dec);
        System.out.println("----");
        char ff =(char)Integer.parseInt(oct, 16);
        System.out.println("ff = "+ff);

    }


    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

}