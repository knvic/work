package ru.javabegin.training.tv7.crc16;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Crc16ServiceImplTest {
    @Test
    public void crc16_valid() throws Exception {
        Crc16ServiceImpl crc16Service=new Crc16ServiceImpl();
        String str="1B 03 03 26 00 12 26 72";
        List<String> list=new ArrayList<String>(Arrays.asList(str.split(" ")));
        System.out.println(crc16Service.crc16_valid(list));

    }

    @Test
    public void crc16_t() throws Exception {

        Crc16ServiceImpl crc16Service=new Crc16ServiceImpl();
        String str3="1B 03 03 26 00 12";
        String str="1B 48 00 1C 00 02 21 66 00 02 00 04 00 01 00 00 00 00";
        String str1="1B 03 03 26 00 12 26 72";
        String str2="FF FF 00 03 3f fc 00 00";
        List<String> list=new ArrayList<String>(Arrays.asList(str.split(" ")));
        //  list.forEach(p->System.out.println(p));
        List<String> list_crc=crc16Service.crc16_t(list);
        list_crc.forEach(p->System.out.println(p));



    }

}