package bruteforceclosestpair;

public class BruteForceClosestPair {

    public static void main(String[] args) {
        //            x  y    x  y    x  y    x  y    x  y    x  y
        int p[][] = {{2, 3}, {4, 5}, {1, 3}, {3, 9}, {9, 2}, {6, 3}};
        for (int i = 0; i < p.length - 1; i++) {
            for (int j = i + 1; j < p.length - 1; j++) {
                calculate(p[i][0], p[i][1], p[j][0], p[j][1]);
            }
        }
    }

    public static void calculate(double x, double y, double x2, double y2) {
        double result;
        result = Math.sqrt(Math.pow((x-x2), 2) + Math.pow(y-y2, 2));
        System.out.println(result);
    }

}
