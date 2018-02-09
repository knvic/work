package ru.javabegin.training.tv7.save;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaveServiceImpl implements SaveService {



    @Override
    public Operationtv7 saveDay(List<Parametr> parametrList){


        //LocalDateTime ldt = LocalDateTime.of(2018,1,9,23,0,0);
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(find(parametrList,"uu"))+2000,Integer.parseInt(find(parametrList,"MM")),Integer.parseInt(find(parametrList,"dd")),Integer.parseInt(find(parametrList,"HH")),0,0);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(ldt);
        System.out.println("время Timestamp = "+ts);


        Operationtv7 operation = new Operationtv7("", "day",  " " , ts,
                12l,
                find(parametrList,"t1Tv1"),
                find(parametrList,"p1Tv1"),
                find(parametrList,"v1Tv1"),
                find(parametrList,"m1Tv1"),
                find(parametrList,"t2Tv1"),
                find(parametrList,"p2Tv1"),
                find(parametrList,"v2Tv1"),
                find(parametrList,"m2Tv1"),
                find(parametrList,"t3Tv1"),
                find(parametrList,"p3Tv1"),
                find(parametrList,"v3Tv1"),
                find(parametrList,"m3Tv1"),
                find(parametrList,"t1Tv2"),
                find(parametrList,"p1Tv2"),
                find(parametrList,"v1Tv2"),
                find(parametrList,"m1Tv2"),
                find(parametrList,"t2Tv2"),
                find(parametrList,"p2Tv2"),
                find(parametrList,"v2Tv2"),
                find(parametrList,"m2Tv2"),
                find(parametrList,"t3Tv2"),
                find(parametrList,"p3Tv2"),
                find(parametrList,"v3Tv2"),
                find(parametrList,"m3Tv2"),
                find(parametrList,"tnvTv1"),
                find(parametrList,"txTv1"),
                find(parametrList,"pxTv1"),
                find(parametrList,"dtTv1"),
                find(parametrList,"dMTv1"),
                find(parametrList,"qtvTv1"),
                find(parametrList,"q12Tv1"),
                find(parametrList,"qgTv1"),
                find(parametrList,"vnrTv1"),
                find(parametrList,"vosTv1"),
                find(parametrList,"tnvTv2"),
                find(parametrList,"txTv2"),
                find(parametrList,"pxTv2"),
                find(parametrList,"dtTv2"),
                find(parametrList,"dMTv2"),
                find(parametrList,"qtvTv2"),
                find(parametrList,"q12Tv2"),
                find(parametrList,"qgTv2"),
                find(parametrList,"vnrTv2"),
                find(parametrList,"vosTv2"),
                find(parametrList,"dp"),
                find(parametrList,"ns1Tv1"),
                find(parametrList,"ns2Tv1"),
                find(parametrList,"ns3Tv1"),
                find(parametrList,"ns1Tv2"),
                find(parametrList,"ns2Tv2"),
                find(parametrList,"ns3Tv2"),
                find(parametrList,"nsTv1"),
                find(parametrList,"nsTv2"),
                find(parametrList,"nsDp"),
                find(parametrList,"signsOfEvents"),
                find(parametrList,"durationOf220"),
                find(parametrList,"durationDisplay"),
                find(parametrList,"durationOut"),
                find(parametrList,"siTv1"),
                find(parametrList,"activeBdTv1"),
                find(parametrList,"frtTv1"),
                find(parametrList,"kt3Tv1"),
                find(parametrList,"siTv2"),
                find(parametrList,"activeBdTv2"),
                find(parametrList,"frtTv2"),
                find(parametrList,"kt3Tv2")  );

return operation;
    }

    @Override
    public Operationtv7T saveTotal(List<Parametr> totalList){


        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(find(totalList,"uu"))+2000,Integer.parseInt(find(totalList,"MM")),Integer.parseInt(find(totalList,"dd")),Integer.parseInt(find(totalList,"HH")),0,0);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(ldt);
        System.out.println("врема Timestamp = "+ts);


        Operationtv7T operationtv7T=new Operationtv7T(
                "customerName",
                "те",
                ts,
                200l,
                find(totalList,"v1Tv1"),
                find(totalList,"m1Tv1"),
                find(totalList,"v2Tv1"),
                find(totalList,"m2Tv1"),
                find(totalList,"v3Tv1"),
                find(totalList,"m3Tv1"),
                find(totalList,"v1Tv2"),
                find(totalList,"m1Tv2"),
                find(totalList,"v2Tv2"),
                find(totalList,"m2Tv2"),
                find(totalList,"v3Tv2"),
                find(totalList,"m3Tv2"),
                find(totalList,"dMTv1"),
                find(totalList,"qtvTv1"),
                find(totalList,"q12Tv1"),
                find(totalList,"qgTv1"),
                find(totalList,"vnrTv1"),
                find(totalList,"vosTv1"),
                find(totalList,"tvminTv1"),
                find(totalList,"tvmaxTv1"),
                find(totalList,"tdtTv1"),
                find(totalList,"tno220Tv1"),
                find(totalList,"tterrTv1"),
                find(totalList,"dMTv2"),
                find(totalList,"qtvTv2"),
                find(totalList,"q12Tv2"),
                find(totalList,"qgTv2"),
                find(totalList,"vnrTv2"),
                find(totalList,"vosTv2"),
                find(totalList,"tvminTv2"),
                find(totalList,"tvmaxTv2"),
                find(totalList,"tdtTv2"),
                find(totalList,"tno220Tv2"),
                find(totalList,"tterrTv2"),
                find(totalList,"dp"),
                find(totalList,"durationOf220"),
                find(totalList,"durationDisplay"),
                find(totalList,"durationOut"),
                find(totalList,"siTv1"),
                find(totalList,"activeBdTv1"),
                find(totalList,"frtTv1"),
                find(totalList,"kt3Tv1"),
                find(totalList,"siTv2"),
                find(totalList,"activeBdTv2"),
                find(totalList,"frtTv2"),
                find(totalList,"kt3Tv2"));

        return operationtv7T;

    }

    @Override
    public String find(List<Parametr> parametrList, String parametr){
       // System.out.println(parametr+" ");
        String val= parametrList
                .parallelStream()
                .filter(p -> p.getName().contains(parametr))
                .collect(Collectors.toList())
                .get(0).getValue();

        System.out.println(parametr+" "+val);
        return val;
    }

}
