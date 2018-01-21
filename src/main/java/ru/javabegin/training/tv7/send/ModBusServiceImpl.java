package ru.javabegin.training.tv7.send;

import ru.javabegin.training.tv7.auxillary.AuxServiceImpl;

import java.time.LocalDateTime;
import java.util.*;

import static java.lang.String.format;

public class ModBusServiceImpl implements ModBusService {

    @Override
    public List<String> typeUnit(int number){
        //String command ="48 00 00 00 06 00 00 00 00 00 00 00 01";
        String command ="48 00 00 00 13 00 00 00 00 00 00 00 00";


        List<String> list = new LinkedList<String>(Arrays.asList(command.split(" ")));
        //list.forEach(System.out::print);
        list.add(0,format("%02X", number));
        // list.forEach(System.out::print);

       String s1="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 35 0D 0A";
       String s2="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 0D 0A";
       String s3="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 35 0D 0A";
       String s4="3A 30 30 34 38 30 30 36 39 30 30 39 32 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 32 42 42 0D 0A";
       // list = new LinkedList<String>(Arrays.asList(s4.split(" ")));
        return list;
    }

    @Override
    public List<String> day(int number, int addrR,int countR,int addrW,int countW, int countByte, int requestCount, List<String> data ){

        StringBuilder command_temp=new  StringBuilder();


        command_temp.delete(0, command_temp.length());
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("48");
        //добавляем адрес начала чтения
        command_temp.append(format("%04X",  addrR));
        command_temp.append(format("%04X", countR ));
        command_temp.append(format("%04X", addrW ));
        command_temp.append(format("%04X", countW ));
        command_temp.append(format("%04X", countByte ));
        command_temp.append(format("%04X", requestCount ));



        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        if (data == null) {
            throw new NullPointerException("listA is null");
        }else
            list.addAll(data);




        return list;
    }

    @Override
    public List<String> archive(int number, Date data, int type, int commandCount){
        AuxServiceImpl auxService=new AuxServiceImpl();

        StringBuilder command_temp=new  StringBuilder();

        command_temp.delete(0, command_temp.length());
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("48");
        //добавляем адрес начала чтения
        command_temp.append(format("%04X",  2740)); // адрес чтения
        command_temp.append(format("%04X", 109 )); //количество регистров чтения
        command_temp.append(format("%04X", 99 )); //адрес записи
        command_temp.append(format("%04X", 6 ));  //кол-во регистров записи
        command_temp.append(format("%04X", 12 )); //количество байт записи
        command_temp.append(format("%04X", commandCount )); // порядковый номер посылаемой команды (свободно назначаемый)
        command_temp.append(auxService.Date_to_tv7(data)); // дата суточного архива преобразовання для применения в ТВ7
        command_temp.append(format("%04X", 1 )); // Тип запрашиваемого архива (1 - суточный архив)
        command_temp.append(format("%04X", 0 )); // номер записи (не используется)
        command_temp.append(format("%04X", 0 )); // Резерв 1 (не используется)

        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }

    @Override
    public List<String> archive(int number,LocalDateTime data,int type, int commandCount){
        AuxServiceImpl auxService=new AuxServiceImpl();

        StringBuilder command_temp=new  StringBuilder();

        command_temp.delete(0, command_temp.length());
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("48");
        //добавляем адрес начала чтения
        command_temp.append(format("%04X",  2740)); // адрес чтения
        command_temp.append(format("%04X", 109 )); //количество регистров чтения
        command_temp.append(format("%04X", 99 )); //адрес записи
        command_temp.append(format("%04X", 6 ));  //кол-во регистров записи
        command_temp.append(format("%04X", 12 )); //количество байт записи
        command_temp.append(format("%04X", commandCount )); // порядковый номер посылаемой команды (свободно назначаемый)
        command_temp.append(auxService.localDate_to_tv7(data)); // дата суточного архива преобразовання для применения в ТВ7
        command_temp.append(format("%04X", 1 )); // Тип запрашиваемого архива (1 - суточный архив)
        command_temp.append(format("%04X", 0 )); // номер записи (не используется)
        command_temp.append(format("%04X", 0 )); // Резерв 1 (не используется)

        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }


    @Override
    public List<String> total(int number,Date data, int commandCount){
        AuxServiceImpl auxService=new AuxServiceImpl();

        StringBuilder command_temp=new  StringBuilder();

        command_temp.delete(0, command_temp.length()); // очищаем буффер на всякий случай перед начало действий
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("48");
        //добавляем адрес начала чтения
        command_temp.append(format("%04X",  2868)); // адрес чтения
        command_temp.append(format("%04X", 109 )); //количество регистров чтения
        command_temp.append(format("%04X", 99 )); //адрес записи
        command_temp.append(format("%04X", 6 ));  //кол-во регистров записи
        command_temp.append(format("%04X", 12 )); //количество байт записи
        command_temp.append(format("%04X", commandCount )); // порядковый номер посылаемой команды (свободно назначаемый)
        command_temp.append(auxService.Date_to_tv7(data)); // дата суточного архива преобразовання для применения в ТВ7
        command_temp.append(format("%04X", 3 )); // Тип запрашиваемого архива (3 - итоговый)
        command_temp.append(format("%04X", 0 )); // номер записи (не используется)
        command_temp.append(format("%04X", 0 )); // Резерв 1 (не используется)

        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }

    @Override
    public List<String> total(int number,LocalDateTime data, int commandCount){
        AuxServiceImpl auxService=new AuxServiceImpl();

        StringBuilder command_temp=new  StringBuilder();

        command_temp.delete(0, command_temp.length()); // очищаем буффер на всякий случай перед начало действий
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("48");
        //добавляем адрес начала чтения
        command_temp.append(format("%04X",  2868)); // адрес чтения
        command_temp.append(format("%04X", 109 )); //количество регистров чтения
        command_temp.append(format("%04X", 99 )); //адрес записи
        command_temp.append(format("%04X", 6 ));  //кол-во регистров записи
        command_temp.append(format("%04X", 12 )); //количество байт записи
        command_temp.append(format("%04X", commandCount )); // порядковый номер посылаемой команды (свободно назначаемый)
        command_temp.append(auxService.localDate_to_tv7(data)); // дата суточного архива преобразовання для применения в ТВ7
        command_temp.append(format("%04X", 3 )); // Тип запрашиваемого архива (3 - итоговый)
        command_temp.append(format("%04X", 0 )); // номер записи (не используется)
        command_temp.append(format("%04X", 0 )); // Резерв 1 (не используется)

        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }

    @Override
    public List<String> infOfDate(int number, int commandCount){

        AuxServiceImpl auxService=new AuxServiceImpl();

        StringBuilder command_temp=new  StringBuilder();

        command_temp.delete(0, command_temp.length()); // очищаем буффер на всякий случай перед начало действий
        //добавляем адрес
        command_temp.append(format("%02X", number));
        //добавляем функцию 0х48 - чтение и запись в одну команду
        command_temp.append("03");
        command_temp.append(format("%04X",  2676)); // адрес чтения
        command_temp.append(format("%04X", 27 )); //количество регистров чтения
        List<String> list = new ArrayList<>(Arrays.asList( command_temp.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }



}
