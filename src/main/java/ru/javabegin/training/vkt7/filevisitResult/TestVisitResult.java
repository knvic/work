package ru.javabegin.training.vkt7.filevisitResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static ru.javabegin.training.vkt7.filevisitResult.MyFileVisitor.file;
import static ru.javabegin.training.vkt7.filevisitResult.TestVisitResult.p1;
import static ru.javabegin.training.vkt7.filevisitResult.TestVisitResult.p2;


class MyFileVisitor extends SimpleFileVisitor<Path> {
public static File file;


    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
    ;
        if(path.getFileName().toString().contains(p1)&(path.getFileName().toString().contains(p2))){
            System.out.println("Требуемый файд найден :" + path.getFileName());
            file=path.toFile();

        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}



public class TestVisitResult {
    public static String p1="Весна";
    public static String p2="5_12";
    public static void main(String[] args) {

        Path pathSource = Paths.get("c:\\demo");
        Path pathDestination = Paths.get("c:\\demo\\temp");


        try {

            Files.walkFileTree(pathSource, new MyFileVisitor());
            System.out.println("найденный файл :" + file.getName());



        } catch (IOException e) {
            e.printStackTrace();
        }

       pathSource = Paths.get("c:\\demo\\"+file.getName().toString());
        pathDestination = Paths.get("c:\\demo\\temp\\"+file.getName().toString());
        try {
            Files.move(pathSource, pathDestination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Source file copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}