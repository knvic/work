package ru.javabegin.training.tv7.ASCII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class AscServiceImpl implements AscService {

    @Override
    public String asciiToHex(String asciiValue){
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }


    @Override
    public List<String> asciiToHexlist(String asciiValue){
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        List<String> list = new ArrayList<>(Arrays.asList(hex.toString().replace(" ","").split("(?<=\\G.{2})")));
        return list;
    }

    @Override
    public String toHex02X(int n){
        String hex=format("%02X", n);
      return  hex;
    }
    @Override
    public String toHex04X(int n){
        String hex=format("%04X", n);
        return  hex;
    }

    @Override
    public int hexToDec(String hex){
        int dec=Integer.parseInt(hex,16);



        return  dec;
    }


    @Override
    public byte[] hex2ByteArray( String hexString ) {
        String hexVal = "0123456789ABCDEF";
        byte[] out = new byte[hexString.length() / 2];

        int n = hexString.length();

        for( int i = 0; i < n; i += 2 ) {
            //make a bit representation in an int of the hex value
            int hn = hexVal.indexOf( hexString.charAt( i ) );
            int ln = hexVal.indexOf( hexString.charAt( i + 1 ) );

            //now just shift the high order nibble and add them together
            out[i/2] = (byte)( ( hn << 4 ) | ln );
        }

        return out;
    }

    @Override
   public  String hex_to_ascii(String hex){

        //String oct = "3A";
        // String oct = "3740";  㝀
        String oct = "37";

        int dec = Integer.parseInt(oct, 16);
        System.out.println("В десятичной с.с. будет " + dec);
        System.out.println("Знак ASCII будет " + (char) dec);
        System.out.println("----");
        char ff =(char)Integer.parseInt(oct, 16);
        System.out.println("ff = "+ff);

        String ascii= String.valueOf(ff);

        return ascii;
    }






    @Override
    public void toHex(String str){
    }
    @Override
    public List<String> toTv7(String str){
        List<String> list = new ArrayList<>();
        list.add(asciiToHex(str.substring(0,1)));
        list.add(asciiToHex(str.substring(1)));
        return list;
    }




}
