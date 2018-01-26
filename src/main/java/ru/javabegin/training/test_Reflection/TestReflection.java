package ru.javabegin.training.test_Reflection;

import java.lang.reflect.Field;

public class TestReflection {

    public void test1(String nameClass) throws ClassNotFoundException {

        //Рефлексия

        System.out.println("Рефлексия ---------------------");
        //Class mClassObject = Operationtv7T.class;

        Class mClassObject = Class.forName("ru.javabegin.training.tv7.entity.Operationtv7T");

        String fullClassName = mClassObject.getName();
        System.out.println("fullClassName "+fullClassName);

        String justClassName = mClassObject.getSimpleName();
        System.out.println("justClassName "+justClassName);


        // Field field = mClassObject.getField("kt3Tv2");
        // String fieldName = field.getName();
        // System.out.println("fieldName "+fieldName);

        //Field[] fields = mClassObject.getFields();
        Field[] fields = mClassObject.getDeclaredFields();
        System.out.println("размер :: "+fields.length);
        for(Field f:fields){
            //Field field = mClassObject.getField("fieldName");
            String fName = f.getName();
            System.out.println("Название поля :  -->"+fName);
        }



////

    }
}
