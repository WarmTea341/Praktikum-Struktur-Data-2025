package kode;
// Struktur data Stack untuk DFS
class StackX {
    private int[] st;
    private int top;

    public StackX(int size) {
        st = new int[size];
        top = -1;
    }
    public void push(int j) { st[++top] = j; }
    public int pop() { return st[top--]; }
    public int peek() { return st[top]; }
    public boolean isEmpty() { return (top == -1); }
}

// Struktur data Queue untuk BFS
class QueueX {
    private int[] queArray;
    private int front;
    private int rear;
    private int nItems;
    private int size;

    public QueueX(int s) {
        size = s;
        queArray = new int[size];
        front = 0;
        rear = -1;
        nItems = 0;
    }
    public void insert(int j) {
        if (rear == size - 1) rear = -1;
        queArray[++rear] = j;
        nItems++;
    }
    public int remove() {
        int temp = queArray[front++];
        if (front == size) front = 0;
        nItems--;
        return temp;
    }
    public boolean isEmpty() { return (nItems == 0); }
}

class Vertex {
    public char label;
    public boolean wasVisited;

    public Vertex(char lab) {
        label = lab;
        wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[]; // Array untuk menyimpan simpul
    private int adjMat[][];      // Adjacency Matrix (0/1)
    private int nVerts;          // Jumlah simpul saat ini
    private StackX theStack;     // Untuk DFS
    private QueueX theQueue;     // Untuk BFS

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++)
            for (int j = 0; j < MAX_VERTS; j++)
                adjMat[i][j] = 0; // Inisialisasi matriks 0
        
        theStack = new StackX(MAX_VERTS);
        theQueue = new QueueX(MAX_VERTS);
    }

    public void addVertex(char lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1; // Undirected Graph (Bolak-balik)
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label + " ");
    }

    // ---------------------------------------------------------
    // 1. DEPTH FIRST SEARCH (DFS) - Pakai STACK
    // ---------------------------------------------------------
    public void dfs() {
        System.out.print("DFS Traversal: ");
        vertexList[0].wasVisited = true; // Mulai dari A
        displayVertex(0);
        theStack.push(0);

        while (!theStack.isEmpty()) {
            // Cari tetangga yang belum dikunjungi
            int v = getAdjUnvisitedVertex(theStack.peek());
            if (v == -1) {
                theStack.pop(); // Jalan buntu, backtrack (pop)
            } else {
                vertexList[v].wasVisited = true; // Tandai
                displayVertex(v);                // Tampilkan
                theStack.push(v);                // Push ke stack
            }
        }
        System.out.println("");
        resetFlags(); // Reset agar bisa dipakai ulang
    }

    // ---------------------------------------------------------
    // 2. BREADTH FIRST SEARCH (BFS) - Pakai QUEUE
    // ---------------------------------------------------------
    public void bfs() {
        System.out.print("BFS Traversal: ");
        vertexList[0].wasVisited = true; // Mulai dari A
        displayVertex(0);
        theQueue.insert(0);              // Masukkan ke antrean

        int v2;
        while (!theQueue.isEmpty()) {
            int v1 = theQueue.remove();  // Ambil kepala antrean

            // Kunjungi semua tetangga v1 sampai habis
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                theQueue.insert(v2);
            }
        }
        System.out.println("");
        resetFlags();
    }

    // Helper: Mencari tetangga yang belum dikunjungi
    public int getAdjUnvisitedVertex(int v) {
        for (int j = 0; j < nVerts; j++) {
            if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false) {
                return j; // Ketemu tetangga
            }
        }
        return -1; // Tidak ada tetangga baru
    }

    public void resetFlags() {
        for (int i = 0; i < nVerts; i++) vertexList[i].wasVisited = false;
    }
}

public class GraphApp {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        
        // Membangun Graph Sederhana
        // A -- B -- D
        // |    |
        // C    E
        
        theGraph.addVertex('A'); // 0
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4

        theGraph.addEdge(0, 1); // A-B
        theGraph.addEdge(0, 2); // A-C
        theGraph.addEdge(1, 3); // B-D
        theGraph.addEdge(1, 4); // B-E

        System.out.println(">> Struktur Graph:");
        System.out.println("   A terhubung ke B dan C");
        System.out.println("   B terhubung ke D dan E");
        System.out.println("--------------------------");

        theGraph.dfs(); // A -> B -> D -> E -> C
        theGraph.bfs(); // A -> B -> C -> D -> E
    }
}