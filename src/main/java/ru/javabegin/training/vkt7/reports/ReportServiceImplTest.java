package ru.javabegin.training.vkt7.reports;

import org.junit.Test;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.junit.Assert.*;

public class ReportServiceImplTest {
    @Test
    public void getObject_ns_to_str() throws Exception {

        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        ReportServiceImpl reportService=new ReportServiceImpl();
        List<DataObject> dataObjectList=new ArrayList<>();
        //String qq="Qo Тв1, t1 Тв1, V2 Тв1, t2 Тв1, V1 Тв1, M1 Тв1, M2 Тв1, Qг Тв1, dt";
        String qq="t1 Тв1,t2 Тв1,V1 Тв1,V2 Тв1, M1 Тв1, M2 Тв1, Qо Тв1, Qг Тв1, dt Тв1";
        System.out.println("srt  ::  "+qq);


        List<String> id_col = new ArrayList<>(Arrays.asList(qq.replace(", ",",").split( ",")));
        id_col=reportService.sort(id_col);
        id_col.forEach(p->System.out.print(p+" "));

        System.out.println();
        for(int i=0; i<2; i++) {
            DataObject object = new DataObject();


            Map<String,Tupel> map=new HashMap<String,Tupel>();
            Tupel tupel=new Tupel();
            if(i==0) {

                map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.81").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2 Тв1", new Tupel("t2 Тв1", (new BigDecimal("53.07").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("dt Тв1", new Tupel("dt Тв1", (new BigDecimal("12.74").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V1 Тв1", new Tupel("V1 Тв1", (new BigDecimal("374.30").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V2 Тв1", new Tupel("V2 Тв1", (new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M1 Тв1", new Tupel("m1 Тв1", (new BigDecimal("366.90").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M2 Тв1", new Tupel("M2 Тв1", (new BigDecimal("0.0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("Qо Тв1", new Tupel("Qо Тв1", (new BigDecimal("20.456").setScale(3, RoundingMode.HALF_EVEN))));
                map.put("Qг Тв1", new Tupel("Qг Тв1", (new BigDecimal("15.791").setScale(3, RoundingMode.HALF_EVEN))));
            }
            if(i==1) {
                map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.21").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2 Тв1", new Tupel("t2 Тв1", (new BigDecimal("51.55").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("dt Тв1", new Tupel("dt Тв1", (new BigDecimal("13.66").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V1 Тв1", new Tupel("V1 Тв1", (new BigDecimal("336.30").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V2 Тв1", new Tupel("V2 Тв1", (new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M1 Тв1", new Tupel("M1 Тв1", (new BigDecimal("329.78").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M2 Тв1", new Tupel("M2 Тв1", (new BigDecimal("0.0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("Qо Тв1", new Tupel("Qо Тв1", (new BigDecimal("18.189").setScale(3, RoundingMode.HALF_EVEN))));
                map.put("Qг Тв1", new Tupel("Qг Тв1", (new BigDecimal("13.736").setScale(3, RoundingMode.HALF_EVEN))));
            }


            object.setOptionalValues(map);
            object.setStaticval2("что то");
            object.setData(auxiliaryService.date_TimeStamp(new Date()));
            dataObjectList.add(object);

        }

        System.out.println("Размер "+dataObjectList.size() );

        //u.optionalValues[id].value

       /* for(DataObject dataObject:dataObjectList){

            System.out.println(dataObject.getOptionalValues().get("t1").getValue());
            System.out.println(dataObject.getData());
            System.out.println(dataObject.getOptionalValues().keySet());

            dataObject.getOptionalValues().keySet().forEach(p->System.out.println(p+" = "+dataObject.getOptionalValues().get(p).getValue()));
        }*/


        //  dataObjectList.get(0).getOptionalValues().keySet().forEach(p->System.out.print(p+"   "));
        id_col.forEach(p->System.out.print(p+"   "));

        System.out.println();

        for(DataObject dataObject:dataObjectList){
            for(String s:id_col){

                System.out.print(dataObject.getOptionalValues().get(s).getValue()+"    ");
            }
            System.out.println();
        }

///////////////////////////////////////////////


        BigDecimal average_t1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_t2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_V1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_V2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_M1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_M2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_Qo=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_Qг=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_dt=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);

        int count_t1=0;
        int count_t2=0;
        int count_V1=0;
        int count_V2=0;
        int count_M1=0;
        int count_M2=0;
        int count_Qo=0;
        int count_Qг=0;
        int count_dt=0;

        for(DataObject dataObject:dataObjectList){


            // dataObject.getOptionalValues().keySet().forEach(p->System.out.print(dataObject.getOptionalValues().get(p).getValue()+"    "));
            System.out.println();
            for(String key: dataObject.getOptionalValues().keySet()){
                // System.out.println("key="+key);

                if(key.equals("t1 Тв1")){
                    average_t1=average_t1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t1++;

                }
                if(key.equals("t2 Тв1")){
                    average_t2=average_t2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t2++;

                }
                if(key.equals("V1 Тв1")){
                    average_V1=average_V1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V1++;

                }
                if(key.equals("V2 Тв1")){
                    average_V2=average_V2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V2++;

                }
                if(key.equals("M1 Тв1")){
                    average_M1=average_M1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M1++;

                }
                if(key.equals("M2 Тв1")){
                    average_M2=average_M2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M2++;

                }
                if(key.equals("Qо Тв1")){
                    average_Qo=average_Qo.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qo++;

                }
                if(key.equals("Qг Тв1")){
                    average_Qг=average_Qг.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qг++;

                }
                if(key.equals("dt Тв1")){
                    average_dt=average_dt.add(dataObject.getOptionalValues().get(key).getValue());
                    count_dt++;

                }

            }

            System.out.println();


        }

        Map<String,Tupel> map_sum=new HashMap<String,Tupel>();
        Map<String,Tupel> map_average=new HashMap<String,Tupel>();

        for (String key: id_col){

            // System.out.println("key= "+key);


            if(key.equals("t1 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_t1.divide(new BigDecimal(count_t1).setScale(2, RoundingMode.HALF_EVEN)))));


            }
            if(key.equals("t2 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_t2.divide(new BigDecimal(count_t2)).setScale(2, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("V1 Тв1")){
                map_sum.put(key, new Tupel(key, (average_V1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("V2 Тв1")){
                map_sum.put(key, new Tupel(key, (average_V2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("M1 Тв1")){
                map_sum.put(key, new Tupel(key, (average_M1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("M2 Тв1")){
                map_sum.put(key, new Tupel(key, (average_M2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("Qо Тв1")){
                map_sum.put(key, new Tupel(key, (average_Qo)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("Qг Тв1")){
                map_sum.put(key, new Tupel(key, (average_Qг)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("dt Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_dt.divide(new BigDecimal(count_dt)).setScale(2, RoundingMode.HALF_EVEN))));

            }

        }

        System.out.println("average_Qo="+average_Qo);

       /* System.out.println("average_t1="+average_t1);
        System.out.println("average_t2="+average_t2);
        System.out.println("average_V1="+average_V1);
        System.out.println("average_V2= "+average_V2);
        System.out.println("average_M1="+average_M1);
        System.out.println("average_M2="+average_M2);
        System.out.println("average_Qo="+average_Qo);
        System.out.println("average_Qг="+average_Qг);
        System.out.println("average_dt="+average_dt);
*/
        DataObject sum= new DataObject();
        sum.setStaticval2("ИТОГО");
        sum.setOptionalValues(map_sum);
        DataObject average= new DataObject();
        average.setStaticval2("Средние");
        average.setOptionalValues(map_average);

        for(String s:id_col) {


            System.out.print(sum.getOptionalValues().get(s).getValue() + "    ");


        }
        System.out.println();
        for(String s:id_col) {
            System.out.print(average.getOptionalValues().get(s).getValue() + "    ");
            // System.out.println();
        }

        //  average.getOptionalValues().keySet().forEach(p -> System.out.print(p + " " + average.getOptionalValues().get(p).getValue() + "    "));


        List<DataObject_str> dataObject_str_List=new ArrayList<>();
 for(DataObject dataObject:dataObjectList){
     DataObject_str dataObject_str=new DataObject_str();
     Map<String,Tupel_str> map_str=new HashMap<String,Tupel_str>();

     for(String coil:id_col){
         map_str.put(coil,new Tupel_str(coil,dataObject.getOptionalValues().get(coil).getValue().toString()));
         //map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.81").setScale(2, RoundingMode.HALF_EVEN))));

     }

    dataObject_str.setOptionalValues(map_str);
    dataObject_str_List.add(dataObject_str);


 }
        System.out.println("\n///////////////////////////////////////////////////////////");

        for(DataObject_str dataObject: dataObject_str_List){
            for(String s:id_col){

                System.out.print(dataObject.getOptionalValues().get(s).getValue()+"    ");
            }
            System.out.println();
        }





    }




    @Test
    public void getCalculations() throws Exception {
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        ReportServiceImpl reportService=new ReportServiceImpl();
        List<DataObject> dataObjectList=new ArrayList<>();
        //String qq="Qo Тв1, t1 Тв1, V2 Тв1, t2 Тв1, V1 Тв1, M1 Тв1, M2 Тв1, Qг Тв1, dt";
        String qq="t1 Тв1,t2 Тв1,V1 Тв1,V2 Тв1, M1 Тв1, M2 Тв1, Qо Тв1, Qг Тв1, dt Тв1";
        System.out.println("srt  ::  "+qq);


        List<String> id_col = new ArrayList<>(Arrays.asList(qq.replace(", ",",").split( ",")));
        id_col=reportService.sort(id_col);
       id_col.forEach(p->System.out.print(p+" "));

        System.out.println();
        for(int i=0; i<2; i++) {
                DataObject object = new DataObject();


            Map<String,Tupel> map=new HashMap<String,Tupel>();
            Tupel tupel=new Tupel();
            if(i==0) {

                map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.81").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2 Тв1", new Tupel("t2 Тв1", (new BigDecimal("53.07").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("dt Тв1", new Tupel("dt Тв1", (new BigDecimal("12.74").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V1 Тв1", new Tupel("V1 Тв1", (new BigDecimal("374.30").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V2 Тв1", new Tupel("V2 Тв1", (new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M1 Тв1", new Tupel("m1 Тв1", (new BigDecimal("366.90").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M2 Тв1", new Tupel("M2 Тв1", (new BigDecimal("0.0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("Qо Тв1", new Tupel("Qо Тв1", (new BigDecimal("20.456").setScale(3, RoundingMode.HALF_EVEN))));
                map.put("Qг Тв1", new Tupel("Qг Тв1", (new BigDecimal("15.791").setScale(3, RoundingMode.HALF_EVEN))));
            }
            if(i==1) {
                map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.21").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2 Тв1", new Tupel("t2 Тв1", (new BigDecimal("51.55").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("dt Тв1", new Tupel("dt Тв1", (new BigDecimal("13.66").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V1 Тв1", new Tupel("V1 Тв1", (new BigDecimal("336.30").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("V2 Тв1", new Tupel("V2 Тв1", (new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M1 Тв1", new Tupel("M1 Тв1", (new BigDecimal("329.78").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("M2 Тв1", new Tupel("M2 Тв1", (new BigDecimal("0.0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("Qо Тв1", new Tupel("Qо Тв1", (new BigDecimal("18.189").setScale(3, RoundingMode.HALF_EVEN))));
                map.put("Qг Тв1", new Tupel("Qг Тв1", (new BigDecimal("13.736").setScale(3, RoundingMode.HALF_EVEN))));
            }


            object.setOptionalValues(map);
            object.setStaticval2("что то");
            object.setData(auxiliaryService.date_TimeStamp(new Date()));
            dataObjectList.add(object);

        }

        System.out.println("Размер "+dataObjectList.size() );

        //u.optionalValues[id].value

       /* for(DataObject dataObject:dataObjectList){

            System.out.println(dataObject.getOptionalValues().get("t1").getValue());
            System.out.println(dataObject.getData());
            System.out.println(dataObject.getOptionalValues().keySet());

            dataObject.getOptionalValues().keySet().forEach(p->System.out.println(p+" = "+dataObject.getOptionalValues().get(p).getValue()));
        }*/


        //  dataObjectList.get(0).getOptionalValues().keySet().forEach(p->System.out.print(p+"   "));
        id_col.forEach(p->System.out.print(p+"   "));

        System.out.println();

        for(DataObject dataObject:dataObjectList){
            for(String s:id_col){

            System.out.print(dataObject.getOptionalValues().get(s).getValue()+"    ");
            }
            System.out.println();
        }

///////////////////////////////////////////////


        BigDecimal average_t1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_t2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_V1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_V2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_M1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_M2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_Qo=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_Qг=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal average_dt=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);

        int count_t1=0;
        int count_t2=0;
        int count_V1=0;
        int count_V2=0;
        int count_M1=0;
        int count_M2=0;
        int count_Qo=0;
        int count_Qг=0;
        int count_dt=0;

        for(DataObject dataObject:dataObjectList){


           // dataObject.getOptionalValues().keySet().forEach(p->System.out.print(dataObject.getOptionalValues().get(p).getValue()+"    "));
            System.out.println();
            for(String key: dataObject.getOptionalValues().keySet()){
               // System.out.println("key="+key);

                if(key.equals("t1 Тв1")){
                    average_t1=average_t1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t1++;

                }
                if(key.equals("t2 Тв1")){
                    average_t2=average_t2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t2++;

                }
                if(key.equals("V1 Тв1")){
                    average_V1=average_V1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V1++;

                }
                if(key.equals("V2 Тв1")){
                    average_V2=average_V2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V2++;

                }
                if(key.equals("M1 Тв1")){
                    average_M1=average_M1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M1++;

                }
                if(key.equals("M2 Тв1")){
                    average_M2=average_M2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M2++;

                }
                if(key.equals("Qо Тв1")){
                    average_Qo=average_Qo.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qo++;

                }
                if(key.equals("Qг Тв1")){
                    average_Qг=average_Qг.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qг++;

                }
                if(key.equals("dt Тв1")){
                    average_dt=average_dt.add(dataObject.getOptionalValues().get(key).getValue());
                    count_dt++;

                }

            }

            System.out.println();


        }

        Map<String,Tupel> map_sum=new HashMap<String,Tupel>();
        Map<String,Tupel> map_average=new HashMap<String,Tupel>();

        for (String key: id_col){

           // System.out.println("key= "+key);


            if(key.equals("t1 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_t1.divide(new BigDecimal(count_t1).setScale(2, RoundingMode.HALF_EVEN)))));


            }
            if(key.equals("t2 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_t2.divide(new BigDecimal(count_t2)).setScale(2, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("V1 Тв1")){
                map_sum.put(key, new Tupel(key, (average_V1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("V2 Тв1")){
                map_sum.put(key, new Tupel(key, (average_V2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("M1 Тв1")){
                map_sum.put(key, new Tupel(key, (average_M1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("M2 Тв1")){
                map_sum.put(key, new Tupel(key, (average_M2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("Qо Тв1")){
                map_sum.put(key, new Tupel(key, (average_Qo)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("Qг Тв1")){
                map_sum.put(key, new Tupel(key, (average_Qг)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

            }
            if(key.equals("dt Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, (average_dt.divide(new BigDecimal(count_dt)).setScale(2, RoundingMode.HALF_EVEN))));

            }

        }

        System.out.println("average_Qo="+average_Qo);

       /* System.out.println("average_t1="+average_t1);
        System.out.println("average_t2="+average_t2);
        System.out.println("average_V1="+average_V1);
        System.out.println("average_V2= "+average_V2);
        System.out.println("average_M1="+average_M1);
        System.out.println("average_M2="+average_M2);
        System.out.println("average_Qo="+average_Qo);
        System.out.println("average_Qг="+average_Qг);
        System.out.println("average_dt="+average_dt);
*/
        DataObject sum= new DataObject();
        sum.setStaticval2("ИТОГО");
        sum.setOptionalValues(map_sum);
        DataObject average= new DataObject();
        average.setStaticval2("Средние");
        average.setOptionalValues(map_average);

        for(String s:id_col) {


            System.out.print(sum.getOptionalValues().get(s).getValue() + "    ");


        }
        System.out.println();
        for(String s:id_col) {
            System.out.print(average.getOptionalValues().get(s).getValue() + "    ");
           // System.out.println();
        }

      //  average.getOptionalValues().keySet().forEach(p -> System.out.print(p + " " + average.getOptionalValues().get(p).getValue() + "    "));


    }

}