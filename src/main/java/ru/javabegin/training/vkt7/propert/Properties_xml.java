package ru.javabegin.training.vkt7.propert;

import ru.javabegin.training.vkt7.propert.entities.Entries;
import ru.javabegin.training.vkt7.propert.entities.Properts;
import ru.javabegin.training.vkt7.propert.impl.JaxbParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Николай on 21.08.2017.
 */
public class Properties_xml {

    public static void saveObject(File file, Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(o,file);
    }

    public static Object getObject(File file, Class c) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(file);

        return object;
    }

    public static LinkedList<Properts> getProperties() throws JAXBException {
        LinkedList<Properts> list = new LinkedList();
        Parser parser = (Parser) new JaxbParser();
        File file = new File("1.xml");
        Properts prop = new Properts();





        list.add(new Properts(0,"t1_1Type", "t1 Тв1",44,57,"int"));
        list.add(new Properts(1,"t2_1Type", "t2 Тв1",44,57,"int"));
        list.add(new Properts(2,"t3_1Type", "t3 Тв1",44,57,"int"));
        list.add(new Properts(3,"V1_1Type", "V1 Тв1",46,59,"int"));
        list.add(new Properts(4,"V2_1Type", "V2 Тв1",46,59,"int"));
        list.add(new Properts(5,"V3_1Type", "V3 Тв1",46,59,"int"));
        list.add(new Properts(6,"M1_1Type", "M1 Тв1",47,60,"int"));
        list.add(new Properts(7,"M2_1Type", "M2 Тв1",47,60,"int"));
        list.add(new Properts(8,"M3_1Type", "M3 Тв1",47,60,"int"));
        list.add(new Properts(9,"P1_1Type", "P1 Тв1",48,61,"int"));
        list.add(new Properts(10,"P2_1Type", "P2 Тв1",48,61,"int"));
        list.add(new Properts(11,"Mg_1TypeP", "Mг Тв1",52,65,"uns_int"));
        list.add(new Properts(12,"Qo_1TypeP", "Qо Тв1",53,66,"int"));
        list.add(new Properts(13,"Qg_1TypeP", "Qг Тв1",54,66,"int"));
        list.add(new Properts(14,"dt_1TypeP", "dt Тв1",49,57,"int"));
        list.add(new Properts(15,"tswTypeP", "tх",50,63,"int"));
        list.add(new Properts(16,"taTypeP", "ta",51,64,"int"));
        list.add(new Properts(17,"QntType_1HIP", "BНP Тв1",55,0,"int"));
        list.add(new Properts(18,"QntType_1P", "BOC Тв1",56,0,"int"));
        list.add(new Properts(19,"G1Type", "G1 Тв1",45,0,"float"));
        list.add(new Properts(20,"G2Type", "G2 Тв1",45,0,"float"));
        list.add(new Properts(21,"G3Type", "G3 Тв1",45,0,"float"));
        list.add(new Properts(22,"t1_2Type", "t1 Тв2",44,57,"int"));
        list.add(new Properts(23,"t2_2Type", "t2 Тв2",44,57,"int"));
        list.add(new Properts(24,"t3_2Type", "t3 Тв2",44,57,"int"));
        list.add(new Properts(25,"V1_2Type", "V1 Тв2",46,69,"int"));
        list.add(new Properts(26,"V2_2Type", "V2 Тв2",46,69,"int"));
        list.add(new Properts(27,"V3_2Type", "V3 Тв2",46,69,"int"));
        list.add(new Properts(28,"M1_2Type", "M1 Тв2",47,70,"int"));
        list.add(new Properts(29,"M2_2Type", "M2 Тв2",47,70,"int"));
        list.add(new Properts(30,"M3_2Type", "M3 Тв2",47,70,"int"));
        list.add(new Properts(31,"P1_2Type", "P1 Тв2",48,71,"int"));
        list.add(new Properts(32,"P2_2Type", "P2 Тв2",48,71,"int"));
        list.add(new Properts(33,"Mg_2TypeP", "Mг Тв2",52,75,"uns_int"));
        list.add(new Properts(34,"Qo_2TypeP", "Qо Тв2",53,76,"int"));
        list.add(new Properts(35,"Qg_2TypeP", "Qг Тв2",54,76,"int"));
        list.add(new Properts(36,"dt_2TypeP", "dt Тв2",49,72,"int"));
        list.add(new Properts(37,"tsw_2TypeP", "резерв",50,73,"int"));
        list.add(new Properts(38,"ta_2TypeP", "резерв",51,74,"int"));
        list.add(new Properts(39,"Qnt_2TypeHIP", "BНP Тв2",55,0,"int"));
        list.add(new Properts(40,"Qnt_2TypeP", " BOC Тв2",56,0,"int"));
        list.add(new Properts(41,"G1_2Type", "G1 Тв2",45,0,"float"));
        list.add(new Properts(42,"G2_2Type", "G2 Тв2",45,0,"float"));
        list.add(new Properts(43,"G3_2Type", "G3 Тв2",45,0,"float"));
        list.add(new Properts(44,"tTypeM", "ед. измерения по t (температуре)"));
        list.add(new Properts(45,"GTypeM", "ед. измерения по G (расходу)"));
        list.add(new Properts(46,"VTypeM", "ед. измерения по V (объему)"));
        list.add(new Properts(47,"MTypeM", "ед. измерения по M (массе)"));
        list.add(new Properts(48,"PTypeM", "ед. измерения по P (давлению)"));
        list.add(new Properts(49,"dtTypeM", "ед. измерения по dt (температуре)"));
        list.add(new Properts(50,"tswTypeM", "ед. измерения по tx (температуре)"));
        list.add(new Properts(51,"taTypeM", "ед. измерения по ta (температуре)"));
        list.add(new Properts(52,"MgTypeM", "ед. измерения по M (массе)"));
        list.add(new Properts(53,"QoTypeM", "ед. измерения по Q (теплу)"));
        list.add(new Properts(54,"QgTypeM", "ед. измерения по Q (теплу)"));
        list.add(new Properts(55,"QntTypeHIM", "ед. измерения по BНP (времени)"));
        list.add(new Properts(56,"QntTypeM", "ед. измерения по ВОС (времени)* (см. прим.)"));
        list.add(new Properts(57,"tTypeFractDiNum", "кол-во знаков после запятой для t"));
        list.add(new Properts(58,"GTypeFractDigNum1", "резерв"));
        list.add(new Properts(59,"VTypeFractDigNum1", "кол-во знаков после запятой для V по Тв1"));
        list.add(new Properts(60,"MTypeFractDigNum1", "кол-во знаков после запятой для M по Тв1"));
        list.add(new Properts(61,"PTypeFractDigNum1", "кол-во знаков после запятой для P"));
        list.add(new Properts(62,"dtTypeFractDigNum1", "кол-во знаков после запятой для dt"));
        list.add(new Properts(63,"tswTypeFractDigNum1", "кол-во знаков после запятой для tx"));
        list.add(new Properts(64,"taTypeFractDigNum1", "кол-во знаков после запятой для ta"));
        list.add(new Properts(65,"MgTypeFractDigNum1", "кол-во знаков после запятой для Mг"));
        list.add(new Properts(66,"QoTypeFractDigNum1", "кол-во знаков после запятой для Q по Тв1"));
        list.add(new Properts(67,"tTypeFractDigNum2", "резерв"));
        list.add(new Properts(68,"GTypeFractDigNum2", "резерв"));
        list.add(new Properts(69,"VTypeFractDigNum2", "ол-во знаков после запятой для V по Тв2"));
        list.add(new Properts(70,"MTypeFractDigNum2", "кол-во знаков после запятой для M по Тв2"));
        list.add(new Properts(71,"PTypeFractDigNum2", "кол-во знаков после запятой для P"));
        list.add(new Properts(72,"dtTypeFractDigNum2", "кол-во знаков после запятой для dt"));
        list.add(new Properts(73,"tswTypeFractDigNum2", "кол-во знаков после запятой для tx"));
        list.add(new Properts(74,"taTypeFractDigNum2", "кол-во знаков после запятой для ta"));
        list.add(new Properts(75,"MgTypeFractDigNum2", "кол-во знаков после запятой для Mг"));
        list.add(new Properts(76,"QoTypeFractDigNum2", "кол-во знаков после запятой для Q по Тв2"));
        list.add(new Properts(77,"NSPrintTypeM_1", "Наличие нештатной ситуации по ТВ1"));
        list.add(new Properts(78,"NSPrintTypeM_2", "Наличие нештатной ситуации по ТВ2"));
        list.add(new Properts(79,"QntNS_1", "Длительность НС по параметрам Тв1"));
        list.add(new Properts(80,"QntNS_2", "Длительность НС по параметрам Тв2"));
        list.add(new Properts(81,"DopInpImpP_Type", "DI",67,0,"float"));
        list.add(new Properts(82,"P3P_Type", "P3"));



        Entries entries = new Entries();
        entries.setEntry(list);
        Properties_xml.saveObject(file, entries);

        return list;
    }


    public static LinkedList<Properts> getProperties_from_xml() throws JAXBException {
        List<Properts> list = new LinkedList();
        Parser parser = (Parser) new JaxbParser();
        File file = new File("1.xml");
        Properts prop = new Properts();

/*
        Entries entries = new Entries();

        System.out.println("массив list::"+list.size());
        list.forEach(System.out::print);
        System.out.println("Обнуляем массив::");
        list=null;
        entries=null;
        //System.out.println("массив::"+list.size());
       //list.forEach(System.out::print);*/


        Entries entries1 =(Entries)getObject(file,Entries.class);

   LinkedList<Properts> list1=entries1.getEntry();
        System.out.println("массив list1::"+list1.size());
        list1.forEach(System.out::print);
        System.out.println();
        list1
                .stream()
                .filter(p->p.getId()==2)
                .forEach(System.out::println);


        return list1;


    }

}
