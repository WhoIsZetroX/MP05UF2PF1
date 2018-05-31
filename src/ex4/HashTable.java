package ex4;

// Original source code: https://gist.github.com/amadamala/3cdd53cb5a6b1c1df540981ab0245479
// Modified by Fernando Porrino Serrano for academic purposes.

//Los cambios que se han hecho se pueden buscar mas facilmente si se buscan las palabras: "CAMBIO" y "MILLORA"

/**
 *
 */
public class HashTable {
    private int INITIAL_SIZE = 16;
    private int size = 0;
    private HashEntry[] entries = new HashEntry[INITIAL_SIZE];

    /**
     * Es un metodo que devuelve el numero de elementos que hay en la hastable.
     * @return Retorna un int que es el numero de elementos que hay.
     */
    public int size(){
        return this.size;
    }

    /**
     * Es un metodo que devuelve el tamaño de la hashtable.
     * @return Retorna un int que es el tamaño de la hashtable.
     */
    public int real_size(){
        return this.INITIAL_SIZE;
    }

    /**
     * Es un metodo para meter un item en la hashtable pasandole una key de tipo String y un value tipo Object.
     * En caso de meter un item que no este en la tabla lo pone pero si la key del item ya esta en la tabla entonces actualizara el value.
     * En caso de que por el hash de la key el item colisiones en el mismo sitio entonces la tabla se hara el doble de grande
     * y se intentara añadir, si sigue habiendo colision se volvera a doblar el tamaño y asi hasta que el hash de la key no coincida (hasta que no haya colision).
     * @param key Es una variable tipo String que es la id que tiene cada elemento para diferenciarlos (Como la id).
     * @param value Es una variable tipo Object que es el valor que le pasas que puede ser de cualquier tipo: String, int, float, char...
     */
    public void put(String key, Object value) {
        int hash = getHash(key);
        final HashEntry hashEntry = new HashEntry(key, value);
        if(entries[hash] == null) {
            entries[hash] = hashEntry;
            this.size++;
        }
        else {
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
        }

    }

    /**
     * Es un metodo que busca un elemento dentro de la hastable con la key que le pasas, en caso de encontrarlo te devuelve el valor pero en caso de que no lo encuentre te devuelve 'null'.
     * @param key Es una variable de tipo String que le pasas al metodo para encontrar el item que buscas.
     * @return retorna nulo en caso de que no haya encontrado el item.
     */
    public Object get(String key) {//CAMBIO: public String get(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {

            //CAMBIO: comentamos el metodo que ya no hace falta usar
            /*HashEntry temp = getHashEntry(key, entries[hash]);
            if (temp.key==key)
                return temp.value;
            else return null;
            */
            if (entries[hash].key==key)
                return entries[hash].value;
            else return null;
        }

        return null;
    }

    /**
     * Es un metodo que elimina un item (lo vuelve 'null'), en caso de encontrarlo lo elimina (lo vuelve 'null'), en caso de no encontrarlo no hace nada.
     * @param key Es una variable tipo String que le pasamos para encontrar la posicion del item por el hash de la key.
     */
    public void drop(String key) {
        int hash = getHash(key);
        if(entries[hash] != null) {
            if (entries[hash].key!=key) return;
            else {
                    entries[hash] = null;

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
        //CAMBIO: comentado porque esta cambiado a object
        //String value;
        Object value;

        /**
         * Es el metodo constructor de la clase "HashEntry"
         * @param key Es el identificador del item y es tipo String
         * @param value Es el valor del item y es de tipo Object
         */
        //CAMBIO: Ahora el metodo se le pasa un object
        public HashEntry(String key, Object value) { // public HashEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Es un metodo para mostrar la informacion del HashEntry mas entendible para el usuario mostrandose por consola.
         * @return Devuelve la key y el value en formato = [key, value]
         */
        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    /**
     * Es un metodo para mostrar la informacion de la HasTable mas entendible para el usuario mostrandose por consola.
     * @return Devuelve todos los elementos de la hashtable en caso de que la posicion no sea 'null' y lo devuelve en el formato = bucket[position] = [key, value]
     */
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
        }
        return hashTableStr.toString();
    }

}