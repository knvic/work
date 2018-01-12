package ru.javabegin.training.tv7.send;

import org.junit.Test;
import ru.javabegin.training.tv7.ASCII.AscServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

public class ModBusServiceImplTest {
    @Test
    public void typeUnit() throws Exception {
        ModBusServiceImpl modBusService=new ModBusServiceImpl();
        AscServiceImpl ascService=new AscServiceImpl();

        List<String> commamd=modBusService.typeUnit(0);
        commamd.forEach(p->System.out.print(p+" "));

        List<String> commamdAsc=ascService.enctypt(commamd);
        commamdAsc.forEach(p->System.out.print(p+" "));
    }

}