package ru.javabegin.training.vkt7.propert;

import ru.javabegin.training.vkt7.propert.entities.Properts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Николай on 05.09.2017.
 */
public class Properts_ready_Impl implements Properts_ready{



    @Override
    //public List<Properts> prop_ready (List<Properts> propertsList, List<Properts> properts)
    public List<Properts> prop_ready (List<Properts> prop_common, List<Properts> prop_specification) {
        Properts prop_temp;
        Properts prop_temp1;

        List<Properts> listProperts = new ArrayList<>();

        for (int i = 0; i < prop_common.size(); i++) {
            prop_temp = prop_common.get(i);

            if (prop_temp.getId_ed() != 0) {
                 for (int j=0;j< prop_specification.size();j++){
                    prop_temp1=prop_specification.get(j);
                    if (prop_temp.getId_ed()==prop_temp1.getId()){
                        prop_temp.setEd(prop_temp1.getEd());
                    }
                }
            }

            if (prop_temp.getId_znak() != 0) {
                for (int j=0;j< prop_specification.size();j++){
                    prop_temp1=prop_specification.get(j);

                    if (prop_temp.getId_znak()==prop_temp1.getId()){
                        prop_temp.setZnak(prop_temp1.getZnak());
                    }
                }
            }


        }

     return prop_common;



        }
    }