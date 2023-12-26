package pl.coderslab;
import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;

public class ProgramOptions {

    public static void taskManagerSystemMessage(String message) {
        System.out.println(ConsoleColors.CYAN_BOLD + "[Task manager] " + ConsoleColors.BLUE  + message + ConsoleColors.RESET);
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
        taskManagerSystemMessage("Please select an option:");
        for (String option : options) {
            System.out.println(option);
        }
    }

    public static void displayTasks2(String[][] taskList, int maxLineCharCount) {

        StringBuilder task = new StringBuilder();
        int maxCharCount = maxLineCharCount;
        int defaultPadding = 10;
        int descriptionPadding = 10;

        for (int r = 0; r < taskList.length; r++) {
            task.append(r).append(" : ");
            for (int c = 0; c < taskList[r].length; c++ ) {

                if (c == 0) {
                    int charCount = taskList[r][c].length();


                    if (charCount > maxCharCount) {
                        maxCharCount = charCount;
                        System.out.println("max " + maxCharCount);
                        System.out.println("string count: " + charCount);
                    }
                    if (defaultPadding < maxCharCount) {
                        descriptionPadding = descriptionPadding + (maxCharCount - descriptionPadding);
                        System.out.println("defaultPadding " + charCount);
                    }
                    String line = StringUtils.rightPad(taskList[r][c],descriptionPadding,' ');
                    task.append(line).append(" ");
                } else {
                    String line = StringUtils.rightPad(taskList[r][c],defaultPadding,' ');
                    task.append(line).append(" ");
                }
            }
            task.append("\n");
        }
        System.out.println(task);
    }
    public static void displayTasks(String[][] taskList) {

        StringBuilder task = new StringBuilder();
        int maxCharCount = 0;
        int defaultPadding = 0;

        for (int r = 0; r < taskList.length; r++) {
            task.append(r).append(" : ");
            for (int c = 0; c < taskList[r].length; c++ ) {

                if (c == 0) {
                    int charCount = taskList[r][c].length();

                    System.out.println("max " + maxCharCount);
                    System.out.println("string count: " + charCount);
                    if (charCount > maxCharCount) {
                        maxCharCount = charCount;
                    }
                    if (defaultPadding < maxCharCount) {
                        defaultPadding = defaultPadding + (maxCharCount - defaultPadding);
                        System.out.println("defaultPadding " + charCount);
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
}
