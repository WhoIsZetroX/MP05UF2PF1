package ex3;

import org.junit.jupiter.api.Assertions;

class HashTableTest {



    @org.junit.jupiter.api.Test
    void put() {
        System.out.println("\n######## PUT ########\n ");
        //Creamos un objeto de la clase Hashtable
        HashTable hashTable = new HashTable();
        //Miramos el tamaño
        Assertions.assertEquals(0, hashTable.size());
        System.out.println(hashTable.real_size());

        //Añadimos un item a la hashtable
        hashTable.put("a","Aleix");
        System.out.println(hashTable.real_size());

        //Miramos el tamaño
        Assertions.assertEquals(1, hashTable.size());
        //Comprobamos que en donde nos ha colocado la clave a no esté vacío
        Assertions.assertNotNull(hashTable.get("a"));
        //Comprobamos que en donde nos ha colocado la clave a está la persona que hemos creado
        Assertions.assertEquals("Aleix", hashTable.get("a"));
        Assertions.assertEquals(1, hashTable.size());

        //Añadimos otro en el mismo sitio para ver si lo actualiza
        hashTable.put("ab",45);
        System.out.println(hashTable.real_size());
        Assertions.assertEquals(45, hashTable.get("ab"));

        //Añadimos el 3r item
        hashTable.put("abb","KAKA");
        System.out.println(hashTable.real_size());
        Assertions.assertEquals("KAKA", hashTable.get("abb"));

        //Actualizamos el 1r item
        hashTable.put("a",true);
        System.out.println(hashTable.real_size());
        Assertions.assertEquals(true, hashTable.get("a"));

        //Actualizamos el 2o item
        hashTable.put("ab",99.85f);
        System.out.println(hashTable.real_size());

        //Actualizamos el 3r item
        hashTable.put("abb","BRIAN");

        //Despues de insertar algunos items lo que haremos será mirar el tamaño
        Assertions.assertEquals(3, hashTable.size());
        System.out.println(hashTable.real_size());
        System.out.println(hashTable.toString());

    }

    @org.junit.jupiter.api.Test
    void get() {
        System.out.println("\n######## GET ########\n ");
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
        System.out.println(hashTable.toString());
        //Assertions.assertEquals("q", hashTable.get("q"));
        //Assertions.assertEquals("\n bucket[0] = [25, Raul]\n bucket[1] = [27, Brian]", hashTable.toString());




    }

    @org.junit.jupiter.api.Test
    void drop() {
        System.out.println("\n######## DROP ########\n ");

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

        //MEtemos todos los usuarios de la linked list
        hashTable.put("a","Brian");
        hashTable.put("ab","Raul");
        //Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
        hashTable.put("abb","Aleix");
        System.out.println("TODO:"+hashTable.toString());

        //Borramos el ultimo y comprobamos
        hashTable.drop("abb");
        System.out.println("\nBorramos abb"+hashTable.toString());

        //Lo volvemos a meter
        hashTable.put("abb","Aleix");
        System.out.println("TODO:"+hashTable.toString());

        //Borramos el del medio y comprobamos
        hashTable.drop("ab");
        System.out.println("\nBorramos el ab"+hashTable.toString());

        //Lo volvemos a meter
        hashTable.put("ab","Raul");
        System.out.println("TODO:"+hashTable.toString());

        //Borramos el primero y comprobamos
        hashTable.drop("a");
        System.out.println("\nBorramos a"+hashTable.toString());

        //Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borrar primero:        Assertions.assertEquals("\n bucket[0] = [ab, Raul] -> [abb, Aleix]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borra por el medio                        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [abb, Aleix]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
//Borra al final        Assertions.assertEquals("\n bucket[0] = [a, Brian] -> [ab, Raul]\n bucket[1] = [b, Brian]\n bucket[2] = [c, Raul]", hashTable.toString());
        //Assertions.assertNull(hashTable.get("a"));
        //Assertions.assertEquals(null, hashTable.get("a"));
    }

}