package ru.javabegin.training.vkt7.reports;

import org.springframework.stereotype.Service;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Николай on 29.10.2017.
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService{

    @Override
    public List<DataObject> getDataObject (List<Operation> operationList){
        List<DataObject> dataList=new ArrayList<>();

        for(Operation operation:operationList){

            List<Measurements> measurementsList=new ArrayList<>();
            measurementsList.addAll(operation.getMeasurementsSet());
            List<String> id_col = new ArrayList<>();
          DataObject dataItem=new DataObject();
            Map<String,Tupel> map=new HashMap<String,Tupel>();
            Tupel tupel=new Tupel();

            for (Measurements m:measurementsList){


                if(m.getText().equals("t1 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("t2 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("t3 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("V1 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("V2 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("V3 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M1 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M2 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M3 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("P1 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("P2 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Mг Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qо Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qг Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }

                if(m.getText().equals("dt Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }

                if(m.getText().equals("tх")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("ta")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }

                if(m.getText().equals("BНP Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("BOC Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G1 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G2 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G3 Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }


                if(m.getText().equals("t1 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("t2 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("t3 Тв2")){

//
                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );

                }
                if(m.getText().equals("V1 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("V2 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("V3 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M1 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M2 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("M3 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("P1 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("P2 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Mг Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qо Тв2")){


                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qг Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))) );
                }

                if(m.getText().equals("dt Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))) );
                }


                if(m.getText().equals("BНP Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("BOC Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G1 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G2 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("G3 Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))) );
                }


                System.out.println("map.size= "+map.size());
                System.out.println("id_col.size= "+id_col.size());

            }

            dataItem.setOptionalValues(map);
            dataItem.setData(operation.getChronological());
            dataList.add(dataItem);


        }

        System.out.println("dataList.size= "+dataList.size());


        return dataList;
    }
}
