package ru.javabegin.training.vkt7.filevisitResult;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static ru.javabegin.training.vkt7.filevisitResult.MyFileVisitorTxt.file;
import static ru.javabegin.training.vkt7.filevisitResult.TestVisitResult.*;


class MyFileVisitorTxt extends SimpleFileVisitor<Path> {
public static File file;


    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) throws IOException {
        System.out.println("file name:" + path.getFileName());
        boolean containsName = false;
        //if(path.getFileName().toString().contains(p1)&(path.getFileName().toString().contains(p2))&(path.getFileName().toString().contains(p3))){
        if(path.getFileName().toString().contains(".txt")){
            //System.out.println("Требуемый файл найден :" + path.getFileName());
            System.out.println("Рассматриваемый файл :" + path.getFileName());
            //file=path.toFile();
            containsName =true;
        }

        /*customerStr="Абонент:Долина нарзанов тп-1";
        dataStr="30/11/17 24:00";*/
        Charset charset = Charset.forName("windows-1251");
        String content = new String(Files.readAllBytes(path),charset );
       // System.out.println(content);
        boolean containsContent = false;
        if(customerStr!=null && content.contains(customerStr)&& content.contains(dataStr)) {

            containsContent = true;
        }

      if(containsName && containsContent) {


         //   foundFiles.add(file);
            file=path.toFile();
            System.out.println("Требуемый файл со всеми условиями найден :" + path.getFileName());
            System.out.println(content);
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}



public class TestVisitResult {
    //public static String p1="Весна";
    //public static String p2="5_12";
    //public static String p1="ДолинаНарзанов_тп_1";
    //public static String p2="5_12";
    //public static String p3="txt";


    public static String p3="txt";
    public static String customerStr;
    public static String dataStr;

    public List<Object> searchMothTxt(String customer, String date) throws IOException {

        this.customerStr=customer;
        this.dataStr=date;
        Path pathSource = Paths.get("c:\\demo\\vkt");
        Path pathDestination = Paths.get("c:\\demo\\temp");


        try {

            Files.walkFileTree(pathSource, new MyFileVisitorTxt());
            System.out.println("найденный файл :" + file.getName());
            System.out.println("file.getAbsolutePath() :" + file.getAbsolutePath());
            System.out.println("file.getParent() :" + file.getParent());
            System.out.println("file.getCanonicalPath() :" + file.getCanonicalPath());
            System.out.println("file.getPath() :" + file.getPath());
            System.out.println("file.getParent() :" + file.getParent());
            System.out.println("file.getParentFile() :" + file.getParentFile());



        } catch (IOException e) {

           // System.out.println("файл с требуемыми условиями не найден");
            e.printStackTrace();
        }


        List<String> list = new ArrayList<>();

        try
        {
            FileInputStream inF = new FileInputStream(file.getAbsolutePath());
            //DataInputStream in = new DataInputStream(inF,"Cp866");
            BufferedReader in = new BufferedReader(new InputStreamReader(inF,"windows-1251"));

            String str = in.readLine();
            System.out.println(str);
            in.close();
            //txt.append(str+"\n");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

        Path wiki_path = Paths.get(file.getAbsolutePath());
        String dannie="";
        String naim="";
        String search="";

        Charset charset = Charset.forName("windows-1251");
        try {
            List<String> lines = Files.readAllLines(wiki_path, charset);

            for (String line : lines) {
                if (line.contains("Заводской")) {
                    System.out.println("Найдена строка"+line);
                    dannie=line;
                }
                if (line.contains("Дата")) {
                    System.out.println("Найдена строка"+line);
                    naim=line;
                }

                if (line.contains("30/11/17 24:00")) {
                    System.out.println("Найдена строка"+line);
                    search=line;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
       // dannie=dannie.replace("  "," ");
        dannie=dannie.replaceAll("[\\s]{2,}", " ").trim();
        List<String> d_list = new ArrayList<>(Arrays.asList(dannie.split(" ")));
        d_list.forEach(p->System.out.print(p+" "));
        System.out.print("\n");

        naim=naim.replace(" ","");
        List<String> n_list = new ArrayList<>(Arrays.asList(naim.split("\\|")));
        n_list.forEach(p->System.out.print(p+" "));
        System.out.print("\n");

        String moth=search.replace(" ","");
        List<String> moth_list = new ArrayList<>(Arrays.asList(moth.split("\\|")));
        moth_list.forEach(p->System.out.print(p+" "));
        System.out.print("\n");
        List<Object> objectList=new ArrayList<>();
        objectList.add(0,d_list);
        objectList.add(1,n_list);
        objectList.add(2,moth_list);




       //pathSource = Paths.get("c:\\demo\\5_12_2017_11_44\\"+file.getName().toString());
        /*
        pathSource = Paths.get(file.getAbsolutePath());
        pathDestination = Paths.get("c:\\demo\\temp\\test.xls");
        try {
            Files.copy(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
            //Files.move(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Source file copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        //ReadExcel readExcel=new ReadExcel();
       // readExcel.read(pathDestination.toString());


    return objectList;

    }
}