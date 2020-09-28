import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NetworkCostFinder {
    int k = 3;
    int[][] capacity;
    int[][] costMatrix;
    int[][] demandMatrix;
    long cost;
    ArrayList<Integer> path;

    NetworkCostFinder(int k){
        this.k = k;
        this.capacity = new int[25][25];
        this.cost = 0;
    }

    /**
     * Using the cost matrix, run the dijkstra algo to find the path which gives the shortest travelling cost.
     */
    public void findMinPath(){
        Generate_Demand gd = new Generate_Demand();
        GenerateCost gc = new GenerateCost();
        costMatrix = gc.getCostMatrix(k);
        demandMatrix = gd.getDemandMatrix();

        //check all nodes as source node and find the one which gives the min cost of travelling
        for(int i=0;i<25;i++){
            dijkstra(i);
        }
    }

    /**
     * find the path from the given source which gives the complete travelling path smallest cost
     * @param src
     */
    public void dijkstra(int src){
        int[] dist = new int[25];
        boolean[] visited = new boolean[25];
        int[] parent = new int[25];

        for(int i=0;i<25;i++){
            parent[i] = -1;
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[src] = 0;

        for(int i=0;i<25;i++){
            int u = minDistance(dist,visited);
            visited[u] = true;
            for(int v=0;v<25;v++){
                if(!visited[v] && costMatrix[u][v] > 0 && (dist[u]+costMatrix[u][v] < dist[v]))
                {
                    parent[v] = u;
                    dist[v] = dist[u]+costMatrix[u][v];
                }
            }
            updateCostAndCapactiy(dist,parent,src);
        }
    }

    private void updateCostAndCapactiy(int[] dist, int[] parent, int src){
        for(int i=0;i<25;i++){
            if(demandMatrix[src][i] > 0){
                path = new ArrayList<Integer>();
                getPath(i,parent);
                if(path.size() > 0){
                    int sum = 0;
                    int demand = demandMatrix[src][i];
                    for(int idx = 0;idx < path.size()-1;idx++){
                        capacity[path.get(idx)][path.get(idx+1)] += demand;
                        sum += costMatrix[path.get(idx)][path.get(idx+1)];
                    }
                    cost += (sum*demand);
                }
            }
        }
    }

    private void getPath(int currentVertex, int[] parent){
        if(currentVertex == -1)return;

        getPath(parent[currentVertex],parent);
        path.add(currentVertex);
    }

    /**
     * find the next node with min cost value from the non visited nodes
     * @param dist
     * @param visited
     * @return
     */
    private int minDistance(int[] dist, boolean[] visited){
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for(int i=0;i<25;i++){
            if(visited[i] == false && dist[i] <= min)
            {
                min_index = i;
                min = dist[i];
            }
        }
        return min_index;
    }
    public static void main(String[] args) throws IOException {
        //for(int k=3;k<=13;k++){
        int k=3;
            NetworkCostFinder networkCostFinder = new NetworkCostFinder(k);
            networkCostFinder.findMinPath();
            System.out.println("K: "+k);
            System.out.println("Cost of the network is "+ networkCostFinder.cost);
            BufferedWriter fw = new BufferedWriter(new FileWriter("src/graph.csv"));
            long edgeCount = 0;
            for(int i=0;i<25;i++){
                for(int j=0;j<25;j++){
                    if(i!=j && networkCostFinder.costMatrix[i][j] > 0){
                        //fw.write(i+","+j+","+networkCostFinder.costMatrix[i][j]);
                        //fw.write(i+","+j);
                        fw.write(i+","+j+","+networkCostFinder.costMatrix[i][j]);
                        fw.newLine();
                        edgeCount++;
                    }
                }
            }
            fw.close();
            System.out.println("Number of edges:"+edgeCount);
            System.out.println("Density of the Network "+ (edgeCount/(25.0*24.0)));
            System.out.println();
        //}
    }
}
