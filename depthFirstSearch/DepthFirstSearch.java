import java.util.ArrayList;
import java.util.Scanner;

class DepthFirstSearch{
    //We will first initialze adjacency List
    ArrayList<ArrayList<Integer>> adjList;
    ArrayList<Boolean> visited;

DepthFirstSearch(Integer N){
    adjList = new ArrayList<>();
    visited = new ArrayList<>();
    for(int i=0;i<N;i++){
        adjList.add(new ArrayList<>());
        visited.add(false);
    }
}
public void depthFirstSearch(int u, ArrayList<Boolean> visited){ //Using Recursion
    if(visited.get(u))
        return;

    visited.set(u,true);
    for(int i=0;i<adjList.get(u).size();i++){
        depthFirstSearch(adjList.get(u).get(i), visited);
    }
    System.out.println(u);
}

public static void main(String[] args){
    //Provide EdgeList Input
    //The Input will be like [[x1,y1],[x2,y2],[x3,y3]]
    Scanner input = new Scanner(System.in); 
   
    int N = input.nextInt();
    input.nextLine();
    String arrString = input.nextLine().replaceAll("[\\[\\]]", "");
     DepthFirstSearch dfs = new DepthFirstSearch(N);
    String[] splitArr = arrString.split(",");

    for(int i=0;i<splitArr.length-1 ;i+=2){
        int a = Integer.parseInt(splitArr[i]);
        int b = Integer.parseInt(splitArr[i+1]);

        dfs.adjList.get(a).add(b);
        dfs.adjList.get(b).add(a);
    }

    
    dfs.depthFirstSearch(0, dfs.visited);
}
};