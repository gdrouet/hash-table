import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test04 {

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

        final char[] expected = ("display(), (6/16) entries in the table:\n" +
                " tutu(len=4, index=2) = valeur-tutu\n" +
                " foo(len=3, index=4) = valeur-foo\n" +
                " oof(len=3, index=4) = valeur-oof\n" +
                " ofo(len=3, index=4) = valeur-ofo\n" +
                " bar(len=3, index=5) = valeur-bar\n" +
                " toto(len=4, index=6) = valeur-toto\n" +
                " titi(len=4, index=10) = valeur-titi\n" +
                " baz(len=3, index=13) = valeur-baz\n" +
                "\n" +
                "8 elements displayed.\n").toCharArray();

        hashTable.add("toto", "valeur-toto");
        hashTable.add("foo", "valeur-foo");
        hashTable.add("bar", "valeur-bar");
        hashTable.add("baz", "valeur-baz");
        hashTable.add("titi", "valeur-titi");
        hashTable.add("tutu", "valeur-tutu");
        hashTable.add("oof", "valeur-oof");
        hashTable.add("ofo", "valeur-ofo");
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
