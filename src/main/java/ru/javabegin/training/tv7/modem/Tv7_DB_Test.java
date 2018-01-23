package ru.javabegin.training.tv7.modem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.initDataClass.InitData;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.tv7.recieve.ModBusRServiceImpl;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;
import ru.javabegin.training.vkt7.db.CustomerService;
import ru.javabegin.training.vkt7.entities.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Tv7_DB_Test {
    @Qualifier("jpaCustomerService")
    @Autowired
    CustomerService customerService;
    public void save_tv7(CustomerService customerService){

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

       parametrList=modBusRService.day(inHex, parametrList,1);
        System.out.println("Вывод в тестовой программе -->");
        for(Parametr p:parametrList){

            System.out.print("\n----> "+p.getNameString()+" = " + p.getValue());
        }


///запись



        //Operationtv7 operation = new Operationtv7();
        //operation.setTypeOperation("day");
        Customer customer=null;
        try {
     customer = customerService.findByIdTv7(178l);

}catch (Exception e){

    System.out.println(e);
}
if (customer!=null){System.out.println("Кoнтакт найден, customer "+ customer.getFirstName()+" id= "+customer.getId());}

/*
        Customer customer=new Customer();
        customer.setFirstName("tv7_1");
        customer.setTelModem("76568");*/

        //Создаем LocalDateTime передавая в качестве аргументов
        //год, месяц, день, час, минуту, сукенду
      //  LocalDateTime ldt = LocalDateTime.of(p4+2000, p1, p2, p3, p6, p5);
       // LocalDateTime ldt = LocalDateTime.of(parametrList.g+2000, p1, p2, p3, p6, p5);
        LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(find(parametrList,"uu"))+2000,Integer.parseInt(find(parametrList,"MM")),Integer.parseInt(find(parametrList,"dd")),Integer.parseInt(find(parametrList,"HH")),0,0);
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(ldt);
        System.out.println("врема Timestamp = "+ts);
      //  Operationtv7 operation = new Operationtv7(customer.getFirstName(), "day", " ", Timestamp chronoligical, Integer idCustomer, String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1, String p2Tv1, String v2Tv1, String m2Tv1, String t3Tv1, String p3Tv1, String v3Tv1, String m3Tv1, String t1Tv2, String p1Tv2, String v1Tv2, String m1Tv2, String t2Tv2, String p2Tv2, String v2Tv2, String m2Tv2, String t3Tv2, String p3Tv2, String v3Tv2, String m3Tv2, String tnvTv1, String txTv1, String pxTv1, String dtTv1, String dMTv1, String qtvTv1, String q12Tv1, String qgTv1, String vnrTv1, String vosTv1, String tnvTv2, String txTv2, String pxTv2, String dtTv2, String dMTv2, String qtvTv2, String q12Tv2, String qgTv2, String vnrTv2, String vosTv2, String dp, String ns1Tv1, String ns2Tv1, String ns3Tv1, String ns1Tv2, String ns2Tv2, String ns3Tv2, String nsTv1, String nsTv2, String nsDp, String signsOfEvents, String durationOf220, String durationDisplay, String durationOut, String siTv1, String activeBdTv1, String frtTv1, String kt3Tv1, String siTv2, String activeBdTv2, String frtTv2, String kt3Tv2);

        System.out.println();
        List<Parametr> filtered =
                parametrList
                        .stream()
                        .filter(p -> p.getName().contains("t1Tv1"))
                        .collect(Collectors.toList());

        System.out.println(filtered);
        System.out.println();

//public Operationtv7(String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1)
 //Operationtv7(String customerName, String typeOperation, String error, Timestamp chronoligical, Integer idCustomer, String t1Tv1, String p1Tv1, String v1Tv1, String m1Tv1, String t2Tv1, String p2Tv1, String v2Tv1, String m2Tv1, String t3Tv1, String p3Tv1, String v3Tv1, String m3Tv1, String t1Tv2, String p1Tv2, String v1Tv2, String m1Tv2, String t2Tv2, String p2Tv2, String v2Tv2, String m2Tv2, String t3Tv2, String p3Tv2, String v3Tv2, String m3Tv2, String tnvTv1, String txTv1, String pxTv1, String dtTv1, String dMTv1, String qtvTv1, String q12Tv1, String qgTv1, String vnrTv1, String vosTv1, String tnvTv2, String txTv2, String pxTv2, String dtTv2, String dMTv2, String qtvTv2, String q12Tv2, String qgTv2, String vnrTv2, String vosTv2, String dp, String ns1Tv1, String ns2Tv1, String ns3Tv1, String ns1Tv2, String ns2Tv2, String ns3Tv2, String nsTv1, String nsTv2, String nsDp, String signsOfEvents, String durationOf220, String durationDisplay, String durationOut, String siTv1, String activeBdTv1, String frtTv1, String kt3Tv1, String siTv2, String activeBdTv2, String frtTv2, String kt3Tv2)
Operationtv7 operation = new Operationtv7(customer.getFirstName(), "day",  " " , ts,
        customer.getId(),
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




/*

        Customer customer=new Customer();
        customer.setFirstName("tv7_1");
        customer.setTelModem("76568");
*/

        //Customer customer = customerService.findById(id);





        customer.addOperationtv7(operation);
        customerService.save(customer);



    }


    public String find(List<Parametr> parametrList, String parametr){
        String val= parametrList
                .stream()
                .filter(p -> p.getName().contains(parametr))
                .collect(Collectors.toList())
                .get(0).getValue();

        System.out.println(val);
        return val;
    }


}
