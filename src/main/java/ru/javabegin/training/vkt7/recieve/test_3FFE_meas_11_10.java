package ru.javabegin.training.vkt7.recieve;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Николай on 11.10.2017.
 */
public class test_3FFE_meas_11_10 {

    public static void main(String args[]){


     String FC_in = "010366" +
             "000000400200" +
             "010000400200" +
             "030000400400" +
             "040000400400" +
             "060000400400" +
             "070000400400" +
             "090000400200" +
             "0A0000400200" +
             "0B0000400400" +
             "0C0000400400" +
             "0D0000400400" +
             "0E0000400200" +
             "110000400400" +
             "120000400400" +
             "130000400400" +
             "140000400400" +
             "4D0000400100" +
             "07A1";

String ff_out="" +
        "03 00 00 40 04 00 " +
        "04 00 00 40 04 00 " +
        "06 00 00 40 04 00 " +
        "07 00 00 40 04 00 " +
        "0B 00 00 40 04 00 " +
        "0C 00 00 40 04 00 " +
        "0D 00 00 40 04 00 " +
        "0E 00 00 40 02 00 " +
        "11 00 00 40 04 00 " +
        "12 00 00 40 04 00 " +
        "13 00 00 40 04 00 " +
        "14 00 00 40 04 00 " +
        "4D 00 00 40 01 00 " +
        "6A 00 \n";

        String ff_in= "01 03 49 2A 6E 6B 00 C0 00 46 D8 62 00 C0 00 E6 B5 65 00 C0 00 FE 3D 60 00 C0 00 E8 77 05 00 C0 00 08 C3 10 00 C0 00 FF B0 02 00 C0 00 FF B0 04 00 F6 40 00 00 C0 00 BF 00 00 00 C0 00 BF 00 00 00 04 00 BF 00 00 00 04 00 00 04 00 DE 7D";

String V1="2A6E6B00";

       int val_V= (int) Long.parseLong(l2b(V1), 16);
        System.out.println("val_V1"+val_V);

        String V2="36DA6200";

        int val_V2= (int) Long.parseLong(l2b(V2), 16);
        System.out.println("val_V2"+val_V2);



/*
        0 t1 Тв1 °C 2 2
        1 t2_1Type t2 Тв1 °C 2 2
        3 V1_1Type V1 Тв1  м3 2 4
        4 V2_1Type V2 Тв1  м3 2 4
        6 M1_1Type M1 Тв1  т 2 4
        7 M2_1Type M2 Тв1  т 2 4
        9 P1_1Type P1 Тв1 кг/см2 2 2
        10 P2_1Type P2 Тв1 кг/см2 2 2
        11 Mg_1TypeP Mг Тв1 null 0 4
        12 Qo_1TypeP Qо Тв1 Гкал 3 4
        13 Qg_1TypeP Qг Тв1 null 3 4
        14 dt_1TypeP dt Тв1 null 0 2
        17 QntType_1HIP BНP Тв1 ч 0 4
        18 QntType_1P BOC Тв1 ч 0 4
        77 NSPrintTypeM_1 Наличие нештатной ситуации по ТВ1 null 0 1
       */

        String str="01034D" +    //
                "E61BC000" +     // 0 t1 Тв1 °C 2 2
                "EA13C000" +     // 1 t2_1Type t2 Тв1 °C 2 2
                "5D010000C000" + // 3 V1_1Type V1 Тв1  м3 2 4
                "60010000C000" + // 4 V2_1Type V2 Тв1  м3 2 4
                "55010000C000" + // 6 M1_1Type M1 Тв1  т 2 4
                "5C010000C000" + // 7 M2_1Type M2 Тв1  т 2 4
                "0C03C000" +     // 9 P1_1Type P1 Тв1 кг/см2 2 2
                "E402C000" +     // 10 P2_1Type P2 Тв1 кг/см2 2 2
                "F9FFFFFFC000" + // 11 Mg_1TypeP Mг Тв1 null 0 4
                "43000000C000" + // 12 Qo_1TypeP Qо Тв1 Гкал 3 4
                "FEFFFFFFC000" + // 13 Qg_1TypeP Qг Тв1 null 3 4
                "FC07C0000" +    // 14 dt_1TypeP dt Тв1 null 0 2
                "1000000C0000" + // 17 QntType_1HIP BНP Тв1 ч 0 4
                "0000000C0002" + // 18 QntType_1P BOC Тв1 ч 0 4
                "0C000" +        //
                "F634";

       /* Измеряемое значение t1 Тв1 размер 2
        t1 Тв1 = 71.42°C байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение t2 Тв1 размер 2
        t2 Тв1 = 50.980000000000004°C байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение V1 Тв1 размер 4
        V1 Тв1 = 3.49 м3 байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение V2 Тв1 размер 4
        V2 Тв1 = 3.52 м3 байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение M1 Тв1 размер 4
        M1 Тв1 = 3.41 т байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение M2 Тв1 размер 4
        M2 Тв1 = 3.48 т байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение P1 Тв1 размер 2
        P1 Тв1 = 7.8кг/см2 байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение P2 Тв1 размер 2
        P2 Тв1 = 7.4кг/см2 байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение Mг Тв1 размер 4
        Mг Тв1 = -0.07 байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение Qо Тв1 размер 4
        Qо Тв1 = 0.067Гкал байт качества -OPC_QUALITY_GOOD 0xC0NS -00
        Измеряемое значение Qг Тв1 размер 4
        */

/*
       if(someVar instanceof Integer)

            temp_measur.setType("int");
            temp_measur.setMeasurInt(Integer.parseInt(l2b(measur),16));

         //   if (razmer==4){

            temp_measur.setType("float");
            temp_measur.setMeasurFloat(Float.intBitsToFloat(Integer.valueOf(l2b(measur),16).intValue()));
          */

String measur = "43000000";
   int val= Integer.parseInt(l2b(measur),16);
System.out.println("val="+val);
        measur = "FEFFFFFF";
        val= (int) Long.parseLong(l2b(measur), 16);
        System.out.println("val="+val);

        measur = "43000000";
        val= (int) Long.parseLong(l2b(measur), 16);
        System.out.println("val="+val);



    }


    public static String l2b (String str) {
        String str_out = "";
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{2})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out = str_out +list.get(i);
        }
        return str_out;
    }


}
