package pl.coderslab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadWriteFile {


    public static String[][] readFromFile(String path, int rows, int columns) throws FileNotFoundException {
        Path source = Paths.get(path);
        String[][] tasks = new String[rows][columns];
        int rowCounter = 0;
        if (Files.exists(source)) {
            Scanner scanner = new Scanner(source.toFile());
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                System.arraycopy(line, 0, tasks[rowCounter], 0, line.length);
                rowCounter ++;
            }
        }
        return tasks;
    }

    public static int[] getFileContentStats(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        int maxDescriptionLineCharCount = 0;
        String line;

        while ((line = reader.readLine()) != null) {

            String[] lineSplit = line.split(",");

            if (maxDescriptionLineCharCount < lineSplit[0].length()) {
                maxDescriptionLineCharCount = lineSplit[0].length();
            }

            lines++;
        }

        reader.close();
        return new int[]{lines, maxDescriptionLineCharCount};
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
}
