package pl.coderslab;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import java.io.*;
import java.util.Scanner;

import static pl.coderslab.ProgramOptions.*;
import static pl.coderslab.ReadWriteFile.*;
import static pl.coderslab.UserInput.*;


public class TaskManager {

    public static void main(String[] args) throws IOException {
        String source = "./src/main/resources/tasks.csv";
        Scanner scanner = new Scanner(System.in);
        int[] fileContentStats = getFileContentStats(source);
        int lineCount = fileContentStats[0];
        int maxDescriptionLineCharCount = fileContentStats[1];

        String[][] taskList = readFromFile("./src/main/resources/tasks.csv", lineCount, 3);
        taskManagerSystemMessage("Number of tasks in source file: " + lineCount);
        displayOptions();


        boolean exitCondition = true;
        while (exitCondition) {

            String selectedOption = getUserInput("");

            switch (selectedOption) {
                case "add":
                    // add new task;
                    String[] newTask = {"", "", ""};
                    newTask[0] = getUserInput("Please provide task description:");
                    newTask[1] = getNewTaskDueDate(scanner);
                    newTask[2] = getTaskImportance(scanner);

                    taskList = addTask(taskList, newTask);
                    taskManagerSystemMessage("New task added");
                    break;
                case "list":
                    taskManagerSystemMessage("List of tasks:");
                    displayTasks(taskList, maxDescriptionLineCharCount);
                    break;
                case "remove":
                    String taskIndex = getNumberOfTaskToDelete(taskList, scanner);
                    if (NumberUtils.isParsable(taskIndex)) {
                        taskList = ArrayUtils.remove(taskList, Integer.parseInt(taskIndex));
                        taskManagerSystemMessage("Task with index" + Integer.parseInt(taskIndex) + "removed");
                        //System.out.printf("Task with index %d removed%n", Integer.parseInt(taskIndex));
                    } else {
                        throw new RuntimeException("Issue with task index - task not removed");
                    }
                    break;
                case "exit":
                    writeToFile(source, taskList);
                    taskManagerSystemMessage("Work saved: " + source);
                    exitCondition = false;
                    taskManagerSystemMessage("Program closed, bye!");
                    break;
                default:
                    taskManagerSystemMessage("Please select a correct option");
            }
        }
    }
}