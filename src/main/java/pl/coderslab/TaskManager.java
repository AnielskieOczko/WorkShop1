package pl.coderslab;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("./src/main/resources/tasks.csv");
        System.out.println(getUserInput("Podaj priorytet zadania"));
    }

    public static void readFromFile(String path) throws FileNotFoundException {
        Path source = Paths.get(path);

        if (Files.exists(source)) {
            Scanner scanner = new Scanner(source.toFile());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }

    public static void writeToFile(String path, String task) throws IOException {
        try(FileWriter writer = new FileWriter(path, true)) {
            writer.append(task).append("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getUserInput(String prompt){
        String userInput = "";
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        while (userInput.isBlank()) {

            userInput = scanner.nextLine();
        }
        return userInput;
    }
}


