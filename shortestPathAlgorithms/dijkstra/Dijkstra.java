

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


class Dijkstra implements DisjkstraInterface{
    private ArrayList<ArrayList<ArrayList<Integer>>> adjList;

    Dijkstra(int N){
        adjList = new ArrayList<>();
        for(int i=0;i<N;i++){
            adjList.add(new ArrayList<>());
        }
    }

    Comparator<ArrayList<Integer>> compareArray = new Comparator<ArrayList<Integer>>() {
        public int compare(ArrayList<Integer> a1, ArrayList<Integer> a2){
            return Integer.compare(a2.get(1),a1.get(1));
        }
    };

    public void addElements(int u, int v, int w){ //For building adjList
        ArrayList<Integer> node1 = new ArrayList<>();
        ArrayList<Integer> node2 = new ArrayList<>();
        node1.add(u);
        node1.add(w);
        node2.add(v);
        node2.add(w);
        adjList.get(u).add(node2);
        adjList.get(v).add(node1);
    }

    public int findShortestPath(int u, int v){
            ArrayList<Integer> shortestDistance = new ArrayList<>();
            int N = adjList.size();
            int intMax = 1000000;
            for(int i=0;i<N;i++){
                shortestDistance.add(intMax);
            }
            shortestDistance.set(u, 0);

            Queue<ArrayList<Integer>> q = new PriorityQueue<>(compareArray);
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(u);
            temp.add(0);

            q.add(temp);

            while(!q.isEmpty()){   
                ArrayList<Integer> top = q.poll();
                for(int i=0;i<adjList.get(top.get(0)).size();i++){
                    ArrayList<Integer> shortest = new ArrayList<>();
                    
                    int d1 = adjList.get(top.get(0)).get(i).get(1)+shortestDistance.get(top.get(0));
                    int d2 = shortestDistance.get(adjList.get(top.get(0)).get(i).get(0));

                    if(d1<d2){
                        shortestDistance.set(adjList.get(top.get(0)).get(i).get(0), d1);
                        shortest.add(adjList.get(top.get(0)).get(i).get(0));
                        shortest.add(d1);
                        q.add(shortest);
                    }
                    
                }
            }

            return shortestDistance.get(v);
            
        }
    public static void main(String args[]){
        try{
            BufferedReader file = new BufferedReader(new FileReader("./input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("./output.txt"));
            Scanner scan = new Scanner(file);
            int N = scan.nextInt();
            scan.nextLine();
            String[] extractString = scan.nextLine().replaceAll("[\\[\\]]", "").split(",");
            
            Dijkstra djk = new Dijkstra(N);
            
            for(int i=0;i<extractString.length;i+=3){
                int u = Integer.parseInt(extractString[i]);
                int v = Integer.parseInt(extractString[i+1]);
                int w = Integer.parseInt(extractString[i+2]);
                djk.addElements(u,v,w);
            }
            
    
           int u=scan.nextInt();
           int v=scan.nextInt();


            writer.write("shortest Distance "+djk.findShortestPath(u, v));
            
            scan.close();
            writer.close();
            System.out.println("Output file has been generated!");
        }
        catch(IOException error){
            System.out.println("IOException: "+error);
        }
       
    }

    public ArrayList<ArrayList<ArrayList<Integer>>> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<ArrayList<ArrayList<Integer>>> adjList) {
        this.adjList = adjList;
    }
}