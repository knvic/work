package ru.javabegin.training.vkt7.reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.entities.Measurements;
import ru.javabegin.training.vkt7.entities.Operation;

import javax.swing.table.DefaultTableModel;
import java.io.DataOutput;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Николай on 29.10.2017.
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService{

@Autowired
    AuxiliaryService auxiliaryService;


    @Override
    public void createReport1(List<ReportCustomers> list){


        final String PATH = "c:\\test_j\\";









            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(PATH+"report_new.jrxml");

                JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

                Map<String, Object> map = new HashMap<>();
                map.put("title", "Тестовый отчет");

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, dataSource);

//            JasperViewer jasperViewer = new JasperViewer(jasperPrint);
//            jasperViewer.setVisible(true);

                JasperExportManager.exportReportToPdfFile(jasperPrint, PATH+"report.pdf");
                JasperExportManager.exportReportToXmlFile(jasperPrint, PATH+"report.xml", false);
                JasperExportManager.exportReportToHtmlFile(jasperPrint, PATH+"report.html");

                JRXlsExporter exporter = new JRXlsExporter();

                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(PATH+"report.xls"));

                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setOnePagePerSheet(true);
                exporter.setConfiguration(configuration);

                exporter.exportReport();

            } catch (JRException ex) {
                ex.printStackTrace();
            }






    }


    @Override
   public void createReport(){

        DefaultTableModel tableModel= prepare_data();

      //  report.fillData();

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport("c:\\test\\template\\report2.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JRTableModelDataSource(tableModel));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), new JRTableModelDataSource(report.getTableModel()));

              //JasperViewer jasperViewer = new JasperViewer(jasperPrint);
             // jasperViewer.setVisible(true);

            LocalDateTime dateTime = LocalDateTime.now();
            System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d-MMM-uuuu:HH:mm")));

            JasperExportManager.exportReportToPdfFile(jasperPrint, "c:\\test\\report2_"+ dateTime.format(DateTimeFormatter.ofPattern("d_MMM_uuuu_HH_mm_ss"))+".pdf");
            JasperExportManager.exportReportToXmlFile(jasperPrint, "c:\\test\\report2_"+ dateTime.format(DateTimeFormatter.ofPattern("d_MMM_uuuu_HH_mm_ss"))+".xml", false);
            JasperExportManager.exportReportToHtmlFile(jasperPrint, "c:\\test\\report2_"+ dateTime.format(DateTimeFormatter.ofPattern("d_MMM_uuuu_HH_mm_ss"))+".html");

            JRXlsExporter exporter = new JRXlsExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("c:\\test\\report2_"+ dateTime.format(DateTimeFormatter.ofPattern("d_MMM_uuuu_HH_mm_ss"))+".xls"));

            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet(true);
            exporter.setConfiguration(configuration);

            exporter.exportReport();

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }




    @Override
    public DefaultTableModel prepare_data(){

    String[] columnNames = {"Id", "Name", "Department", "Email"};
    String[][] data = {
            {"111", "G Conger", " Orthopaedic", "jim@wheremail.com"},
            {"222", "A Date", "ENT", "adate@somemail.com"},
            {"333", "R Linz", "Paedriatics", "rlinz@heremail.com"},
            {"444", "V Sethi", "Nephrology", "vsethi@whomail.com"},
            {"555", "K Rao", "Orthopaedics", "krao@whatmail.com"},
            {"666", "V Santana", "Nephrology", "vsan@whenmail.com"},
            {"777", "J Pollock", "Nephrology", "jpol@domail.com"},
            {"888", "H David", "Nephrology", "hdavid@donemail.com"},
            {"999", "P Patel", "Nephrology", "ppatel@gomail.com"},
            {"101", "C Comer", "Nephrology", "ccomer@whymail.com"}
    };
    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);


    return tableModel;

}


    @Override
    public List<Object> getObject_fullround (List<Operation> operationList){
        List<DataObject> dataList=new ArrayList<>();
        List<Object> objectList=new ArrayList<>();

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
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(5, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qг Тв1")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(5, RoundingMode.HALF_EVEN))) );
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
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(5, RoundingMode.HALF_EVEN))) );
                }
                if(m.getText().equals("Qг Тв2")){

                    id_col.add(m.getText());
                    map.put(m.getText(),new Tupel(m.getText(),(new BigDecimal(m.getMeasurText()).setScale(5, RoundingMode.HALF_EVEN))) );
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
            objectList.add(sort(id_col));

        }

        System.out.println("dataList.size= "+dataList.size());



        objectList.add(0,dataList);



        return objectList;
    }


    @Override
    public List<Object> getObject (List<Operation> operationList){
        List<DataObject> dataList=new ArrayList<>();
        List<Object> objectList=new ArrayList<>();

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
            objectList.add(sort(id_col));

        }

        System.out.println("dataList.size= "+dataList.size());



        objectList.add(0,dataList);



        return objectList;
    }


    @Override
    public List<Object> getObject_ns (List<Operation> operationList){
        List<DataObject> dataList=new ArrayList<>();
        List<Object> objectList=new ArrayList<>();

        for(Operation operation:operationList){

            List<Measurements> measurementsList=new ArrayList<>();
            measurementsList.addAll(operation.getMeasurementsSet());
            List<String> id_col = new ArrayList<>();
            DataObject dataItem=new DataObject();
            Map<String,Tupel> map=new HashMap<String,Tupel>();
            Tupel tupel=new Tupel();

            String qq= "НС_t1_1, НС_t2_1, НС_t3_1, НС_V1_1, НС_V2_1, НС_V3_1, НС_M1_1, НС_M2_1, НС_M3_1, НС_P1_1, НС_P2_1, НС_Mг_1, НС_Qо_1, НС_Qг_1, НС_dt_1, НС_tх, НС_ta, НС_BНP_1, НС_BOC_1, НС_G1_1, НС_G2_1, НС_G3_1, " +
                       "НС_t1_2, НС_t2_2, НС_t3_2, НС_V1_2, НС_V2_2, НС_V3_2, НС_M1_2, НС_M2_2, НС_M3_2, НС_P1_2, НС_P2_2, НС_Mг_2, НС_Qо_2, НС_Qг_2, НС_dt_2, НС_BНP_2, НС_BOC_2, НС_G1_2, НС_G2_2, НС_G3_2";

            for (Measurements m:measurementsList){


                if(m.getText().equals("t1 Тв1")){

                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t1_1");
                    map.put("НС_t1_1", new Tupel("НС_t1_1", (ns_to_bd(m.getNs()))));



                }

                if(m.getText().equals("t2 Тв1")){

                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t2_1");
                    map.put("НС_t2_1", new Tupel("НС_t2_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("t3 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t3_1");
                    map.put("НС_t3_1", new Tupel("НС_t3_1", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("V1 Тв1")){


                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V1_1");
                    map.put("НС_V1_1", new Tupel("НС_V1_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("V2 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V2_1");
                    map.put("НС_V2_1", new Tupel("НС_V2_1", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("V3 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V3_1");
                    map.put("НС_V3_1", new Tupel("НС_V3_1", (ns_to_bd(m.getNs()))));
                }
                if(m.getText().equals("M1 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M1_1");
                    map.put("НС_M1_1", new Tupel("НС_M1_1", (ns_to_bd(m.getNs()))));
                }
                if(m.getText().equals("M2 Тв1")){


                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M2_1");
                    map.put("НС_M2_1", new Tupel("НС_M2_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("M3 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M3_1");
                    map.put("НС_M3_1", new Tupel("НС_M3_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("P1 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_P1_1");
                    map.put("НС_P1_1", new Tupel("НС_P1_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("P2 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_P2_1");
                    map.put("НС_P2_1", new Tupel("НС_P2_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("Mг Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Mг_1");
                    map.put("НС_Mг_1", new Tupel("НС_Mг_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("Qо Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Qо_1");
                    map.put("НС_Qо_1", new Tupel("НС_Qо_1", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("Qг Тв1")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Qг_1");
                    map.put("НС_Qг_1", new Tupel("НС_Qг_1", (ns_to_bd(m.getNs()))));

                }

                if(m.getText().equals("dt Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_dt_1");
                    map.put("НС_dt_1", new Tupel("НС_dt_1", (ns_to_bd(m.getNs()))));

                }

                if(m.getText().equals("tх")){


                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_tх");
                    map.put("НС_tх", new Tupel("НС_tх", (ns_to_bd(m.getNs()))));




                }
                if(m.getText().equals("ta")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_ta");
                    map.put("НС_ta", new Tupel("НС_ta", (ns_to_bd(m.getNs()))));


                }

                if(m.getText().equals("BНP Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_BНP_1");
                    map.put("НС_BНP_1", new Tupel("НС_BНP_1", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("BOC Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_BOC_1");
                    map.put("НС_BOC_1", new Tupel("НС_BOC_1", (ns_to_bd(m.getNs()))));

                }
                if(m.getText().equals("G1 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G1_1");
                    map.put("НС_G1_1", new Tupel("НС_G1_1", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("G2 Тв1")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G2_1");
                    map.put("НС_G2_1", new Tupel("НС_G2_1", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("G3 Тв1")){


                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G3_1");
                    map.put("НС_G3_1", new Tupel("НС_G3_1", (ns_to_bd(m.getNs()))));


                }


                if(m.getText().equals("t1 Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t1_2");
                    map.put("НС_t1_2", new Tupel("НС_t1_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("t2 Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t2_2");
                    map.put("НС_t2_2", new Tupel("НС_t2_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("t3 Тв2")){

//



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_t3_2");
                    map.put("НС_t3_2", new Tupel("НС_t3_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("V1 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V1_2");
                    map.put("НС_V1_2", new Tupel("НС_V1_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("V2 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V2_2");
                    map.put("НС_V2_2", new Tupel("НС_V2_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("V3 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_V3_2");
                    map.put("НС_V3_2", new Tupel("НС_V3_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("M1 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M1_2");
                    map.put("НС_M1_2", new Tupel("НС_M1_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("M2 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M2_2");
                    map.put("НС_M2_2", new Tupel("НС_M2_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("M3 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_M3_2");
                    map.put("НС_M3_2", new Tupel("НС_M3_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("P1 Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_P1_2");
                    map.put("НС_P1_2", new Tupel("НС_P1_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("P2 Тв2")){


                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_P2_2");
                    map.put("НС_P2_2", new Tupel("НС_P2_2", (ns_to_bd(m.getNs()))));




                }
                if(m.getText().equals("Mг Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Mг_2");
                    map.put("НС_Mг_2", new Tupel("НС_Mг_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("Qо Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Qо_2");
                    map.put("НС_Qо_2", new Tupel("НС_Qо_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("Qг Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(4, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_Qг_2");
                    map.put("НС_Qг_2", new Tupel("НС_Qг_2", (ns_to_bd(m.getNs()))));


                }

                if(m.getText().equals("dt Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(2, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_dt_2");
                    map.put("НС_dt_2", new Tupel("НС_dt_2", (ns_to_bd(m.getNs()))));


                }


                if(m.getText().equals("BНP Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_BНP_2");
                    map.put("НС_BНP_2", new Tupel("НС_BНP_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("BOC Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(1, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_BOC_2");
                    map.put("НС_BOC_2", new Tupel("НС_BOC_2", (ns_to_bd(m.getNs()))));



                }
                if(m.getText().equals("G1 Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G1_2");
                    map.put("НС_G1_2", new Tupel("НС_G1_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("G2 Тв2")){




                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G2_2");
                    map.put("НС_G2_2", new Tupel("НС_G2_2", (ns_to_bd(m.getNs()))));


                }
                if(m.getText().equals("G3 Тв2")){



                    id_col.add(m.getText());
                    if (m.getMeasurText()!=null) {
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal(m.getMeasurText()).setScale(3, RoundingMode.HALF_EVEN))));
                    }else{
                        map.put(m.getText(), new Tupel(m.getText(), (new BigDecimal("0").setScale(4, RoundingMode.HALF_EVEN))));
                    }
                    //Добавляем нештатные ситуаии
                    id_col.add("НС_G3_2");
                    map.put("НС_G3_2", new Tupel("НС_G3_2", (ns_to_bd(m.getNs()))));



                }


                System.out.println("map.size= "+map.size());
                System.out.println("id_col.size= "+id_col.size());

            }

            dataItem.setOptionalValues(map);
            dataItem.setData(operation.getChronological());
            dataList.add(dataItem);
            objectList.add(sort_ns(id_col));

        }

        System.out.println("dataList.size= "+dataList.size());



        objectList.add(0,dataList);



        return objectList;
    }



    @Override
    public List<DataObject_str> getObject_ns_to_Str(List<DataObject> dataObjectList, List<String> id_coil){


        List<DataObject_str> dataObject_str_List=new ArrayList<>();
        for(DataObject dataObject:dataObjectList){
            DataObject_str dataObject_str=new DataObject_str();
            Map<String,Tupel_str> map_str=new HashMap<String,Tupel_str>();

            for(String coil:id_coil){
                map_str.put(coil,new Tupel_str(coil,dataObject.getOptionalValues().get(coil).getValue().toString()));
                //map.put("t1 Тв1", new Tupel("t1 Тв1", (new BigDecimal("65.81").setScale(2, RoundingMode.HALF_EVEN))));

            }

            dataObject_str.setData(auxiliaryService.timeStamp_to_string(dataObject.getData()));
            dataObject_str.setOptionalValues(map_str);
            dataObject_str_List.add(dataObject_str);


        }
        System.out.println("\n///////////////////////////////////////////////////////////");

        for(DataObject_str dataObject: dataObject_str_List){
            for(String s:id_coil){

                System.out.print(dataObject.getOptionalValues().get(s).getValue()+"    ");
            }
            System.out.println();
        }
        System.out.println("\n///////////////////////////////////////////////////////////");

        return dataObject_str_List;


    }


    @Override
    public List<Object> getCalculations(List<DataObject> dataObjectList, List<String> id_col){


        String qq="t1 Тв1,t2 Тв1,t3 Тв1,V1 Тв1,V2 Тв1, V3 Тв1, M1 Тв1, M2 Тв1, M3 Тв1, P1 Тв1, P2 Тв1, Mг Тв1, Qо Тв1, Qг Тв1, dt Тв1, tх, ta, BНP Тв1, BOC Тв1," +
                " G1 Тв1, G2 Тв1, G3 Тв1, t1 Тв2, t2 Тв2, t3 Тв2, V1 Тв2, V2 Тв2, V3 Тв2, M1 Тв2, M2 Тв2, M3 Тв2, P1 Тв2, P2 Тв2, Mг Тв2, Qо Тв2, Qг Тв2, dt Тв2, BНP Тв2, BOC Тв2," +
                " G1 Тв2, G2 Тв2, G3 Тв2";

        BigDecimal sum_t1_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_t2_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_t3_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V1_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V2_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V3_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M1_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M2_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M3_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_P1_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_P2_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Mг_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Qо_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Qг_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_dt_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_tx=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_ta=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_BНP_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_BOC_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G1_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G2_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G3_1=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_t1_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_t2_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_t3_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V1_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V2_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_V3_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M1_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M2_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_M3_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_P1_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_P2_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Mг_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Qо_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_Qг_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_dt_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_BНP_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_BOC_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G1_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G2_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal sum_G3_2=new BigDecimal("0").setScale(2, RoundingMode.HALF_EVEN);


        int count_t1_1=0;
        int count_t2_1=0;
        int count_t3_1=0;
        int count_V1_1=0;
        int count_V2_1=0;
        int count_V3_1=0;
        int count_M1_1=0;
        int count_M2_1=0;
        int count_M3_1=0;
        int count_P1_1=0;
        int count_P2_1=0;
        int count_Mг_1=0;
        int count_Qо_1=0;
        int count_Qг_1=0;
        int count_dt_1=0;
        int count_tx=0;
        int count_ta=0;
        int count_BНP_1=0;
        int count_BOC_1=0;
        int count_G1_1=0;
        int count_G2_1=0;
        int count_G3_1=0;
        int count_t1_2=0;
        int count_t2_2=0;
        int count_t3_2=0;
        int count_V1_2=0;
        int count_V2_2=0;
        int count_V3_2=0;
        int count_M1_2=0;
        int count_M2_2=0;
        int count_M3_2=0;
        int count_P1_2=0;
        int count_P2_2=0;
        int count_Mг_2=0;
        int count_Qо_2=0;
        int count_Qг_2=0;
        int count_dt_2=0;
        int count_BНP_2=0;
        int count_BOC_2=0;
        int count_G1_2=0;
        int count_G2_2=0;
        int count_G3_2=0;




        for (DataObject dataObject: dataObjectList) {
            for(String key:id_col){
                if(key.equals("t1 Тв1")){
                    sum_t1_1=sum_t1_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t1_1++;
                }
                if(key.equals("t2 Тв1")){
                    sum_t2_1=sum_t2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t2_1++;
                }
                if(key.equals("t3 Тв1")){
                    sum_t3_1=sum_t3_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t3_1++;
                }
                if(key.equals("V1 Тв1")){
                    sum_V1_1=sum_V1_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V1_1++;
                }
                if(key.equals("V2 Тв1")){
                    sum_V2_1=sum_V2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V2_1++;
                }
                if(key.equals("V3 Тв1")){
                    sum_V3_1=sum_V3_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V3_1++;
                }
                if(key.equals("M1 Тв1")){
                    sum_M1_1=sum_M1_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M1_1++;
                }
                if(key.equals("M2 Тв1")){
                    sum_M2_1=sum_M2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M2_1++;
                }
                if(key.equals("M3 Тв1")){
                    sum_M3_1=sum_M3_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M3_1++;
                }


                if(key.equals("P1 Тв1")){
                    sum_P1_1=sum_P1_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_P1_1++;
                }
                if(key.equals("P2 Тв1")){
                    sum_P2_1=sum_P2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_P2_1++;
                }
                if(key.equals("Mг Тв1")){
                    sum_Mг_1=sum_Mг_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Mг_1++;
                }
                if(key.equals("Qо Тв1")){
                    sum_Qо_1=sum_Qо_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qо_1++;
                }
                if(key.equals("Qг Тв1")){
                    sum_Qг_1=sum_Qг_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qг_1++;
                }
                if(key.equals("dt Тв1")){
                    sum_dt_1=sum_dt_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_dt_1++;
                }
                if(key.equals("tx")){
                    sum_tx=sum_tx.add(dataObject.getOptionalValues().get(key).getValue());
                    count_tx++;
                }
                if(key.equals("ta")){
                    sum_ta=sum_ta.add(dataObject.getOptionalValues().get(key).getValue());
                    count_ta++;
                }
                if(key.equals("BНP Тв1")){
                    sum_BНP_1=sum_BНP_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_BНP_1++;
                }
                if(key.equals("BOC Тв1")){
                    sum_BOC_1=sum_BOC_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_BOC_1++;
                }
                if(key.equals("G1 Тв1")){
                    sum_G1_1=sum_G1_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G1_1++;
                }
                if(key.equals("G2 Тв1")){
                    sum_G2_1=sum_G2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G2_1++;
                }
                if(key.equals("G3 Тв1")){
                    sum_G3_1=sum_G3_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G3_1++;
                }




                if(key.equals("t1 Тв2")){
                    sum_t1_2=sum_t1_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t1_2++;
                }
                if(key.equals("t2 Тв2")){
                    sum_t2_2=sum_t2_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t2_2++;
                }
                if(key.equals("t3 Тв2")){
                    sum_t3_2=sum_t3_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_t3_2++;
                }
                if(key.equals("V1 Тв2")){
                    sum_V1_2=sum_V1_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V1_2++;
                }
                if(key.equals("V2 Тв2")){
                    sum_V2_2=sum_V2_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V2_2++;
                }
                if(key.equals("V3 Тв2")){
                    sum_V3_2=sum_V3_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_V3_2++;
                }
                if(key.equals("M1 Тв2")){
                    sum_M1_2=sum_M1_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M1_2++;
                }
                if(key.equals("M2 Тв2")){
                    sum_M2_2=sum_M2_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M2_2++;
                }
                if(key.equals("M3 Тв2")){
                    sum_M3_2=sum_M3_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_M3_2++;
                }


                if(key.equals("P1 Тв2")){
                    sum_P1_2=sum_P1_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_P1_2++;
                }
                if(key.equals("P2 Тв2")){
                    sum_P2_1=sum_P2_1.add(dataObject.getOptionalValues().get(key).getValue());
                    count_P2_1++;
                }
                if(key.equals("Mг Тв2")){
                    sum_Mг_2=sum_Mг_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Mг_2++;
                }
                if(key.equals("Qо Тв2")){
                    sum_Qо_2=sum_Qо_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qо_2++;
                }
                if(key.equals("Qг Тв2")){
                    sum_Qг_2=sum_Qг_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_Qг_2++;
                }
                if(key.equals("dt Тв2")){
                    sum_dt_2=sum_dt_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_dt_2++;
                }

                if(key.equals("BНP Тв2")){
                    sum_BНP_2=sum_BНP_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_BНP_2++;
                }
                if(key.equals("BOC Тв2")){
                    sum_BOC_2=sum_BOC_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_BOC_2++;
                }
                if(key.equals("G1 Тв2")){
                    sum_G1_2=sum_G1_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G1_2++;
                }
                if(key.equals("G2 Тв2")){
                    sum_G2_2=sum_G2_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G2_2++;
                }
                if(key.equals("G3 Тв2")){
                    sum_G3_2=sum_G3_2.add(dataObject.getOptionalValues().get(key).getValue());
                    count_G3_2++;
                }


            } ///конец перебора по key


        } ///конец перебора по DataObjectList

        Map<String,Tupel> map_sum=new HashMap<String,Tupel>();
        Map<String,Tupel> map_average=new HashMap<String,Tupel>();

        Map<String,Tupel_str> map_sum_str=new HashMap<String,Tupel_str>();
        Map<String,Tupel_str> map_average_str=new HashMap<String,Tupel_str>();


        for(String key:id_col){
            if(key.equals("t1 Тв1")){
// BigDecimal qwerty=sum_t1_1.divide(new BigDecimal(count_t1_1).setScale(2, RoundingMode.HALF_EVEN), 2,RoundingMode.HALF_EVEN);
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t1_1.divide(new BigDecimal(count_t1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t1_1.divide(new BigDecimal(count_t1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));


            }
            if(key.equals("t2 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t2_1.divide(new BigDecimal(count_t2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t2_1.divide(new BigDecimal(count_t2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("t3 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t3_1.divide(new BigDecimal(count_t3_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t3_1.divide(new BigDecimal(count_t3_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("V1 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_V1_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V1_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));


            }
            if(key.equals("V2 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_V2_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V2_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("V3 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_V3_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V3_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M1 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_M1_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M1_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M2 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_M2_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M2_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M3 Тв1")){
                map_sum.put(key, new Tupel(key, (sum_M3_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M3_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }




            if(key.equals("P1 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_P1_1.divide(new BigDecimal(count_P1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_P1_1.divide(new BigDecimal(count_P1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("P2 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_P2_1.divide(new BigDecimal(count_P2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_P2_1.divide(new BigDecimal(count_P2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Mг Тв1")){
                map_sum.put(key, new Tupel(key, (sum_Mг_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Mг_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Qо Тв1")){
                map_sum.put(key, new Tupel(key, (sum_Qо_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Qо_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Qг Тв1")){
                map_sum.put(key, new Tupel(key, (sum_Qг_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Qг_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("dt Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_dt_1.divide(new BigDecimal(count_dt_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_dt_1.divide(new BigDecimal(count_dt_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("tx")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_tx.divide(new BigDecimal(count_tx).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_tx.divide(new BigDecimal(count_tx).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("ta")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_ta.divide(new BigDecimal(count_ta).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_ta.divide(new BigDecimal(count_ta).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("BНP Тв1")){
                map_sum.put(key, new Tupel(key, (sum_BНP_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_BНP_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("BOC Тв1")){
                map_sum.put(key, new Tupel(key, (sum_BOC_1)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_BOC_1.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("G1 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G1_1.divide(new BigDecimal(count_G1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G1_1.divide(new BigDecimal(count_G1_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("G2 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G2_1.divide(new BigDecimal(count_G2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G2_1.divide(new BigDecimal(count_G2_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("G3 Тв1")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G3_1.divide(new BigDecimal(count_G3_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G3_1.divide(new BigDecimal(count_G3_1).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }




            if(key.equals("t1 Тв2")){

                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t1_2.divide(new BigDecimal(count_t1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t1_2.divide(new BigDecimal(count_t1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));


            }
            if(key.equals("t2 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t2_2.divide(new BigDecimal(count_t2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t2_2.divide(new BigDecimal(count_t2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("t3 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_t3_2.divide(new BigDecimal(count_t3_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_t3_2.divide(new BigDecimal(count_t3_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("V1 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_V1_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V1_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));


            }
            if(key.equals("V2 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_V2_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V2_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("V3 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_V3_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_V3_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M1 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_M1_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M1_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M2 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_M2_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M2_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("M3 Тв2")){
                map_sum.put(key, new Tupel(key, (sum_M3_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_M3_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }


            if(key.equals("P1 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_P1_2.divide(new BigDecimal(count_P1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_P1_2.divide(new BigDecimal(count_P1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("P2 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_P2_2.divide(new BigDecimal(count_P2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_P2_2.divide(new BigDecimal(count_P2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Mг Тв2")){
                map_sum.put(key, new Tupel(key, (sum_Mг_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Mг_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Qо Тв2")){
                map_sum.put(key, new Tupel(key, (sum_Qо_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Qо_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("Qг Тв2")){
                map_sum.put(key, new Tupel(key, (sum_Qг_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_Qг_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("dt Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_dt_2.divide(new BigDecimal(count_dt_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_dt_2.divide(new BigDecimal(count_dt_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }

            if(key.equals("BНP Тв2")){
                map_sum.put(key, new Tupel(key, (sum_BНP_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_BНP_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("BOC Тв2")){
                map_sum.put(key, new Tupel(key, (sum_BOC_2)));
                map_average.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));

                map_sum_str.put(key, new Tupel_str(key, (sum_BOC_2.toString())));
                map_average_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("G1 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G1_2.divide(new BigDecimal(count_G1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G1_2.divide(new BigDecimal(count_G1_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));;
            }
            if(key.equals("G2 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G2_2.divide(new BigDecimal(count_G2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G2_2.divide(new BigDecimal(count_G2_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }
            if(key.equals("G3 Тв2")){
                map_sum.put(key, new Tupel(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN))));
                map_average.put(key, new Tupel(key, sum_G3_2.divide(new BigDecimal(count_G3_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)));

                map_sum_str.put(key, new Tupel_str(key, (new BigDecimal("0").setScale(1, RoundingMode.HALF_EVEN).toString())));
                map_average_str.put(key, new Tupel_str(key, (sum_G3_2.divide(new BigDecimal(count_G3_2).setScale(2, RoundingMode.HALF_EVEN),2,RoundingMode.HALF_EVEN)).toString()));
            }


        } ///конец перебора по key

        DataObject sum= new DataObject();
        sum.setStaticval2("ИТОГО");
        sum.setOptionalValues(map_sum);
        DataObject average= new DataObject();
        average.setStaticval2("Средние");
        average.setOptionalValues(map_average);

        DataObject_str sum_str= new DataObject_str();
        sum_str.setStaticval2("ИТОГО");
        sum_str.setOptionalValues(map_sum_str);
        DataObject_str average_str= new DataObject_str();
        average_str.setStaticval2("Средние");
        average_str.setOptionalValues(map_average_str);


List<Object> calculation=new ArrayList<>();
calculation.add(0,sum);
calculation.add(1,average);
        calculation.add(2,sum_str);
        calculation.add(3,average_str);



return calculation;
    }



    @Override
    public void getCalculations_total(List<Operation> operationList, DataObject sum) {
        String total_element = "V1 Тв1, V2 Тв1, V3 Тв1, M1 Тв1, M2 Тв1, M3 Тв1, Mг Тв1, Qо Тв1, Qг Тв1, BНP Тв1, BOC Тв1, " +
                "V1 Тв2, V2 Тв2, V3 Тв2, M1 Тв2, M2 Тв2, M3 Тв2, Mг Тв2, Qо Тв2, Qг Тв2, BНP Тв2, BOC Тв2";

        List<String> total_element_all = new ArrayList<>(Arrays.asList(total_element.replace(", ", ",").split(",")));

        List<String> list_calc = new ArrayList<>(sum.getOptionalValues().keySet());
        List<String> list_calc_total = new ArrayList<>();
        Map<String, Tupel> map_total = new HashMap<String, Tupel>();
        Map<String, Tupel_str> map_total_str = new HashMap<String, Tupel_str>();


        list_calc = sort(list_calc);

        DataObject total_begin = new DataObject();
        DataObject_str total_begin_str = new DataObject_str();
        Operation operation = operationList.get(0);
        List<Measurements> measurementsList = new ArrayList<>(operation.getMeasurementsSet());
////Сформировали список для вывода измерений ИТОГОВЫЕ ТЕКУЩИЕ.
        for (String col : list_calc) {
            for (String col_total : total_element_all) {
                if (col.equals(col_total)) {
                    list_calc_total.add(col);
                }
            }
        }

            //Отсортировали
            list_calc_total = sort(list_calc_total);
//// Достали измерение ИТОГОВЫЕ ТЕКУЩИЕ и по сформированному из общего списка колонок заполнили тектовую и цифровую версию DataObject
////  Формируем данные для помещения Measurements в объекты DataObject и DataObject_str
            for (String c : list_calc_total) {
                for (Measurements measurements : measurementsList) {
                    if (c.equals(measurements.getText())) {
                        map_total.put(c, new Tupel(c, new BigDecimal(measurements.getMeasurText())));

                        System.out.println("Найдено совпадение " + c + " :  getText()= " + measurements.getText() + " BigDecimal =" + new BigDecimal(measurements.getMeasurText()));
                        map_total_str.put(c, new Tupel_str(c, new BigDecimal(measurements.getMeasurText()).toString()));

                    }
                }
            }

            total_begin.setOptionalValues(map_total);
            total_begin.setData(operation.getChronological());

            total_begin_str.setOptionalValues(map_total_str);
            total_begin_str.setData(auxiliaryService.timeStamp_to_string(operation.getChronological()));

/////
/////
       ////Переделываем sum под формат вывода ИТОГОВЫЕ ТЕКУЩИЕ
        Map<String, Tupel> map_sum = new HashMap<String, Tupel>();
        Map<String, Tupel_str> map_sum_str = new HashMap<String, Tupel_str>();
        List<String> old_sum_col= new ArrayList<>(sum.getOptionalValues().keySet());
        for (String c : list_calc_total) {
            for(String old_c:old_sum_col) {

                if (c.equals(old_c)) {
                    map_sum.put(c,new Tupel(c,sum.getOptionalValues().get(c).getValue()));
                    map_sum_str.put(c, new Tupel_str(c,sum.getOptionalValues().get(c).getValue().toString()));

                }
            }


        }

        DataObject sum_new=new DataObject();
        sum_new.setOptionalValues(map_sum);
        sum_new.setData(sum.getData());
        DataObject_str sum_new_str=new DataObject_str();
        sum_new_str.setOptionalValues(map_sum_str);
        sum_new_str.setData(auxiliaryService.timeStamp_to_string(sum.getData()));



            ////Суммируем

            Map<String, Tupel> map_total_sum = new HashMap<String, Tupel>();
            Map<String, Tupel_str> map_total_sum_str = new HashMap<String, Tupel_str>();


            for (String c : list_calc_total) {

                BigDecimal summa = total_begin.getOptionalValues().get(c).getValue().add(sum.getOptionalValues().get(c).getValue());

                map_total_sum.put(c, new Tupel(c, summa));
                map_total_sum_str.put(c, new Tupel_str(c, summa.toString()));
            }

        DataObject total_end = new DataObject();
        total_end.setOptionalValues(map_total_sum);
        total_end.setData(auxiliaryService.date_TimeStamp(new Date()));

        DataObject_str total_end_str = new DataObject_str();
        total_end_str.setOptionalValues(map_total_sum_str);
        total_end_str.setData("Итого:");



    }




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


    public List<String> sort (List<String> list){
        //// Делаем соритровку по списку
        String qq="t1 Тв1,t2 Тв1,t3 Тв1,V1 Тв1,V2 Тв1, V3 Тв1, M1 Тв1, M2 Тв1, M3 Тв1, P1 Тв1, P2 Тв1, Mг Тв1, Qо Тв1, Qг Тв1, dt Тв1, tх, ta, BНP Тв1, BOC Тв1, G1 Тв1, G2 Тв1, G3 Тв1, t1 Тв2, t2 Тв2, t3 Тв2, V1 Тв2, V2 Тв2, V3 Тв2, M1 Тв2, M2 Тв2, M3 Тв2, P1 Тв2, P2 Тв2, Mг Тв2, Qо Тв2, Qг Тв2, dt Тв2, BНP Тв2, BOC Тв2, G1 Тв2, G2 Тв2, G3 Тв2";

        List<String> et = new ArrayList<>(Arrays.asList(qq.replace(", ",",").split( ",")));
        List<String> result = new LinkedList<>();

        for (int i=0; i<et.size(); i++){
            for (String w:list){

                if(w.equals(et.get(i))){

                    if(et.get(i).equals("t1 Тв1")){
                        System.out.println("eсть" );}
                    result.add(w);
                }


            }

        }
        return result;

    }

    public List<String> sort_ns (List<String> list){
        //// Делаем соритровку по списку
        String qq="t1 Тв1,t2 Тв1,t3 Тв1,V1 Тв1,V2 Тв1, V3 Тв1, M1 Тв1, M2 Тв1, M3 Тв1, P1 Тв1, P2 Тв1, Mг Тв1, Qо Тв1, Qг Тв1, dt Тв1, tх, ta, BНP Тв1, BOC Тв1, G1 Тв1, G2 Тв1, G3 Тв1, t1 Тв2, t2 Тв2, t3 Тв2, V1 Тв2, V2 Тв2, V3 Тв2, M1 Тв2, M2 Тв2, M3 Тв2, P1 Тв2, P2 Тв2, Mг Тв2, Qо Тв2, Qг Тв2, dt Тв2, BНP Тв2, BOC Тв2, G1 Тв2, G2 Тв2, G3 Тв2" +
                "НС_t1_1,НС_t2_1,НС_t3_1,НС_V1_1,НС_V2_1 НС_V3_1, НС_M1_1, НС_M2_1, НС_M3_1, НС_P1_1, НС_P2_1, НС_Mг_1, " +
                "НС_Qо_1, НС_Qг_1, НС_dt_1, НС_tх, НС_ta, НС_BНP_1, НС_BOC_1, НС_G1_1, НС_G2_1, НС_G3_1, НС_t1_2, НС_t2_2, НС_t3_2, НС_V1_2, НС_V2_2, НС_V3_2, НС_M1_2, НС_M2_2, НС_M3_2, НС_P1_2, НС_P2_2, НС_Mг_2, НС_Qо_2, НС_Qг_2, НС_dt_2, НС_BНP_2, НС_BOC_2, НС_G1_2, НС_G2_2, НС_G3_2";

        List<String> et = new ArrayList<>(Arrays.asList(qq.replace(", ",",").split( ",")));
        List<String> result = new LinkedList<>();

        for (int i=0; i<et.size(); i++){
            for (String w:list){

                if(w.equals(et.get(i))){

                    if(et.get(i).equals("t1 Тв1")){
                        System.out.println("eсть" );}
                    result.add(w);
                }


            }

        }
        return result;

    }

    /**
     * Перевol пришедшей нештатной ситуации в BigDecimal
     * @param ns HEX код нештатной ситуации
     * @return
     */
    public BigDecimal ns_to_bd(String ns){
        return new BigDecimal(Integer.toString(Integer.parseInt(ns,16)));

    }


}
