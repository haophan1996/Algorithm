package bruteforceconvexhull;

/*
    a = y2-y1, b=x1-x2, c=x1y2-y1-x2
    ax+by-c
    
    brute_force_convex_hull(a set ò n points, p){
    
        create empty set of line segments L
        for (each point p1 in p)
            for (each point p2 in p after p1)
                a = p2.y - p1.y; b = p1. = p2.x
                c = p1.x*p2y - p1.y*p2x
                foundProblem = false
                for (each point p3 in P (not p1 or p2))
                    check = a*p3.x + b*p3.y -c
                    if ( check does not match others)
                        foundProblem = true
                        break
                    if (!foundProblem) add segment p1,p2 to L
            extract and return list of points from L
    } 
    Check out this link for example or understand 
    https://github.com/haophan1996/Algorithm/blob/main/Example/BruteForceConvexHull.JPG
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author HaoPhan
 */
public class BruteForceConvexHull extends JPanel {

    int[] coordinates = {100, 20};
    int mar = 50;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        Line2D lin;
        double px;
        double py;
        double p2x;
        double p2y;
        int x1 = 50;
        int y1 = 525;

        g1.draw(new Line2D.Double(mar, mar, mar, height - mar));
        g1.draw(new Line2D.Double(mar, height - mar, width - mar, height - mar));
        double x = (double) (width - 2 * mar) / (coordinates.length - 1);
        double scale = (double) (height - 2 * mar) / getMax();
        g1.setPaint(Color.BLUE);
        int s[][] = {{1, 1}, {2, 5}, {3, 2}, {3, 4}, {4, 3}, {5, 7}, {6, 2}};

        
        // Draw a every point x y of array s
        for (int i = 0; i < s.length; i++) {
            double x2 = x1 + s[i][0] * 60;
            double y2 = y1 - s[i][1] * 60;
            g1.fill(new Ellipse2D.Double(x2, y2, 4, 4));
            
            g1.drawString(String.valueOf("(" + s[i][0] + "," + s[i][1] + ")"), (int)x2, (int)y2);
        }
        
        //Label number every x and y along the line x-axis y-axis
        for (int i = 0; i < 10; i++) {
            g1.drawString(String.valueOf(i), x1 + (60 * i), 550);
            g1.fill(new Ellipse2D.Double(x1 + (60 * i), 524, 4, 4));
            if (i != 0) {
                g1.drawString(String.valueOf(i), 25, y1+9 - (60 * i));
                g1.fill(new Ellipse2D.Double(49, y1 - (60 * i), 4, 4));
            }
        }
        
        
        s = convexHullProblem(s);
        //Draw a line that can connect to each other
        for (int i = 0; i < s.length; i++) {
            px = x1 + s[i][0] * 60;
            py = y1 - s[i][1] * 60;

            if (i == (s.length - 1)) {
                p2x = x1 + s[0][0] * 60;
                p2y = y1 - s[0][1] * 60;
            } else {
                p2x = x1 + s[i + 1][0] * 60;
                p2y = y1 - s[i + 1][1] * 60;
            }

            lin = new Line2D.Double(px, py, p2x, p2y);
            g1.draw(lin);
        }

    }

    private int getMax() {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i] > max) {
                max = coordinates[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("convex");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(800, 600));
        frame.setLocation(200, 200);
        frame.add(new BruteForceConvexHull());
        frame.setVisible(true);

        /*int s[][] = {{1, 1}, {2, 5}, {3, 2}, {3, 4}, {4, 3}, {5, 7}, {6, 2}};
        s = convexHullProblem(s);

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i][0] + "," + s[i][1]);

        }*/
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
