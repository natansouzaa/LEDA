package adt.linkedList;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String args[]){

        DoubleLinkedListImpl minhaLista = new DoubleLinkedListImpl();
        minhaLista.insert(03);
        minhaLista.insert(12);

        System.out.println(minhaLista.search(3));
        System.out.println(minhaLista.isEmpty());
        System.out.println(minhaLista.size());
        System.out.println(Arrays.toString(minhaLista.toArray()));

    }

}
