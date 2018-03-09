package ru.javabegin.training.test_pattern;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class test_pattern_27_02_18 {


    public static void main(String[] args) throws ParseException {


 /* String regularExpression2="^(t\\d_|dt|tx|tнв)";
        String regularExpression3="^(dM)";

        if (Pattern.compile(regularExpression1).matcher(p.getName()).find())*/
        String archive_all="t1Tv1, t2Tv1, t3Tv1, dtTv1, p1Tv1, p2Tv1, p3Tv1, v1Tv1, v2Tv1, v3Tv1, m1Tv1, m2Tv1, m3Tv1, dMTv1, qtvTv1, q12Tv1, qgTv1, tnvTv1, txTv1, pxTv1, vnrTv1, vosTv1, t1Tv2, t2Tv2, t3Tv2, dtTv2, p1Tv2, p2Tv2, p3Tv2, v1Tv2, v2Tv2, v3Tv2, m1Tv2, m2Tv2, m3Tv2, dMTv2, qtvTv2, q12Tv2, qgTv2, tnvTv2, txTv2, pxTv2, vnrTv2, vosTv2";
        List<String> archiveTv = new ArrayList<>(Arrays.asList(archive_all.replace(" ","").split(",")));
        archiveTv.forEach(p->System.out.print(p+" "));
        System.out.println("Количество элементов "+archiveTv.size());

        int aver =0;
        int sum=0;

        String regularExpression_aver="^(t\\d|p|dp|dM|dt|tn|tx)";
        String regularExpression_sum="^(v|m|vn|vo|q)";

        for (String s:archiveTv) {
            if (Pattern.compile(regularExpression_aver).matcher(s).find()){
                System.out.println("Среднее  = " + s);
                aver++;
            }else
            if (Pattern.compile(regularExpression_sum).matcher(s).find()){
                System.out.println("Сумма   = " + s);
                sum++;
            }else
            {
                System.out.println("=====>>>>> не понятный элемент = " + s);
            }


        }

        System.out.println("всего элементов = " +archiveTv.size());
        System.out.println("Средних = " + aver);
        System.out.println("Сумма = " + sum);
        System.out.println("средние + сумма = " + (sum+aver));




    }

}
