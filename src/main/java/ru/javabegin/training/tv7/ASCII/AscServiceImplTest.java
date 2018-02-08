package ru.javabegin.training.tv7.ASCII;

import org.junit.Test;
import ru.javabegin.training.tv7.LRC.LrcServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class AscServiceImplTest {
    @Test
    public void enctypt() throws Exception {

        String str="1B 03 03 26 00 12";
        List<String> list = new ArrayList<>(Arrays.asList(str.replace(" ","").split("(?<=\\G.{2})")));
        LrcServiceImpl lrcService= new LrcServiceImpl();

        list=lrcService.lrcAdd(list);
        list.forEach( p-> System.out.print(p+" "));

        AscServiceImpl ascService=new AscServiceImpl();
        list=ascService.enctypt(list);
        list.forEach( p-> System.out.print(p+" "));


    }

    @Test
    public void dectypt() throws Exception {

        AscServiceImpl ascService=new AscServiceImpl();
        //String str="3A 31 42 30 33 32 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 42 45 0D 0A";



        //запрос
        // String str="3A 30 30 34 38 30 41 36 44 30 30 30 34 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 42 33 32 0D 0A";
        //ответ
        //String str="3A 30 31 34 38 30 30 30 38 30 30 30 42 39 35 32 31 30 30 30 30 39 33 41 36 30 30 30 30 42 35 0D 0A ";

        //запрос
         //String str="3A 30 30 34 38 30 30 36 39 30 30 39 32 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 43 42 31 0D 0A";
         //после преобразования в нex 00 48 00 69 00 92 00 00 00 00 00 00 00 0C B1

        //ответ
        /*String str="3A 30 31 34 38 30 31 32 34 30 30 30 43 31 46 31 " +
                "37 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30" +
                " 30 30 30 30 30 30 30 35 30 30 30 30 30 30 34 38 30" +
                " 30 30 30 30 30 30 30 46 31 43 33 33 46 36 31 30 30" +
                " 30 30 30 30 30 30 43 43 43 44 33 46 43 43 30 30 30" +
                " 30 33 46 41 30 30 30 30 30 30 30 30 30 30 30 30 30" +
                " 34 32 31 30 30 30 30 30 30 30 30 30 30 30 34 38 30 " +
                "30 30 30 30 30 30 30 36 39 42 34 33 46 35 41 30 30 " +
                "30 30 30 30 30 30 43 43 43 44 33 46 43 43 30 30 30 " +
                "30 33 46 41 30 30 30 30 30 30 30 30 30 30 30 30 30" +
                " 34 32 31 30 30 30 30 30 30 30 30 30 30 30 34 30 30 " +
                "30 30 30 34 32 35 43 39 39 39 41 33 46 31 39 30 30 " +
                "30 30 30 30 30 30 43 43 43 44 33 46 43 43 30 30 30 " +
                "30 34 31 32 30 30 30 30 30 34 31 32 30 " +
                "30 30 30 30 34 32 43 38 30 30 30 30 34 31 41 30 " +
                "30 30 34 30 30 30 30 30 34 32 42 34 39 39 39 41"+
                "33 46 31 39 30 30 30 30 30 30 30 30 43 43 43 44"+
                "33 46 43 43 30 30 30 30 34 31 32 30 30 30 30 30"+
                "34 31 32 30 30 30 30 30"+
                "34 32 43 38 30 30 30 30 34 31 41 30 30 30 34 30"+
                "30 30 30 30 34 32 37 30 39 39 39 41 33 46 31 39"+
                "30 30 30 30 30 30 30 30 43 43 43 44 33 46 43 43"+
                "30 30 30 30 34 31 32 30 30 30 30 30 34 31 32 30"+
                "30 30 30 30 34 32 43 38 30 30 30 30 34 31 41 30"+
                "30 30 34 30 30 30 30 30 34 32 35 43 39 39 39 41"+
                "33 46 31 39 30 30 30 30"+
                "30 30 30 30 43 43 43 44 33 46 43 43 30 30 30 30"+
                "34 31 32 30 30 30 30 30 34 31 32 30 30 30 30 30"+
                "34 32 43 38 30 30 30 30 34 31 41 30 30 30 30 36"+
                "30 30 30 30 34 30 38 30 30 30 30 30 30 30 30 30"+
                "44 36 45 36 33 45 43 38 33 30 38 38 30 30 30 30"+
                "30 30 30 30 30 30 30 30 34 30 38 30 30 30 30 30"+
                "30 30 30 30"+
                "44 36 45 36 33 45 43 38 30 30 35 43 30 30 30 30"+
                "30 30 34 30 30 30 30 30 34 32 42 34 39 39 39 41"+
                "33 46 31 39 30 30 30 30 30 30 30 30 43 43 43 44"+
                "33 46 43 43"+
                "30 30 30 30 34 31 32 30"+
                "30 30 30 30 34 31 32 30"+
                "30 30 30 30 34 32 43 38 30 30 30 30 34 31 41 30"+
                "30 30 34 30 34 33 0D 0A         ";
*/

        //запрос после преобразования в нex 00 48 00 69 00 92 00 00 00 00 00 00 00 0C B1
        // ответ после преобразования в hex
        // 01 48 01 24 00 0C 1F 17 00 00 00 00 00 00 00 00 00 00 00
        // 05 00 00 00 48 00 00 00 00 F1 C3 3F 61 00 00 00 00 CC CD
        // 3F CC 00 00 3F A0 00 00 00 00 00 00 42 10 00 00 00 00 00
        // 48 00 00 00 00 69 B4 3F 5A 00 00 00 00 CC CD 3F CC 00 00
        // 3F A0 00 00 00 00 00 00 42 10 00 00 00 00 00 40 00 00 42
        // 5C 99 9A 3F 19 00 00 00 00 CC CD 3F CC 00 00 41 20 00 00
        // 41 20 00 00 42 C8 00 00 41 A0 00 40 00 00 42 B4 99 9A 3F
        // 19 00 00 00 00 CC CD 3F CC 00 00 41 20 00 00 41 20 00 00
        // 42 C8 00 00 41 A0 00 40 00 00 42 70 99 9A 3F 19 00 00 00
        // 00 CC CD 3F CC 00 00 41 20 00 00 41 20 00 00 42 C8 00 00
        // 41 A0 00 40 00 00 42 5C 99 9A 3F 19 00 00 00 00 CC CD 3F
        // CC 00 00 41 20 00 00 41 20 00 00 42 C8 00 00 41 A0 00 06
        // 00 00 40 80 00 00 00 00 D6 E6 3E C8 30 88 00 00 00 00 00
        // 00 40 80 00 00 00 00 D6 E6 3E C8 00 5C 00 00 00 40 00 00
        // 42 B4 99 9A 3F 19 00 00 00 00 CC CD 3F CC 00 00 41 20 00
        // 00 41 20 00 00 42 C8 00 00 41 A0 00 40 43 hexListIn-1 43

        //запрос
       // String str="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 39 42 0D 0A ";
        // После decrypt 00 48 00 00 00 13 00 00 00 00 00 00 00 0A 9B

        //ответ
        //String str="3A 30 31 34 38 30 30 32 36 30 30 30 41 31 37 30 32 30 31 30 30 30 33 30 30 44 35 32 45 30 30 30 30 34 30 46 37 30 30 45 35 33 36 32 30 32 31 33 32 31 38 33 31 30 34 30 30 30 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 35 32 0D 0A";
        // После decrypt 01 48 00 26 00 0A 17 02 01 00 03 00 D5 2E 00 00 40 F7 00 E5 36 20 21 32 18 31 04 00 03 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 52


        String req="00 48 00 69 00 92 00 00 00 00 00 00 00 0C B1";

        String answ="01 48 01 24 00 0C " +
                "1F 17 00 00 00 00 00 00 00 00 00 00 00 05 00 00 00 48 00 00"+
                "00 00 F1 C3 3F 61 00 00 00 00 CC CD 3F CC 00 00 3F A0 00 00" +
                "00 00 00 00 42 10 00 00 00 00 00 48 00 00 00 00 69 B4 3F 5A " +
                "00 00 00 00 CC CD 3F CC 00 00 3F A0 00 00 00 00 00 00 42 10" +
                "00 00 00 00 00 40 00 00 42 5C 99 9A 3F 19 00 00 00 00 CC CD" +
                "3F CC 00 00 41 20 00 00 41 20 00 00 42 C8 00 00 41 A0 00 40 " +
                "00 00 42 B4 99 9A 3F 19 00 00 00 00 CC CD 3F CC 00 00 41 20 " +
                "00 00 41 20 00 00 42 C8 00 00 41 A0 00 40 00 00 42 70 99 9A " +
                "3F 19 00 00 00 00 CC CD 3F CC 00 00 41 20 00 00 41 20 00 00 " +
                "42 C8 00 00 41 A0 00 40 00 00 42 5C 99 9A 3F 19 00 00 00 00 " +
                "CC CD 3F CC 00 00 41 20 00 00 41 20 00 00 42 C8 00 00 41 A0 " +
                "00 06 00 00 40 80 00 00 00 00 D6 E6 3E C8 30 88 00 00 00 00 " +
                "00 00 40 80 00 00 00 00 D6 E6 3E C8 00 5C 00 00 00 40 00 00 "+
                "42 B4 99 9A 3F 19 00 00 00 00 CC CD 3F CC 00 00 41 20 00 00 " +
                "41 20 00 00 42 C8 00 00 41 A0 00 40 43 hexListIn-1 43";


        //String str="3A 30 30 34 38 30 30 30 30 30 30 31 33 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 30 41 35 0D 0A ";


        // Чтение суточного архива за 12.01.2018 23:00:00
            String str="3A 30 30 34 38 30 41 42 34 30 30 36 44 30 30 36 33 30 30 30 36 30 30 30 43 30 30 32 37 30 31 30 43 31 37 31 32 30 30 30 30 30 30 30 31 30 30 30 30 30 30 30 30 42 41 0D 0A  ";


            List<String> list=ascService.dectypt(str);
       list.forEach( p-> System.out.print(p+" "));
        LrcServiceImpl lrcService= new LrcServiceImpl();

        System.out.println("проверка LRC : "+lrcService.lrcCheck(list));
    }


    @Test
    public void asciiToHex() throws Exception {
    AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("Результат= "+ ascService.asciiToHex("1B03"));




        System.out.println("Рез = "+Integer.parseInt("72",16));
        System.out.println(" -- - - - - -");



        System.out.println("Рез = "+Integer.parseInt("0012",16));


        String hex = Integer.toHexString(806);

        System.out.println("hex = "+hex);
        System.out.println(" -- - - - - -");

        int a=806;
        String a_s=Integer.toString(a);
        String i_to_h=format("%04X", a);
        System.out.println(" i_to_h= "+ i_to_h);


    }

    @Test
    public void asciiToHexlist() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        ascService.asciiToHexlist("1B03");


    }


    @Test
    public void toHex02X() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("toHex02X = "+ ascService.toHex02X(18));


    }

    @Test
    public void toHex04X() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("toHex04X = "+ ascService.toHex04X(806));
    }

    @Test
    public void hexToDec() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        System.out.println("hexToDec = "+ ascService.hexToDec("0326"));
        System.out.println("hexToDec = "+ ascService.hexToDec("0012"));

    }


    @Test
    public void hex2ByteArray() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();
        //[B@5faeada1

        System.out.println(ascService.hex2ByteArray("314230333033323630303132"));

    }


    @Test
    public void hex_to_ascii() throws Exception {
        AscServiceImpl ascService=new AscServiceImpl();

        System.out.println(ascService.hex_to_ascii("72"));

    }


}