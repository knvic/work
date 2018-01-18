package ru.javabegin.training.tv7.initDataClass;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class InitDataTest {
    @Test
    public void initDay() throws Exception {
        InitData initData=new InitData();
        List<Parametr> parametrList=initData.initDay();
        for(Parametr p:parametrList){

            System.out.println(p);
        }

        System.out.println("размер = "+ parametrList.size());
    }

}