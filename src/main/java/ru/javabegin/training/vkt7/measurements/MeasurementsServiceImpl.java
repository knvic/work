package ru.javabegin.training.vkt7.measurements;

import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

/**
 * Created by Николай on 06.09.2017.
 */
public class MeasurementsServiceImpl implements MeasurementsService {


    @Override
    public List<Object> current_command  (List<Properts> active_items){

        Properts temp;
        Measurements temp1;

        //List<Integer> list = new ArrayList<>(0,1,2,9,10,14,15,16,19,20,21,22,23,24,31,32,36,41,42,43,77,78);
        int[] curr = new int[]{0,1,2,9,10,14,15,16,19,20,21,22,23,24,31,32,36,41,42,43,77,78};
        List<Measurements> list_current= new ArrayList<>();
        for (int i=0;i<active_items.size();i++){
            for(int j=0;j<curr.length;j++)
                if (active_items.get(i).getId()==curr[j]){
                    temp=active_items.get(i);
                    Measurements item=new Measurements(active_items.get(i).getId(),active_items.get(i).getName(),active_items.get(i).getText(),
                            active_items.get(i).getEd(),active_items.get(i).getZnak(),active_items.get(i).getSize());
                    list_current.add(item);

                    break;
                }

        }

        String temp_command="";
        for (int i=0;i<list_current.size();i++){
            temp1= list_current.get(i);
            temp_command=temp_command+format("%02X", temp1.getId())+"000040"+format("%02X", temp1.getSize())+"00";
        }
        List<String> command = new ArrayList<>(Arrays.asList(temp_command.replace(" ", "").split("(?<=\\G.{2})")));

List<Object> current = new ArrayList<>();
        current.add(command);
        current.add(list_current);


        return current;
    }

    @Override
    public List<Measurements> current (List<Properts> active_items){

        Properts temp;
        Measurements temp1;
        List<String> command=new ArrayList<>();
        //List<Integer> list = new ArrayList<>(0,1,2,9,10,14,15,16,19,20,21,22,23,24,31,32,36,41,42,43,77,78);
        int[] curr = new int[]{0,1,2,9,10,14,15,16,19,20,21,22,23,24,31,32,36,41,42,43,77,78};
        List<Measurements> list_current= new ArrayList<>();
        for (int i=0;i<active_items.size();i++){
            for(int j=0;j<curr.length;j++)
            if (active_items.get(i).getId()==curr[j]){
                temp=active_items.get(i);
                Measurements item=new Measurements(active_items.get(i).getId(),active_items.get(i).getName(),active_items.get(i).getText(),
                        active_items.get(i).getEd(),active_items.get(i).getZnak(),active_items.get(i).getSize());
                        list_current.add(item);

                break;
            }

        }



        String temp_command="";
        for (int i=0;i<list_current.size();i++){
            temp1= list_current.get(i);
            temp_command=temp_command+format("%02X", temp1.getId())+"000040"+format("%02X", temp1.getSize())+"00";
        }





        return list_current;
    }


    @Override
    public List<Measurements> current_total (){
        List<Measurements> list_current= new ArrayList<>();


        return list_current;
    }
    @Override
    public List<Measurements> archive (){
        List<Measurements> list_archive= new ArrayList<>();


        return list_archive;
    }

    @Override
    public List<Measurements> archive_total (){
        List<Measurements> list_archive= new ArrayList<>();


        return list_archive;
    }

    static List<String> id_2_hex (int id){

        String temp = Integer.toHexString((id | 0x40000000));
        System.out.print("\n"+temp);
        List<String> list = new ArrayList<>(Arrays.asList(temp.replace(" ","").split("(?<=\\G.{2})")));
        Collections.reverse(list);

        return list;
    }




}
