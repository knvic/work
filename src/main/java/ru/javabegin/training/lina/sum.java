package ru.javabegin.training.lina;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class sum {

    public static void main(String[] args) throws IOException {


        int d1;
        int d2;
        int sum;

        String s1;
        String s2;

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите значение первого монстрика d1: ");
        s1 = kb.readLine();
        System.out.println("Введите значение второго монстрика d2: ");
        s2 = kb.readLine();


        d1 = Integer.parseInt(s1);
        d2 = Integer.parseInt(s2);

        sum = d1 + d2;


        System.out.println("Сумма =: " + sum);


    }
}
