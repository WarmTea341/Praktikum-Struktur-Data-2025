/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kode;
// ---------------------------------------------------------
// Nama File: HashVisualApp.java
// Deskripsi: Visualisasi Tabrakan Data (Collision) & Linear Probing
// ---------------------------------------------------------

import java.util.Arrays;

class VisualHashTable {
    private Integer[] hashArray;
    private int arraySize;

    public VisualHashTable(int size) {
        arraySize = size;
        hashArray = new Integer[arraySize]; // null berarti kosong
    }

    public void displayTable() {
        System.out.println("\n+---------------------------------------+");
        System.out.print("| Index |");
        for(int j=0; j<arraySize; j++) System.out.printf(" %2d |", j);
        System.out.println("\n| Data  |");
        for(int j=0; j<arraySize; j++) {
            if(hashArray[j] == null) System.out.print(" -- |");
            else System.out.printf(" %2d |", hashArray[j]);
        }
        System.out.println("\n+---------------------------------------+");
    }

    public int hashFunc(int key) {
        return key % arraySize;
    }

    public void insert(int key) {
        int hashVal = hashFunc(key);
        int originalHash = hashVal;
        int step = 1;

        System.out.println("\n>> INSERT: " + key);
        System.out.println("   1. Hitung Hash: " + key + " % " + arraySize + " = Indeks [" + hashVal + "]");

        // Logika Linear Probing dengan Visualisasi Langkah
        while (hashArray[hashVal] != null) {
            System.out.println("   2. Cek Indeks [" + hashVal + "]: TERISI oleh angka " + hashArray[hashVal]);
            System.out.println("      -> TERJADI COLLISION (Tabrakan)!");
            System.out.println("      -> Probing (Geser) ke kanan...");
            
            ++hashVal;              // Geser
            hashVal %= arraySize;   // Wrap around (balik ke 0 jika mentok)
            
            if(hashVal == originalHash) {
                System.out.println("   Error: Tabel Penuh!");
                return;
            }
        }

        // Menemukan tempat kosong
        hashArray[hashVal] = key;
        System.out.println("   3. Cek Indeks [" + hashVal + "]: KOSONG.");
        System.out.println("   -> SUKSES: Data " + key + " masuk di Indeks [" + hashVal + "]");
    }
}

public class HashVisualApp {
    public static void main(String[] args) {
        // Ukuran tabel 10 agar mudah menghitung modulo
        VisualHashTable table = new VisualHashTable(10);

        System.out.println("=== DEMO HASH TABLE (LINEAR PROBING) ===");
        table.displayTable();

        // 1. Insert Normal (Tanpa Tabrakan)
        table.insert(10); // Masuk ke 0
        table.insert(12); // Masuk ke 2
        table.displayTable();

        // 2. Insert Penyebab Collision (Tabrakan Ringan)
        // 20 % 10 = 0 (Tabrakan sama 10 -> Geser ke 1)
        table.insert(20); 
        table.displayTable();

        // 3. Insert Penyebab Primary Clustering (Tabrakan Beruntun)
        // 30 % 10 = 0 (Tabrakan sama 10 -> 20 -> Masuk ke 3)
        // Harusnya di 0, tapi 0, 1, 2 sudah penuh semua.
        table.insert(30); 
        table.displayTable();
    }
}