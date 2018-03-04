package ru.javabegin.training.tv7.excel;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeService;
import ru.javabegin.training.tv7.auxillary.AuxDateTimeServiceImpl;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculation {


    public List<Object> archiveXsl(List<Operationtv7> operationtv7List, List<Operationtv7T> operationtv7TList) throws NoSuchFieldException, IllegalAccessException, ExecutionException, InterruptedException {






        Callable task = () -> {
            System.out.println("работает поток "+ Thread.currentThread().getName());



            List<DataObjectTv7> total= totalXsl(operationtv7TList);
            //daily_moth_cron.daily_all_cycle(customerList, customerService, auxiliaryService, data, type);
            return total;
        };


      ExecutorService service_total = Executors.newSingleThreadExecutor();
       Future<List<DataObjectTv7> >future_total = service_total.submit(task);




        service_total.shutdown();


        System.out.println("Основная программа работу закончила");




        String archive_all="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";

        List<String> archive= new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));
        String total_all=" v1Tv1, v2Tv1, v3Tv1, m1Tv1,  m2Tv1,  m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, vnrTv1, vosTv1, v1Tv2, v2Tv2, v3Tv2, m1Tv2,  m2Tv2,  m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2";
        List<String>  total= new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));
        String regularExpression_aver="^(t\\d|p|dp|dt|tn|tx)";
        String regularExpression_sum="^(v|m|vn|vo|q|dM)";

        String znak0="^(vn|vo)";
        String znak1="^(p|dp)";
        String znak2="^(t\\d|dt|tn|tx)";
        String znak3="^(v|m|vn|vo|q)";

        Operationtv7 sumaverage=new Operationtv7();

        Field field, field_arch;
        BigDecimal field_value, field_value_arch ;
        String fieldName="";
        String field_archName="";
        int znak=0;

        Class mClassObject_arch = sumaverage.getClass();
        int count_id=0;

        List<DataObjectTv7> dataObjectTv7List =new ArrayList<>();



        for(int i=0; i<operationtv7List.size(); i++){
            System.out.println(operationtv7List.get(i).getCustomerName());
            Class mClassObject = operationtv7List.get(i).getClass();
            count_id++;

            HashMap<String,TupelExcelTv7> tv7HashMap=new HashMap<>();



            System.out.println("T1Tv1() : "+sumaverage.getT1Tv1());
            System.out.println("T1Tv2() : "+sumaverage.getT2Tv1());


            for (String id:archive ) {



                field=mClassObject.getDeclaredField(id);
                field.setAccessible(true);
                fieldName = field.getName();
                System.out.println("проверка :: "+fieldName);

                field_arch=mClassObject_arch.getDeclaredField(id);
                field_arch.setAccessible(true);
                field_archName = field_arch.getName();
                System.out.println("проверка :: "+field_archName);
                System.out.println("проверка значение:: "+field_arch.get(sumaverage));

               // System.out.println("проверка значение:: "+field_arch.get(mClassObject_arch));
                //перебираем значения полей класса для суммирования или среднего
                //Если значение поля равно NULL то не рассматриваем это поле
                try {
                    field_value = new BigDecimal((String) field.get(operationtv7List.get(i)));
                }catch ( Exception e){
                    System.out.println("Поле : "+fieldName+" равно NULL");
                    continue;
                }

                tv7HashMap.put(fieldName,new TupelExcelTv7(fieldName,field_value.toString()));



                if (Pattern.compile(znak0).matcher(fieldName).find()){
                    znak=0;
                }
                if (Pattern.compile(znak1).matcher(fieldName).find()){
                    znak=1;
                }
                if (Pattern.compile(znak2).matcher(fieldName).find()){
                    znak=2;
                }
                if (Pattern.compile(znak3).matcher(fieldName).find()){
                    znak=3;
                }

                System.out.println(operationtv7List.get(i).getCustomerName()+ " поле : "+ id+" fieldName : "+fieldName+
                        " field_archName : "+field_archName +" val : "+field_value+" кол-во знаков : "+znak);

                if (Pattern.compile(regularExpression_aver).matcher(fieldName).find()){

                    //BigDecimal field_value= new BigDecimal((String)field.get(operationtv7List.get(i))).setScale(2, RoundingMode.HALF_EVEN);




                    //Проверяем значение поля класса, в который будем записывать средние/суммы.
                    //Если полу было NULL то, леоаем это поле "0" для работы с Big Decimal

                    try {
                        field_value_arch = new BigDecimal((String) field_arch.get(sumaverage));
                    }catch ( Exception e){
                        field_value_arch=new BigDecimal("0");
                    }

                    System.out.println("count_id = "+count_id);

                    if (count_id>1){
                    System.out.println("COUNT====>> >1  "+"f_arch name : "+ field_arch.getName()+" VALUE = "+  field_value_arch.toString());
                    }

                    field_value_arch=field_value_arch.add(field_value);
                    System.out.println("Итоговое СУММА значение поля "+field.getName()+ " = "+field_value_arch.toString());
                    if(count_id == operationtv7List.size()) {

                    try {
                        field_value_arch = (field_value_arch.divide(new BigDecimal(count_id),znak+4,BigDecimal.ROUND_HALF_EVEN).setScale(znak, RoundingMode.HALF_EVEN));
                    }catch (Exception e){
                        System.out.println("Ошибка СУММ :" +e);
                    }
                    }
                   try {
                       field_arch.set(sumaverage, field_value_arch.toString());

                   }catch (Exception e){
                       System.out.println("Не возможно записать в поле : "+e);
                   }

                    System.out.println("Итоговое СУММА значение поля "+field.getName()+" получаем значение :: "+ field_value_arch.toString());



                }

                if (Pattern.compile(regularExpression_sum).matcher(fieldName).find()){

                    //Проверяем значение поля класса, в который будем записывать средние/суммы.
                    //Если полу было NULL то, леоаем это поле "0" для работы с Big Decimal

                    System.out.println("попали в sum"+operationtv7List.get(i).getCustomerName()+ " поле : "+ id+" fieldName : "+fieldName+ " field_archName : "+
                            field_archName +" val : "+field_value+ " кол-во знаков "+znak);

                    try {
                        field_value_arch = new BigDecimal((String) field_arch.get(sumaverage));
                    }catch ( Exception e){
                        field_value_arch=new BigDecimal("0");
                    }


                    System.out.println("count_id = "+count_id);

                    if (count_id>1){
                        System.out.println("COUNT====>> >1  "+"f_arch name : "+ field_arch.getName()+" VALUE = "+  field_value_arch.toString());
                    }

                    field_value_arch=(field_value_arch.add(field_value)).setScale(znak, RoundingMode.HALF_EVEN);
                    System.out.println("field_value_arch : "+field_value_arch.toString()+"new BigDecimal(count_id) :"+new BigDecimal(count_id).toString());

                    field_arch.set(sumaverage,field_value_arch.toString());


                    System.out.println("Итоговое СРЕДНЕЕ значение поля "+field.getName()+" получаем значение :: "+ field_value_arch);


                }


            }
            DataObjectTv7 dataObjectTv7=new DataObjectTv7();
            AuxDateTimeServiceImpl auxDateTimeService=new AuxDateTimeServiceImpl();
            dataObjectTv7.setDataString(auxDateTimeService.timeStamp_to_stringData(operationtv7List.get(i).getChronoligical()));
            //dataObjectTv7.setId_coils(archive);
            dataObjectTv7.setOptionalValues(tv7HashMap);
            dataObjectTv7List.add(dataObjectTv7);

        }


        System.out.println("Опрос закончен");

        //Делаем объект со средними значениями и сумами.

        DataObjectTv7 sumaver=new DataObjectTv7();
        HashMap<String,TupelExcelTv7> sumaver_hash=new HashMap<>();


        for (String id:archive) {

            field_arch=mClassObject_arch.getDeclaredField(id);
            field_arch.setAccessible(true);
            sumaver_hash.put(id,new TupelExcelTv7(id, (String)field_arch.get(sumaverage)));

        }
        sumaver.setDataString("ИТОГО/СРЕДНИЕ");
        sumaver.setOptionalValues(sumaver_hash);

        //Добавляем к общему списку

        dataObjectTv7List.add(sumaver);

        //operationtv7List.add(sumaverage);



        List<Object> objectList=new ArrayList<>();

        objectList.add(0,archive);
        objectList.add(1,dataObjectTv7List);


        //Добавляем в итоговые объекты значения ИТОГОВЫЕ
        ///В начале мы запускали поток. Теперь проверяем, получили ли мы данные и если не получили, тождем

        List<DataObjectTv7> totalData = future_total.get();
        //добавляем список колонок для ИТОГОВЫЕ
        objectList.add(2,total);
        //добавляем список колонок для ИТОГОВЫЕ
        objectList.add(3,totalData);
        /*for(String person : personMap.keySet()){
            System.out.println(person + " имеет");
            for (String pet : personMap.get(person)){
                System.out.println("  " + pet);
            }
        }*/
        return objectList;

    }

/////////////////////////////////    ДЛЯ ИТОГОВЫХ     //////////////////////

    public List<DataObjectTv7> totalXsl(List<Operationtv7T> list) throws NoSuchFieldException, IllegalAccessException {



        String total_all=" v1Tv1, v2Tv1, v3Tv1, m1Tv1,  m2Tv1,  m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, vnrTv1, vosTv1, v1Tv2, v2Tv2, v3Tv2, m1Tv2,  m2Tv2,  m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, vnrTv2, vosTv2";
        List<String> archive= new ArrayList<>(Arrays.asList(total_all.replace(" ","").split(",")));

        String regularExpression_sum="^(v|m|vn|vo|q|dM)";

        String znak0="^(vn|vo)";
        String znak1="^(p|dp)";
        String znak2="^(t\\d|dt|tn|tx)";
        String znak3="^(v|m|vn|vo|q)";

        Operationtv7T sum=new Operationtv7T();

        Field field, field_arch;
        BigDecimal field_value, field_value_arch ;
        String fieldName="";
        String field_archName="";
        int znak=0;

        Class mClassObject_arch = sum.getClass();
        int count_id=0;

        List<DataObjectTv7> dataObjectTv7List =new ArrayList<>();





//Создаем новый массив в который добавляем первое и полседнее по дате значение ИТОГОВЫЕ
        List<Operationtv7T> operationtv7TList=new ArrayList<>();
        operationtv7TList.add(0,list.stream().min((p1, p2) -> p1.getChronoligical().compareTo(p2.getChronoligical())).get());
        operationtv7TList.add(1,list.stream().max((p1, p2) -> p1.getChronoligical().compareTo(p2.getChronoligical())).get());


        for(int i=1; i>-1; i--){


            System.out.println(operationtv7TList.get(i).getCustomerName());


            Class mClassObject = operationtv7TList.get(i).getClass();
            count_id++;

            HashMap<String,TupelExcelTv7> tv7HashMap=new HashMap<>();



            for (String id:archive ) {



                field=mClassObject.getDeclaredField(id);
                field.setAccessible(true);
                fieldName = field.getName();


                field_arch=mClassObject_arch.getDeclaredField(id);
                field_arch.setAccessible(true);
                field_archName = field_arch.getName();



                //перебираем значения полей класса для суммирования или среднего
                //Если значение поля равно NULL то не рассматриваем это поле
                try {
                    field_value = new BigDecimal((String) field.get(operationtv7TList.get(i)));
                }catch ( Exception e){
                    System.out.println("Поле : "+fieldName+" равно NULL");
                    continue;
                }

                tv7HashMap.put(fieldName,new TupelExcelTv7(fieldName,field_value.toString()));



                if (Pattern.compile(znak0).matcher(fieldName).find()){
                    znak=0;
                }
                if (Pattern.compile(znak1).matcher(fieldName).find()){
                    znak=1;
                }
                if (Pattern.compile(znak2).matcher(fieldName).find()){
                    znak=2;
                }
                if (Pattern.compile(znak3).matcher(fieldName).find()){
                    znak=3;
                }

                System.out.println(operationtv7TList.get(i).getCustomerName()+ " поле : "+ id+" fieldName : "+fieldName+
                        " field_archName : "+field_archName +" val : "+field_value+" кол-во знаков : "+znak);





                    //Проверяем значение поля класса, в который будем записывать средние/суммы.
                    //Если полу было NULL то, леоаем это поле "0" для работы с Big Decimal

                    try {
                        field_value_arch = new BigDecimal((String) field_arch.get(sum));
                    }catch ( Exception e){
                        field_value_arch=new BigDecimal("0");
                    }




                   if (i==1) {

                    field_value_arch = field_value.setScale(znak, RoundingMode.HALF_EVEN);

                    field_arch.set(sum, field_value_arch.toString());
                }else{

                       field_value_arch=(field_value_arch.subtract(field_value)).setScale(znak, RoundingMode.HALF_EVEN);
                       field_arch.set(sum, field_value_arch.toString());

                   }






            }
            DataObjectTv7 dataObjectTv7=new DataObjectTv7();
            AuxDateTimeServiceImpl auxDateTimeService=new AuxDateTimeServiceImpl();
            dataObjectTv7.setDataString(auxDateTimeService.timeStamp_to_stringData(operationtv7TList.get(i).getChronoligical()));
            dataObjectTv7.setData(operationtv7TList.get(i).getChronoligical());
            //dataObjectTv7.setId_coils(archive);
            dataObjectTv7.setOptionalValues(tv7HashMap);
            dataObjectTv7List.add(dataObjectTv7);

        }


        System.out.println("Опрос закончен");

        //Делаем объект со средними значениями и сумами.

        DataObjectTv7 total=new DataObjectTv7();
        HashMap<String,TupelExcelTv7>total_hash=new HashMap<>();


        for (String id:archive) {

            field_arch=mClassObject_arch.getDeclaredField(id);
            field_arch.setAccessible(true);
            total_hash.put(id,new TupelExcelTv7(id, (String)field_arch.get(sum)));

        }
        total.setDataString("ИТОГО");
        total.setOptionalValues(total_hash);

        //Добавляем к общему списку
        List<DataObjectTv7> sortlist=dataObjectTv7List.stream()
                .sorted((o1,o2) -> o1.getData().compareTo(o2.getData()))
                .collect(Collectors.toList());

        sortlist.add(total);

        //dataObjectTv7List.add(total);

        //operationtv7List.add(sumaverage);




      /*
        for(String person : personMap.keySet()){
            System.out.println(person + " имеет");
            for (String pet : personMap.get(person)){
                System.out.println("  " + pet);
            }
        }

*/

        //return dataObjectTv7List;
        return sortlist;



    }


}
