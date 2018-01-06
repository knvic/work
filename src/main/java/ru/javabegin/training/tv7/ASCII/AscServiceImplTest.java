package ru.javabegin.training.tv7.ASCII;

import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class AscServiceImplTest {


    @Test
    public void asciiToHex() throws Exception {
    AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("Результат= "+ ascService.asciiToHex("1B03"));




        System.out.println("Рез = "+Integer.parseInt("0326",16));
        System.out.println(" -- - - - - -");



        System.out.println("Рез = "+Integer.parseInt("0012",16));


        String hex = Integer.toHexString(806);

        System.out.println("hex = "+hex);
        System.out.println(" -- - - - - -");

        int a=806;
        String a_s=Integer.toString(a);
        String i_to_h=format("%04X", a);
        System.out.println(" i_to_h= "+ i_to_h);


    }

    @Test
    public void asciiToHexlist() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        ascService.asciiToHexlist("1B03");


    }


    @Test
    public void toHex02X() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("toHex02X = "+ ascService.toHex02X(18));


    }

    @Test
    public void toHex04X() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("toHex04X = "+ ascService.toHex04X(806));
    }

    @Test
    public void hexToDec() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("hexToDec = "+ ascService.hexToDec("0326"));
        System.out.println("hexToDec = "+ ascService.hexToDec("0012"));

    }


    @Test
    public void hex2ByteArray() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        //[B@5faeada1

        System.out.println(ascService.hex2ByteArray("314230333033323630303132"));

    }


    @Test
    public void hex_to_ascii() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();

        System.out.println(ascService.hex_to_ascii("37"));

    }


}