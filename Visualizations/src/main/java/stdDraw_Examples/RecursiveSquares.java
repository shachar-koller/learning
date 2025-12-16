package stdDraw_Examples;
import edu.princeton.cs.algs4.StdDraw;

// RecursiveSquares.java
// Robert Sedgewick and Kevin Wayne
// Slightly reformatted by Andrew Davison, Nov. 2022

// https://introcs.cs.princeton.edu/java/23recursion/RecursiveSquares.java

/* Plot an order n tree of overlapping gray squares.

   Usage:
     > compile RecursiveSquares.java
     > run RecursiveSquares n
   e.g.
     > run RecursiveSquares 6
*/


public class RecursiveSquares
{

    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Usage: java RecursiveSquares <levels>");
            return;
        }
        int n = Integer.parseInt(args[0]);

        double x = 0.5, y = 0.5;   // center of square
        double size = 0.5;    // side length of square
        draw(n, x, y, size);
    } // end of main()



    private static void draw(int n, double x, double y, double size)
    // plot an order n recursive squares pattern
    // centered on (x, y) of the given side length
    {
        if (n == 0)
            return;

        drawSquare(x, y, size);

        // 2.2 ratio looks good
        double ratio = 2.2;

        // recursively draw 4 smaller trees of order n-1
        draw(n-1, x - size/2, y - size/2, size/ratio);   // lower left
        draw(n-1, x - size/2, y + size/2, size/ratio);   // upper left
        draw(n-1, x + size/2, y - size/2, size/ratio);   // lower right
        draw(n-1, x + size/2, y + size/2, size/ratio);   // upper right
    } // end of draw()


    private static void drawSquare(double x, double y, double size)
    // plot a square, centered on (x, y) of the given side length
    // with a light gray background and black border
    {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledSquare(x, y, size/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(x, y, size/2);
    }

}  // end of RecursiveSquares class
