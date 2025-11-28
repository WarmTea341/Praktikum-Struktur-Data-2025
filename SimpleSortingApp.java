package kode;

// ---------------------------------------------------------
// Nama File: SimpleSortingApp.java
// Deskripsi: Implementasi Bubble, Selection, dan Insertion Sort
// ---------------------------------------------------------
import java.util.Arrays;

class SortAlgo {
    
    // 1. BUBBLE SORT
    // Membandingkan tetangga dan menukar jika salah urutan
    public void bubbleSort(long[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Proses Swap
                    long temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 2. SELECTION SORT
    // Mencari nilai minimum, lalu menukar ke posisi depan
    public void selectionSort(long[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            // Mencari elemen terkecil di sisa array
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            // Menukar elemen terkecil dengan elemen pertama di bagian unsorted
            long temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // 3. INSERTION SORT
    // Menyisipkan elemen ke posisi yang tepat (digeser)
    public void insertionSort(long[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            long key = arr[i];
            int j = i - 1;

            // Geser elemen yang lebih besar dari key ke kanan
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}

public class SimpleSortingApp {
    public static void main(String[] args) {
        SortAlgo sorter = new SortAlgo();
        
        // Data awal (kita gunakan clone agar datanya sama untuk tiap algo)
        long[] data1 = {64, 34, 25, 12, 22, 11, 90};
        long[] data2 = data1.clone();
        long[] data3 = data1.clone();

        System.out.println("Data Awal: " + Arrays.toString(data1));
        System.out.println("---------------------------------------");

        // Tes Bubble Sort
        sorter.bubbleSort(data1);
        System.out.println("Hasil Bubble Sort:    " + Arrays.toString(data1));

        // Tes Selection Sort
        sorter.selectionSort(data2);
        System.out.println("Hasil Selection Sort: " + Arrays.toString(data2));

        // Tes Insertion Sort
        sorter.insertionSort(data3);
        System.out.println("Hasil Insertion Sort: " + Arrays.toString(data3));
    }
}