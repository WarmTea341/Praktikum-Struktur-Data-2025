/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kode;
// ---------------------------------------------------------
// Nama File: GraphComparisonApp.java
// Deskripsi: Perbandingan Visual Urutan DFS vs BFS
// ---------------------------------------------------------

import java.util.*;

class GraphVisual {
    private int V; // Jumlah simpul
    private LinkedList<Integer> adj[]; // Adjacency List

    public GraphVisual(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Menambah Edge (Satu arah agar urutan lebih prediktif untuk demo)
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // ---------------------------------------------------------
    // 1. Logika BFS (Pakai Queue) - Melebar
    // ---------------------------------------------------------
    void BFS(int s) {
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s] = true;
        queue.add(s);

        System.out.print("Urutan BFS: ");
        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " -> ");

            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
        System.out.println("SELESAI");
    }

    // ---------------------------------------------------------
    // 2. Logika DFS (Pakai Rekursi/Stack) - Menyelam
    // ---------------------------------------------------------
    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " -> ");

        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[V];
        System.out.print("Urutan DFS: ");
        DFSUtil(v, visited);
        System.out.println("SELESAI");
    }
}

public class GraphComparisonApp {
    public static void main(String[] args) {
        GraphVisual g = new GraphVisual(7);

        // Membangun Graf Pohon Biner Sederhana
        //        0
        //      /   \
        //     1     2
        //    / \   / \
        //   3   4 5   6
        
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);

        System.out.println(">> Struktur Graf:");
        System.out.println("   [0] terhubung ke [1], [2]");
        System.out.println("   [1] terhubung ke [3], [4]");
        System.out.println("   [2] terhubung ke [5], [6]");
        System.out.println("-------------------------------");

        // Bandingkan Outputnya!
        g.BFS(0); 
        g.DFS(0); 
    }
}