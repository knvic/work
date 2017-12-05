package ru.javabegin.training.vkt7.filevisitResult;
import java.io.IOException;
import java.nio.file.*;





public class TestVisitResult {
    public static void main(String[] args) {

        Path pathSource = Paths.get("c:\\demo");
        try {

            Files.walkFileTree(pathSource, new MyFileVisitor());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}