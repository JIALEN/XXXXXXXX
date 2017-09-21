/**
 * Created by 张超 on 2017/9/21.
 */
public class Min_Edit_Distance {
    private static int cost = 0;
    public static int minEdit_distance(String source, String target)
    {
        final int n = target.length();
        final int m = source.length();

        if(m == 0 )return m;
        if(n == 0)return n;

        int[][] distance_matrix = new int[m+1][n+1];
        distance_matrix[0][0] = 0;

        for(int i=0;i <= n;i++){

            distance_matrix[0][i] = i;
        }
        for(int j=0;j <= m;j++){

            distance_matrix[j][0] = j;
        }

        for(int i=1;i <= m;i++){

            char ci = source.charAt(i - 1);

            for(int j=1;j <= n;j++){

                char cj = target.charAt(j - 1);

                if(ci == cj){

                    cost = 0;

                }else{

                    cost = 2;
                }

                distance_matrix[i][j] = Math.min(distance_matrix[i-1][j-1]+cost, Math.min(distance_matrix[i-1][j]+1, distance_matrix[i][j-1]+1));

            }
        }

        return distance_matrix[m][n];
    }
    //取最长
    float max(String source,String target){
        int sourceL=source.length();
        int targetL=target.length();
        if(sourceL>targetL){
            return sourceL;
        }
        return targetL;
    }
    public static void main(String[] args){

        String source = "chin";
        String target = "china";

        System.out.println("最小编辑距离是："+minEdit_distance(source,target));
        Min_Edit_Distance min=new Min_Edit_Distance();
        float similarityRadio=1-(float)minEdit_distance(source,target)/min.max(source,target);
        System.out.println("相似度是："+similarityRadio);
    }
}