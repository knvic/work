package ru.javabegin.training.tv7.recieve;

import ru.javabegin.training.tv7.ASCII.AscServiceImpl;
import ru.javabegin.training.tv7.initDataClass.Parametr;
import ru.javabegin.training.vkt7.reports.Tupel;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModBusRServiceImpl implements ModBusRService {


    @Override
    public void infOfDate(List<String> list){
        StringBuilder temp=new StringBuilder();
        list.forEach(p-> temp.append(p));
        System.out.println("\nStringBuilder = "+temp.toString());


        List<String> id_col = new ArrayList<>(Arrays.asList("b_hour_dd","b_hour_MM","b_hour_uu","b_hour_HH","b_hour_mm","b_hour_ss",
                "b_day_dd","b_day_MM","b_day_uu","b_day_HH","b_day_mm","b_day_ss",
                "b_moth_dd","b_moth_MM","b_moth_uu","b_moth_HH","b_moth_mm","b_moth_ss",
                "b_total_dd","b_total_MM","b_total_uu","b_total_HH","b_total_mm","b_total_ss",
                "e_hour_dd","e_hour_MM","e_hour_uu","e_hour_HH","e_hour_mm","e_hour_ss",
                "e_day_dd","e_day_MM","e_day_uu","e_day_HH","e_day_mm","e_day_ss",
                "e_moth_dd","e_moth_MM","e_moth_uu","e_moth_HH","e_moth_mm","e_moth_ss",
                "e_total_dd","e_total_MM","e_total_uu","e_total_HH","e_total_mm","e_total_ss",
                "reboot_dd","reboot_MM","reboot_uu","reboot_HH","reboot_mm","reboot_ss"));
      int colRecieve=Integer.parseInt(temp.substring(4,6).toString(),16);
        System.out.println("количество принятых байт ="+colRecieve);
      int j=0;
        for (int i=6;i<temp.length();i=i+4){

            System.out.println("Дата параметр ="+id_col.get(j));
            int p1= Integer.parseInt(temp.substring(i,i+2).toString(),16);
            j=j+1;

            System.out.println("Дата параметр ="+id_col.get(j));
            int p2= Integer.parseInt(temp.substring(i+2,i+4).toString(),16);
            j=j+1;
            //map.put(id_col.get(j+1),new Tupel_tv7(id_col.get(j+1),null));
        }




    }


    @Override
    public void total(List<String> list, List<Parametr> parametrList, int sys){

        StringBuilder temp=new StringBuilder();
        list.forEach(p-> temp.append(p));
        System.out.println("\nStringBuilder = "+temp.toString());

        AscServiceImpl ascService=new AscServiceImpl();
        int i1=12;
        String str="";
        for(Parametr p:parametrList){

            str=temp.substring(i1,i1+p.getSize()*2).toString();
            System.out.println(p.getNameString()+" : str= "+ str);

            if (p.getType().equals("unsigned char")) {
                p.setValue(Integer.toString(Integer.parseInt(str,16)));
                System.out.println(" Параметр "+p.getNameString()+" = " + p.getValue());
            }

            if (p.getType().equals("unsigned short")){
                p.setValue(new BigDecimal(Integer.parseInt(l2b(str),16)).setScale(0, RoundingMode.HALF_EVEN).toString());
                System.out.println("Параметр "+p.getNameString()+" = " + p.getValue());
            }

            if (p.getType().equals("unsigned long")){
                p.setValue(new BigDecimal(Integer.parseInt(l2b(str),16)).setScale(0, RoundingMode.HALF_EVEN).toString());
                System.out.println("Параметр unsigned long : "+p.getNameString()+" = "+ Long.parseLong(l2b(str), 16));
                //System.out.println("Параметр "+p.getNameString()+" = " + p.getValue());
            }

            if (p.getType().equals("double")) {
                double m_ = Double.longBitsToDouble(Long.parseLong(l2b(str), 16));
                //System.out.println("парамметр : "+p.getName());
                //System.out.println("строка : "+str);
               // System.out.println(" double m_ : "+ m_);


                if (Double.isNaN(m_)){
                    p.setValue(null);
                    i1=i1+p.getSize()*2;
                    continue;
                }
                else {
                    String regularExpression1="^(M\\d_|V\\d_|dM)";
                    String regularExpression2="^(t\\d_|dt|tx|tнв)";



                    if(p.getName().contains("Q")) {

                        p.setValue(new BigDecimal(Double.longBitsToDouble(Long.parseLong(l2b(str), 16))/4.1868).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());

                    }
                    else if(p.getName().contains("P")){
                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())*10.197162).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }
                    else if (Pattern.compile(regularExpression1).matcher(p.getName()).find()){
                        p.setValue(new BigDecimal(Double.longBitsToDouble(Long.parseLong(l2b(str), 16))).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }

                    else if (Pattern.compile(regularExpression2).matcher(p.getName()).find()){
                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }




                }

            }


            i1=i1+p.getSize()*2;
        }


        for(Parametr p:parametrList){

            System.out.print("\n-> "+p.getNameString()+" = " + p.getValue());
        }

    }





    @Override
    public void day(List<String> list, List<Parametr> parametrList, int sys){

        StringBuilder temp=new StringBuilder();
        list.forEach(p-> temp.append(p));
        System.out.print("\nstr = "+temp.toString());
        String in = "0148" +
                "00DA 8-0027 12-01 14-0C 16-17 18-12 20-FB2242C0 28-F1C13F61" +
                "36-AA3E42CF 44-9BA842C7 52-0A844245 60-69B43F5A  68-400042C8 76-06ED42C6 84-00007FF0" +
                "92-00007FF0 100-00007FF0 108-00007FF0 116-00007FF0 124-00007FF0 132-00007FF0 `140-00007FF0 148-00007FF0 156-00007FF0 164-00007FF0 172-00007FF0 " +
                "180-00007FF0 188-00007FF0 196-00007FF0 204-00007FF0 212-00007FF0 220-00007FF0 228-00007FF0 236-EBC0423C 244-5D203F4A 252-702A419F 260-00007FF0" +
                "268-00007FF0 276-00180000 284-00007FF0 292-00007FF0 300-00007FF0 308-00007FF0 316-00007FF0 324-00007FF0 332-00007FF0 340-00007FF0 348-0000 0000 " +
                "00007FF0 00000000 00000000 00000000 02840000 00000000 00000600 01000000 0B040000 95210000 00000000 0000A5";
        AscServiceImpl ascService=new AscServiceImpl();
        int i1=12;
        String str="";
        for(Parametr p:parametrList){
            System.out.println();

            str=temp.substring(i1,i1+p.getSize()*2).toString();

            System.out.println ("Пар :: "+p.getNameString()+" стр :: "+str );

            if (p.getType().equals("unsigned char")) {
                p.setValue(Integer.toString(Integer.parseInt(str,16)));
                System.out.print("\nПараметр "+p.getNameString()+" = " + p.getValue());
            }

            if (p.getType().equals("unsigned short")){
                p.setValue(new BigDecimal(Integer.parseInt(l2b(str),16)).setScale(2, RoundingMode.HALF_EVEN).toString());
                System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
            }

            if (p.getType().equals("float")) {


                float m_  =Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue());
                System.out.println("парамметр : "+p.getName());
                System.out.println("строка : "+str);
                System.out.println("float m_ : "+ m_);


                if (Float.isNaN(m_)){
                    p.setValue(null);
                    i1=i1+p.getSize()*2;
                    continue;
                }
                else {
                    String regularExpression1="^(M\\d_|V\\d_|dM)";
                    String regularExpression2="^(t\\d_|dt|tx|tнв)";
                    String regularExpression3="^(ВНР|ВОС)";



                    if(p.getName().contains("Q")) {

                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())/4.1868).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());

                    }
                    else if(p.getName().contains("P")){
                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())*10.197162).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }
                    else if (Pattern.compile(regularExpression1).matcher(p.getName()).find()){
                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }

                    else if (Pattern.compile(regularExpression2).matcher(p.getName()).find()){
                        p.setValue(new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(str), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN).toString());
                        System.out.print("\nПареметр "+p.getNameString()+" = " + p.getValue());
                    }






                }

            }


            i1=i1+p.getSize()*2;
            }


            for(Parametr p:parametrList){

                System.out.print("\n-> "+p.getNameString()+" = " + p.getValue());
            }

    }




    @Override
    public void day_old(List<String> list, List<Parametr> parametrList, int sys){

        StringBuilder temp=new StringBuilder();
        list.forEach(p-> temp.append(p));
        System.out.print("\nstr = "+temp.toString());
        String in = "0148" +
                "00DA 8-0027 12-01 14-0C 16-17 18-12 20-FB2242C0 28-F1C13F61" +
                "36-AA3E42CF 44-9BA842C7 52-0A844245 60-69B43F5A  68-400042C8 76-06ED42C6 84-00007FF0" +
                "92-00007FF0 100-00007FF0 108-00007FF0 116-00007FF0 124-00007FF0 132-00007FF0 `140-00007FF0 148-00007FF0 156-00007FF0 164-00007FF0 172-00007FF0 " +
                "180-00007FF0 188-00007FF0 196-00007FF0 204-00007FF0 212-00007FF0 220-00007FF0 228-00007FF0 236-EBC0423C 244-5D203F4A 252-702A419F 260-00007FF0" +
                "268-00007FF0 276-00180000 284-00007FF0 292-00007FF0 300-00007FF0 308-00007FF0 316-00007FF0 324-00007FF0 332-00007FF0 340-00007FF0 348-0000 0000 " +
                "00007FF0 00000000 00000000 00000000 02840000 00000000 00000600 01000000 0B040000 95210000 00000000 0000A5";
        AscServiceImpl ascService=new AscServiceImpl();

        int countByte=(int) Long.parseLong(temp.substring(4,8).toString(), 16);
        System.out.print("\nКоличество байт = "+countByte);

        int MM= Integer.parseInt(temp.substring(12,14).toString(),16);
        System.out.print("\nДень = "+MM);
        int dd  =Integer.parseInt(temp.substring(14,16).toString(),16);
        System.out.print("\nМесяц = "+dd);
        int HH  =Integer.parseInt(temp.substring(16,18).toString(),16);
        System.out.print("\nЧас  = "+HH);
        int uu  =Integer.parseInt(temp.substring(18,20).toString(),16);
        System.out.println("\nГод = "+uu);




        List<String> id_col = new ArrayList<>(Arrays.asList("t1_tv1","P1_tv1","V1_tv1","M1_tv1","t2_tv1","P2_tv1","V2_tv1","M2_tv1","t3_tv1","P3_tv1","V3_tv1","M3_tv1","t1_tv2","P1_tv2","V1_tv2",
                "M1_tv2","t2_tv2","P2_tv2","V2_tv2","M2_tv2","t3_tv2","P3_tv2","V3_tv2","M3_tv2",
                "tнв_tv1","tx_tv1","Px_tv1","dt_tv1","dM_tv1","Qтв_tv1","Q12_tv1","Qг_tv1","ВНР_tv1","ВОС_tv1","tнв_tv2","tx_tv2","Px_tv2","dt_tv2","dM_tv2","Qтв_tv2","Q12_tv2","Qг_tv2","ВНР_tv2","ВОС_tv2"));

        Map<String,Tupel_tv7> map=new HashMap<String,Tupel_tv7>();
        id_col.forEach(p-> System.out.println(p));

        System.out.println("\nPазмер : "+ id_col.size());




        for(int i=20; i<349; i=i+8){
            System.out.print(i+" ");
        }


        int j=0;
        //  for(int i=20; i<212; i=i+8){
      /*  String regularExpression_P="^P[d]*0D0A$";


        Pattern pattern = Pattern.compile(regularExpression1);
        Matcher match = pattern.matcher(temp1.toString().replace(" ", ""));

        if(match.find()){*/

        float val;
        for(int i=20; i<349; i=i+8){
            System.out.println("i= " + i + " j= " + j);
            float m_  =Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i,i+8).toString()), 16).intValue());
            System.out.println("строка : "+temp.substring(i,i+8).toString());
            //System.out.println("Значение = "+m_);
            if (Float.isNaN(m_)&id_col.get(j).contains("ВНР")){
                map.put(id_col.get(j),new Tupel_tv7(id_col.get(j),null));
                map.put(id_col.get(j+1),new Tupel_tv7(id_col.get(j+1),null));
                j=j+2;
                continue;
            }
            if(id_col.get(j).contains("ВНР")){
                int vnr= Integer.parseInt(temp.substring(i,i+4).toString(),16);

                System.out.println("Значение "+id_col.get(j)+" = "+vnr);

                //map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Integer.parseInt(temp.substring(i,i+4).toString(),16)).setScale(2, RoundingMode.HALF_EVEN))));
                map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(vnr).setScale(2, RoundingMode.HALF_EVEN))));
                int vos= Integer.parseInt(temp.substring(i+4,i+8).toString(),16);
                System.out.println("Значение "+id_col.get(j+1)+" = "+vos);

                map.put(id_col.get(j+1), new Tupel_tv7(id_col.get(j+1), (new BigDecimal(vos).setScale(2, RoundingMode.HALF_EVEN))));

                j=j+2;
                continue;

            }


            if (Float.isNaN(m_)){
                // System.out.println(id_col.get(j)+ " Значение NaN");
                //map.put(id_col.get(j),new Tupel_tv7(id_col.get(j),(new BigDecimal("0.0").setScale(2, RoundingMode.HALF_EVEN))));
                map.put(id_col.get(j),new Tupel_tv7(id_col.get(j),null));
            }
            else {
                String regularExpression1="^(M\\d_|V\\d_|dM)";
                String regularExpression2="^(t\\d_|dt|tx|tнв)";

                //Pattern pattern1 = Pattern.compile(regularExpression1);
                //Pattern pattern1 = Pattern.compile(regularExpression1);
                //Matcher match = pattern.matcher(id_col.get(j));
                /*if (match.find()) {
                    System.out.println("Нaйдено соответствие : "+ id_col.get(j));
                }*/


                if(id_col.get(j).contains("Q")) {
                    if(sys==1){map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())/4.1868).setScale(3, RoundingMode.HALF_EVEN))));}
                    if(sys==0){map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN))));}
                    System.out.println(id_col.get(j) + " : " + map.get(id_col.get(j)).getValue().toString() + " ::: " + "i= " + i + " j= " + j);
                }
                else if(id_col.get(j).contains("P")){
                    if(sys==1){map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())*10.197162).setScale(2, RoundingMode.HALF_EVEN))));}
                    if(sys==0){map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())).setScale(2, RoundingMode.HALF_EVEN))));}
                    System.out.println(id_col.get(j) + " : " + map.get(id_col.get(j)).getValue().toString() + " ::: " + "i= " + i + " j= " + j);
                }
                else if (Pattern.compile(regularExpression1).matcher(id_col.get(j)).find()){
                    map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())).setScale(3, RoundingMode.HALF_EVEN))));
                    System.out.println(id_col.get(j) + " : " + map.get(id_col.get(j)).getValue().toString() + " ::: " + "i= " + i + " j= " + j);
                }

                else if (Pattern.compile(regularExpression2).matcher(id_col.get(j)).find()){
                    map.put(id_col.get(j), new Tupel_tv7(id_col.get(j), (new BigDecimal(Float.intBitsToFloat(Integer.valueOf(l2b(temp.substring(i, i + 8).toString()), 16).intValue())).setScale(2, RoundingMode.HALF_EVEN))));
                    System.out.println(id_col.get(j) + " : " + map.get(id_col.get(j)).getValue().toString() + " ::: " + "i= " + i + " j= " + j);
                }




            }
            j = j + 1;
        }

        id_col.forEach(p-> {
            if (map.get(p).getValue()!=null){
                System.out.println(p+" : "+map.get(p).getValue().toString());}
            else{
                System.out.println(p+" : значение null ");}


        });




    }

    public void test_double(){


    }


    public String l2b (String str) {
        StringBuilder str_out = new StringBuilder();
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ", "").split("(?<=\\G.{4})")));
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++){
            str_out.append(list.get(i));
        }
        return str_out.toString();
    }


    public void temp_match(){

    }

}
