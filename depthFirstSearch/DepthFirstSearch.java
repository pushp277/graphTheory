import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


class DepthFirstSearch implements DepthFirstSearchInterface{
    //We will first initialze adjacency List
    ArrayList<ArrayList<Integer>> adjList;
    ArrayList<Boolean> visited;
    int N;

DepthFirstSearch(Integer N){
    this.N = N;
    adjList = new ArrayList<>();
    visited = new ArrayList<>();
    for(int i=0;i<N;i++){
        adjList.add(new ArrayList<>());
        visited.add(false);
    }
}

public void DepthFirstSearchRec(int u, ArrayList<Boolean> visited){ //Using Recursion
    if(visited.get(u))
        return;

        System.out.println(u);
    visited.set(u,true);
    for(int i=0;i<adjList.get(u).size();i++){
        DepthFirstSearchRec(adjList.get(u).get(i), visited);
    }
}

public void DFS(int u){
    Stack<Integer> stk = new Stack<>();
    ArrayList<Boolean> visit = new ArrayList<>();

    for(int i=0;i<N;i++){
        visit.add(false);
    }

    stk.push(u);
    visit.set(u,true);
    ArrayList<Integer> arr = new ArrayList<>();
    while(!stk.isEmpty()){
        int top = stk.pop();
        arr.add(top);
        for(int i=0;i<adjList.get(top).size();i++){
            if(!visit.get(adjList.get(top).get(i))){
                stk.push(adjList.get(top).get(i));
                visit.set(adjList.get(top).get(i),true);
            }
        }
    }

    for(int x: arr){
        System.out.println(x);
    }
}

public static void main(String[] args){
    try (//Provide EdgeList Input
        //The Input will be like [[x1,y1],[x2,y2],[x3,y3]]
    Scanner input = new Scanner(System.in)) {
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

        System.out.println("DFS Using Recussion");

        dfs.DepthFirstSearchRec(0, dfs.visited);

        System.out.println("DFS using Stack");
        dfs.DFS(0);
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
}
};