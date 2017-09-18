package ru.javabegin.training.vkt7.send;

/**
 * Created by Николай on 20.08.2017.
 */
public class Main_test_send {
    public static void main(String[] args) {
        Send10ServiceImpl service=new Send10ServiceImpl();
        //service.s_3FFF_n("01");
        service.s_3FFF("01");
    }
}
