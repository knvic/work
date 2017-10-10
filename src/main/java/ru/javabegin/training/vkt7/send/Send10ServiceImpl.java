package ru.javabegin.training.vkt7.send;

import ru.javabegin.training.vkt7.propert.Properties_xml;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import javax.xml.bind.JAXBException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 * Created by Николай on 20.08.2017.
 */
public class Send10ServiceImpl implements Send10Service{

    @Override
    public List<String> s_3FFF_n (String number){
        String command= "FF FF 10 3F FF 00 00 CC 80 00 00 00";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 10 3F FF 00 00 CC 80 00 00 00"
         */
        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
       // list.forEach(System.out::print);
        list.add(2,number);
        //list.forEach(System.out::print);
        return list;

    }

    @Override
    public ArrayList<Object> s_3FFF (String number){
        /**
         * Получаем список свойств
          */
        LinkedList<Properts> prop_common = new LinkedList<>();
        try {
            prop_common= Properties_xml.getProperties();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
       prop_common.forEach(System.out::print);

       String str3= "FF FF 10 3F FF 00 00 60 2C 00 00 40 07 00" +
                " 2D 00 00 40 07 00 2E 00 00 40 07 00" +
                " 2F 00 00 40 07 00 30 00 00 40 07 00" +
                " 35 00 00 40 07 00 37 00 00 40 07 00" +
                " 38 00 00 40 07 00 39 00 00 40 01 00" +
                " 3B 00 00 40 01 00 3C 00 00 40 01 00" +
                " 3D 00 00 40 01 00 42 00 00 40 01 00" +
                " 46 00 00 40 01 00 45 00 00 40 01 00" +
                " 4C 00 00 40 01 00";
        List<String> command = new LinkedList<String>(Arrays.asList(str3.split(" ")));

        //добавляем номер устройства к ПРИМЕРУ 01

        command.add(2,number);

           /*Получаем "FF FF 01 10 3F FF 00 00 60 2C 00 00 40 07 00" +
                        " 2D 00 00 40 07 00 2E 00 00 40 07 00" +
                        " 2F 00 00 40 07 00 30 00 00 40 07 00" +
                        " 35 00 00 40 07 00 37 00 00 40 07 00" +
                        " 38 00 00 40 07 00 39 00 00 40 01 00" +
                        " 3B 00 00 40 01 00 3C 00 00 40 01 00" +
                        " 3D 00 00 40 01 00 42 00 00 40 01 00" +
                        " 46 00 00 40 01 00 45 00 00 40 01 00" +
                        " 4C 00 00 40 01 00";*/

        /**
         * Формируем массив элементов, посылаемых в запросе
         * Формируем Integeкr масив из строкового массива элементов команды
         */
        List<Integer> data1 = new ArrayList<Integer>();
        List<Properts> prop_specification = new ArrayList<Properts>();
        System.out.println();
        IntStream.range(9, command.size())
                .forEach(p-> System.out.print(command.get(p)+" "));

        IntStream.range(9, command.size())
                .forEach(p->data1.add(Integer.parseInt(command.get(p) ,16)));

        System.out.println();

        data1.forEach(System.out::print);

/**specification completed
 * Находим соответствие в полсылаемой строке соответствующие свойства и добаляем в массив,
 * в котором будут cодержаться запрашиваемые в данном сеансе свойства.
 */

        for(int i=0;i<data1.size();i=i+6) {
            for(Properts item : prop_common) {
                if(data1.get(i)==item.getId()){
                    System.out.println(item.getId()+"::: "+item.getName()+"::: "+item.getText());
                    prop_specification.add(item);
                }
            }
        }
        //Vector<Object> list_out=new Vector<>();
        ArrayList<Object>list_out=new ArrayList<Object>();

        list_out.add(prop_specification);
        list_out.add(command);
        list_out.add(prop_common);

        command
                .stream()
                .forEach(p-> System.out.print((p)+" "));


        return list_out;

    }


    @Override
    public List<String> s_3FFF_end (int number, List<String> command){

        /**
         * * Добавляем FF FF и номер узла, и остальные параметры  "FF FF number 10 3F FF 00 00 "
         * Начальный адрес = 0x3FFB.
         * Количество регистров
         * размер данных
         */

        //List<String> list= new ArrayList<>();
        int command_size=command.size();
        System.out.println("Размер массива "+command_size);
        StringBuffer temporal  = new StringBuffer();
        temporal.append("FF FF"+format("%02X", number)+"10 3F FF 00 00"+format("%02X", command_size));
        List<String> command_temp = new ArrayList<>(Arrays.asList(temporal.toString().replace(" ","").split("(?<=\\G.{2})")));
        System.out.println("Выводим полученную строку ");
        command_temp.forEach(System.out::print);
       command.addAll(0,command_temp);

        return command;

    }


    @Override
    public List<String> s_3FFD(int number, int type){
        String command= "FF FF 10 3F FD 00 00 02 00 ";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 10 3F FD 00 00 02 00"
         */
        List<String> list = new ArrayList<String>(Arrays.asList(command.split(" ")));
        // list.forEach(System.out::print);
        list.add(2,format("%02X", number));
        list.add(9,format("%02X", type));
        list.forEach(p->System.out.print(p+" "));
        return list;
    }



    @Override
    public List<String> s_3FFB(int number, Date date){

        Instant date_time = date.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(date_time, ZoneId.systemDefault());
        System.out.println("value of Date: " + date_time);
        System.out.println("value of LocalDateTime: " + ldt);

      /*  System.out.println("Переводим Timestamp в localDateTime");
        LocalDateTime date = date_ts.toLocalDateTime();*/

      String date_str=ldt.format(DateTimeFormatter.ofPattern("dd:MM:uu:HH"));
        System.out.println("ddd = "+date_str);
        List<String> date_dd_MM_UU_HH = new ArrayList<String>(Arrays.asList(date_str.split(":")));


        String command= "FF FF 10 3F FB 00 00 04";
        /**
         * Преобразовываем строку в массив <String>
         * Добавляем номер узла тритьим параметром "FF FF number 10 3F FD 00 00 02 00"
         */
        List<String> list = new ArrayList<String>(Arrays.asList(command.split(" ")));
        // list.forEach(System.out::print);
        list.add(2,format("%02X", number));
        list.add(9,format("%02X", Integer.parseInt(date_dd_MM_UU_HH.get(0))));
        list.add(10,format("%02X", Integer.parseInt(date_dd_MM_UU_HH.get(1))));
        list.add(11,format("%02X", Integer.parseInt(date_dd_MM_UU_HH.get(2))));
        list.add(12,format("%02X", Integer.parseInt(date_dd_MM_UU_HH.get(3))));

        list.forEach(p->System.out.print(p+" "));
        return list;
    }



}
