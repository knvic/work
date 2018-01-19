package ru.javabegin.training.tv7.NS;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NsServiceImpl implements NsService{
    @Override
    public  StringBuilder hexToBin(String hex,int size){

        String preBin = new BigInteger(hex, 16).toString(2);
        Integer length = preBin.length();
        if (length < 8*size) {
            for (int i = 0; i < 8*size - length; i++) {
                preBin = "0" + preBin;
            }
        }
        StringBuilder str=new StringBuilder(preBin);
        return str.reverse();
    }


    @Override
    public List<String> nsT (String hex){
        List<String> outNS = new ArrayList<>();
        StringBuilder str=hexToBin(hex,1);
       List<String> list = new ArrayList<>(Arrays.asList(str.toString().replace(" ", "").split("(?<=\\G.{1})")));
       //list.forEach(p->System.out.print(p+" "));
       if(list.get(0).equals("1")){outNS.add("t<min");}
        if(list.get(1).equals("1")){outNS.add("t>max");}
        if(list.get(2).equals("1")){outNS.add("неиспр. датчика t");}
        if(list.get(3).equals("1")){outNS.add("P<min");}
        if(list.get(4).equals("1")){outNS.add("P>max");}
        if(list.get(5).equals("1")){outNS.add("V<min");}
        if(list.get(6).equals("1")){outNS.add("V>max");}
        if(list.get(7).equals("1")){outNS.add("неиспр. или отсут. питания ВС");}
        return outNS;
    }

    @Override
    public List<String> nsTV (String hex){
        List<String> outNS = new ArrayList<>();
        StringBuilder str=hexToBin(hex,2);
        List<String> list = new ArrayList<>(Arrays.asList(str.toString().replace(" ", "").split("(?<=\\G.{1})")));
        //list.forEach(p->System.out.print(p+" "));
        if(list.get(0).equals("1")){outNS.add("dt");}
        if(list.get(1).equals("1")){outNS.add("dM");}
        if(list.get(2).equals("1")){outNS.add("Qтв");}
        if(list.get(3).equals("1")){outNS.add("tх<min");}
        if(list.get(4).equals("1")){outNS.add("tх>max");}
        if(list.get(5).equals("1")){outNS.add("неиспр. датчика tх");}
        if(list.get(6).equals("1")){outNS.add("tнв<min");}
        if(list.get(7).equals("1")){outNS.add("tнв>max");}
        if(list.get(8).equals("1")){outNS.add("неиспр. датчика tнв");}
        if(list.get(9).equals("1")){outNS.add("Q12");}
        if(list.get(10).equals("1")){outNS.add("Qг");}
        if(list.get(11).equals("1")){outNS.add("Px<min");}
        if(list.get(12).equals("1")){outNS.add("Px>max");}

        return outNS;
    }
    @Override
    public List<String> nsDP (String hex){
        List<String> outNS = new ArrayList<>();
        StringBuilder str=hexToBin(hex,1);
        List<String> list = new ArrayList<>(Arrays.asList(str.toString().replace(" ", "").split("(?<=\\G.{1})")));
      //  list.forEach(p->System.out.print(p+" "));
        if(list.get(0).equals("0")){outNS.add("ДП<min");}
        if(list.get(0).equals("1")){outNS.add("ДП>max");}
        return outNS;
    }

    @Override
    public List<String>  nsSE (String hex){
        List<String> outNS = new ArrayList<>();
        StringBuilder str=hexToBin(hex,2);
        List<String> list = new ArrayList<>(Arrays.asList(str.toString().replace(" ", "").split("(?<=\\G.{1})")));
       // list.forEach(p->System.out.print(p+" "));
        if(list.get(0).equals("1")){outNS.add("нажатие кнопки «Доступ»");}
        if(list.get(1).equals("1")){outNS.add("отсутствие сетевого питания");}
        if(list.get(2).equals("1")){outNS.add("LB");}
        if(list.get(3).equals("1")){outNS.add("перезапуск процессора");}
        if(list.get(4).equals("1")){outNS.add("ускоренный режим");}
        if(list.get(5).equals("1")){outNS.add("ресурс батареи близок к окончанию");}
        if(list.get(6).equals("1")){outNS.add("перевод часов");}
        if(list.get(7).equals("1")){outNS.add("LCD");}
        if(list.get(8).equals("1")){outNS.add("нажатие клавиатуры");}
        if(list.get(9).equals("1")){outNS.add("Mdb");}
        if(list.get(10).equals("1")){outNS.add("сигнализация");}


        return outNS;
    }

}
