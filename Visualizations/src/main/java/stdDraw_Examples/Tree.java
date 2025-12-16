package stdDraw_Examples;
import edu.princeton.cs.algs4.StdDraw;
// Tree.java
// Robert Sedgewick and Kevin Wayne
// Slightly reformatted by Andrew Davison, Nov. 2022

// https://introcs.cs.princeton.edu/java/23recursion/Tree.java.html

/* Plot angle tree fractal.

   Usage:
     > compile Tree.java
     > run Tree n
   e.g.
     > run Tree 6
*/


public class Tree
{
    private static final double BEND_ANGLE   = Math.toRadians(15);
    private static final double BRANCH_ANGLE = Math.toRadians(37);
    private static final double BRANCH_RATIO = 0.65;


    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Usage: java Tree <int>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        tree(n, 0.5, 0, Math.PI/2, 0.3);
    } /* main */


    public static void tree(int n, double x, double y,
                            double angle, double branchRadius)
    {
        double cx = x + Math.cos(angle) * branchRadius;
        double cy = y + Math.sin(angle) * branchRadius;
        StdDraw.setPenRadius(0.001 * Math.pow(n, 1.2));
        StdDraw.line(x, y, cx, cy);

        if (n == 0)
            return;

        tree(n-1, cx, cy, angle+BEND_ANGLE - BRANCH_ANGLE,
                branchRadius * BRANCH_RATIO);
        tree(n-1, cx, cy, angle+BEND_ANGLE + BRANCH_ANGLE,
                branchRadius * BRANCH_RATIO);
        tree(n-1, cx, cy, angle+BEND_ANGLE,
                branchRadius * (1 - BRANCH_RATIO));
    } /* tree */


}  // end of Tree class