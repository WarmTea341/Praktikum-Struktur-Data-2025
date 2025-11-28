package kode;
// --- BAGIAN 1: LOGIKA MERGE SORT ---
class DArray {
    private long[] theArray;
    private int nElems;

    public DArray(int max) {
        theArray = new long[max];
        nElems = 0;
    }

    public void insert(long value) {
        theArray[nElems] = value;
        nElems++;
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            System.out.print(theArray[j] + " ");
        }
        System.out.println("");
    }

    public void mergeSort() {
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }

    private void recMergeSort(long[] workSpace, int lowerBound, int upperBound) {
        if (lowerBound == upperBound) {
            return; // Base case
        } else {
            int mid = (lowerBound + upperBound) / 2;
            recMergeSort(workSpace, lowerBound, mid);      // Sort kiri
            recMergeSort(workSpace, mid + 1, upperBound);  // Sort kanan
            merge(workSpace, lowerBound, mid + 1, upperBound); // Gabung
        }
    }

    private void merge(long[] workSpace, int lowPtr, int highPtr, int upperBound) {
        int j = 0;
        int lowerBound = lowPtr;
        int mid = highPtr - 1;
        int n = upperBound - lowerBound + 1;

        while (lowPtr <= mid && highPtr <= upperBound) {
            if (theArray[lowPtr] < theArray[highPtr]) {
                workSpace[j++] = theArray[lowPtr++];
            } else {
                workSpace[j++] = theArray[highPtr++];
            }
        }

        while (lowPtr <= mid) {
            workSpace[j++] = theArray[lowPtr++];
        }

        while (highPtr <= upperBound) {
            workSpace[j++] = theArray[highPtr++];
        }

        for (j = 0; j < n; j++) {
            theArray[lowerBound + j] = workSpace[j];
        }
    }
}

// --- BAGIAN 2: LOGIKA MENARA HANOI ---
class HanoiSolver {
    static int steps = 0;

    // Method Rekursif untuk memindahkan cakram
    public void doTowers(int topN, char src, char inter, char dest) {
        // Base Case: Jika hanya 1 cakram, langsung pindahkan
        if (topN == 1) {
            System.out.println("Langkah " + (++steps) + ": Pindah cakram 1 dari " 
                               + src + " ke " + dest);
        } else {
            // 1. Pindahkan n-1 cakram dari Asal ke Perantara
            doTowers(topN - 1, src, dest, inter);
            
            // 2. Pindahkan cakram paling besar (n) dari Asal ke Tujuan
            System.out.println("Langkah " + (++steps) + ": Pindah cakram " + topN + " dari " + src + " ke " + dest);
            
            // 3. Pindahkan n-1 cakram dari Perantara ke Tujuan
            doTowers(topN - 1, inter, src, dest);
        }
    }
}

// --- CLASS UTAMA (MAIN) ---
public class RecursionApp {
    public static void main(String[] args) {
        // --- DEMO 1: MENARA HANOI ---
        System.out.println("=================================");
        System.out.println(">> DEMO 1: MENARA HANOI (3 Cakram)");
        System.out.println("=================================");
        HanoiSolver hanoi = new HanoiSolver();
        // Memindahkan 3 cakram dari Tiang A ke C dengan bantuan B
        hanoi.doTowers(3, 'A', 'B', 'C');

        System.out.println("\n=================================");
        System.out.println(">> DEMO 2: MERGE SORT");
        System.out.println("=================================");
        int maxSize = 100;
        DArray arr = new DArray(maxSize);

        arr.insert(64);
        arr.insert(21);
        arr.insert(33);
        arr.insert(70);
        arr.insert(12);
        arr.insert(85);
        
        System.out.print("Data Awal: ");
        arr.display();

        arr.mergeSort();

        System.out.print("Data Urut: ");
        arr.display();
    }
}