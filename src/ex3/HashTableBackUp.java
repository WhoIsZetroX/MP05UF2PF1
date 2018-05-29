package ex3;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

//Los cambios que se han hecho se pueden buscar más facilmente si se buscan las palabras: "CAMBIO" y "MILLORA"

public class HashTableBackUp {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    public int size(){
        return this.size;
    }

    public int real_size(){
        return this.INITIAL_SIZE;
    }

    //CAMBIO: Ahora se le pasa un value de tipo object
    public void put(String key, Object value) { //    public void put(String key, String value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);
        if(entries[hash] == null) {
            entries[hash] = hashEntry;
            this.size++;
        }
        else {//MILLORA: He hecho que ahora si hay colisión se duplique la tabla
            // Si la key que queremos introducir ya existe actualizamos el valor
            if (entries[hash].key == key){
                entries[hash].value = value;
            }else {// Si hay colision y no es la misma clave duplicamos la lista
                INITIAL_SIZE = INITIAL_SIZE * 2;
                HashEntry[] entries2 = new HashEntry[INITIAL_SIZE];

                if (entries[hash] == null)
                    entries[hash] = hashEntry;

                for (int i = 0; i < entries.length; i++) {
                    HashEntry he = entries[i];
                    if (he != null) {
                        int new_index = getHash(he.key);
                        entries2[new_index] = he;

                    }
                }
                entries = entries2;
                put(key, value);
            }
            //MILLORA: He hecho que ahora si hay colisión se duplique la tabla
            /*HashEntry temp = entries[hash];
            boolean found = false;
            do{
                if(temp.key == key){
                    temp.value = value;
                    found = true;
                    break;
                }
                else if(temp.next != null)
                    temp = temp.next;

            }while (temp.next != null || temp.key.equals(key));

            if(!found) {
                temp.next = hashEntry;
                hashEntry.prev = temp;
                this.size++;
            }*/
        }

    }

    /**
     * Returns 'null' if the element is not found.
     */
    public Object get(String key) {//CAMBIO: public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = getHashEntry(key, entries[hash]);

            if (temp.key==key)
                return temp.value;
            else return null;
        }

        return null;
    }

    private HashEntry getHashEntry(String key, HashEntry entry) {
        HashEntry temp = entry;
        while( !temp.key.equals(key) && temp.next != null)
            temp = temp.next;
        return temp;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = getHashEntry(key, entries[hash]);

            if (temp.key!=key) return;
            else{

                //Borrar si es el primero
                if (temp.prev==null) {
                    //Borrar si es el primero
                    entries[hash] = temp.next;             //esborrar element únic (no col·lissió)
                }else if (temp.next==null){
                    //Borrar si es el ultimo
                    temp.prev.next=null;
                }else {
                    //Borrar si está en medio
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                size--;
            }
        }
    }

    private int getHash(String key) {
        // piggy backing on java string
        // hashcode implementation.
        return key.hashCode() % INITIAL_SIZE;
    }

    private class HashEntry {
        String key;
        //CAMBIO: comentado porque está cambiado a object
        //String value;
        Object value;

        // Linked list of same hash entries.
        HashEntry next;
        HashEntry prev;

        //CAMBIO: Ahora el metodo se le pasa un object
        public HashEntry(String key, Object value) { // public HashEntry(String key, String value) {
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

}