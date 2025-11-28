package kode;

// 1. IMPLEMENTASI STACK (LIFO)
class MyStack {
    private int maxSize;
    private char[] stackArray;
    private int top;

    public MyStack(int s) {
        maxSize = s;
        stackArray = new char[maxSize];
        top = -1;
    }

    // Method Push: Menambah item ke posisi paling atas
    public void push(char j) {
        stackArray[++top] = j;
    }

    // Method Pop: Mengambil item dari posisi paling atas
    public char pop() {
        return stackArray[top--];
    }

    public boolean isEmpty() {
        return (top == -1);
    }
}

// 2. IMPLEMENTASI QUEUE (FIFO & Circular)
class MyQueue {
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public MyQueue(int s) {
        maxSize = s;
        queArray = new long[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    // Method Insert: Menambah item di belakang (Rear)
    public void insert(long j) {
        if (rear == maxSize - 1) { // Circular logic (wrap around)
            rear = -1;
        }
        queArray[++rear] = j;
        nItems++;
    }

    // Method Remove: Mengambil item dari depan (Front)
    public long remove() {
        long temp = queArray[front++];
        if (front == maxSize) { // Circular logic (wrap around)
            front = 0;
        }
        nItems--;
        return temp;
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }
}

public class StackQueueApp {
    public static void main(String[] args) {
        // --- DEMO STACK (Membalik Kata) ---
        System.out.println(">> DEMO 1: STACK (LIFO)");
        String input = "STRUKT  UR";
        int stackSize = input.length();
        MyStack theStack = new MyStack(stackSize);

        System.out.println("Input Kata : " + input);
        
        // Push huruf satu per satu
        for (int j = 0; j < input.length(); j++) {
            theStack.push(input.charAt(j));
        }

        System.out.print("Output (Pop): ");
        while (!theStack.isEmpty()) {
            char value = theStack.pop();
            System.out.print(value);
        }
        System.out.println("\n-----------------------------");

        // --- DEMO QUEUE (Antrian) ---
        System.out.println(">> DEMO 2: QUEUE (FIFO)");
        MyQueue theQueue = new MyQueue(5);

        // Insert data (10, 20, 30, 40)
        System.out.println("Memasukkan data: 10, 20, 30, 40");
        theQueue.insert(10);
        theQueue.insert(20);
        theQueue.insert(30);
        theQueue.insert(40);

        System.out.print("Output (Remove): ");
        while (!theQueue.isEmpty()) {
            long n = theQueue.remove();
            System.out.print(n + " ");
        }
        System.out.println("");
    }
}