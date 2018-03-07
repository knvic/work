package ru.javabegin.training.test_StreamAPI;

import org.junit.Before;
import org.junit.Test;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StreamAPI_sort_delTest {

    @Test
    public void sort() {

        List<Operationtv7> list=new ArrayList<>();
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

        LocalDateTime l=LocalDateTime.now();

        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(l.minusDays(4));

        list.add(new Operationtv7(2, "45","234",auxiliaryService.localDateTime_TimeStamp(l.minusDays(14))) );
        list.add(new Operationtv7(30, "ert","вап",auxiliaryService.localDateTime_TimeStamp(l.minusDays(75))) );
        list.add(new Operationtv7(6, "ее","ее",auxiliaryService.localDateTime_TimeStamp(l.minusDays(3))) );
        list.add(new Operationtv7(88, "pp","hg",auxiliaryService.localDateTime_TimeStamp(l.minusDays(7))) );
        list.add(new Operationtv7(9, "4h","gh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(9))) );
        list.add(new Operationtv7(50, "fgntht","2ghgh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(4))) );

        StreamAPI_sort_del streamAPI_sort_del= new StreamAPI_sort_del();
        streamAPI_sort_del.sort(list);
    }






    @Test
    public void del() {

        List<Operationtv7> list=new ArrayList<>();
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

        LocalDateTime l=LocalDateTime.now();

        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(l.minusDays(4));

        list.add(new Operationtv7(30, "ert","вап",auxiliaryService.localDateTime_TimeStamp(l.minusDays(75))) );
        list.add(new Operationtv7(2, "45","234",auxiliaryService.localDateTime_TimeStamp(l.minusDays(14))) );
        list.add(new Operationtv7(6, "ее","ее",auxiliaryService.localDateTime_TimeStamp(l.minusDays(3))) );
        list.add(new Operationtv7(88, "pp","hg",auxiliaryService.localDateTime_TimeStamp(l.minusDays(7))) );
        list.add(new Operationtv7(9, "4h","gh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(9))) );
        list.add(new Operationtv7(50, "fgntht","2ghgh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(4))) );

        StreamAPI_sort_del streamAPI_sort_del= new StreamAPI_sort_del();
        streamAPI_sort_del.del(list);
    }

    @Test
    public void max_min() {
        List<Operationtv7> list=new ArrayList<>();
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();

        LocalDateTime l=LocalDateTime.now();

        Timestamp ts=auxiliaryService.localDateTime_TimeStamp(l.minusDays(4));

        list.add(new Operationtv7(30, "ert","вап",auxiliaryService.localDateTime_TimeStamp(l.minusDays(75))) );
        list.add(new Operationtv7(2, "45","234",auxiliaryService.localDateTime_TimeStamp(l.minusDays(14))) );
        list.add(new Operationtv7(6, "ее","ее",auxiliaryService.localDateTime_TimeStamp(l.minusDays(3))) );
        list.add(new Operationtv7(88, "pp","hg",auxiliaryService.localDateTime_TimeStamp(l.minusDays(7))) );
        list.add(new Operationtv7(9, "4h","gh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(9))) );
        list.add(new Operationtv7(50, "fgntht","2ghgh4",auxiliaryService.localDateTime_TimeStamp(l.minusDays(4))) );

        StreamAPI_sort_del streamAPI_sort_del= new StreamAPI_sort_del();
        streamAPI_sort_del.max_min(list);
    }
}