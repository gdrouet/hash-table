package com.github.gdrouet.hashtable;

public class Test02 {
    public static void main(final String[] args) {
        Hashtable t = new Hashtable((s) -> s.length());

        t.table[2] = new Hashtable.Entry();
        t.table[2].key = "b1";
        t.table[2].value = "b-val";

        t.table[2].next = new Hashtable.Entry();
        t.table[2].next.key = "b2";
        t.table[2].next.value = "b-val2";

        t.count = 1;

        System.out.println("get(\"b1\"):" + t.get("b1"));
        System.out.println("get(\"b2\"):" + t.get("b2"));
    }
}
