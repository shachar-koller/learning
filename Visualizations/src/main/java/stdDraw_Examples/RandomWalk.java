package stdDraw_Examples;

public class RandomWalk {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: run RandomWalk <distance>");
            return;
        }

        int n = Integer.parseInt(args[0]);

        StdDraw.setScale(-n - 0.5, n + 0.5);
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.enableDoubleBuffering();

        int x = 0, y = 0;
        int steps = 0;
        while (Math.abs(x) < n && Math.abs(y) < n) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.filledSquare(x, y, 0.45);

            double r = Math.random();
            if (r < 0.25) x--;
            else if (r < 0.50) x++;
            else if (r < 0.75) y--;
            else y++;

            steps++;
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledSquare(x, y, 0.45);

            StdDraw.show();
            StdDraw.pause(10);
        }
        System.out.println("Total steps = " + steps);
    }


}
