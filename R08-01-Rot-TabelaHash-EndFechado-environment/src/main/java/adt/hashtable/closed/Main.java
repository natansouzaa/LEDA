package adt.hashtable.closed;

import static adt.hashtable.hashfunction.HashFunctionClosedAddressMethod.DIVISION;

public class Main {

    public static void main(String args[]){

        HashtableClosedAddressImpl minhaTabelaFechada = new HashtableClosedAddressImpl(5, DIVISION);
        minhaTabelaFechada.insert(5);

        System.out.println(minhaTabelaFechada.search(5));

    }

}
