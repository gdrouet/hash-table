import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test06 {

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

        final char[] expected = ("display(), (20/256) entries in the table:\n" +
                " aaaaaaaa(len=8, index=8) = v\n" +
                " aaaaaaaaaaaaaaaa(len=16, index=16) = v\n" +
                " aaa(len=3, index=35) = v\n" +
                " aaaaaaaaaaa(len=11, index=43) = v\n" +
                " aaaaaaaaaaaaaaaaaaa(len=19, index=51) = v\n" +
                " aaaaaa(len=6, index=70) = v\n" +
                " aaaaaaaaaaaaaa(len=14, index=78) = v\n" +
                " a(len=1, index=97) = v\n" +
                " aaaaaaaaa(len=9, index=105) = v\n" +
                " aaaaaaaaaaaaaaaaa(len=17, index=113) = v\n" +
                " aaaa(len=4, index=132) = v\n" +
                " aaaaaaaaaaaa(len=12, index=140) = v\n" +
                " aaaaaaaaaaaaaaaaaaaa(len=20, index=148) = v\n" +
                " aaaaaaa(len=7, index=167) = v\n" +
                " aaaaaaaaaaaaaaa(len=15, index=175) = v\n" +
                " aa(len=2, index=194) = v\n" +
                " aaaaaaaaaa(len=10, index=202) = v\n" +
                " aaaaaaaaaaaaaaaaaa(len=18, index=210) = v\n" +
                " aaaaa(len=5, index=229) = v\n" +
                " aaaaaaaaaaaaa(len=13, index=237) = v\n" +
                "\n" +
                "20 elements displayed.\n").toCharArray();


        final int start = 1;
        final int end = 20;

        for (int i = start; i <= end; i++) {
            String str = "";

            for (int j = 0;j < i; j++) {
                str += "a";
            }

            hashTable.add(str, "v");
        }

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
