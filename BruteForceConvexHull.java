package convexhullbruteforce;

import java.util.ArrayList;
import java.util.LinkedHashSet;

/**
 *
 * @author HaoPhan
 */
public class ConvexHullBruteForce {

    public static void main(String[] args) {
        int s[][] = {{1, 1}, {2, 5}, {3, 2}, {3, 4}, {4, 3}, {5, 7}, {6,2}};
        s = convexHullProblem(s);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i][0] + "," + s[i][1]);

        }
    }

    public static int[][] convexHullProblem(int s[][]) {
        int a = 0, b = 0, c = 0, count = 0;
        boolean found = false, t = false;
        String match = ""; 
        ArrayList<Integer> l = new ArrayList<Integer>();

        // Finding the convex
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (j != i) {
                    a = s[j][1] - s[i][1];
                    b = s[i][0] - s[j][0];
                    c = s[i][0] * s[j][1] - s[i][1] * s[j][0];
                    found = false;
                    int check = 0;
                    for (int k = 0; k < s.length; k++) {
                        if (k != i & k != j) {
                            check = a * s[k][0] + b * s[k][1] - c;
                            if (count == 0) {
                                match = foundProblem(check);
                            } else {
                                if (match != foundProblem(check)) {
                                    found = true;
                                    break;
                                } else {
                                    found = false;
                                }
                            }
                            count++;
                        }
                    }
                    if (found == false) {
                        l.add(i);
                        l.add(j);
                    }
                    count = 0;
                }
            }
        }

        //Clean Up the array and Rearrange the point
        int arr[] = new int[l.size()];
        arr[0] = l.get(0);
        for (int i = 0; i < l.size(); i++) {
            for (int k = 0; k < l.size(); k += 2) {
                if ((k != i) && (l.get(k) == arr[i])) {
                    for (int n = 0; n < arr.length; n++) {
                        if (arr[n] == l.get(k + 1)) {
                            t = true;
                            break;
                        }
                    }
                    if (!t) {
                        arr[i + 1] = l.get(k + 1);
                        break;
                    } else {
                        t = false;
                    }
                }

            }
        }

        l.clear();
        for (int i = 0; i < arr.length; i++) {
            l.add(i, arr[i]);
        }
        LinkedHashSet<Integer> hash = new LinkedHashSet<>(l);
        ArrayList<Integer> lis = new ArrayList<>(hash);
        int arra[][] = new int[lis.size()][2];

        for (int k = 0; k < lis.size(); k++) {
            for (int i = 0; i < 2; i++) {
                arra[k][i] = s[lis.get(k)][i];
            }
        }
        return arra;
    }

    public static String foundProblem(int check) {
        String pr = "";
        if (check == 0) {
            pr = "equal";
        } else if (check > 0) {
            pr = "right";
        } else if (check < 0) {
            pr = "left";
        }
        return pr;
    }
}
