package kode;

class Karyawan {
    public long id;        // ID Karyawan (Key untuk sorting)
    public String nama;    // Data tambahan

    public Karyawan(long id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public void displayKaryawan() {
        System.out.print("{ID:" + id + ", " + nama + "} ");
    }
}

// Class Wrapper untuk Array
class ArrayKaryawan {
    private Karyawan[] theArray;
    private int nElems;

    public ArrayKaryawan(int max) {
        theArray = new Karyawan[max];
        nElems = 0;
    }

    public void insert(long id, String nama) {
        theArray[nElems] = new Karyawan(id, nama);
        nElems++;
    }

    public void display() {
        for (int j = 0; j < nElems; j++) {
            theArray[j].displayKaryawan();
        }
        System.out.println("");
    }

    // -------------------------------------------------------
    // 1. SHELL SORT
    // Mengurutkan berdasarkan ID menggunakan Gap Sequence
    // -------------------------------------------------------
    public void shellSort() {
        int inner, outer;
        Karyawan temp;

        int h = 1;
        // Menghitung gap awal menggunakan rumus Knuth (h = 3*h + 1)
        while (h <= nElems / 3) {
            h = h * 3 + 1; 
        }

        while (h > 0) {
            // Melakukan Insertion Sort dengan lompatan sebesar 'h'
            for (outer = h; outer < nElems; outer++) {
                temp = theArray[outer];
                inner = outer;

                // Geser elemen yang ID-nya lebih besar sejauh 'h'
                while (inner > h - 1 && theArray[inner - h].id >= temp.id) {
                    theArray[inner] = theArray[inner - h];
                    inner -= h;
                }
                theArray[inner] = temp;
            }
            h = (h - 1) / 3; // Perkecil gap
        }
    }

    // -------------------------------------------------------
    // 2. QUICK SORT
    // Wrapper method untuk memanggil rekursif
    // -------------------------------------------------------
    public void quickSort() {
        recQuickSort(0, nElems - 1);
    }

    // Method Rekursif Quick Sort
    private void recQuickSort(int batasKiri, int batasKanan) {
        if (batasKanan - batasKiri <= 0) {
            return; // Base case: array ukuran 1 atau 0
        } else {
            // Menentukan Pivot (ambil elemen paling kanan)
            long pivot = theArray[batasKanan].id;

            // Lakukan Partitioning
            int partisi = partitionIt(batasKiri, batasKanan, pivot);

            // Rekursif sort bagian kiri dan kanan
            recQuickSort(batasKiri, partisi - 1);
            recQuickSort(partisi + 1, batasKanan);
        }
    }

    // Logika Inti: Partitioning
    private int partitionIt(int batasKiri, int batasKanan, long pivot) {
        int indexKiri = batasKiri - 1;
        int indexKanan = batasKanan;

        while (true) {
            // Cari yang lebih besar dari pivot (dari kiri)
            while (indexKiri < batasKanan && 
                   theArray[++indexKiri].id < pivot);

            // Cari yang lebih kecil dari pivot (dari kanan)
            while (indexKanan > batasKiri && 
                   theArray[--indexKanan].id > pivot);

            if (indexKiri >= indexKanan) {
                break; // Pointer bertemu
            } else {
                swap(indexKiri, indexKanan); // Tukar elemen
            }
        }
        swap(indexKiri, batasKanan); // Kembalikan pivot ke posisi tengah
        return indexKiri; // Return lokasi pivot
    }

    private void swap(int one, int two) {
        Karyawan temp = theArray[one];
        theArray[one] = theArray[two];
        theArray[two] = temp;
    }
}

public class AdvancedSortApp {
    public static void main(String[] args) {
        int maxSize = 10;
        ArrayKaryawan arrShell = new ArrayKaryawan(maxSize);
        ArrayKaryawan arrQuick = new ArrayKaryawan(maxSize);

        // Data Dummy (ID acak)
        // Kita masukkan data yang sama ke dua array berbeda untuk perbandingan
        long[] ids = {202305, 202301, 202309, 202303, 202307, 202302, 202308, 202304};
        String[] names = {"Budi", "Andi", "Zara", "Dedi", "Gita", "Citra", "Hana", "Eko"};

        for(int i=0; i<ids.length; i++) {
            arrShell.insert(ids[i], names[i]);
            arrQuick.insert(ids[i], names[i]);
        }

        System.out.println(">> Data Awal (Sebelum Sort):");
        arrShell.display();

        System.out.println("\n>> Hasil Shell Sort:");
        arrShell.shellSort();
        arrShell.display();

        System.out.println("\n>> Hasil Quick Sort:");
        arrQuick.quickSort();
        arrQuick.display();
    }
}