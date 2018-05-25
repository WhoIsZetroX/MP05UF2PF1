package ex2;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

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
            //ERROR: La variable size nunca amuenta ni disminuye, para esto debemos hacer que aumente +1 al hacer put
            this.size++;
        }
        else {
            //ERROR: Al poner un item con la misma clave no se actualizaba si no que se añadía a ala siguiente posición
            //ERROR: s'ha de comprovar si el ID es el mateix per actualitzar l'element existent
            HashEntry temp = entries[hash];
            boolean found = false;
            do{
                if(temp.key == key){
                    temp.value = value;
                    found = true;
                    break;
                }
                else if(temp.next != null)
                    temp = temp.next;

            }while (temp.next != null || temp.key.equals(key)); //ERROR: No actualizaba el ultimo, lo que hacia era añadir otro igual

            if(!found) {
                temp.next = hashEntry;
                hashEntry.prev = temp;
                this.size++;     //ERROR: faltaba incrementar el comptador
            }
        }

    }

    /**
     * Returns 'null' if the element is not found.
     */
    public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            HashEntry temp = entries[hash];

            while( !temp.key.equals(key) && temp.next != null) //ERROR: Si se encontraba una posición nula peta por lo que hay que decirle que si encuentra un valor nulo no siga
                temp = temp.next;

            //ERROR: Si encuentra un valor nulo no lo devuelve
            if (temp.key==key)
                return temp.value;
            else return null;
        }

        return null;
    }

    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            HashEntry temp = entries[hash];
            while( !temp.key.equals(key) && temp.next != null) //ERROR: Para que no siga al salir
                temp = temp.next;

            if (temp.key!=key) return;
            else{   //ERROR: No borraba bien en según que posición estuviese

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
                size--; //ERROR: La variable size nunca amuenta ni disminuye, para esto debemos hacer que disminuya -1 al hacer drop



                /*if (temp.prev == null ) {
                    entries[hash] = null;             //esborrar element únic (no col·lissió)
                    size--; //ERROR: La variable size nunca amuenta ni disminuye, para esto debemos hacer que disminuya -1 al hacer drop
                }
                else {
                    if (temp.next != null)
                        temp.next.prev = temp.prev;   //esborrem temp, per tant actualitzem l'anterior al següent
                    temp.prev.next = temp.next;                         //esborrem temp, per tant actualitzem el següent de l'anterior
                }*/
                //size--; //ERROR: La variable size nunca amuenta ni disminuye, para esto debemos hacer que disminuya -1 al hacer drop
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

}