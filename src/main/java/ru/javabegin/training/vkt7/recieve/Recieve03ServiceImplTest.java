package ru.javabegin.training.vkt7.recieve;

import org.junit.Test;

import static org.junit.Assert.*;

public class Recieve03ServiceImplTest {
    @Test
    public void hexToASCII() throws Exception {
        Recieve03ServiceImpl recieve03Service=new Recieve03ServiceImpl();
        //hex="30 30 32 34 37 37 39 32";
        String ss=recieve03Service.hexToASCII("3030 32 3437 37 39 32");
        System.out.println("Идентификатор ="+ss);
    }

}