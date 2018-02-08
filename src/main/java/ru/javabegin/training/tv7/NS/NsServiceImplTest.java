package ru.javabegin.training.tv7.NS;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NsServiceImplTest {

    @Test
    public void nsT() throws Exception {
        NsServiceImpl nsService=new NsServiceImpl();
        List<String> list=nsService.nsT("80");
        list.forEach(p->System.out.println(p+" "));
    }

    @Test
    public void nsSE() throws Exception {

        NsServiceImpl nsService=new NsServiceImpl();
        List<String> list= nsService.nsSE("0286");
        list.forEach(p->System.out.println(p+" "));
    }

}