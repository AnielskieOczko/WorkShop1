package pl.coderslab;
//import pl.coderslab.ConsoleColors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;


public class TaskManager {

    public static void main(String[] args) throws IOException {
        String source = "./src/main/resources/tasks.csv";
        Scanner scanner = new Scanner(System.in);
        int lineCount = getNumberOfLinesInFile(source);
        System.out.println(lineCount);
        String[][] taskList = readFromFile("./src/main/resources/tasks.csv", lineCount, 3);

        boolean exitCondition = true;
        while (exitCondition) {
            displayOptions();
            String selectedOption = getUserInput("");

            switch (selectedOption) {
                case "add":
                    // add new task;
                    String[] newTask = {"", "", ""};
                    newTask[0] = getUserInput("Please provide task description:");
                    newTask[1] = getUserInput("Please provide task due date (yyyy-mm-dd)");
                    newTask[2] = getUserInput("Is task important options: true/false");

                    taskList = addTask(taskList, newTask);
                    System.out.println("New task added");
                    break;
                case "list":
                    System.out.println("List of tasks displayed");
                    displayTasks(taskList);
                    break;
                case "remove":
                    int taskIndex = Integer.parseInt(getNumberOfTaskToDelete(taskList, scanner));
                    taskList = ArrayUtils.remove(taskList, taskIndex);
                    System.out.printf("Task with index %d removed%n", taskIndex);
                    break;
                case "exit":
                    writeToFile(source, taskList);
                    System.out.println("Work saved: " + source);
                    exitCondition = false;
                    System.out.println("Program closed");
                    break;
                default:
                    System.out.println("Please select a correct option");
            }
        }
    }

    private static String getNumberOfTaskToDelete(String[][] taskList, Scanner scanner) {

        int maxTaskIndex = taskList.length - 1;
//        Scanner scanner = new Scanner(System.in);
        String taskNumber = null;

        System.out.printf("Please provide task number between %d and %d%n", 0, maxTaskIndex);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if (NumberUtils.isParsable(line)) {
                if (Integer.parseInt(line) <= maxTaskIndex) {
                    return line;
                } else {
                    System.out.printf("Provided task index out of range. Please provide task number between %d and %d%n", 0, maxTaskIndex);
                }
            } else {
                System.out.printf("Please provide task number between %d and %d%n", 0, maxTaskIndex);
            }
        }
        return null;
    }

    public static String[][] addTask(String[][] taskList, String[] newTaskDetails) {

        taskList = Arrays.copyOf(taskList, taskList.length + 1);

        taskList[taskList.length - 1] = new String[3];
        taskList[taskList.length - 1][0] = newTaskDetails[0];
        taskList[taskList.length - 1][1] = newTaskDetails[1];
        taskList[taskList.length - 1][2] = newTaskDetails[2];

        return taskList;
    }
    public static void displayOptions(){
        String[] options = {"add", "remove", "list", "exit"};
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        for (String option : options) {
            System.out.println(ConsoleColors.RESET + option);
        }
    }

    public static void displayTasks(String[][] taskList) {

        StringBuilder task = new StringBuilder();
        int maxCharCount = 0;
        int defaultPadding = 1;

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


    public static void writeToFile(String path, String[][] taskList) {

        StringBuilder s = new StringBuilder();
        try(FileWriter writer = new FileWriter(path)) {
            for (int r = 0; r < taskList.length; r++) {
                s.append(String.join(", ", taskList[r])).append("\n");
            }
            writer.append(s);
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


