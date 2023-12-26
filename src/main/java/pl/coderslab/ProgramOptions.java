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

    public static void displayTasks(String[][] taskList, int maxDescriptionLineCharCount) {

        for (String[] strings : taskList) {
            if (strings[0].length() > maxDescriptionLineCharCount) {
                maxDescriptionLineCharCount = strings[0].length();
            }
        }

        StringBuilder task = new StringBuilder();

        int defaultPadding = 10;
        int descriptionPadding = 10;

        for (int r = 0; r < taskList.length; r++) {
            task.append(r).append(" : ");
            for (int c = 0; c < taskList[r].length; c++ ) {

                if (c == 0) {
                    int charCount = taskList[r][c].length();

                    if (charCount > maxDescriptionLineCharCount) {
                        maxDescriptionLineCharCount = charCount;
                    }
                    if (defaultPadding < maxDescriptionLineCharCount) {
                        descriptionPadding = descriptionPadding + (maxDescriptionLineCharCount - descriptionPadding);
                    }
                    String line = StringUtils.rightPad(taskList[r][c].trim(),descriptionPadding,' ');
                    task.append(line).append("  ");
                } else {
                    String line = StringUtils.rightPad(taskList[r][c].trim(),defaultPadding,' ');
                    task.append(line).append("  ");
                }
            }
            task.append("\n");
        }
        System.out.println(task);
    }
}
