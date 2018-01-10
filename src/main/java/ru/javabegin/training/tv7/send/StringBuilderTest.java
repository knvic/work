package ru.javabegin.training.tv7.send;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringBuilderTest {

    public static void main(String args[]){
        String str="3A 30 30 34 38 30 41 36 44 30 30 30 3430 3030 30 30 3030 30 30 30 30 30 30 30 30 31 33 43 0D 0A  ";
        String str1="3A D0  440D 0A  ";
        StringBuilder strBuffer = new StringBuilder(str);
        int startIndex = 0;
        int endIndex = 2;
        char[] buffer = new char[endIndex-startIndex];
        strBuffer.getChars(startIndex, endIndex, buffer, 0);
        System.out.println(buffer);
        String s=buffer.toString();
        System.out.println(s);
        if (s.equals("3A")){
            System.out.println("Совпадает");
        }

        String regularExpression1="^3A[A-Z\\d]*0D0A$";

        String str_temp=strBuffer.toString().replace(" ", "");
        Pattern pattern = Pattern.compile(regularExpression1);
        Matcher match = pattern.matcher(str_temp);

      if(match.find()){
          System.out.println("Найдено");
      }


    }
}
