package pl.coderslab;
//import pl.coderslab.ConsoleColors;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class TaskManager {

    public static void main(String[] args) throws IOException {
        int lineCount = getNumberOfLinesInFile("./src/main/resources/tasks.csv");
        System.out.println(lineCount);
        String[][] taskList = readFromFile("./src/main/resources/tasks.csv", lineCount, 3);

        boolean exitCondition = true;
        while (exitCondition) {
            displayOptions();
            String userInput = getUserInput("");

            switch (userInput) {
                case "add":
                    // add new task;
                    System.out.println("New task added");
                    break;
                case "list":
                    System.out.println("List of tasks displayed");
                    displayTasks(taskList);
                    break;
                case "remove":
                    System.out.println("Task removed");
                    break;
                case "exit":
                    System.out.println("Program closed");
                    exitCondition = false;
                    break;
                default:
                    System.out.println("Please select a correct option");
            }
        }
    }

    public static void displayOptions(){
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(ConsoleColors.RESET + options[i]);
        }
    }

    public static void displayTasks(String[][] taskList) {

        StringBuilder task = new StringBuilder();
        int maxCharCount = 0;
        int defaultPadding = 10;

        for (int r = 0; r < taskList.length; r++) {
            task.append(r).append(" : ");
            for (int c = 0; c < taskList[r].length; c++ ) {

                if (c == 0) {
                    int charCount = taskList[r][c].length();

                    if (charCount > maxCharCount) {
                        maxCharCount = charCount;
                    }
                    if (defaultPadding < maxCharCount) {
                        defaultPadding = defaultPadding + (maxCharCount - defaultPadding);
                    }
                    String line = StringUtils.rightPad(taskList[r][c],defaultPadding,' ');
                    task.append(line).append(" ");
                } else {
                    String line = StringUtils.rightPad(taskList[r][c],10,' ');
                    task.append(line).append(" ");
                }
            }
            task.append("\n");
        }
        System.out.println(task);
    }

    public static String[][] readFromFile(String path, int rows, int columns) throws FileNotFoundException {
        Path source = Paths.get(path);
        String[][] tasks = new String[rows][columns];
        int rowCounter = 0;
        if (Files.exists(source)) {
            Scanner scanner = new Scanner(source.toFile());
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                //System.arraycopy(line, 0, tasks[rowCounter], 0, line.length);
                for (int c = 0; c <line.length; c++) {
                    tasks[rowCounter][c] = line[c];
                }
                //System.out.println(scanner.nextLine());
                rowCounter ++;
            }
        }
        return tasks;
    }

    public static int getNumberOfLinesInFile(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }


    public static void writeToFile(String path, String task) {
        try(FileWriter writer = new FileWriter(path, true)) {
            writer.append(task).append("\n");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getUserInput(String prompt){
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}


