import org.kohsuke.args4j.*;
import java.io.IOException;
import java.util.Scanner;

public  class Transpose {

    @Option(name = "-o", usage = "name of output file")
    private String output;

    @Option(name = "-a", usage = "space for each word")
    private int num;

    @Option(name = "-t", usage = "cut word")
    private boolean cut;

    @Option(name = "-r", usage = "align word to right side")
    private boolean align;

    @Argument(required = true, usage = "name of input file")
    private String input;

    public static void main(String args[]) throws IOException {
        new Transpose().call(args);
    }

    public boolean isDigit (char c) { // check on digits
        return Character.isDigit(c);
    }

    public void call(String args[]) throws IOException {
        CmdLineParser parse = new CmdLineParser(this);

        try {
            parse.parseArgument(args);
        } catch (CmdLineException exc) {
            System.err.println(exc.getMessage());
            System.err.println("java -jar transpose.jar -a num -t -r -o OutputName inputFileName");
            parse.printUsage(System.err);
        }

        if (input == null) { // reading from console for empty value of output field
            Scanner scan = new Scanner(System.in);
            input = scan.next();
        }

        if (input.contains(" ") || isDigit(input.charAt(0))) throw new IOException(); // check on wrong format of input

        if (num == 0) Transposer.transposer(input, output, 10, cut, align); // launch for empty value of -a
        else Transposer.transposer(input, output, num, cut, align); // standard launch
    }
}