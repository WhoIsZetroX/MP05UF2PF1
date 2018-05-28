package ex3;

import org.junit.jupiter.api.Assertions;

class HashTableTest {



    @org.junit.jupiter.api.Test
    void put() {
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Miramos el tamaño
        Assertions.assertEquals(0, hashTable.size());

        //Añadimos un item a la hashtable
        hashTable.put("a","Aleix");
        //Miramos el tamaño
        Assertions.assertEquals(1, hashTable.size());
        //Comprobamos que en donde nos ha colocado la clave a no esté vacío
        Assertions.assertNotNull(hashTable.get("a"));
        //Comprobamos que en donde nos ha colocado la clave a está la persona que hemos creado
        Assertions.assertEquals("Aleix", hashTable.get("a"));
        Assertions.assertEquals(1, hashTable.size());

        //Añadimos otro en el mismo sitio para ver si lo actualiza
        hashTable.put("ab","Lala");
        Assertions.assertEquals("Lala", hashTable.get("ab"));

        //Añadimos el 3r item
        hashTable.put("abb","KAKA");
        Assertions.assertEquals("KAKA", hashTable.get("abb"));

        //Actualizamos el 1r item
        hashTable.put("a","KOKO");
        Assertions.assertEquals("KOKO", hashTable.get("a"));

        //Actualizamos el 2o item
        hashTable.put("ab","Lolo");

        //Actualizamos el 3r item
        hashTable.put("abb","BRIAN");

        //Despues de insertar algunos items lo que haremos será mirar el tamaño
        Assertions.assertEquals(3, hashTable.size());
        System.out.println(hashTable.toString());

    }

    @org.junit.jupiter.api.Test
    void get() {
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Añadimos un item a la hashtable
        hashTable.put("a","Aleix");
        hashTable.put("a","Brian");
        hashTable.put("b","Raul");

        //Comprobamos que en donde nos ha colocado la clave 27 no esté vacío
        Assertions.assertNotNull(hashTable.get("b"));
        Assertions.assertNotNull(hashTable.get("a"));
        Assertions.assertNotNull(hashTable.get("a"));

        //Comprobamos la linked list que se van añadiendo uno despues de otro
        Assertions.assertEquals("\n bucket[0] = [a, Brian]\n bucket[1] = [b, Raul]", hashTable.toString());
        Assertions.assertEquals("Raul", hashTable.get("b"));
        hashTable.put("c","lala");
        Assertions.assertEquals("\n bucket[0] = [a, Brian]\n bucket[1] = [b, Raul]\n bucket[2] = [c, lala]", hashTable.toString());
        //Comprobamos que en donde nos ha colocado la clave 27 está la persona que hemos creado
        Assertions.assertEquals("Brian", hashTable.get("a"));

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
        hashTable.drop("a");
        hashTable.put("b","Brian");
        hashTable.put("c","Raul");

        //Comprobamos que en donde nos ha colocado la clave 27 está vacío
        Assertions.assertNotNull(hashTable.get("c"));
        Assertions.assertNotNull(hashTable.get("b"));
        Assertions.assertNull(hashTable.get("a"));

        //Comprobamos que en donde nos ha colocado la clave 27 ahora es null
        Assertions.assertEquals(null, hashTable.get("a"));
        Assertions.assertEquals("Raul", hashTable.get("c"));
        Assertions.assertEquals("Brian", hashTable.get("b"));

        hashTable.put("a","Brian");
        hashTable.put("ab","Raul");
        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
        hashTable.put("abb","Aleix");
        hashTable.drop("abb");
        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borrar primero:        Assertions.assertEquals("\n bucket[0] = [ab, Raul] -> [abb, Aleix]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borra por el medio                        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [abb, Aleix]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borra al final        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
        //Assertions.assertNull(hashTable.get("a"));
        //Assertions.assertEquals(null, hashTable.get("a"));
    }

}