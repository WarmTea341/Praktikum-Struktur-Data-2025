
package kode;

class Node {
    public int data;        // Data item
    public Node next;       // Referensi ke node selanjutnya
    public Node previous;   // Referensi ke node sebelumnya

    public Node(int d) {
        data = d;
    }

    public void displayNode() {
        System.out.print(data + " ");
    }
}

class DoublyLinkedList {
    private Node first;
    private Node last;

    public DoublyLinkedList() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    // ---------------------------------------------------------
    // Method insertFirst: Menambah node di awal list
    // ---------------------------------------------------------
    public void insertFirst(int dd) {
        Node newNode = new Node(dd);

        if (isEmpty()) {
            last = newNode;         // Jika kosong, last juga menunjuk newNode
        } else {
            first.previous = newNode; // Node lama pertama menunjuk ke newNode
        }
        newNode.next = first;       // newNode menunjuk ke node lama pertama
        first = newNode;            // Head (first) pindah ke newNode
    }

    // ---------------------------------------------------------
    // Method deleteKey: Menghapus node berdasarkan nilai data
    // ---------------------------------------------------------
    public Node deleteKey(int key) {
        Node current = first;

        // 1. Mencari node dengan loop
        while (current.data != key) {
            current = current.next;
            if (current == null) {
                return null; // Data tidak ditemukan
            }
        }

        // 2. Jika node ditemukan, atur link
        if (current == first) {
            first = current.next;       // First mundur ke node kedua
        } else {
            current.previous.next = current.next; // Bypass node saat ini
        }

        if (current == last) {
            last = current.previous;    // Last mundur ke node sebelum terakhir
        } else {
            current.next.previous = current.previous; // Bypass node saat ini
        }
        return current; // Mengembalikan node yang dihapus
    }

    // Method untuk menampilkan list dari depan ke belakang
    public void displayForward() {
        System.out.print("List (First --> Last): ");
        Node current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }
        System.out.println("");
    }
}

public class DoublyLinkedListApp {
    public static void main(String[] args) {
        DoublyLinkedList theList = new DoublyLinkedList();

        // Skenario 1: Insert Data
        System.out.println(">> Menambahkan data (Insert First)...");
        theList.insertFirst(22);
        theList.insertFirst(44);
        theList.insertFirst(66);
        theList.insertFirst(88); // 88 akan jadi yang paling depan
        
        theList.displayForward();

        // Skenario 2: Menghapus Data di tengah
        int keyToDelete = 44;
        System.out.println("\n>> Menghapus data: " + keyToDelete);
        Node deleted = theList.deleteKey(keyToDelete);
        
        if (deleted != null) {
            System.out.println("   Berhasil menghapus: " + deleted.data);
        } else {
            System.out.println("   Data tidak ditemukan.");
        }
        theList.displayForward();

        // Skenario 3: Menghapus Data pertama (Head)
        keyToDelete = 88;
        System.out.println("\n>> Menghapus data: " + keyToDelete);
        theList.deleteKey(keyToDelete);
        theList.displayForward();
    }
}