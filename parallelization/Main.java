import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ParallelSummation ps = new ParallelSummation();

        // ***** MATCH PROFESSOR'S ARRAY SIZES *****
        // slide page 5: 42,949,672 elements
        // slide page 55: 89,478,484 elements
        int n = 100_000_000;   // you can also try 89_478_484 for his M1 Pro test
        int threshold = 3000; // best threshold from slide pages 13 and 55

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i; // EXACTLY like his experiment

        System.out.println("Array size = " + n);
        System.out.println("Threshold = " + threshold);
        System.out.println("Cores = " +
                Runtime.getRuntime().availableProcessors());
        System.out.println();

        // ********** SEQUENTIAL **********
        long t1 = System.nanoTime();
        BigDecimal seq = ParallelSummation.computeSequentialSum(arr, 0, arr.length);
        long t2 = System.nanoTime();
        double seqMs = (t2 - t1) / 1_000_000.0;

        System.out.println("Sequential sum: " + seq);
        System.out.printf("Sequential time: %.2f ms%n%n", seqMs);

        // ********** PARALLEL (FORK-JOIN) **********
        long t3 = System.nanoTime();
        BigDecimal par = ps.parallelSum(arr, threshold);
        long t4 = System.nanoTime();
        double parMs = (t4 - t3) / 1_000_000.0;

        System.out.println("Parallel sum:   " + par);
        System.out.printf("Parallel time:  %.2f ms%n%n", parMs);

        // ********** SPEEDUP **********
        System.out.printf("Speedup = %.2fx%n", (seqMs / parMs));
    }
}
