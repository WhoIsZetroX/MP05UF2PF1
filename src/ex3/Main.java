package ex3;

public class Main {

    //REFACCIÓ: Extracción del main y el metodo log porque he considerado mejor dejar el main en otra clase a parte
    // y el metodo log porque solo lo usa el main

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        log("****   HashTable  ***");
        log(hashTable.toString());
        log("\nValue for key(20) : " + hashTable.get("20"));
    }

    private static void log(String msg) {
        System.out.println(msg);
    }

}