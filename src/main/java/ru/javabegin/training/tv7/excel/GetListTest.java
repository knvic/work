package ru.javabegin.training.tv7.excel;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.initDataClass.InitData;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.tv7.recieve.ModBusRServiceImpl;
import ru.javabegin.training.tv7.save.SaveServiceImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetListTest {

    @Test
    public void list_1_2_3_tv1() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {



/// начало Подготовка объекта OperationTv7T
        String in_total=  "3A3031343830304536303031453032304331373132443543303531454345333446343045323346413033463136363838353430453234313630414532373730383934304532423735303132383332373746343045323030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030313430303234383234313842343038303239303039453634344230323430413730303030303030303030303030303030303030303030303030303030303030303236314530303733303030303030303030304332303032413030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303030303430303030353332383030303530363030303130303030303030423034303238343935323130303030303030303030303030300D0A";
        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(in_total);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
//        inHex.forEach(p->System.out.print(p+" "));
        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        InitData initData = new InitData();
        List<Parametr> totalList =initData.initTotal();

        totalList= modBusRService.total(inHex,  totalList,1);

        SaveServiceImpl saveService=new SaveServiceImpl();
        Operationtv7T operationtv7T=saveService.saveTotal(totalList);


        List<Operationtv7T> operationtv7TList=new ArrayList<>();
        operationtv7TList.add(operationtv7T);
/// конец Подготовка объекта OperationTv7T
        System.out.println();
        System.out.println();

        GetList getList=new GetList();
        List<DataObjectTv7> objectTv7List =getList.list_1_2_3_tv1(operationtv7TList);


        for (DataObjectTv7 objectTv7:objectTv7List) {
            objectTv7.getId_coils().forEach(p->System.out.print(p+" "));
            System.out.println();
            System.out.println("Результат");
            for (String id:objectTv7.getId_coils()){
                System.out.println(id+" = "+ objectTv7.getOptionalValues().get(id).getValue());
            }

        }



        System.out.println();
        System.out.println("Результат");
       /* for (String key:mapTv7.keySet()) {
            System.out.println("Key ="+key);

        }*/
       //l.forEach(p->System.out.print(p+" "));












    }
}