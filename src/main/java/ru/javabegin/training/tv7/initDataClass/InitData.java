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

        list.add(new Parametr("t1_tv1","t1",4,"float"));
        list.add(new Parametr("P1_tv1","P1",4,"float"));
        list.add(new Parametr("V1_tv1","V1",4,"float"));
        list.add(new Parametr("M1_tv1","M1",4,"float"));

        list.add(new Parametr("t2_tv1","t2",4,"float"));
        list.add(new Parametr("P2_tv1","P2",4,"float"));
        list.add(new Parametr("V2_tv1","V2",4,"float"));
        list.add(new Parametr("M2_tv1","M2",4,"float"));

        list.add(new Parametr("t3_tv1","t3",4,"float"));
        list.add(new Parametr("P3_tv1","P3",4,"float"));
        list.add(new Parametr("V3_tv1","V3",4,"float"));
        list.add(new Parametr("M3_tv1","M3",4,"float"));

        list.add(new Parametr("t1_tv2","t1",4,"float"));
        list.add(new Parametr("P1_tv2","P1",4,"float"));
        list.add(new Parametr("V1_tv2","V1",4,"float"));
        list.add(new Parametr("M1_tv2","M1",4,"float"));

        list.add(new Parametr("t2_tv2","t2",4,"float"));
        list.add(new Parametr("P2_tv2","P2",4,"float"));
        list.add(new Parametr("V2_tv2","V2",4,"float"));
        list.add(new Parametr("M2_tv2","M2",4,"float"));

        list.add(new Parametr("t3_tv2","t3",4,"float"));
        list.add(new Parametr("P3_tv2","P3",4,"float"));
        list.add(new Parametr("V3_tv2","V3",4,"float"));
        list.add(new Parametr("M3_tv2","M3",4,"float"));


        list.add(new Parametr("tnv_tv1","tнв",4,"float"));
        list.add(new Parametr("tx_tv1","tx",4,"float"));
        list.add(new Parametr("Px_tv1","Px",4,"float"));
        list.add(new Parametr("dt_tv1","dt",4,"float"));
        list.add(new Parametr("dM_tv1","dM",4,"float"));
        list.add(new Parametr("Qtv_tv1","Qтв",4,"float"));
        list.add(new Parametr("Q12_tv1","Q12",4,"float"));
        list.add(new Parametr("Qg_tv1","Qг",4,"float"));
        list.add(new Parametr("vnr_tv1","ВНР",2,"unsigned short"));
        list.add(new Parametr("vos_tv1","ВОС",2,"unsigned short"));

        list.add(new Parametr("tnv_tv2","tнв",4,"float"));
        list.add(new Parametr("tx_tv2","tx",4,"float"));
        list.add(new Parametr("Px_tv2","Px",4,"float"));
        list.add(new Parametr("dt_tv2","dt",4,"float"));
        list.add(new Parametr("dM_tv2","dM",4,"float"));
        list.add(new Parametr("Qtv_tv2","Qтв",4,"float"));
        list.add(new Parametr("Q12_tv2","Q12",4,"float"));
        list.add(new Parametr("Qg_tv2","Qг",4,"float"));
        list.add(new Parametr("vnr_tv2","ВНР",2,"unsigned short"));
        list.add(new Parametr("vos_tv2","ВОС",2,"unsigned short"));


        list.add(new Parametr("dp","dp",4,"float"));
        list.add(new Parametr("ns1_tv1","НС1",1,"unsigned char"));
        list.add(new Parametr("ns2_tv1","НС2",1,"unsigned char"));
        list.add(new Parametr("ns3_tv1","НС3",1,"unsigned char"));

        list.add(new Parametr("ns1_tv2","НС1",1,"unsigned char"));
        list.add(new Parametr("ns2_tv2","НС2",1,"unsigned char"));
        list.add(new Parametr("ns3_tv2","НС3",1,"unsigned char"));

        list.add(new Parametr("NS_tv1","НС по ТВ1",2,"unsigned short"));
        list.add(new Parametr("NS_tv2","НС по ТВ2",2,"unsigned short"));
        list.add(new Parametr("NS_DP","НС по ДП",1,"unsigned char"));
        list.add(new Parametr("reserv1","Резерв",1,"unsigned char"));

        list.add(new Parametr("signs_of_events","Признаки событий",2,"unsigned short"));
        list.add(new Parametr("reserv2","Резерв",2,"unsigned short"));
        list.add(new Parametr("duration_of 220","Длит.работы по сети",2,"unsigned short"));
        list.add(new Parametr("duration_display","Длит.работы дисплея",2,"unsigned short"));
        list.add(new Parametr("duration_out","Длит.отсут.сет.питания",2,"unsigned short"));


        list.add(new Parametr("si_tv1","СИ ТВ1",1,"unsigned char"));
        list.add(new Parametr("activeBD","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frt_tv1","ФРТ ТВ1",1,"unsigned char"));
        list.add(new Parametr("kt3_tv1","КТ3 ТВ1",1,"unsigned char"));

        list.add(new Parametr("si_tv2","СИ ТВ2",1,"unsigned char"));
        list.add(new Parametr("activeBD","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frt_tv2","ФРТ ТВ2",1,"unsigned char"));
        list.add(new Parametr("kt3_tv2","КТ3 ТВ2",1,"unsigned char"));




return list;
    }

    public List<Parametr> initTotal(){
        List<Parametr> list= new ArrayList<>();


        list.add(new Parametr("MM","Месяц",1,"unsigned char"));
        list.add(new Parametr("dd","День",1,"unsigned char"));
        list.add(new Parametr("HH","Час",1,"unsigned char"));
        list.add(new Parametr("uu","Год",1,"unsigned char"));


        list.add(new Parametr("V1_tv1","V1",8,"double"));
        list.add(new Parametr("M1_tv1","M1",8,"double"));

        list.add(new Parametr("V2_tv1","V2",8,"double"));
        list.add(new Parametr("M2_tv1","M2",8,"double"));

        list.add(new Parametr("V3_tv1","V3",8,"double"));
        list.add(new Parametr("M3_tv1","M3",8,"double"));

        list.add(new Parametr("V1_tv2","V1",8,"double"));
        list.add(new Parametr("M1_tv2","M1",8,"double"));

        list.add(new Parametr("V2_tv2","V2",8,"double"));
        list.add(new Parametr("M2_tv2","M2",8,"double"));

        list.add(new Parametr("V3_tv2","V3",8,"double"));
        list.add(new Parametr("M3_tv2","M3",8,"double"));

        list.add(new Parametr("dM_tv1","dM",8,"double"));
        list.add(new Parametr("Qтв_tv1","Qтв",8,"double"));
        list.add(new Parametr("Q12_tv1","Q12",8,"double"));
        list.add(new Parametr("Qг_tv1","Qг",8,"double"));
        list.add(new Parametr("ВНР_tv1","ВНР",2,"unsigned short"));
        list.add(new Parametr("ВОС_tv1","ВОС",2,"unsigned short"));

        list.add(new Parametr("TVmin_tv1","TVmin",2,"unsigned short"));
        list.add(new Parametr("TVmax_tv1","TVmax",2,"unsigned short"));
        list.add(new Parametr("Tdt_tv1","Tdt",2,"unsigned short"));
        list.add(new Parametr("Tno220_tv1","Tбез.пит.",2,"unsigned short"));
        list.add(new Parametr("Tterr._tv1","Ttнеиспр.",2,"unsigned short"));


        list.add(new Parametr("dM_tv2","dM",8,"double"));
        list.add(new Parametr("Qтв_tv2","Qтв",8,"double"));
        list.add(new Parametr("Q12_tv2","Q12",8,"double"));
        list.add(new Parametr("Qг_tv2","Qг",8,"double"));
        list.add(new Parametr("ВНР_tv2","ВНР",2,"unsigned short"));
        list.add(new Parametr("ВОС_tv2","ВОС",2,"unsigned short"));

        list.add(new Parametr("TVmin_tv2","TVmin",2,"unsigned short"));
        list.add(new Parametr("TVmax_tv2","TVmax",2,"unsigned short"));
        list.add(new Parametr("Tdt_tv2","Tdt",2,"unsigned short"));
        list.add(new Parametr("Tno220_tv2","Tбез.пит.",2,"unsigned short"));
        list.add(new Parametr("Tterr._tv2","Ttнеиспр.",2,"unsigned short"));


        list.add(new Parametr("dp","dp",8,"double"));
        list.add(new Parametr("duration_of 220","Длит.работы по сети",4,"unsigned long"));
        list.add(new Parametr("duration_display","Длит.работы дисплея",4,"unsigned long"));
        list.add(new Parametr("duration_out","Длит.отсут.сет.питания",4,"unsigned long"));

        list.add(new Parametr("si_tv1","СИ ТВ1",1,"unsigned char"));
        list.add(new Parametr("activeBD","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frt_tv1","ФРТ ТВ1",1,"unsigned char"));
        list.add(new Parametr("kt3_tv1","КТ3 ТВ1",1,"unsigned char"));

        list.add(new Parametr("si_tv2","СИ ТВ2",1,"unsigned char"));
        list.add(new Parametr("activeBD","Активная БД",1,"unsigned char"));
        list.add(new Parametr("frt_tv2","ФРТ ТВ2",1,"unsigned char"));
        list.add(new Parametr("kt3_tv2","КТ3 ТВ2",1,"unsigned char"));










        return list;
    }

}
