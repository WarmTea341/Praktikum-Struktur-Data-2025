/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kode;
// ---------------------------------------------------------
// Nama File: BinaryTreeApp.java
// Deskripsi: Implementasi BST dan 3 Jenis Traversal
// ---------------------------------------------------------

// KITA GANTI NAMA CLASS JADI 'TreeNode' AGAR TIDAK BENTROK
class TreeNode {
    public int id;              // Data utama (sebelumnya conflict)
    public TreeNode leftChild;  // Referensi ke anak kiri
    public TreeNode rightChild; // Referensi ke anak kanan

    public TreeNode(int id) {
        this.id = id;
    }

    public void displayNode() {
        System.out.print(id + " ");
    }
}

class Tree {
    private TreeNode root; // Menggunakan TreeNode

    public Tree() {
        root = null;
    }

    // -------------------------------------------------------
    // Method Insert
    // -------------------------------------------------------
    public void insert(int id) {
        TreeNode newNode = new TreeNode(id);

        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (id < current.id) { // Masuk ke Kiri?
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else { // Masuk ke Kanan?
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    // -------------------------------------------------------
    // Method Traversals
    // -------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder  (Root-Left-Right): ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder   (Left-Root-Right): ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder (Left-Right-Root): ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    private void preOrder(TreeNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.id + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(TreeNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.id + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(TreeNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.id + " ");
        }
    }
}

public class BinaryTreeApp {
    public static void main(String[] args) {
        Tree theTree = new Tree();

        System.out.println(">> Memasukkan data: 50, 25, 75, 12, 37, 43, 30, 33, 87, 93, 97");
        theTree.insert(50);
        theTree.insert(25);
        theTree.insert(75);
        theTree.insert(12);
        theTree.insert(37);
        theTree.insert(43);
        theTree.insert(30);
        theTree.insert(33);
        theTree.insert(87);
        theTree.insert(93);
        theTree.insert(97);

        // Menampilkan hasil 3 jenis traversal
        theTree.traverse(1); // Preorder
        theTree.traverse(2); // Inorder
        theTree.traverse(3); // Postorder
    }
}