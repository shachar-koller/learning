import java.math.BigDecimal;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ParallelSummation {

    // sequential BigDecimal sum of squares
    public static BigDecimal computeSequentialSum(int[] array, int lo, int hi) {
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = lo; i < hi; i++) {
            BigDecimal x = BigDecimal.valueOf(array[i]);
            BigDecimal square = x.multiply(x);
            sum = sum.add(square);
        }

        return sum;
    }

    // RecursiveTask for ForkJoin (BigDecimal)
    class ForkJoinSum extends RecursiveTask<BigDecimal> {

        private final int[] array;
        private final int lo, hi;
        private final int threshold;

        ForkJoinSum(int[] array, int lo, int hi, int threshold) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
            this.threshold = threshold;
        }

        @Override
        protected BigDecimal compute() {
            if (hi - lo <= threshold) {
                return computeSequentialSum(array, lo, hi);
            }

            int mid = (lo + hi) / 2;

            ForkJoinSum left = new ForkJoinSum(array, lo, mid, threshold);
            ForkJoinSum right = new ForkJoinSum(array, mid, hi, threshold);

            left.fork();                          // run left asynchronously
            BigDecimal rightAns = right.compute(); // compute right synchronously
            BigDecimal leftAns = left.join();      // wait for left

            return leftAns.add(rightAns);
        }
    }

    public BigDecimal parallelSum(int[] array, int threshold) {
        ForkJoinPool pool = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors()
        );

        ForkJoinTask<BigDecimal> task =
                new ForkJoinSum(array, 0, array.length, threshold);

        BigDecimal result = pool.invoke(task);
        pool.shutdown();
        return result;
    }
}
