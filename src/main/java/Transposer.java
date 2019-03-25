import java.io.*;
import java.util.Scanner;

public class Transposer {

    public static void transposer(String input, String output, int num, boolean cut, boolean align) throws IOException {

        if (input == null) { // make up inputFile from console strings for empty value of input
            Scanner scanner = new Scanner(System.in);
            FileWriter buffWriter = new FileWriter(input);
            String string = scanner.nextLine();
            while (string != null) buffWriter.write(input);
        }

        BufferedReader buf = new BufferedReader(new FileReader(new File(input)));
        BufferedReader reader = new BufferedReader(new FileReader(new File(input)));

        String lineReader = reader.readLine();
        String line = buf.readLine();

        int maxLength = 0;

        while (lineReader != null) { //search line with maximal length for creating array with optimal size
            if (lineReader.length() > maxLength) maxLength = lineReader.length();
            lineReader = reader.readLine();
        }

        String[] transposedStrings = new String[maxLength]; // array, which stores transposed strings

        while (line != null) {
            String[] strings = line.split(" +"); //transform current string to the array of strings

            for (int i = 0; i < strings.length; i++) {
                if (cut && strings[i].length() > num) strings[i] = strings[i].substring(0, num - 1);
                StringBuilder result = new StringBuilder();

                if (!align && strings[i].length() < num) {
                    result.append(strings[i]);  // left alignment
                    for (int j = 0; j < num; j++) result.append(" ");
                } else {
                    for (int j = 0; j < num; j++) result.append(" "); // right alignment
                    result.append(strings[i]);
                }

                transposedStrings[i] += result; // add string to result array
            }

            line = buf.readLine();
        }
        buf.close();

        if (output != null) { //write final strings into output file
            FileWriter writer = new FileWriter(output);
            for (String str: transposedStrings) writer.write(str + "\n");
            writer.close();
        } else for (String str: transposedStrings) System.out.println(str); // write final strings into console
    }
}