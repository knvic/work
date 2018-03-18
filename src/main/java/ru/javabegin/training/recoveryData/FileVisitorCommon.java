package ru.javabegin.training.recoveryData;



import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class FileVisitorCommon extends SimpleFileVisitor<Path> {

    public static File file;
    public static String p3="txt";
    public static String customerStr;
    public static String dataStr;


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


    public   List<String> searchMothTxt(String customer, String date) throws IOException {

        this.customerStr=customer;
        this.dataStr=date;
        Path pathSource = Paths.get("c:\\demo\\vkt");
        Path pathDestination = Paths.get("c:\\demo\\temp");


        try {

            Files.walkFileTree(pathSource, new FileVisitorCommon());
            System.out.println("найденный файл :" + file.getName());
            System.out.println("file.getAbsolutePath() :" + file.getAbsolutePath());
            System.out.println("file.getParent() :" + file.getParent());
            System.out.println("file.getCanonicalPath() :" + file.getCanonicalPath());
            System.out.println("file.getPath() :" + file.getPath());
            System.out.println("file.getParent() :" + file.getParent());
            System.out.println("file.getParentFile() :" + file.getParentFile());



        } catch (Exception e) {

            System.out.println("файл с требуемыми условиями не найден");
            e.printStackTrace();
        }




        /*try
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
        }*/

        Path wiki_path = Paths.get(file.getAbsolutePath());

        Charset charset = Charset.forName("windows-1251");

        List<String> lines = Files.readAllLines(wiki_path, charset);

        return  lines;

    }



}
