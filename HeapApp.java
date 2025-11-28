package kode;

class Node {
    private int data;

    public Node(int key) {
        data = key;
    }

    public int getKey() {
        return data;
    }
}

class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int size) {
        maxSize = size;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    // -------------------------------------------------------
    // 1. INSERT: Masukkan di akhir, lalu Trickle UP
    // -------------------------------------------------------
    public boolean insert(int key) {
        if (currentSize == maxSize) return false; // Penuh

        Node newNode = new Node(key);
        heapArray[currentSize] = newNode; // Taruh di index terakhir
        trickleUp(currentSize++);         // Naikkan ke posisi tepat
        return true;
    }

    // Logika menaikkan node (Promosi)
    public void trickleUp(int index) {
        int parent = (index - 1) / 2;
        Node bottom = heapArray[index];

        // Selama node baru lebih besar dari parent-nya, tukar
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent]; // Parent turun
            index = parent;                       // Pindah fokus ke atas
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom; // Tempatkan node baru
    }

    // -------------------------------------------------------
    // 2. REMOVE: Hapus Root, ganti dengan yg akhir, Trickle DOWN
    // -------------------------------------------------------
    public Node remove() {
        if (isEmpty()) return null;

        Node root = heapArray[0]; // Simpan root (Max value)
        
        // Pindahkan node terakhir ke posisi root
        heapArray[0] = heapArray[--currentSize]; 
        
        // Turunkan node pengganti ke posisi tepat (Demosi)
        trickleDown(0);
        
        return root;
    }

    // Logika menurunkan node
    public void trickleDown(int index) {
        int largerChild;
        Node top = heapArray[index]; // Node pengganti (yg tadinya di akhir)

        // Selama belum sampai paling bawah (masih punya setidaknya 1 anak)
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            // Tentukan anak mana yang lebih besar
            if (rightChild < currentSize && 
                heapArray[leftChild].getKey() < heapArray[rightChild].getKey()) {
                largerChild = rightChild;
            } else {
                largerChild = leftChild;
            }

            // Jika node top sudah lebih besar dari anak terbesar, berhenti
            if (top.getKey() >= heapArray[largerChild].getKey()) {
                break;
            }

            // Jika tidak, anak naik menggantikan posisi parent
            heapArray[index] = heapArray[largerChild];
            index = largerChild; // Lanjut turun ke bawah
        }
        heapArray[index] = top; // Tempatkan node di posisi final
    }

    // Visualisasi sederhana array
    public void displayArray() {
        System.out.print("Isi Heap Array: ");
        for (int j = 0; j < currentSize; j++) {
            System.out.print(heapArray[j].getKey() + " ");
        }
        System.out.println("");
    }
}

public class HeapApp {
    public static void main(String[] args) {
        Heap theHeap = new Heap(10);

        System.out.println(">> Memasukkan data acak: 70, 40, 50, 20, 60, 100, 80, 30, 10, 90");
        theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100); // Akan naik jadi Root
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        theHeap.displayArray();
        
        // Skenario Priority Queue: Mengambil data prioritas tertinggi
        System.out.println("\n>> Simulasi Remove (Priority Queue):");
        
        Node removedNode = theHeap.remove();
        System.out.println("1. Mengambil (Remove): " + removedNode.getKey() + " (Harusnya 100)");
        theHeap.displayArray();

        removedNode = theHeap.remove();
        System.out.println("2. Mengambil (Remove): " + removedNode.getKey() + " (Harusnya 90)");
        theHeap.displayArray();
    }
}