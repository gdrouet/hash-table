import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test07 {

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

        final char[] expected = ("display(), (3/16) entries in the table:\n" +
                " foo(len=3, index=4) = valeur-foo\n" +
                " oof(len=3, index=4) = valeur-oof\n" +
                " ofo(len=3, index=4) = valeur-ofo\n" +
                " bar(len=3, index=5) = valeur-bar\n" +
                " baz(len=3, index=13) = valeur-baz\n" +
                "\n" +
                "5 elements displayed.\ndisplay(), (1/16) entries in the table:\n" +
                " oof(len=3, index=4) = valeur-oof\n" +
                " ofo(len=3, index=4) = valeur-ofo\n" +
                "\n" +
                "2 elements displayed.\n").toCharArray();

        hashTable.add("foo", "valeur-foo");
        hashTable.add("oof", "valeur-oof");
        hashTable.add("ofo", "valeur-ofo");
        hashTable.add("bar", "valeur-bar");
        hashTable.add("baz", "valeur-baz");
        hashTable.display();

        hashTable.remove("foo");
        hashTable.remove("baz");
        hashTable.remove("bar");
        hashTable.remove("unkwn");
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
