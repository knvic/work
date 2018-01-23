package ru.javabegin.training.tv7.recieve;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.initDataClass.InitData;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ModBusRServiceImplTest {
    @Test
    public void infOfDate() throws Exception {

        String str1="3A30313033333630423136304131313030303030373139313731313030303030433146313731303030303030373139313731313030303030313135303931323030303030313134313731323030303030433146313731313030303030313134313731323030303030433143304631303332333946360D0A";
        String hex="01 03 36 0B 16 0A 11 00 00 07 19 17 11 00 00 0C 1F 17 10 00 00 07 19 17 11 00 00 01 15 09 12 00 00 01 14 17 12 00 00 0C 1F 17 11 00 00 01 14 17 12 00 00 0C 1C 0F 10 32 39 F6";

        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(str1);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
        inHex.forEach(p->System.out.print(p+" "));

        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        modBusRService.infOfDate(inHex);

    }

    @Test
    public void test_double() throws Exception {
        String str1= "55C047AA593B40E1";
        String str2= "BFA0FD0BEB4440E0";
        // float m_  =Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue());
        //double m_ = Double.longBitsToDouble(Integer.valueOf(l2b(str1), 16).intValue());
        double m_ = Double.longBitsToDouble(Long.valueOf(l2b(str1), 16).intValue());

        System.out.println("парамметр : "+m_);


        long longHex = parseUnsignedHex(l2b(str1));
        double d = Double.longBitsToDouble(longHex);
        System.out.println(d);

        double e = Double.longBitsToDouble(Long.parseLong(l2b(str1), 16));
        System.out.println("e= "+e);





    }

    public static long parseUnsignedHex(String text) {
        if (text.length() == 16) {
            return (parseUnsignedHex(text.substring(0, 1)) << 60)
                    | parseUnsignedHex(text.substring(1));
        }
        return Long.parseLong(text, 16);
    }

    @Test
    public void total() throws Exception {
        String in_total="3A30 31 34 38 30 30 45 38" +
                "  30 30 32 38 30 31 30 43 31 37 31 32" +
                "  35 35 43 30" +
                "  34 37 41 41 35 39 33 42 34 30 45 31 42 46 41 30 " +
                " 46 44 30 42 45 42 34 34  " +
                " 34 30 45 30 43 31 36 30 41 33 45 37 46 33 33 33 " +
                " 34 30 45 30 37 37 35 30 33 37 30 45 41 45 30 46" +
                "  34 30 45 30 30 30 30 30 30 30 30 30 30 30 30 30" +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30" +
                " 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                " 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30" +
                "  30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 32 38 30 30 46 45 36 34 39 41 45 32 " +
                " 34 30 37 45 32 39 30 30 46 45 34 41 34 37 32 31 " +
                " 34 30 41 33 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                " 30 30 30 30 30 30 30 30 30 30 30 30 32 33 33 39  " +
                "  30 30 37 30 30 30 30 30 30 30 30 30 30 30 43 32 " +
                "  30 30 32 37 30 30 30 30 30 30 30 30 30 30 30 30  " +
                " 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30  " +
                "  30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30  " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30  " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30  " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30  " +
                " 30 30 30 30 30 30 30 34  " +
                "  30 30 30 30 35 32 43 38 30 30 30 35 30 36 30 30  " +
                " 30 31 30 30 30 30 30 30 30 42 30 34 30 32 38 34  " +
                "  39 35 32 31 " +
                "  30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 " +
                "  37 43 0D 0A  ";

        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(in_total);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
        inHex.forEach(p->System.out.print(p+" "));

        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        InitData initData = new InitData();
        List<Parametr> totalList =initData.initTotal();

        modBusRService.total(inHex,  totalList,1);


    }


    @Test
    public void day() throws Exception {

        String in="3A " +
                "30 31 34 38 30 30 44 41 30 30 32 37 30 31 30 43 31 37 31 32 46 42 32 32 34 32 43 30 46 31 43 31 33 46 36 31 41 41 33 45 34 32 43 46 " +
                "39 42 41 38 34 32 43 37 30 41 38 34 34 32 34 35 36 39 42 34 33 46 35 41 34 30 30 30 34 32 43 38 30 36 45 44 34 32 43 36 30 30 30 30 " +
                "37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 " +
                "30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 " +
                "37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 " +
                "30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 45 42 43 30 34 32 33 43 35 44 32 30 33 46 34 41 37 30 32 41 34 31 39 46 30 30 30 30 " +
                "37 46 46 30 30 30 30 30 37 46 46 30 30 30 31 38 30 30 30 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 " +
                "30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 37 46 46 30 30 30 30 30 " +
                "30 30 30 30 30 30 30 30 37 46 46 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 32 38 34 30 30 30 30 " +
                "30 30 30 30 30 30 30 30 30 30 30 30 30 36 30 30 30 31 30 30 30 30 30 30 30 42 30 34 30 30 30 30 39 35 32 31 30 30 30 30 30 30 30 30 " +
                "30 30 30 30 30 30 30 30 41 35 " +
                "0D 0A";

        String in_09_01_17="3A30 31 34 38 30 30 44 4130 30 30 32 30 31 30 3931 37 31 32 33 41 44 4534 32 41 33 46 31 43 3133 46 36 3145 39 39 41 34 32 42 3839 30 34 39 34 32 42 3342 38 32 38 34 32 32 4136 39 42 34 33 46 35 4132 42 38 35 34 32 42 3342 32 39 43 34 32 42 3130 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3042 44 39 34 34 32 31 4244 36 31 43 33 46 36 4546 36 31 36 34 31 36 4330 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 31 37 30 30 30 3130 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 37 46 46 3030 30 30 30 30 30 30 3030 30 30 30 37 46 46 3038 30 38 30 30 30 30 3030 30 30 30 30 30 30 3030 30 30 30 30 30 30 3030 32 38 36 30 30 30 3030 30 30 30 30 30 30 3030 30 30 41 30 36 30 3030 31 30 30 30 30 30 3030 42 30 34 30 30 30 3039 35 32 31 30 30 30 3030 30 30 30 30 30 30 3030 30 30 30 41 38 0D 0A";

        String recive = "014800DA0002010917123ADE42A3F1C13F61E99A42B8904942B3B828422A69B43F5A2B8542B3B29C42B100007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF0BD94421BD61C3F6EF616416C00007FF000007FF00017000100007FF000007FF000007FF000007FF000007FF000007FF000007FF000007FF000000000" +
                "00007FF0" +
                "80 80 00 00 00 00 0000 0000" +
                "00 00 0286 0000 0000 0000 000A 06 00 01 00 00 00 0B 04 000095210000000000000000A8";

        AscServiceImpl ascService= new AscServiceImpl();
        LrcServiceImpl lrcService= new LrcServiceImpl();
        List<String> inHex=ascService.dectypt(in_09_01_17);
        System.out.println("LRC : "+lrcService.lrcCheck(inHex));
        inHex.forEach(p->System.out.print(p+" "));
        String inH="01 48 00 DA 00 27 01 0C 17 12 FB 22 42 C0 F1 C1 3F 61 AA 3E 42 CF 9B A8 42 C7 0A 84 42 45 69 B4 3F 5A 40 00 42 C8 06 ED 42 C6 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 EB C0 42 3C 5D 20 3F 4A 70 2A 41 9F 00 00 7F F0 00 00 7F F0 00 18 00 00 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 7F F0 00 00 00 00 00 00 7F F0 00 00 00 00 00 00 00 00 00 00 00 00 02 84 00 00 00 00 00 00 00 00 06 00 01 00 00 00 0B 04 00 00 95 21 00 00 00 00 00 00 00 00 A5";

        ModBusRServiceImpl modBusRService= new ModBusRServiceImpl();
        InitData initData = new InitData();
        List<Parametr> parametrList =initData.initDay();

 parametrList= modBusRService.day(inHex, parametrList,1);

        System.out.println("Из теста ");

        parametrList.forEach(p->System.out.println(p.getName()+"   "+p.getValue()));

        System.out.println("Фильтрация ");
        //List<Parametr> filtered =

        String oo= parametrList
                        .stream()
                        .filter(p -> p.getName().contains("nsDp"))
                        .collect(Collectors.toList())
                .get(0).getValue();

        System.out.println(oo);


/*

        Operationtv7 operation = new Operationtv7(find(parametrList,"t1Tv1"),
                find(parametrList,"p1Tv1"), find(parametrList,"v1Tv1"), find(parametrList,"m1Tv1"), find(parametrList,"t2Tv1"));

        System.out.println(operation);
*/


//год, месяц, день, час, минуту, сукенду
        //  LocalDateTime ldt = LocalDateTime.of(p4+2000, p1, p2, p3, p6, p5);
        // LocalDateTime ldt = LocalDateTime.of(parametrList.g+2000, p1, p2, p3, p6, p5);
        LocalDateTime ldt = LocalDateTime.of(2018,1,9,23,0,0);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(ldt);
        System.out.println("врема Timestamp = "+ts);


        Operationtv7 operation = new Operationtv7("dfgdfg", "day",  " " , ts,
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


        System.out.println(operation);




        //  parametrList.stream().filter("t1_tv1"::equals).findFirst().get();



    }





    @Test
    public void temp_match() throws Exception {
        //String regularExpression1="^3A[A-Z\\d]*0D0A$";

        List<String> id_col = new ArrayList<>(Arrays.asList("t1_tv1","P1_tv1","V1_tv1","M1_tv1","t2_tv1","P2_tv1","V2_tv1","M2_tv1","t3_tv1","P3_tv1","V3_tv1","M3_tv1","t1_tv2","P1_tv2","V1_tv2",
                "M1_tv2","t2_tv2","P2_tv2","V2_tv2","M2_tv2","t3_tv2","P3_tv2","V3_tv2","M3_tv2",
                "tнв_tv1","tx_tv1","Px_tv1","dt_tv1","dM_tv1","Qтв_tv1","Q12_tv1","Qг_tv1","ВНР_tv1","ВОС_tv1","tнв_tv2","tx_tv2","Px_tv2","dt_tv2","dM_tv2","Qтв_tv2","Q12_tv2","Qг_tv2","ВНР_tv2","ВОС_tv2"));


        //String regularExpression1="(^P\\d_*) | (^M\\d_*)";
        String regularExpression1="^(M\\d_|P\\d_|dM)";

        Pattern pattern = Pattern.compile(regularExpression1);
        for(String str:id_col) {
            Matcher match = pattern.matcher(str);


            if (match.find()) {
                System.out.println("Нaйдено соответствие : "+ str);
            }
        }

    }



                ////// Вспомогательный метод /////////////
    public String l2b (String str) {
        StringBuilder str_out = new StringBuilder();
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{4})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out.append(list.get(i));
        }
        return str_out.toString();
    }

    public String find(List<Parametr> parametrList, String parametr){
        System.out.println(parametr+" ");
        String val= parametrList
                .parallelStream()
                .filter(p -> p.getName().contains(parametr))
                .collect(Collectors.toList())
                .get(0).getValue();

        System.out.println(parametr+" "+val);
        return val;
    }


}