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

    //MILLORA: Ahora debemos pasarle un object en vez de String
    public void put(String key, Object value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);

        if (entries[hash] == null) {
            entries[hash] = hashEntry;
            this.size++;
        } else {

            HashEntry temp = entries[hash];
            boolean found = false;
            do {
                if (temp.key == key) {
                    temp.value = value;
                    found = true;
                    break;
                } //else if (temp.key!=key){}
                else if (temp.next != null)
                    temp = temp.next;

            } while (temp.next != null);

            if (!found) {
                temp.next = hashEntry;
                hashEntry.prev = temp;
                this.size++;     //ERROR: faltaba incrementar el comptador
            }
        }
    }


    /**
     * Returns 'null' if the element is not found.
     */
    public Object get(String key) { //MILLORA: Ahora debemos hacer que nos devuelva un object en vez de String
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
        while( !temp.key.equals(key) && temp.next != null)
            temp = temp.next;
        return temp;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];
            temp = getHashEntry(key, temp);

            if (temp.key==key) {

                if (temp.prev == null) entries[hash] = null;             //esborrar element únic (no col·lissió)
                else {
                    if (temp.next != null)
                        temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                    temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
                }
                size--;
            }else return;
        }
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {
        String key;
        //String value;
        //MILLORA: He cambiado el String por Object
        Object value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        //MILLORA: Ahora debemos pasarle un object en vez de String
        public HashEntry(String key, Object value) {
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

    protected static void log(String msg) {
        System.out.println(msg);
    }
}