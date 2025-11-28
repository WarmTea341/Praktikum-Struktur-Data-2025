package kode;
class DataItem {
    private int data;

    public DataItem(int data) {
        this.data = data;
    }

    public int getKey() {
        return data;
    }
}

class HashTable {
    private DataItem[] hashArray;
    private int arraySize;
    private DataItem nonItem; // Penanda untuk item yang dihapus

    public HashTable(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        nonItem = new DataItem(-1); // -1 menandakan sel bekas hapus
    }

    // -------------------------------------------------------
    // Fungsi Hash Sederhana (Modulo)
    // Mengubah Key menjadi Indeks
    // -------------------------------------------------------
    public int hashFunc(int key) {
        return key % arraySize;
    }

    // -------------------------------------------------------
    // Method Insert dengan Linear Probing
    // Jika indeks penuh, geser ke sebelah kanan
    // -------------------------------------------------------
    public void insert(int key) {
        DataItem item = new DataItem(key);
        int hashVal = hashFunc(key); // 1. Hitung indeks awal
        int originalHash = hashVal;

        System.out.println(">> Insert " + key + " (Hash awal: " + hashVal + ")");

        // 2. Loop Linear Probing jika terjadi collision
        // Geser terus selama sel tidak kosong dan bukan bekas hapus
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            System.out.println("   Tabrakan di indeks " + hashVal + ", geser ke kanan...");
            ++hashVal;                 // Geser 1 langkah
            hashVal %= arraySize;      // Wrap around (balik ke 0 jika max)
            
            // Mencegah infinite loop jika tabel penuh
            if(hashVal == originalHash) {
                System.out.println("   Error: Tabel Penuh!");
                return;
            }
        }
        
        // 3. Menempatkan item di sel kosong
        hashArray[hashVal] = item;
        System.out.println("   Berhasil masuk di indeks: " + hashVal);
    }

    // -------------------------------------------------------
    // Method Find
    // Mencari dengan merekonstruksi langkah probing
    // -------------------------------------------------------
    public DataItem find(int key) {
        int hashVal = hashFunc(key);

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];
            }
            ++hashVal;
            hashVal %= arraySize;
        }
        return null;
    }

    // -------------------------------------------------------
    // Visualisasi Tabel
    // -------------------------------------------------------
    public void displayTable() {
        System.out.println("\nVisualisasi Hash Table:");
        System.out.print("Indeks: ");
        for (int j = 0; j < arraySize; j++) System.out.printf("%3d ", j);
        System.out.println("");
        
        System.out.print("Isi   : ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null && hashArray[j].getKey() != -1)
                System.out.printf("%3d ", hashArray[j].getKey());
            else
                System.out.print(" -- ");
        }
        System.out.println("\n------------------------------------------------");
    }
}

public class HashTableApp {
    public static void main(String[] args) {
        // Buat tabel ukuran 10 agar mudah menghitung modulo
        HashTable theTable = new HashTable(10);

        // Skenario Tanpa Collision (Modulo 10)
        // 10 % 10 = 0
        // 22 % 10 = 2
        theTable.insert(10);
        theTable.insert(22);
        theTable.displayTable();

        // Skenario Collision (Linear Probing)
        // 30 % 10 = 0 (Tabrakan dengan 10 -> Geser ke 1)
        // 50 % 10 = 0 (Tabrakan dengan 10, lalu 30 -> Geser ke 3) (indeks 2 sudah isi 22)
        theTable.insert(30); 
        theTable.insert(50); 
        
        theTable.displayTable();

        // Cari Data
        int findKey = 50;
        DataItem found = theTable.find(findKey);
        if (found != null) {
            System.out.println("Pencarian: Data " + findKey + " ditemukan.");
        } else {
            System.out.println("Pencarian: Data " + findKey + " tidak ada.");
        }
    }
}