package ex3;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

import javax.naming.InitialContext;

public class HashTable {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    //ERROR: La variable size nunca amuenta ni disminuye, para esto debemos hacer que aumente +1 al hacer put y al eliminar que reste -1
    public int size(){
        return this.size;
    }

    public int real_size(){
        return this.INITIAL_SIZE;
    }

    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);
        if(entries[hash] == null) {
            entries[hash] = hashEntry;
            this.size++;
        }
        else {
            INITIAL_SIZE = INITIAL_SIZE *2;
            HashEntry[] entries2 = new HashEntry[INITIAL_SIZE];
            for (int i = 0; i<entries2.length; i++){
                System.out.println(entries[i].key+"-"+entries[i].value);
                Item entry = new Item(entries[i].key, entries[i].value);
                if (entry!=null){
                    int new_index = getHash(entry.key);
                    entries2[new_index].key = entry.key;
                    entries2[new_index].value = entry.value;
                }
            }
            entries = entries2;
            Item item = new Item(key, value);
            putObj(item);

        }
    }

    //MILLORA: He creado este nuevo metodo por si se le pasa un objeto que sepa detectarlo.
     public void putObj(Item item) {
        int hash = getHash(item.key);
        final HashEntry hashEntry = new HashEntry(item.key, item.value);
        if(entries[hash] == null) {
            entries[hash] = hashEntry;
            this.size++;
        }
        else {

        }
    }


    /**
     * Returns 'null' if the element is not found.
     */
    public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            temp = getHashEntry(key, temp);

            if (temp.key==key)
                return temp.value;
            else return null;
        }

        return null;
    }

    private HashEntry getHashEntry(String key, HashEntry temp) {
        while( !temp.key.equals(key) && temp.next != null) //ERROR: Si se encontraba una posición nula peta por lo que hay que decirle que si encuentra un valor nulo no siga
            temp = temp.next;
        return temp;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];
            temp = getHashEntry(key, temp);

            if(temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
            else{
                if(temp.next != null) temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
            }
        }
        size--;
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {
        String key;
        String value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    @Override
    public String toString() {
        int bucket = 0;
        StringBuilder hashTableStr = new StringBuilder();
        for (HashEntry entry : entries) {
            if(entry == null) {
                continue;
            }
            hashTableStr.append("\n bucket[")
                    .append(bucket)
                    .append("] = ")
                    .append(entry.toString());
            bucket++;
            HashEntry temp = entry.next;
            while(temp != null) {
                hashTableStr.append(" -> ");
                hashTableStr.append(temp.toString());
                temp = temp.next;
            }
        }
        return hashTableStr.toString();
    }

    //TODO:
    protected static void log(String msg) {
        System.out.println(msg);
    }
}