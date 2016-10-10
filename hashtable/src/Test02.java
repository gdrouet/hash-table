import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Test02 {
    public static void main(final String[] args) {
        final PrintStream out = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }
        }));

        Hashtable t = new Hashtable((s) -> s.length());

        t.table[2] = new Hashtable.Entry();
        t.table[2].key = "b1";
        t.table[2].value = "b-val";

        t.table[2].next = new Hashtable.Entry();
        t.table[2].next.key = "b2";
        t.table[2].next.value = "b-val2";

        t.count = 1;

        System.setOut(out);

        if (t.get("b1").equals("b-val") && t.get("b2").equals("b-val2")) {
            System.out.println(">>>>>>>>>>>>>>>>>> SUCCESS <<<<<<<<<<<<<<<<<<<<<<");
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>> FAILURE <<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("Expected:");
            System.out.println("get(\"b1\"):b-val");
            System.out.println("get(\"b2\"):b-val2");
        }
    }
}
