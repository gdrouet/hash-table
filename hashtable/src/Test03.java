import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Test03 {

    public static void main(String[] args) {
        final PrintStream out = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        }));

        final Hashtable hashTable = new Hashtable();
        System.setOut(out);

        if ("[198, 198, 207, 207]".equals(Arrays.toString(
                Arrays.asList(hashTable.hashFunction.apply("ABC"),
                hashTable.hashFunction.apply("CBA"),
                hashTable.hashFunction.apply("DEF"),
                hashTable.hashFunction.apply("FED")).toArray()))) {
            System.out.println(">>>>>>>>>>>>>>>> SUCCESS <<<<<<<<<<<<<<<");
        } else {
            System.out.println(">>>>>>>>>>>>>>>> FAILURE <<<<<<<<<<<<<<<");
            System.out.println("Expected: ABC=198, CBA=198, DEF=207, FED=207");
        }
    }
}
