package ru.javabegin.training.vkt7.reports;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.ROUND_CEILING;

/**
 * Created by Николай on 27.10.2017.
 */
public class Test_round_temp {

    public static void main (String[] args) {


    /*    double a= 0.3333224441;
        System.out.println(" число =  "+ a);
        BigDecimal a1= new BigDecimal(a);
        a1= a1.setScale(4,ROUND_CEILING);
        System.out.println(" bd =  "+ a1);
        float num1=1.3456783f;
        BigDecimal bd1 = BigDecimal.valueOf(num1).setScale(4, BigDecimal.ROUND_CEILING);
        System.out.println(" из float =  "+ bd1);

        double num2=1.3678678567856785678;
        BigDecimal bd2 = BigDecimal.valueOf(num2).setScale(4, BigDecimal.ROUND_CEILING);
        System.out.println(" из double =  "+ bd2);

        String num3="0.9989877866";
        BigDecimal bd3 = new BigDecimal(num3).setScale(4, BigDecimal.ROUND_CEILING);
        System.out.println(" из String =  "+ bd2);

        BigDecimal r=bd1.add(bd2).add(bd3);

        String str_bd=r.toString();
        System.out.println(" Сумма  в String=  "+str_bd);
        System.out.println(" ----------------------------------------- ");

        num2=1.423551;
        BigDecimal rr = BigDecimal.valueOf(num2).setScale(4,BigDecimal.ROUND_HALF_EVEN);

        System.out.println(" до    округления = "+ num2);
        System.out.println(" после округления = "+ rr);

        System.out.println(" ----------------------------------------- ");
        System.out.println(" просто 3 = "+ BigDecimal.valueOf(3));


       // r=(bd1.add(bd2).add(bd3)).divide(BigDecimal.valueOf(3));
        BigDecimal u = r.divide(BigDecimal.valueOf(3), RoundingMode.HALF_EVEN);

        System.out.println(" u = "+u);

        BigDecimal w= r.divide(BigDecimal.valueOf(3), 8,RoundingMode.HALF_EVEN);
        System.out.println(" w = "+w);


        String n="0.9989487866";
        BigDecimal b = new BigDecimal(n).setScale(4, RoundingMode.HALF_EVEN);
        System.out.println(" из String =  "+ b);
        b=b.movePointRight(3);
        System.out.println(" из String =  "+ b);
*/
        String qw="0";
        BigDecimal bqw = new BigDecimal(qw).setScale(5, RoundingMode.HALF_EVEN);

        System.out.println(" из String =  "+bqw);
        String model = Integer.toString(Integer.parseInt("FF",16));
        System.out.println(" из String =  "+model);
        BigDecimal bqw1 = new BigDecimal(model);

        System.out.println(" из String =  "+bqw1);




    }
}
