package ru.javabegin.training.vkt7.reports;

import org.junit.Test;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static org.junit.Assert.*;

public class ReportServiceImplTest {
    @Test
    public void getCalculations() throws Exception {
        AuxiliaryServiceImpl auxiliaryService=new AuxiliaryServiceImpl();
        List<DataObject> dataObjectList=new ArrayList<>();

        for(int i=0; i<3; i++) {
                DataObject object = new DataObject();
            Map<String,Tupel> map=new HashMap<String,Tupel>();
            Tupel tupel=new Tupel();
            if(i==0) {
                map.put("t1", new Tupel("t1", (new BigDecimal("0.987").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2", new Tupel("t2", (new BigDecimal("0.56").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v1", new Tupel("v1", (new BigDecimal("0.987").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v2", new Tupel("v2", (new BigDecimal("0.9995464").setScale(2, RoundingMode.HALF_EVEN))));
            }
            if(i==1) {
                map.put("t1", new Tupel("t1", (new BigDecimal("0.234").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2", new Tupel("t2", (new BigDecimal("0.3456").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v1", new Tupel("v1", (new BigDecimal("0.43563").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v2", new Tupel("v2", (new BigDecimal("0.5464").setScale(2, RoundingMode.HALF_EVEN))));
            }
            if(i==2) {
                map.put("t1", new Tupel("t1", (new BigDecimal("6.234").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("t2", new Tupel("t2", (new BigDecimal("7.56756").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v1", new Tupel("v1", (new BigDecimal("8.456").setScale(2, RoundingMode.HALF_EVEN))));
                map.put("v2", new Tupel("v2", (new BigDecimal("9.9").setScale(2, RoundingMode.HALF_EVEN))));
            }

            object.setOptionalValues(map);
            object.setData(auxiliaryService.date_TimeStamp(new Date()));
            dataObjectList.add(object);

        }

        System.out.println("Размер "+dataObjectList.size() );






    }

}