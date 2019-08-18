package adt.linkedList.set;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static void main(String args[]){

        SetLinkedListImpl minhaSet = new SetLinkedListImpl();
        SetLinkedListImpl setAuxiliar = new SetLinkedListImpl();

        minhaSet.insert(1);
        minhaSet.insert(3);
        minhaSet.insert(5);
        minhaSet.insert(7);
        minhaSet.insert(5);
        setAuxiliar.insert(2);
        setAuxiliar.insert(4);
        setAuxiliar.insert(6);
        setAuxiliar.insert(8);
        setAuxiliar.insert(6);

        System.out.println(Arrays.toString(minhaSet.toArray()));
        minhaSet.removeDuplicates();
        setAuxiliar.removeDuplicates();
        minhaSet.concatenate(setAuxiliar);
        System.out.println(Arrays.toString(minhaSet.toArray()));
    }

}
