package ru.javabegin.training.vkt7.recieve;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Николай on 20.08.2017.
 */
public class Main_test_recieve_04_09 {


    public static void main(String[] args) throws ParseException {
       Recieve10ServiceImpl recieve10Service=new Recieve10ServiceImpl();
       Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();


        /*System.out.println("проверяем 3F F9");
        List<String> list=recieve03Service.r_3FF9("010310 27 0806 9802 3030323437373932 01 1F 04 AE1E");
        list.forEach(p-> System.out.print(p+" "));*/
       // System.out.println("проверяем запрос 3FFА после зароса активных элементов");
        //recieve03Service.r_3FFC_zapros("qwe");
        //list.forEach(p-> System.out.print(p+" "));

        //List<Properts> prop =new ArrayList<>();

       // recieve03Service.r_3FFE("sdf",prop);
      //  recieve03Service.r_3FFC("sdf");
       String a=  recieve03Service.hextostr("CA");
        System.out.println("a= "+a);


        String asd="01 03 3B" +
                " BC 1A C0 00" +
                " 77 15 C0 00" +
                " 77 15 04 00" +
                " 77 15 04 00" +
                " 77 15 00 00 04 00" +
                " 77 15 00 00 04 00" +
                " 45 05 C0 00" +
                " 45 05 00 00 04 00" +
                " 45 05 00 00 04 00" +
                " 3B A8 83 3F C0 00" +
                " CE C7 60 3F C0 00" +
                " 20 C0 00" +
                " FE DC \n";

        List<String> list = new ArrayList<>(Arrays.asList(asd.replace(" ","").split("(?<=\\G.{2})")));
        int data_size=Integer.parseInt(list.get(2),16);
        System.out.println("data_size "+data_size);
        for (int i=3;i<data_size+3;i++){
            System.out.print(list.get(i)+" ");
        }
       int t=Integer.parseInt("1ABC",16);
        System.out.println("\nt1=  "+t);
        /*  t=Integer.parseInt("1577",16);
        System.out.println("\nt2=  "+t);
        t=Integer.parseInt("0545",16);
        System.out.println("\ndt=  "+t);

        t=Integer.parseInt("833F",32);
        System.out.println("\ndt=  "+t);*/
        int g=Integer.parseInt("3BA8833F",16);
        System.out.println("\nG=  "+g);
       //int g=Integer.parseInt("A83B3F83",16);
        //System.out.println("G1=  "+g);

       float y=Float.parseFloat(String.valueOf(0x3F83A83B));
        System.out.println("\nGG=  "+y);

        //String myString = "BF800000";
        String myString = "3F83A83B";
        Long i = Long.parseLong(myString, 16);
        Float f = Float.intBitsToFloat(i.intValue());
        System.out.println("G="+f);
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));

       f = Float.intBitsToFloat(Integer.valueOf(myString,16).intValue());
        System.out.println("GG="+f);

        String a_10_09_17_0 ="01 03 23 07 0B C0 00 F2 0D C0 00 0C 03 C0 00 E4 02 C0 00 15 FD C0 00 15 FD FF FF 04 00 15 FD FF FF 04 00 20 C0 00 1F 67 ";
        String a_10_09_17 ="07 0B C0 00" +
                           "F2 0D C0 00" +
                           "0C 03 C0 00" +
                           " E4 02 C0 00" +
                           " 15 FD C0 00" +
                           " 15 FD FF FF 04 00" +
                           " 15 FD FF FF 04 00" +
                           " 20 C0 00";





    }
}
