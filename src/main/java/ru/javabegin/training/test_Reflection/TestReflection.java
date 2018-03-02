package ru.javabegin.training.test_Reflection;

import ru.javabegin.training.tv7.entity.Operationtv7;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestReflection {

    public void test1(String nameClass) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        //Рефлексия

        System.out.println("Рефлексия ---------------------");
        //Class mClassObject = Operationtv7T.class;
        String archive_all="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";
        List<String> archive= new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));


        Operationtv7 o=new Operationtv7();
        //Class mClassObject = Class.forName("ru.javabegin.training.tv7.entity.Operationtv7");
        Class mClassObject = o.getClass();
        String fullClassName = mClassObject.getName();
        System.out.println("fullClassName "+fullClassName);

        String justClassName = mClassObject.getSimpleName();
        System.out.println("justClassName "+justClassName);


        // Field field = mClassObject.getField("kt3Tv2");
        // String fieldName = field.getName();
        // System.out.println("fieldName "+fieldName);

        //Field[] fields = mClassObject.getFields();
        Field[] fields = mClassObject.getDeclaredFields();
        System.out.println("размер :: "+fields.length);
        for(Field f:fields){
            //Field field = mClassObject.getField("fieldName");
            String fName = f.getName();
            System.out.println("Название поля :  -->"+fName);
        }

        Field ff=mClassObject.getDeclaredField("v1Tv1");
        String fieldName = ff.getName();

        System.out.println("проверка :: "+fieldName);
      //  ff.set(mClassObject,"2342342");
        double i=22.345;
        for (String id :archive) {
            ff=mClassObject.getDeclaredField(id);
            ff.setAccessible(true);
            System.out.println("Поле "+ff.getName()+" получаем значение :: "+ ff.get(o));
            ff.set(o,String.valueOf(i));
            i=i+43.874;
            System.out.println("Поле "+ff.getName()+"новое значение :: "+ ff.get(o));
            //BigDecimal field_value= new BigDecimal((String)ff.get(o)).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal field_value= new BigDecimal((String)ff.get(o));
            System.out.println("Поле "+ff.getName()+"d bigdecimal "+field_value.toString() );

        }




////

    }
}
