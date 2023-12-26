package pl.coderslab;
import org.apache.commons.lang3.math.NumberUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

import static pl.coderslab.ProgramOptions.taskManagerSystemMessage;


public class UserInput {

    public static String getTaskImportance(Scanner scanner) {

        System.out.println("Is task important options: true/false");

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("true") || line.equalsIgnoreCase("false")) {
                return line.toLowerCase();
            } else {
                System.out.println(ConsoleColors.RED + "Task importance has only two valid options: true/false");
                System.out.print(ConsoleColors.RESET);
            }
        }
        return null;
    }
    public static String getNewTaskDueDate(Scanner scanner) {

        LocalDate parsedDueDate;
        taskManagerSystemMessage("Please provide task due date in following format: yyyy-mm-dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();
                try {
                    parsedDueDate = LocalDate.parse(line, formatter);
                    return parsedDueDate.toString();
                } catch (DateTimeParseException e) {
                    System.out.println(ConsoleColors.RED + "Please provide valid due date in format: yyyy-MM-dd");
                    System.out.print(ConsoleColors.RESET);
                }
        }
        return null;
    }
    public static String getNumberOfTaskToDelete(String[][] taskList, Scanner scanner) {

        int maxTaskIndex = taskList.length - 1;

        System.out.printf(ConsoleColors.BLUE + "Please provide task number between %d and %d%n", 0, maxTaskIndex);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (NumberUtils.isParsable(line)) {
                if (Integer.parseInt(line) <= maxTaskIndex) {
                    return line;
                } else {
                    System.out.printf(ConsoleColors.RED + "Provided task index out of range. Please provide task number between %d and %d%n", 0, maxTaskIndex);
                    System.out.print(ConsoleColors.RESET);
                }
            } else {
                System.out.printf(ConsoleColors.RED + "Please provide task number between %d and %d%n", 0, maxTaskIndex);
                System.out.print(ConsoleColors.RESET);
            }
        }
        return null;
    }

    public static String getUserInput(String prompt){
        if (!prompt.isEmpty()) {
            taskManagerSystemMessage(prompt);
        }
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

