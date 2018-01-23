package ru.javabegin.training.tv7.initDataClass;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public List<Parametr> initDay(){
        List<Parametr> list= new ArrayList<>();

        list.add(new Parametr("MM","Месяц",1,"unsigned char"));
        list.add(new Parametr("dd","День",1,"unsigned char"));
        list.add(new Parametr("HH","Час",1,"unsigned char"));
        list.add(new Parametr("uu","Год",1,"unsigned char"));

        list.add(new Parametr("t1Tv1","t1",4,"float"));
        list.add(new Parametr("p1Tv1","p1",4,"float"));
        list.add(new Parametr("v1Tv1","v1",4,"float"));
        list.add(new Parametr("m1Tv1","m1",4,"float"));

        list.add(new Parametr("t2Tv1","t2",4,"float"));
        list.add(new Parametr("p2Tv1","p2",4,"float"));
        list.add(new Parametr("v2Tv1","v2",4,"float"));
        list.add(new Parametr("m2Tv1","m2",4,"float"));

        list.add(new Parametr("t3Tv1","t3",4,"float"));
        list.add(new Parametr("p3Tv1","p3",4,"float"));
        list.add(new Parametr("v3Tv1","v3",4,"float"));
        list.add(new Parametr("m3Tv1","m3",4,"float"));

        list.add(new Parametr("t1Tv2","t1",4,"float"));
        list.add(new Parametr("p1Tv2","p1",4,"float"));
        list.add(new Parametr("v1Tv2","v1",4,"float"));
        list.add(new Parametr("m1Tv2","m1",4,"float"));

        list.add(new Parametr("t2Tv2","t2",4,"float"));
        list.add(new Parametr("p2Tv2","p2",4,"float"));
        list.add(new Parametr("v2Tv2","v2",4,"float"));
        list.add(new Parametr("m2Tv2","m2",4,"float"));

        list.add(new Parametr("t3Tv2","t3",4,"float"));
        list.add(new Parametr("p3Tv2","p3",4,"float"));
        list.add(new Parametr("v3Tv2","v3",4,"float"));
        list.add(new Parametr("m3Tv2","m3",4,"float"));


        list.add(new Parametr("tnvTv1","tнв",4,"float"));
        list.add(new Parametr("txTv1","tx",4,"float"));
        list.add(new Parametr("pxTv1","px",4,"float"));
        list.add(new Parametr("dtTv1","dt",4,"float"));
        list.add(new Parametr("dMTv1","dM",4,"float"));
        list.add(new Parametr("qtvTv1","Qтв",4,"float"));
        list.add(new Parametr("q12Tv1","Q12",4,"float"));
        list.add(new Parametr("qgTv1","Qг",4,"float"));
        list.add(new Parametr("vnrTv1","ВНР",2,"unsigned short"));
        list.add(new Parametr("vosTv1","ВОС",2,"unsigned short"));

        list.add(new Parametr("tnvTv2","tнв",4,"float"));
        list.add(new Parametr("txTv2","tx",4,"float"));
        list.add(new Parametr("pxTv2","px",4,"float"));
        list.add(new Parametr("dtTv2","dt",4,"float"));
        list.add(new Parametr("dMTv2","dM",4,"float"));
        list.add(new Parametr("qtvTv2","Qтв",4,"float"));
        list.add(new Parametr("q12Tv2","Q12",4,"float"));
        list.add(new Parametr("qgTv2","Qг",4,"float"));
        list.add(new Parametr("vnrTv2","ВНР",2,"unsigned short"));
        list.add(new Parametr("vosTv2","ВОС",2,"unsigned short"));


        list.add(new Parametr("dp","dp",4,"float"));
        list.add(new Parametr("ns1Tv1","НС1",1,"unsigned char"));
        list.add(new Parametr("ns2Tv1","НС2",1,"unsigned char"));
        list.add(new Parametr("ns3Tv1","НС3",1,"unsigned char"));

        list.add(new Parametr("ns1Tv2","НС1",1,"unsigned char"));
        list.add(new Parametr("ns2Tv2","НС2",1,"unsigned char"));
        list.add(new Parametr("ns3Tv2","НС3",1,"unsigned char"));

        list.add(new Parametr("nsTv1","НС по ТВ1",2,"unsigned short"));
        list.add(new Parametr("nsTv2","НС по ТВ2",2,"unsigned short"));
        list.add(new Parametr("nsDp","НС по ДП",1,"unsigned char"));
        list.add(new Parametr("reserv1","Резерв",1,"unsigned char"));

        list.add(new Parametr("signsOfEvents","Признаки событий",2,"unsigned short"));
        list.add(new Parametr("reserv2","Резерв",2,"unsigned short"));
        list.add(new Parametr("durationOf220","Длит.работы по сети",2,"unsigned short"));
        list.add(new Parametr("durationDisplay","Длит.работы дисплея",2,"unsigned short"));
        list.add(new Parametr("durationOut","Длит.отсут.сет.питания",2,"unsigned short"));


        list.add(new Parametr("siTv1","СИ ТВ1",1,"unsigned char"));
        list.add(new Parametr("activeBdTv1","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frtTv1","ФРТ ТВ1",1,"unsigned char"));
        list.add(new Parametr("kt3Tv1","КТ3 ТВ1",1,"unsigned char"));

        list.add(new Parametr("siTv2","СИ ТВ2",1,"unsigned char"));
        list.add(new Parametr("activeBdTv2","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frtTv2","ФРТ ТВ2",1,"unsigned char"));
        list.add(new Parametr("kt3Tv2","КТ3 ТВ2",1,"unsigned char"));




return list;
    }

    public List<Parametr> initTotal(){
        List<Parametr> list= new ArrayList<>();


        list.add(new Parametr("MM","Месяц",1,"unsigned char"));
        list.add(new Parametr("dd","День",1,"unsigned char"));
        list.add(new Parametr("HH","Час",1,"unsigned char"));
        list.add(new Parametr("uu","Год",1,"unsigned char"));


        list.add(new Parametr("v1Tv1","v1",8,"double"));
        list.add(new Parametr("m1Tv1","m1",8,"double"));

        list.add(new Parametr("v2Tv1","v2",8,"double"));
        list.add(new Parametr("m2Tv1","m2",8,"double"));

        list.add(new Parametr("v3Tv1","v3",8,"double"));
        list.add(new Parametr("m3Tv1","m3",8,"double"));

        list.add(new Parametr("v1Tv2","v1",8,"double"));
        list.add(new Parametr("m1Tv2","m1",8,"double"));

        list.add(new Parametr("v2Tv2","v2",8,"double"));
        list.add(new Parametr("m2Tv2","m2",8,"double"));

        list.add(new Parametr("v3Tv2","v3",8,"double"));
        list.add(new Parametr("m3Tv2","m3",8,"double"));

        list.add(new Parametr("dMTv1","dM",8,"double"));
        list.add(new Parametr("qtvTv1","Qтв",8,"double"));
        list.add(new Parametr("q12Tv1","Q12",8,"double"));
        list.add(new Parametr("qgTv1","Qг",8,"double"));
        list.add(new Parametr("vnrTv1","ВНР",2,"unsigned short"));
        list.add(new Parametr("vosTv1","ВОС",2,"unsigned short"));

        list.add(new Parametr("tvminTv1","Tvmin",2,"unsigned short"));
        list.add(new Parametr("tvmaxTv1","Tvmax",2,"unsigned short"));
        list.add(new Parametr("tdtTv1","Tdt",2,"unsigned short"));
        list.add(new Parametr("tno220Tv1","Tбез.пит.",2,"unsigned short"));
        list.add(new Parametr("tterr.Tv1","Ttнеиспр.",2,"unsigned short"));


        list.add(new Parametr("dMTv2","dM",8,"double"));
        list.add(new Parametr("qtvTv2","Qтв",8,"double"));
        list.add(new Parametr("q12Tv2","Q12",8,"double"));
        list.add(new Parametr("qgTv2","Qг",8,"double"));
        list.add(new Parametr("vnrTv2","ВНР",2,"unsigned short"));
        list.add(new Parametr("vosTv2","ВОС",2,"unsigned short"));

        list.add(new Parametr("tvminTv2","Tvmin",2,"unsigned short"));
        list.add(new Parametr("tvmaxTv2","Tvmax",2,"unsigned short"));
        list.add(new Parametr("tdtTv2","Tdt",2,"unsigned short"));
        list.add(new Parametr("tno220Tv2","Tбез.пит.",2,"unsigned short"));
        list.add(new Parametr("tterr.Tv2","Ttнеиспр.",2,"unsigned short"));


        list.add(new Parametr("dp","dp",8,"double"));
        list.add(new Parametr("durationOf220","Длит.работы по сети",4,"unsigned long"));
        list.add(new Parametr("durationDisplay","Длит.работы дисплея",4,"unsigned long"));
        list.add(new Parametr("durationOut","Длит.отсут.сет.питания",4,"unsigned long"));

        list.add(new Parametr("siTv1","СИ ТВ1",1,"unsigned char"));
        list.add(new Parametr("activeBdTv1","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frtTv1","ФРТ ТВ1",1,"unsigned char"));
        list.add(new Parametr("kt3Tv1","КТ3 ТВ1",1,"unsigned char"));

        list.add(new Parametr("siTv2","СИ ТВ2",1,"unsigned char"));
        list.add(new Parametr("activeBdTv2","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frtTv2","ФРТ ТВ2",1,"unsigned char"));
        list.add(new Parametr("kt3Tv2","КТ3 ТВ2",1,"unsigned char"));










        return list;
    }

}
