package ru.javabegin.training.tv7.excel;

import org.springframework.stereotype.Component;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.tv7.initDataClass.InitData;
import ru.javabegin.training.tv7.initDataClass.Parametr;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class GetListDataImpl implements GetListData {
    @Override
    public List<DataObjectTv7> getTv7List(List<Operationtv7> operationtv7List,String type) {

        List<String> archiveTv=null;

        String archive="chronoligical, t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, p1Tv2, v1Tv2, m1Tv2, t2Tv2, p2Tv2, v2Tv2, m2Tv2, t3Tv2, p3Tv2, v3Tv2, m3Tv2,     tnvTv2, txTv2, pxTv2, dtTv2, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2, dp, ns1Tv1, ns2Tv1, ns3Tv1, ns1Tv2, ns2Tv2, ns3Tv2, nsTv1, nsTv2, nsDp, signsOfEvents, durationOf220, durationDisplay, durationOut, siTv1, activeBdTv1, frtTv1, kt3Tv1, siTv2, activeBdTv2, frtTv2, kt3Tv2, customer";

        String archive_all="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";

        String archive_tv1="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1";

        String archive_tv2="t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";
        if(type.equals("TV1")){
            archiveTv= new ArrayList<>(Arrays.asList(archive_tv1.replace(" ","").split(",")));
        }
        if(type.equals("TV2")){
            archiveTv= new ArrayList<>(Arrays.asList(archive_tv2.replace(" ","").split(",")));
        }
        if(type.equals("ALL")){
            archiveTv= new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));
        }


        InitData initData=new InitData();
        List<Parametr> parametrList=initData.initDay();

        Map<String,TupelExcelTv7> map=new HashMap<>();
        List<DataObjectTv7> objectTv7List=new ArrayList<>();

        Field f;
        String dd="";
        for (Operationtv7 o:operationtv7List) {
            List<String> id_col = new ArrayList<>();
            Class mClassObject = o.getClass();
            Field[] fields = mClassObject.getDeclaredFields();
            for (String id:archiveTv) {

                f=findField(fields ,id);
                if(f!=null) {
                    f.setAccessible(true);
                    try {
                        dd = (String) f.get(o);

                        if (!id.contains("vnrTv1")&&!id.contains("vosTv1")&&new BigDecimal("0").compareTo(new BigDecimal(dd).setScale(2, RoundingMode.HALF_EVEN))!=0){
                            System.out.println("!Проверка пройдена");
                            System.out.println(f.getName() + " == " + dd);
                            id_col.add(id);
                            map.put(id,new TupelExcelTv7(id,dd) );
                        }

                        if (id.contains("vnrTv1")||id.contains("vosTv1")){
                            System.out.println("!!!!содеожит ВНР ВОС");
                            System.out.println(f.getName() + " == " + dd);
                            id_col.add(id);
                            map.put(id,new TupelExcelTv7(id,dd) );
                        }


                        map.put(id,new TupelExcelTv7(id,dd) );

                    } catch (Exception e) {
                        System.out.println("Не String поле");
                    }

                }

            }

            id_col.forEach(p->System.out.print(p+" "));

            DataObjectTv7 dataItem=new DataObjectTv7();
            dataItem.setData(o.getChronoligical());
            dataItem.setId_coils(id_col);
            dataItem.setOptionalValues(map);
            objectTv7List.add(dataItem);


        }




        return objectTv7List;
    }



    @Override
    public List<DataObjectTv7> getTv7TList(List<Operationtv7T> operationtv7TList,String type) {
        List<String> totalTv=null;

        String total="id, customerName, error, chronoligical, idCustomer, v1Tv1, m1Tv1, v2Tv1, m2Tv1, v3Tv1, m3Tv1, v1Tv2, m1Tv2, v2Tv2, m2Tv2, v3Tv2, m3Tv2, dMTv1, qtvTv1, q12Tv1, qgTv1, vnrTv1, vosTv1, tvminTv1, tvmaxTv1, tdtTv1, tno220Tv1, tterrTv1, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2, tvminTv2, tvmaxTv2, tdtTv2, tno220Tv2, tterrTv2, dp, durationOf220, durationDisplay, durationOut, siTv1, activeBdTv1, frtTv1, kt3Tv1, siTv2, activeBdTv2, frtTv2, kt3Tv2";
        String total_all=" v1Tv1, v2Tv1, v3Tv1, m1Tv1,  m2Tv1,  m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, vnrTv1, vosTv1, v1Tv2, v2Tv2, v3Tv2, m1Tv2,  m2Tv2,  m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2";
        String total_tv1=" v1Tv1, v2Tv1, v3Tv1, m1Tv1,  m2Tv1,  m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, vnrTv1, vosTv1";
        String total_tv2=" v1Tv2, v2Tv2, v3Tv2, m1Tv2,  m2Tv2,  m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2";

       // Проверяем запрашиваемый тип и формируем список параметров TV1/TV2/all

        if(type.equals("TV1")){
            totalTv= new ArrayList<>(Arrays.asList(total_tv1.replace(" ","").split(",")));
        }
        if(type.equals("TV2")){
            totalTv= new ArrayList<>(Arrays.asList(total_tv2.replace(" ","").split(",")));
        }
        if(type.equals("ALL")){
            totalTv= new ArrayList<>(Arrays.asList(total_all.replace(" ","").split(",")));
        }



        InitData initData=new InitData();
        List<Parametr> parametrList=initData.initTotal();
        //System.out.println( parametrList);

        Map<String,TupelExcelTv7> map=new HashMap<>();
        //TupelExcelTv7 tupel=new TupelExcelTv7();
        List<DataObjectTv7> objectTv7List=new ArrayList<>();


        int count_coils=0;
        int count_coils_max=0;

        Field f;
        String dd="";
        for (Operationtv7T o:operationtv7TList) {
            List<String> id_col = new ArrayList<>();
            Class mClassObject = o.getClass();
            Field[] fields = mClassObject.getDeclaredFields();
            for (String id:totalTv) {

                f=findField(fields ,id);
                if(f!=null) {
                    f.setAccessible(true);
                    try {
                        dd = (String) f.get(o);

                        if (!id.contains("vnrTv1")&&!id.contains("vosTv1")&&new BigDecimal("0").compareTo(new BigDecimal(dd).setScale(2, RoundingMode.HALF_EVEN))!=0){
                            System.out.println("!Проверка пройдена");
                            System.out.println(f.getName() + " == " + dd);
                            id_col.add(id);
                            map.put(id,new TupelExcelTv7(id,dd) );
                        }

                        if (id.contains("vnrTv1")||id.contains("vosTv1")){
                            System.out.println("!!!!содеожит ВНР ВОС");
                            System.out.println(f.getName() + " == " + dd);
                            id_col.add(id);
                            map.put(id,new TupelExcelTv7(id,dd) );
                        }


                        map.put(id,new TupelExcelTv7(id,dd) );

                    } catch (Exception e) {
                        System.out.println("Не String поле");
                    }

                }

            }

            id_col.forEach(p->System.out.print(p+" "));

            DataObjectTv7 dataItem=new DataObjectTv7();
            dataItem.setData(o.getChronoligical());
            dataItem.setId_coils(id_col);
            dataItem.setOptionalValues(map);
            objectTv7List.add(dataItem);


        }




        return objectTv7List;
    }





    public Field findField(Field[] fields ,String id ){



        Field f= Arrays.stream(fields).filter(p -> p.getName().contains(id))
                .collect(Collectors.toList())
                .get(0);


        return f;
    }
}
