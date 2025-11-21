public class MatrixMultiplication {
    public static void main(String[] args) {
        // Example matrices
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8},
            {9, 10},
            {11, 12}
        };

        // Multiply matrices
        int[][] C = multiply(A, B);

        // Print result
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;       // rows in A
        int n = A[0].length;    // cols in A
        int p = B[0].length;    // cols in B

        // Check if multiplication is possible
        if (B.length != n) {
            throw new IllegalArgumentException("A's columns must equal B's rows");
        }

        int[][] C = new int[m][p];

        // Compute each element
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
}

