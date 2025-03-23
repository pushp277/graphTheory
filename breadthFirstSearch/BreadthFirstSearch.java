
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {
    public ArrayList<ArrayList<Integer>> adjList;
    public ArrayList<Integer> bfsArray;
    int N;


    public void BFS(int u) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Boolean> visited = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            visited.add(false);
        }
        q.add(u);
        visited.set(u, true);
        while (!q.isEmpty()) {
            int top = q.poll();
            
            bfsArray.add(top);
            for (int i = 0; i < adjList.get(top).size(); i++) {
                if (!visited.get(adjList.get(top).get(i))) {
                    q.add(adjList.get(top).get(i));
                    visited.set(adjList.get(top).get(i), true);
                }

            }
        }

    }

    BreadthFirstSearch(int N, ArrayList<Integer> edgeList) {
        this.N = N;
        bfsArray = new ArrayList<>();
        adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeList.size(); i += 2) {
            adjList.get(edgeList.get(i)).add(edgeList.get(i + 1));
            adjList.get(edgeList.get(i + 1)).add(edgeList.get(i));
        }
    }

    public static void main(String[] args) {
        try {
            // Reading and Writing Operations are handled here
           BufferedReader reader = new BufferedReader(new FileReader("./input.txt")); // For taking input
            BufferedWriter writer = new BufferedWriter(new FileWriter("./output.txt")); // For taking output
            Scanner scanner = new Scanner(reader);

            int N = scanner.nextInt();
            scanner.nextLine();
            String[] EdgeListString = scanner.nextLine().replaceAll("[\\[\\]]", "").split(",");
            ArrayList<Integer> edgeList = new ArrayList<>();

            for (int i = 0; i < EdgeListString.length; i++) {
                edgeList.add(Integer.parseInt(EdgeListString[i]));
            }

            BreadthFirstSearch BFS = new BreadthFirstSearch(N, edgeList);
            BFS.BFS(0);

            for(int i=0;i<BFS.bfsArray.size();i++){
                writer.write(BFS.bfsArray.get(i)+" ");
            }
            System.out.println("Output File Generated Successfully ");
            writer.close();
            scanner.close();

        } catch (IOException error) {
            System.out.println(error.getMessage());
        }

    }

}
