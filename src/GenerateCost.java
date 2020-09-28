import java.util.ArrayList;
import java.util.Collections;

//import java.util.Arrays;
//import java.util.Random;
//
//public class GenerateCost {
//    int[][] cost;
//    Random rand;
//    public int[][] getCostMatrix(int k){
//        cost = new int[25][25];
//        rand = new Random();
//
//        for(int i=0;i<25;i++) {
//            for (int j = 0; j < 25; j++) {
//                if (i == j)
//                    cost[i][j] = 0;
//                else
//                    cost[i][j] = 250;
//            }
//        }
//
//        for(int i=0;i<25;i++){
//            int r = rand.nextInt(25);
//            int count = Math.max(r-k,0);
//            while(count <= r){
//                cost[i][count] = 1;
//                count++;
//            }
//        }
//        return cost;
//    }
//}
public class GenerateCost {
    int[][] cost = new int[25][25];
    ArrayList<Integer> list;

    public int[][] getCostMatrix(int k){
        for(int i=0;i<25;i++){
            for(int j=0;j<25;j++){
                if(i==j)
                    cost[i][j]=0;
                else
                    cost[i][j] = 250;
            }
        }

        for(int i=0;i<25;i++){
            int index=0;
            int count=0;
            getRandomNodes();
            while (count<k){
                int nodeJ = list.get(index);
                if(i==nodeJ){
                    index++;
                } else {
                    cost[i][nodeJ] = 1;
                    index++;
                    count++;
                }
            }
        }
        return cost;
    }

    public void getRandomNodes(){
        list = new ArrayList<>();
        for (int i=0; i<=24; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

    }

}