import java.util.function.Function;

public class Test09 {

    static final String[] WORDS = Generator.INSTANCE.generate();

    static boolean warmUp = true;

    public static void main(final String[] args) {
        // Warm up
        run(null);
        run((s) -> s.length());
        run((s) -> s.hashCode());
        warmUp = false;

        System.out.println("### Hash based on default hash ###");
        run(null);
        System.out.println();
        System.out.println("### Hash based on length ###");
        run((s) -> s.length());
        System.out.println();
        System.out.println("### Hash based on String.hashCode() ###");
        run((s) -> s.hashCode());
    }

    static void run(final Function<String, Integer> hashFunction) {
        Long begin = System.currentTimeMillis();
        final Hashtable hashTable = hashFunction == null ? new Hashtable() : new Hashtable(hashFunction);

        for (int i = 0; i < WORDS.length; i++) {
            hashTable.add(WORDS[i], "v");
        }

        if (!warmUp) {
            System.out.println("Time: " + (System.currentTimeMillis() - begin) + "ms");
            System.out.println("Hash collisions: " + hashTable.collisions);
        }
    }
}
