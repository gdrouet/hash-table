import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test05 {

    public static void main(final String[] args) {
        final PrintStream out = System.out;
        final CharArrayWriter caw = new CharArrayWriter();
        Hashtable hashTable = new Hashtable();

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                if (b != '\r') {
                    caw.write(b);
                }
            }
        }));

        final char[] expected = ("display(), (2/16) entries in the table:\n" +
                " tutu(len=4, index=2) = valeur-tutu2\n" +
                " titi(len=4, index=10) = valeur-titi\n" +
                "\n" +
                "2 elements displayed.\n").toCharArray();

        hashTable.add("titi", "valeur-titi");
        hashTable.add("tutu", "valeur-tutu");
        hashTable.add("tutu", "valeur-tutu2");
        hashTable.display();

        final char[] actual = caw.toCharArray();
        boolean fail = false;
        System.setOut(out);

        for (int i = 0; i < Math.max(actual.length, expected.length); i++) {
            if (actual.length == i) {
                System.out.println(String.format("%s != EOF, Actual length %d, expected index %s", expected[i], actual.length, i));
                fail = true;
            } else if (expected.length == i) {
                System.out.println(String.format("%s != EOF, Expected length %d, actual index %s", actual[i], expected.length, i));
                fail = true;
            } else if (actual[i] != expected[i]) {
                System.out.println(String.format("%s != %s, index %d", actual[i], expected[i], i));
                fail = true;
            }

            if (fail) {
                break;
            }
        }

        if (fail) {
            System.out.println(">>>>>>>>>>>>>>>>>> FAILURE <<<<<<<<<<<<<<<<<<<<<<");
            System.out.println(String.format("Expected: '%s'.", new String(expected)));
            System.out.println(String.format("Actual: '%s'.", new String(actual)));
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>> SUCCESS <<<<<<<<<<<<<<<<<<<<<<");
        }
    }
}
