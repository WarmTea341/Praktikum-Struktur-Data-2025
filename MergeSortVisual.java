/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kode;
import java.util.Arrays;

public class MergeSortVisual {

    public static void main(String[] args) {
        int[] data = {38, 27, 43, 3, 9, 82, 10};
        System.out.println(">> Data Awal: " + Arrays.toString(data));
        System.out.println("---------------------------------------------");
        
        // Mulai sort dengan level indentasi 0
        mergeSort(data, 0, data.length - 1, 0);
        
        System.out.println("---------------------------------------------");
        System.out.println(">> Hasil Akhir: " + Arrays.toString(data));
    }

    // Tambahkan parameter 'level' untuk mengatur spasi (indentasi)
    public static void mergeSort(int[] arr, int left, int right, int level) {
        // Membuat String spasi berdasarkan kedalaman rekursi
        String indent = "";
        for (int i = 0; i < level; i++) indent += "    |"; // Visualisasi garis pohon

        if (left < right) {
            int mid = (left + right) / 2;

            // VISUALISASI 1: Saat memecah (Divide)
            System.out.println(indent + "--> Pecah: " + printSub(arr, left, right));

            // Rekursif ke kiri (level + 1)
            mergeSort(arr, left, mid, level + 1);
            
            // Rekursif ke kanan (level + 1)
            mergeSort(arr, mid + 1, right, level + 1);

            // VISUALISASI 2: Saat menggabung (Merge)
            merge(arr, left, mid, right);
            System.out.println(indent + "<-- Gabung: " + printSub(arr, left, right));
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        // (Logika Merge standar - sama seperti sebelumnya)
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Helper untuk mencetak sebagian array saja (agar rapi)
    private static String printSub(int[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]);
            if (i < end) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}