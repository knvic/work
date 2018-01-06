package ru.javabegin.training.tv7.LRC;

import org.junit.Test;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import static org.junit.Assert.*;

public class LRCTest {


    @Test
    public void calculateLRC() throws Exception {
        LRC lrc=new LRC();

        //String hexString="314230333033323630303132";
        //String hexString="314230333033323630303132";
          String hexString="313130333030364230303033";

        HexBinaryAdapter adapter = new HexBinaryAdapter();

        byte[] bytes = adapter.unmarshal(hexString);
        System.out.println(bytes);

        System.out.println(lrc.calculateLRC(bytes));


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



    }

    @Test
    public void lrcHex() throws Exception {
        int num1 = (Integer.parseInt("02", 16));
        int num2 = (Integer.parseInt("01", 16));
        int num3 = (Integer.parseInt("00", 16));
        int num4 = (Integer.parseInt("00", 16));
        int num5 = (Integer.parseInt("00", 16));
        int num6 = (Integer.parseInt("08", 16));
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

}