package ru.javabegin.training.tv7.ASCII;

import org.junit.Test;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class TestASCIITest {

    @Test
    public void hexToFloat() {
        String str="CF98BDC2";
        System.out.println("str= "+str);

        System.out.println("str Float - Mid-Little Endian (CDAB)= "+l2b(str));

//        float m_  =Float.intBitsToFloat(Integer.parseInt("BDC2CF98"));
        // java.lang.NumberFormatException: For input string: "BE859F52"

        //float m_  =Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue());

       // p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN).toString());



        String myString = "BDC2CF98";

        // Вариант правильного преобразования 1
        Long i = Long.parseLong(myString, 16);
        Float f = Float.intBitsToFloat(i.intValue());
        System.out.println(f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));

        // Вариант правильного преобразования 2
        f = Float.intBitsToFloat((int) Long.parseLong(myString, 16));
        System.out.println(f);



        //double e = Double.longBitsToDouble(Long.parseLong(l2b(str), 16));
        //System.out.println("e= "+e);


    }



    @Test
    public void send_ASCII() throws Exception {
        int a=72;
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
        String oct = "72";

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
    public String l2b (String str) {
        StringBuilder str_out = new StringBuilder();
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{4})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out.append(list.get(i));
        }
        return str_out.toString();
    }

}