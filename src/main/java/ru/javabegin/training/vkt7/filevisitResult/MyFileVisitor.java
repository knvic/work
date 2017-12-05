package ru.javabegin.training.vkt7.filevisitResult;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

class MyFileVisitor extends SimpleFileVisitor<Path> {

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("file name:" + path.getFileName());
        if(path.getFileName().toString().contains("Весна")&(path.getFileName().toString().contains("5_12"))){
            System.out.println("Требуемый файд найден :" + path.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        System.out.println("Directory name:" + path);
        return FileVisitResult.CONTINUE;
    }
}
