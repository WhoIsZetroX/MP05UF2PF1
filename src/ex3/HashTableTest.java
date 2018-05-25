package ex3;

import org.junit.jupiter.api.Assertions;

class HashTableTest {



    @org.junit.jupiter.api.Test
    void put() {
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Añadimos un item a la hashtable
        hashTable.put("a","Aleix");
        //Comprobamos que en donde nos ha colocado la clave 27 no esté vacío
        Assertions.assertNotNull(hashTable.get("a"));
        //Comprobamos que en donde nos ha colocado la clave 27 está la persona que hemos creado
        Assertions.assertEquals("Aleix", hashTable.get("a"));
        Assertions.assertEquals(1, hashTable.size());

        hashTable.put("a","Lala");
        Assertions.assertEquals("Lala", hashTable.get("a"));

        //Despues de insertar algunos items lo que haremos será mirar el tamaño
        Assertions.assertEquals(1, hashTable.size());

    }

    @org.junit.jupiter.api.Test
    void get() {
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Añadimos un item a la hashtable
        hashTable.put("27","Aleix");
        hashTable.put("27","Brian");
        hashTable.put("25","Raul");

        //Comprobamos que en donde nos ha colocado la clave 27 no esté vacío
        Assertions.assertNotNull(hashTable.get("25"));
        Assertions.assertNotNull(hashTable.get("27"));
        Assertions.assertNotNull(hashTable.get("27"));

        //Comprobamos la linked list que se van añadiendo uno despues de otro
        Assertions.assertEquals("\n bucket[0] = [25, Raul]\n bucket[1] = [27, Brian]", hashTable.toString());
        Assertions.assertEquals("Raul", hashTable.get("25"));
        hashTable.put("24","lala");
        Assertions.assertEquals("\n bucket[0] = [24, lala]\n bucket[1] = [25, Raul]\n bucket[2] = [27, Brian]", hashTable.toString());
        //Comprobamos que en donde nos ha colocado la clave 27 está la persona que hemos creado
        Assertions.assertEquals("Brian", hashTable.get("27"));

        hashTable.put("a","a");
        //hashTable.put("q","q");
        Assertions.assertEquals("a", hashTable.get("a"));
        //Assertions.assertEquals("q", hashTable.get("q"));
        //Assertions.assertEquals("\n bucket[0] = [25, Raul]\n bucket[1] = [27, Brian]", hashTable.toString());




    }

    @org.junit.jupiter.api.Test
    void drop() {
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Eliminamos la persona de la clave de la persona de antes
        hashTable.drop("27");
        hashTable.put("26","Brian");
        hashTable.put("25","Raul");

        //Comprobamos que en donde nos ha colocado la clave 27 está vacío
        Assertions.assertNotNull(hashTable.get("25"));
        Assertions.assertNotNull(hashTable.get("26"));
        Assertions.assertNull(hashTable.get("27"));

        //Comprobamos que en donde nos ha colocado la clave 27 ahora es null
        Assertions.assertEquals(null, hashTable.get("27"));
        Assertions.assertEquals("Raul", hashTable.get("25"));
        Assertions.assertEquals("Brian", hashTable.get("26"));

        hashTable.put("27","Brian");
        hashTable.put("27","Raul");
        hashTable.drop("27");
        Assertions.assertNull(hashTable.get("27"));
        Assertions.assertEquals(null, hashTable.get("27"));
    }

}