import java.io.*;
import java.util.ArrayList;

public class Transposer {

    public static void transposer(String input, String output, int num, boolean cut, boolean align) throws IOException {

        BufferedReader buf;

        if (input == null) { // make up inputFile from console strings for empty value of input
            InputStreamReader reader = new InputStreamReader(System.in);
            buf = new BufferedReader(reader);
        } else buf = new BufferedReader(new FileReader(input));

        String line = buf.readLine();

        ArrayList<StringBuilder> transposedStrings = new ArrayList<StringBuilder>(); // array, which stores transposed strings
        ArrayList<String> finish = new ArrayList<String>();

        while (line != null) {
            String[] strings = line.split(" +"); //transform current string to the array of strings

            for (int i = 0; i < strings.length; i++) {
                if (cut && strings[i].length() > num) strings[i] = strings[i].substring(0, num);
                StringBuilder result = new StringBuilder();
                if (i > transposedStrings.size() - 1) transposedStrings.add(new StringBuilder());

                if (!align && strings[i].length() < num) {
                    result.append(strings[i]);  // left alignment
                    for (int j = 0; j < num - strings[i].length(); j++) result.append(" ");
                } else {
                    for (int j = 0; j < num - strings[i].length(); j++) result.append(" "); // right alignment
                    result.append(strings[i]);
                }

                transposedStrings.get(i).append(result); // add string to result array
            }

            line = buf.readLine();


        }
        for (StringBuilder str: transposedStrings) finish.add(str.toString());
        buf.close();

        Writer writer;

        if (output != null) { //write final strings into output file
            writer = new FileWriter(output);

            for (String string: finish) if (!string.equals(transposedStrings.get(transposedStrings.size() - 1))) {
                writer.write(string + "\n");
            } else writer.write(string);

            writer.close();
        } else {
            writer = new StringWriter();

            for (String string: finish) writer.write(string); // write final strings into console
        }
    }
}