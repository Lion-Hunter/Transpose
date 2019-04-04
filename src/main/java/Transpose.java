import org.kohsuke.args4j.*;
import java.io.IOException;

public  class Transpose {

    @Option(name = "-o", usage = "name of output file")
    private String output;

    @Option(name = "-a", usage = "space for each word")
    private int num = 10;

    @Option(name = "-t", usage = "cut the word")
    private boolean cut;

    @Option(name = "-r", usage = "align word to right side")
    private boolean align;

    @Argument(usage = "name of input file")
    private String input;

    public static void main(String args[]) throws IOException {
        new Transpose().call(args);
    }

    public void call(String args[]) throws IOException {
        CmdLineParser parse = new CmdLineParser(this);

        try {
            parse.parseArgument(args);
        } catch (CmdLineException exc) {
            System.err.println(exc.getMessage());
            System.err.println("java -jar transpose.jar -a num -t -r -o OutputFileName inputFileName");
            parse.printUsage(System.err);
        }

        Transposer.transposer(input, output, num, cut, align); // standard launch
    }
}