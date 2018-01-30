package ru.javabegin.training.tv7.ASCII;

public class TestASCII {

    void hexToFloat(String hex){


    }


    void send_ASCII(){

    }

    void asc_dec_sumbol(){
    }

    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }




}
