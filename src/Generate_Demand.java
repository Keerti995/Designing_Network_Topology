/**
 * Each link between any 2 nodes is assigned with the demand value.
 */
public class Generate_Demand {
    int n = 25;
    String nID = "2021483960";
    int[] generatedID = new int[]{2,0,2,1,4,8,3,9,6,0,2,0,2,1,4,8,3,9,6,0,2,0,2,1,4};
    public int[][] getDemandMatrix()
    {
        int[][] demand = new int[n][n];
        for(int i=0;i<n;i++){
             for(int j=0;j<n;j++){
                 if(i==j)
                     demand[i][j] = 0;
                 else
                     demand[i][j] = Math.abs(generatedID[i]-generatedID[j]);
             }
        }
        return demand;
    }
}
