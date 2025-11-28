/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kode;
// ---------------------------------------------------------
// Nama File: OrderedApp.java
// Deskripsi: Implementasi Ordered Array dan Binary Search
// ---------------------------------------------------------

class OrderedArray {
    private long[] a;                 // Referensi ke array
    private int nElemen;              // Jumlah item data saat ini

    // Constructor
    public OrderedArray(int max) {
        a = new long[max];            // Membuat array
        nElemen = 0;                  // Inisialisasi jumlah elemen 0
    }

    // ---------------------------------------------------------
    // Method size(): Mengembalikan jumlah elemen
    // ---------------------------------------------------------
    public int size() {
        return nElemen;
    }

    // ---------------------------------------------------------
    // Method find(): Menggunakan BINARY SEARCH
    // Kompleksitas Waktu: O(log N)
    // ---------------------------------------------------------
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElemen - 1;
        int curIn;

        while (true) {
            curIn = (lowerBound + upperBound) / 2; // Mencari titik tengah
            
            if (a[curIn] == searchKey) {
                return curIn;              // Data ditemukan (kembalikan indeks)
            } else if (lowerBound > upperBound) {
                return nElemen;            // Data tidak ditemukan (batas saling melewati)
            } else {
                // Membagi range pencarian menjadi dua
                if (a[curIn] < searchKey) {
                    lowerBound = curIn + 1; // Cari di separuh atas
                } else {
                    upperBound = curIn - 1; // Cari di separuh bawah
                }
            }
        }
    }

    // ---------------------------------------------------------
    // Method insert(): Menjaga keteraturan data (ORDERED)
    // Kompleksitas Waktu: O(N) karena ada proses shifting
    // ---------------------------------------------------------
    public void insert(long value) {
        int j;
        
        // 1. Linear Search untuk menemukan posisi yang tepat
        for (j = 0; j < nElemen; j++) {
            if (a[j] > value) break;
        }
        
        // 2. Menggeser elemen yang lebih besar ke atas (Shifting)
        for (int k = nElemen; k > j; k--) {
            a[k] = a[k - 1];
        }
        
        // 3. Menyisipkan nilai baru
        a[j] = value;
        nElemen++;
    }

    // ---------------------------------------------------------
    // Method display(): Menampilkan isi array
    // ---------------------------------------------------------
    public void display() {
        for (int j = 0; j < nElemen; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println("");
    }
}

// ---------------------------------------------------------
// Class Utama dengan Main Method
// ---------------------------------------------------------
public class OrderedApp {
    public static void main(String[] args) {
        int maxSize = 100;             // Ukuran array
        OrderedArray arr = new OrderedArray(maxSize);

        // 1. Simulasi Insert Data Acak
        // Meskipun di-insert acak, data akan tersusun otomatis
        System.out.println(">> Memasukkan data secara acak...");
        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert(00);
        arr.insert(66);
        arr.insert(33);

        // 2. Menampilkan Array (Bukti Ordered Array)
        System.out.print(">> Isi Array (Terurut Otomatis): \n");
        arr.display();
        System.out.println("---------------------------------");

        // 3. Simulasi Pencarian (Binary Search)
        long searchKey = 55; // Data yang ada
        System.out.println(">> Mencari data: " + searchKey);
        
        if (arr.find(searchKey) != arr.size()) {
            System.out.println("   Ditemukan pada indeks: " + arr.find(searchKey));
        } else {
            System.out.println("   Data tidak ditemukan.");
        }

        // 4. Simulasi Pencarian Data yang Tidak Ada
        searchKey = 90; // Data yang tidak ada
        System.out.println("\n>> Mencari data: " + searchKey);
        
        if (arr.find(searchKey) != arr.size()) {
            System.out.println("   Ditemukan pada indeks: " + arr.find(searchKey));
        } else {
            System.out.println("   Status: Data tidak ditemukan.");
        }
    }
}