package ru.javabegin.training.vkt7.recieve;


import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.propert.Properties_xml;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import javax.xml.bind.JAXBException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Николай on 20.08.2017.
 */
public class Recieve03ServiceImpl_old_07_10_17 implements Recieve03Service{

    @Override
    public int r_3FFE_1 (String str) {
        int version;
        //str= "01 03 AE 3F 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 54 37 00 00 00 00 00 00 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 1B 08";
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        version = Integer.parseInt(list.get(64));
        return version;
    }
@Override
    public List<String> r_3FF9(String str){
    //str="01 03 10 27 08 06 98 02 30 30 32 34 37 37 39 32 01 1F 04 AE 1E";
    List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        if(!list.get(1).equals("03"))System.out.println("Ошибка в ответе!!!!!!!!!!!!!!");
        list.forEach(System.out::print);
        String s =list.get(4)+list.get(5);

      //  System.out.println("s = "+s);
     //   list.forEach(System.out::print);

/**
 * Функция parse_3FF9(s) для получения [{схема подключения},{Tp3},{t5}]
 * реализована в этом же классе ниже
 */

    List<String>list1 = new ArrayList<>();
    List<String>list_out = new ArrayList<>();
    s =list.get(3);
    list1=parse_3FF9(s,"1");
    System.out.println("Версия ПО :: "+ list1.get(0));
    list_out.add(list1.get(0));

    list1=null;
    s =list.get(4)+list.get(5);
    list1 = (parse_3FF9(s,"2"));
    System.out.println("Данные по Тв1 :: ");
    System.out.println("Схема подключения :: "+ list1.get(0));
    System.out.println("Tp3 :: "+ list1.get(1));
    System.out.println("t5 :: "+ list1.get(2));
    list_out.add(1,list1.get(0));
    list_out.add(2,list1.get(1));
    list_out.add(3,list1.get(2));


    list1=null;
    s =list.get(6)+list.get(7);
    list1=parse_3FF9(s,"3");
    System.out.println("Данные по Тв2 :: ");
    System.out.println("Схема подключения :: "+ list1.get(0));
    System.out.println("Tp3 :: "+ list1.get(1));
    System.out.println("t5 :: "+ list1.get(2));
    list_out.add(4,list1.get(0));
    list_out.add(5,list1.get(1));
    list_out.add(6,list1.get(2));

    /////4////
    StringBuilder ss = new StringBuilder();

  //  System.out.println("Новое!!!!!!!!!!!!!!!!!!");
   // String ss="";
    /*IntStream.range(8, 15)
            .forEach(p-> {
                System.out.println("Hex = " + list.get(p) + "Int = " + Integer.parseInt(list.get(p), 16));
                System.out.println(ss + Integer.toString(Integer.parseInt(list.get(p), 16)));
            });*/
    IntStream.range(8, 15)
            .forEach(p-> ss.append(new StringBuffer(list.get(p).subSequence(0, list.get(p).length()))));

    //System.out.println("абонент = "+ss);
    list_out.add(7,ss.toString());




    ///Сетевой номер
    String nomer = Integer.toString(Integer.parseInt(list.get(16),16));
  //  System.out.println("nomer hex = "+list.get(16)+" int= "+Integer.parseInt(list.get(16),16));
    list_out.add(8,nomer);
    ///Дата странная
    String data = Integer.toString(Integer.parseInt(list.get(17),16));
 //   System.out.println("data hex = "+list.get(17)+" int= "+Integer.parseInt(list.get(17),16));
    list_out.add(9,data);
    ///Модель
    String model = Integer.toString(Integer.parseInt(list.get(18),16));
  //  System.out.println("model hex = "+list.get(18)+" int= "+Integer.parseInt(list.get(18),16));
    list_out.add(10,model);

    list_out.forEach(p->System.out.print(p+" "));

        return list_out;
    }

    @Override
    public List<Properts> r_3FFE (String str, List<Properts> prop_session) {

        Properts temp_prop;

        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));

        System.out.println();

        String str1="";
        int count=0;

        for (int i=3;i<=Integer.parseInt(list.get(2),16);) {
            temp_prop=prop_session.get(count);

            if (list.get(i+1).equals("00")) {
                for (int j = i + 2; j < i + 2 + Integer.parseInt(list.get(i), 16); j++) {
                    str1 = str1 + list.get(j);
                }
                System.out.println(temp_prop.getText()+" " + temp_prop.getName() + " " + hextostr(str1));
                i = i + 2 + Integer.parseInt(list.get(i), 16) + 2;
                temp_prop.setEd(hextostr(str1));
                str1 = "";
                count++;
                continue;

            }
            if (list.get(i+1).equals("C0")){
                System.out.println("Кол-во зн. "+temp_prop.getText()+ " " +temp_prop.getName()+ " " + Integer.parseInt(list.get(i), 16) );
                temp_prop.setZnak(Integer.parseInt(list.get(i), 16));
                i = i + 3 ;
                count++;
                 }

        }

        prop_session.forEach(p->System.out.println(p.getId()+"  "+ p.getName() + "  "+ p.getText()+ "  "+ p.getEd()+ "  "+ p.getZnak()));
// Получаем массив элементов свойств класса Properts с добавленными ед. изм. и количеством знаков

        return  prop_session;
    }

    @Override
    public ArrayList<Timestamp> r_3FF6 (String str) throws ParseException {
        int version;
        /*str= "01 03 0C " +
                "03 07 11 11 " +
                "14 08 11 10 " +
                "13 04 11 17 " +
                "5F A6";*/

        //List<String> list = new LinkedList<String>(Arrays.asList(str.split(" ")));
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        // list.forEach(System.out::print);
        System.out.println();
        int linght = 0;
        String str1="";
        int count=1;
        ArrayList<Timestamp> data = new ArrayList<>();
        for (int i=3;i<=Integer.parseInt(list.get(2),16);) {
             str1 =  String.valueOf(Integer.parseInt(list.get(i),16))+":"+String.valueOf(Integer.parseInt(list.get(i+1),16))+":"+String.valueOf(Integer.parseInt(list.get(i+2),16))+":"+String.valueOf(Integer.parseInt(list.get(i+3),16));
             System.out.println("Str=" + str1);





           /* //SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
            SimpleDateFormat format = new SimpleDateFormat("dd:MM:yy:HH");
            Date date = format.parse(str1);
            System.out.println("Date : " + date);
            data.add(date);*/


            //LocalDateTime randDate = LocalDateTime.of(17, 8, 11, 14, 0, 0);
            //LocalDateTime randDate = LocalDateTime.of(YYY, MM, DD, HH, SS, 0);

            LocalDateTime randDate = LocalDateTime.of((2000+Integer.parseInt(list.get(i+2),16)), Integer.parseInt(list.get(i+1),16), Integer.parseInt(list.get(i),16), Integer.parseInt(list.get(i+3),16), 0, 0);
            System.out.println("localDateTime Str = "+randDate);
            System.out.println(randDate.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
            Timestamp timestamp = Timestamp.valueOf(randDate);
            data.add(timestamp);


            i=i+4;
        }
        //
        return data;

    }


    @Override
    public List<Properts> r_3FFC (String str, List<Properts> prop_completed) {
        //str="0103 66 00 00 00 40 02 00 01 00 00 40 02 00 03 00 00 40 04 00 04 00 00 40 04 00 06 00 00 40 04 00 07 00 00 40 04 00 09 00 00 40 02 00 0A 00 00 40 02 00 0B 00 00 40 04 00 0C 00 00 40 04 00 0D 00 00 40 04 00 0E 00 00 40 02 00 11 00 00 40 04 00 12 00 00 40 04 00 13 00 00 40 04 00 14 00 00 40 04 00 4D 00 00 40 01 00 07 A1 ";

        //str= "01 03 4F 02 00 F8 43 C0 00 04 00 AC 33 2F E7 C0 00 03 00 20 AC 33 C0 00 02 00 20 E2 C0 00 06 00 AA A3 2F E1 AC 32 C0 00 04 00 83 AA A0 AB C0 00 01 00 E7 C0 00 01 00 E7 C0 00 02 C0 00 02 C0 00 02 C0 00 02 C0 00 03 C0 00 02 C0 00 02 C0 00 03 C0 00 91 C0";
//str="0103510200F843C0000400AC332FE7C000030020AC33C000020020E2C0000600AAA32FE1AC32C000040083AAA0ABC0000100E7C000030020AC33C00002C00002C00002C00002C00003C00002C00002C00003C000CF58";
        //List<String> list = new LinkedList<String>(Arrays.asList(str.split(" ")));
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        //list.forEach(System.out::print);
        System.out.println();


        /*LinkedList<Properts> prop = new LinkedList<>();
        try {
            prop= Properties_xml.getProperties();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
       // prop.forEach(System.out::println);*/
        List<Properts> active_items = new ArrayList<>();
        for (int i=3;i<=Integer.parseInt(list.get(2),16);) {

            for(Properts temp_prop:  prop_completed){

                if (temp_prop.getId() == Integer.parseInt(list.get(i),16) ){
                    //System.out.println("элемент для размера "+list.get(i+4));
                    temp_prop.setSize(Integer.parseInt(list.get(i+4),16) );
                    active_items.add(temp_prop);
                    break;
                     }
            }
            i=i+6;

        }
        active_items
                .stream()
                .forEach(p-> System.out.println("\n "+ p.getId()+" "+p.getText()+" "+p.getSize()));
        return active_items;

    }







    @Override
    public List<Measurements> r_3FFE_Measurements(String str, List<Measurements> current_measur) {

        Measurements temp_measur;
        List<String> data = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        List<String> data1 = new ArrayList<>();
        System.out.println();
        data.forEach(p->System.out.print(p+" "));
        System.out.println();
        //for(String item : data.subList(3, parseInt(data.get(2),16))) {
        for(String item : data.subList(3, data.size()-2)) {
            data1.add(item);
        }
        System.out.println();
        data1.forEach(p->System.out.print(p+" "));
        System.out.println();
        int marker = 0;
        int razmer=0;
        String measur="";
        String  ns;
        String quality;

        for (int i=0; i<current_measur.size(); i++){
            temp_measur=current_measur.get(i);
            razmer=temp_measur.getSize();
            System.out.println("Измеряемое значение "+ temp_measur.getText()+" размер "+ razmer);
                for (int j=marker;j<marker+razmer;j++){
                measur=measur+data1.get(j);
                }
            //System.out.println(temp_measur.getText()+" = " +measur);
           // System.out.println("Переворачиваем."+ temp_measur.getText()+" = " +l2b(measur));

            quality=data1.get(marker+razmer);
            ns=data1.get(marker+razmer+1);




            if (razmer==2){
                if(quality.equals("C0")){

                    temp_measur.setType("int");
                    temp_measur.setMeasurInt(Integer.parseInt(l2b(measur),16));
                        if (temp_measur.getEd()!=null){
                            temp_measur.setMeasurText(String.valueOf( temp_measur.getMeasurInt()*0.01)+temp_measur.getEd());}
                        else{
                            temp_measur.setMeasurText(String.valueOf(temp_measur.getMeasurInt()*0.01));
                        }
                                    }
                else{System.out.println(temp_measur.getText()+"  - не верный байт качества");}
            }
            if (razmer==4){
                if(quality.equals("C0")){

                    temp_measur.setType("float");
                    temp_measur.setMeasurFloat(Float.intBitsToFloat(Integer.valueOf(l2b(measur),16).intValue()));
                        if (temp_measur.getEd()!=null){
                        temp_measur.setMeasurText(String.valueOf(temp_measur.getMeasurFloat())+temp_measur.getEd());}
                        else{
                            temp_measur.setMeasurText(String.valueOf(temp_measur.getMeasurFloat()));
                        }
                    //System.out.println(temp_measur.getText()+" = " +temp_measur.getMeasur_float());

                }
                else{System.out.println(temp_measur.getText()+"  - не верный байт качества");}

            }

            temp_measur.setQuality(quality);
            temp_measur.setQualityText(OPC_QUALITY(quality));
            temp_measur.setNs(ns);
            //System.out.println("Байт качества = "+OPC_QUALITY(quality));
            //System.out.println("Байт нештатной ситуации = "+ns);

            System.out.println(temp_measur.getText()+" = " +temp_measur.getMeasurText()+" байт качества -"+temp_measur.getQualityText()+"NS -"+temp_measur.getNs());




        marker=marker+razmer+2;
       // System.out.println("marker = "+marker);
            measur="";
        }



        return  current_measur;
    }






    @Override
    public int r_3ECD (String str) {

        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        //list.forEach(System.out::print);
        System.out.println();


        return Integer.parseInt(list.get(3),16);

    }
    @Override
    public int r_3F5B (String str) {

        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        //list.forEach(System.out::print);
        System.out.println();


        return Integer.parseInt(list.get(3),16);

    }

    @Override
    public int r_3FE9 (String str) {
int number_active_base=10000;
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        //list.forEach(System.out::print);
        System.out.println();
        if(Integer.parseInt(list.get(3),16)==0){
            number_active_base=1;
        }
        if(Integer.parseInt(list.get(3),16)==1){
            number_active_base=2;
        }


        return number_active_base;

    }










    @Override
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



    /////////////////////////////////////////////////////////////////////////
    //////Internal/  Внутренние функции. Вспомогательные, временные функции
    ///////////////////////////////////////////////////////////////////////


    public List<String> parse_3FF9(String s, String f){
        /**
         *
         * @param s  целое 16-ти разрядное число из запроса 0х3FF9;
         * @return массив [{схема подключения},{Tp3},{t5}]
         */
        String digits = "0123456789ABCDEF";
        LinkedList<String> list= new LinkedList<>();
        int binnum[] = new int[100];
        int i, j;
        s = s.toUpperCase();
        int decnum = 0;
        for ( i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            decnum = 16*decnum + d;
        }
       // System.out.println("decnum= "+decnum);
        String temp = Integer.toBinaryString((decnum | 0x80000000));
        temp = temp.substring(Integer.SIZE - Short.SIZE);
      //  System.out.println("temp2 = "+temp);
        if (f.equals("1")) {
            //String s1 = temp.substring(8) + temp.substring(0, 8);
            int s1=Integer.parseInt(temp.substring(8,12),2);
          //  System.out.println("s1 = "+s1);
            int s2=Integer.parseInt(temp.substring(12,16),2);

          //  System.out.println("s1 = "+s2);
            String version =Integer.toString(s1)+"."+Integer.toString(s2);
            list.add(version);
            /*list.add(Integer.parseInt(temp.substring(3, 7), 2));
            list.add(Integer.parseInt(s1.substring(7, 9), 2));
            list.add(Integer.parseInt(s1.substring(9, 11), 2));
            System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));*/
        }

        if (f.equals("2")||f.equals("3")) {
            String s1 = temp.substring(8) + temp.substring(0, 8);
            list.add(Integer.toString(Integer.parseInt(s1.substring(3, 7), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(7, 9), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(9, 11), 2)));
           // System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));
        }

        if (f.equals("4")) {
            String s1 = temp.substring(8) + temp.substring(0, 8);
            list.add(Integer.toString(Integer.parseInt(s1.substring(3, 7), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(7, 9), 2)));
            list.add(Integer.toString(Integer.parseInt(s1.substring(9, 11), 2)));
          //  System.out.println(list.get(0) + ":: " + list.get(1) + ":: " + list.get(2));
        }

        return list;
    }

    /**
     * вспомогательный метод для определения параметров посылаемых программой vkteasy
     * выбранных после Запроса активных элементов
      */


    public void r_3FFC_zapros (String str) {
        String str1="FF FF 01 10 3F FF 00 00 48 00 00 00 40 02 00 01 00 00 40 02 00 09 00 00 40 02 00 0A 00 00 40 02 00 0C 00 00 40 04 00 0D 00 00 40 04 00 0E 00 00 40 02 00 11 00 00 40 04 00 12 00 00 40 04 00 13 00 00 40 04 00 14 00 00 40 04 00 4D 00 00 40 01 00 C6 6C ";
        str="FF FF 01 10 3F FF 00 00 48 00 00 00 40 02 00 01 00 00 40 02 00 09 00 00 40 02 00 0A 00 00 40 02 00 0C 00 00 40 04 00 0D 00 00 40 04 00 0E 00 00 40 02 00 11 00 00 40 04 00 12 00 00 40 04 00 13 00 00 40 04 00 14 00 00 40 04 00 4D 00 00 40 01 00 C6 6C ";
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        //list.forEach(System.out::print);
        System.out.println();


        LinkedList<Properts> prop = new LinkedList<>();
        try {
            prop= Properties_xml.getProperties();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        // prop.forEach(System.out::println);
        List<Properts> active_items = new ArrayList<>();
        for (int i=9;i<=Integer.parseInt(list.get(8),16);) {

            for(Properts temp_prop:  prop){

                if (temp_prop.getId() == Integer.parseInt(list.get(i),16) ){
                    //System.out.println("элемент для размера "+list.get(i+4));
                    temp_prop.setSize(Integer.parseInt(list.get(i+4),16) );
                    active_items.add(temp_prop);
                    break;
                }
            }
            i=i+6;

        }
        active_items
                .stream()
                .forEach(p-> System.out.println("\n "+ p.getId()+" "+p.getText()+" "+p.getSize()));


    }

    public String OPC_QUALITY (String str) {
        String quality="";
        if (str.equals("C0")) {
            //System.out.println("OPC_QUALITY_GOOD 0xC0");
            quality="OPC_QUALITY_GOOD 0xC0";
        }
        if (str.equals("00")) {
            //System.out.println("OPC_QUALITY_BAD 0x00");
            quality="OPC_QUALITY_BAD 0x00";
        }
        if (str.equals("04")) {
            //System.out.println("OPC_QUALITY_CONFIG_ERROR 0x04");
            quality="OPC_QUALITY_CONFIG_ERROR 0x04";
        }
        if (str.equals("0C")) {
            //System.out.println("OPC_QUALITY_DEVICE_FAILURE 0x0C");
            quality="OPC_QUALITY_DEVICE_FAILURE 0x0C";
        }
        if (str.equals("40")) {
            //System.out.println("OPC_QUALITY_UNCERTAIN 0x40");
            quality="OPC_QUALITY_UNCERTAIN 0x40";
        }
        if (str.equals("50")) {
            //System.out.println("OPC_QUALITY_SENSOR_CAL 0x50");
            quality="OPC_QUALITY_SENSOR_CAL 0x50";
        }
        return quality;
    }


    public String l2b (String str) {
        String str_out = "";
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{2})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out = str_out +list.get(i);
            }
        return str_out;
    }





}
