import java.io.*;
import java.util.ArrayList;

public class Transposer {

    public static void transposer(String input, String output, int num, boolean cut, boolean align) throws IOException {

        BufferedReader buf;
        BufferedReader bufR = null;

        if (input == null) { // make up inputFile from console strings for empty value of input
            InputStreamReader reader = new InputStreamReader(System.in);
            buf = new BufferedReader(reader);
            bufR = new BufferedReader(reader);
        } else buf = new BufferedReader(new FileReader(input));


        String line = buf.readLine();

        ArrayList<String> transposedStrings = new ArrayList<String>(); // array, which stores transposed strings

        while (line != null) {
            String[] strings = line.split(" +"); //transform current string to the array of strings

            for (int i = 0; i < strings.length; i++) {
                if (cut && strings[i].length() > num) strings[i] = strings[i].substring(0, num);
                StringBuilder result = new StringBuilder();
                if (i > transposedStrings.size() - 1) transposedStrings.add("");

                if (!align && strings[i].length() < num) {
                    result.append(strings[i]);  // left alignment
                    for (int j = 0; j < num - strings[i].length(); j++) result.append(" ");
                } else {
                    for (int j = 0; j < num - strings[i].length(); j++) result.append(" "); // right alignment
                    result.append(strings[i]);
                }

                transposedStrings.set(i, transposedStrings.get(i) + result); // add string to result array
            }

            line = buf.readLine();
        }
        buf.close();

        if (output != null) { //write final strings into output file
            FileWriter wr = new FileWriter(output);
            for (String string: transposedStrings) if (!string.equals(transposedStrings.get(transposedStrings.size() - 1))) {
                wr.write(string + "\n");
            } else wr.write(string);
            wr.close();
        } else for (String string: transposedStrings) System.out.println(string); // write final strings into console

    }
}