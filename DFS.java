package dfs;

import java.util.LinkedList;
import java.util.Stack;

class node {

    int vertex;
    LinkedList<Integer> graph[];
    boolean visited[];

    node(int vertext) {
        this.vertex = vertext;
        visited = new boolean[vertex];
        graph = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++) {
            graph[i] = new LinkedList<>();
            visited[i] = false;
        }
    }

    public boolean isVisited(int e) {
        return visited[e];
    }

    public void setVisted(int v, boolean e) {
        visited[v] = e;
    }

    public void addNeighbor(int v, int u) {
        graph[v].add(u);
        graph[u].add(v);
    }

    @Override
    public String toString() {
        for (int i = 0; i < vertex; i++) {
            System.out.print(i + " Connect to ");
            for (int k = 0; k < graph[i].size(); k++) {
                System.out.print(graph[i].get(k) + " ");
            }
            System.out.println();
        }
        System.out.println();
        return "";
    }

}

public class DFS {

    static int deadend = 0;
    static int count = 0;
    static LinkedList dfsinOrder;
    static LinkedList deadendList;

    public static void main(String[] args) {
        dfsinOrder = new LinkedList();
        deadendList = new LinkedList();

        /*node node = new node(6);
        node.addNeighbor(0, 1);
        node.addNeighbor(0, 3);
        node.addNeighbor(1, 2);
        node.addNeighbor(1, 4);
        node.addNeighbor(3, 4);
        node.addNeighbor(4, 2);
        node.addNeighbor(2, 5); 
        //Expect value 0 1 2 4 3 5*/
 /* node node = new node(5);
        node.addNeighbor(0, 1);
        node.addNeighbor(0, 4);
        node.addNeighbor(1, 4);
        node.addNeighbor(1, 3);
        node.addNeighbor(1, 2);
        node.addNeighbor(3, 4);
        node.addNeighbor(3, 2);
        //Expect value 0 1 4 3 2 */
        node node = new node(10);
        node.addNeighbor(0, 2);
        node.addNeighbor(3, 2);
        node.addNeighbor(0, 3);
        node.addNeighbor(4, 5);
        node.addNeighbor(1, 5);
        node.addNeighbor(4, 1);
        node.addNeighbor(0, 4);
        node.addNeighbor(6, 7);
        node.addNeighbor(8, 9);
        node.addNeighbor(7, 8);
        node.addNeighbor(9, 6);
        node.addNeighbor(2, 5);

        node.toString();

        for (int i = 0; i < node.vertex; i++) {
            deadendList.add(-1);
        }

        DFSGo(node);
        System.out.println("count | DFS | DeadEnd");
        for (int i = 0; i < dfsinOrder.size(); i++) {
            System.out.println(i + 1 + "     |  " + dfsinOrder.get(i) + "  | " + deadendList.get((int) dfsinOrder.get(i)));
        }

    }

    public static void DFSGo(node node) {
        Stack stack = new Stack();
        //Set all v is false
        for (int i = 0; i < node.vertex; i++) {
            if (node.isVisited(i) == false) {
                if (i > 1) {
                    System.out.println("Another graph start at:" + i);
                }
                stack.push(i);
                dfs(node, stack);
            }
        }
    }

    private static void dfs(node node, Stack stack) {
        //Get the top number of 
        int current = (int) stack.peek();

        node.setVisted(current, true);
        //System.out.println(current);
        dfsinOrder.add(current);
        while (!stack.empty()) {
            stack.pop();
            for (int k = 0; k < node.graph[current].size(); k++) {
                if (node.isVisited(node.graph[current].get(k)) == false) {
                    node.setVisted(node.graph[current].get(k), true);
                    stack.push(node.graph[current].get(k));

                    dfs(node, stack);

                }
                if (k == node.graph[current].size() - 1) {
                    deadend++;
                    deadendList.set(current, deadend);
                }

            }

        }

    }

}
