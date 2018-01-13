package ru.javabegin.training.tv7.auxillary;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.*;

public class AuxServiceImplTest {


    @Test
    public void localDate_to_tv7() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();
        auxService.localDate_to_tv7(LocalDateTime.now());

    }

    @Test
    public void date_to_tv7() throws Exception {
        AuxServiceImpl auxService=new AuxServiceImpl();
        Date data=new Date();
        auxService.Date_to_tv7(data);
        //0D0D177E20000
    }

}