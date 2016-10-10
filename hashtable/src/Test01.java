import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test01 {

    public static void main(final String[] args) {
        final PrintStream out = System.out;
        final CharArrayWriter caw = new CharArrayWriter();

        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                if (b != '\r') {
                    caw.write(b);
                }
            }
        }));

        Hashtable t = new Hashtable((s) -> s.length());

        t.table[1] = new Hashtable.Entry();
        t.table[1].key = "a";
        t.table[1].value = "a-val";

        t.table[2] = new Hashtable.Entry();
        t.table[2].key = "b1";
        t.table[2].value = "b-val";

        t.table[2].next = new Hashtable.Entry();
        t.table[2].next.key = "b2";
        t.table[2].next.value = "b-val";

        t.table[4] = new Hashtable.Entry();
        t.table[4].key = "ccc";
        t.table[4].value = "c-val";

        t.count = 3;

        t.display();
        System.setOut(out);

        final char[] expected = ("display(), (3/16) entries in the table:\n" +
                " a(len=1, index=1) = a-val\n" +
                " b1(len=2, index=2) = b-val\n" +
                " b2(len=2, index=2) = b-val\n" +
                " ccc(len=3, index=4) = c-val\n" +
                "\n" +
                "4 elements displayed.\n").toCharArray();

        final char[] actual = caw.toCharArray();
        boolean fail = false;

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
