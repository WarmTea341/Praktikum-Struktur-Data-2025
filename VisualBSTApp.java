/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kode;
// ---------------------------------------------------------
// Nama File: VisualBSTApp.java
// Deskripsi: Visualisasi Struktur Binary Search Tree di Konsol
// ---------------------------------------------------------

class TreeNode {
    public int id;              
    public TreeNode leftChild;  
    public TreeNode rightChild; 

    public TreeNode(int id) {
        this.id = id;
    }
}

class VisualTree {
    private TreeNode root;

    public VisualTree() {
        root = null;
    }

    public void insert(int id) {
        TreeNode newNode = new TreeNode(id);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (id < current.id) { // Ke Kiri
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else { // Ke Kanan
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // ---------------------------------------------------------
    // Method Visualisasi (Cetak Pohon Horizontal)
    // ---------------------------------------------------------
    public void displayTree() {
        System.out.println("\n>> Visualisasi Struktur Tree (Putar kepala ke kiri):");
        print2D(root, 0);
    }

    // Fungsi Rekursif untuk mencetak spasi dan node
    private void print2D(TreeNode node, int space) {
        int COUNT = 5; // Jarak antar level (jumlah spasi)

        if (node == null) {
            return;
        }

        // 1. Menambah jarak (indentasi) untuk level berikutnya
        space += COUNT;

        // 2. Proses Anak KANAN dulu (posisi paling atas visual)
        print2D(node.rightChild, space);

        // 3. Cetak Node saat ini setelah spasi
        System.out.print("\n");
        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(node.id);

        // 4. Proses Anak KIRI (posisi paling bawah visual)
        print2D(node.leftChild, space);
    }
}

public class VisualBSTApp {
    public static void main(String[] args) {
        VisualTree theTree = new VisualTree();

        // Skenario Data agar Tree terlihat seimbang dan bagus
        // Root: 50
        System.out.println(">> Memasukkan data...");
        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert(60); // Anak kiri dari 75
        theTree.insert(85); // Anak kanan dari 75
        
        // Tambahan agar lebih dalam
        theTree.insert(5);
        theTree.insert(90);

        // Tampilkan Visualisasi
        theTree.displayTree();
    }
}