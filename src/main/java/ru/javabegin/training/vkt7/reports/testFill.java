package ru.javabegin.training.vkt7.reports;

import javax.swing.table.DefaultTableModel;

/**
 * Created by Николай on 30.10.2017.
 */
public class testFill {

    public static void main (String[] args) {
       // int[] request=new int[crc.size()];
        String[] columnNames=new String[5];
        //String[][] data = {};
        //DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);

        columnNames[0]="werwer";
        System.out.println(columnNames[0]);


        String[][] data1= {
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

        System.out.println(data1.length);
        String[][] data=new String[4][];
        data[0]= new String[]{"sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff"};
        data[1]= new String[]{"sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff"};
        data[2]= new String[]{"sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff"};
        data[3]= new String[]{"sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff","sff"};

    }

}
