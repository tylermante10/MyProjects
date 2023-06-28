import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class ProgramWithIOAndStaticMethod {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ProgramWithIOAndStaticMethod() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod(int a) {
        /*
         * Put your code for myMethod here
         */
        int[] array = new int[a];
        for (int idx = 0; idx < a; idx++) {
            array[idx] = a + 6;
            System.out.printf("a is %d\n", array[idx]);
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        int a = 3;
        int i = -6;
        while (i < a) {
            out.println("Hello World I am Tyler");
            i++;
        }
        myMethod(a);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
