package ru.javabegin.training.tv7.excel;

import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Calculation {

    public void atchive(List<Operationtv7> operationtv7List, List<Operationtv7T> operationtv7TList) throws NoSuchFieldException, IllegalAccessException {



       /* String regularExpression2="^(t\\d_|dt|tx|tнв)";
        String regularExpression3="^(dM)";

        if (Pattern.compile(regularExpression1).matcher(p.getName()).find())*/
        String archive_all="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";

        List<String> archive= new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));
        String regularExpression_aver="^(t\\d|p|dp|dM|dt|tn|tx)";
        String regularExpression_sum="^(v|m|vn|vo|q)";

        Operationtv7 sumaverage=new Operationtv7();

        Field ff;
        String fieldName="";

        for(int i=0; i<operationtv7List.size(); i++){

            Class mClassObject = operationtv7List.get(i).getClass();

            for (String id:archive ) {
                ff=mClassObject.getDeclaredField(id);
                fieldName = ff.getName();
                System.out.println("проверка :: "+fieldName);
                if (Pattern.compile(regularExpression_aver).matcher(fieldName).find()){

                    ff.setAccessible(true);
                    System.out.println("Поле "+ff.getName()+" получаем значение :: "+ ff.get(operationtv7List.get(i)));
                    ff.set(operationtv7List.get(i),String.valueOf(i));

                    System.out.println("Поле "+ff.getName()+"новое значение :: "+ ff.get(operationtv7List.get(i)));



                }

               // ff.set();

            }









        }


    }
}
