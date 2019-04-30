import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TransposeTest {
    public static boolean test(String input, String output, String test, int num,
                               boolean cut, boolean align) throws IOException {
        Transposer.transposer(input, output, num, cut, align);

        BufferedReader buf1 = new BufferedReader(new FileReader(output));
        BufferedReader buf2 = new BufferedReader(new FileReader(test));
        String outLine = buf1.readLine();
        String testLine = buf2.readLine();

        while (outLine != null || testLine != null) {
            if (outLine == null || !outLine.equals(testLine)) {
                return false;
            }

            outLine = buf1.readLine();
            testLine = buf2.readLine();
        }

        return true;
    }

    @Test
    public void standardTest() throws IOException {
        Assert.assertTrue(test("InputFiles/first.txt", "output1.txt",
                "TestFiles/test1.txt", 6, false, false));
    }

    @Test
    public void cutTest() throws IOException {
        Assert.assertTrue(test("InputFiles/second.txt", "output2.txt",
                "TestFiles/test2.txt", 7, true, false));
    }

    @Test
    public void alignTest() throws IOException {
        Assert.assertTrue(test("InputFiles/third.txt", "output3.txt",
                "TestFiles/test3.txt", 5, false, true));
    }

    @Test
    public void bothTest() throws IOException {
        Assert.assertTrue(test("InputFiles/fourth.txt", "output4.txt",
                "TestFiles/test4.txt", 4, true, true));
    }
}
