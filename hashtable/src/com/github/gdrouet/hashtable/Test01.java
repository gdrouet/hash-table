package com.github.gdrouet.hashtable;

public class Test01 {

    public static void main(final String[] args) {
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
    }
}
