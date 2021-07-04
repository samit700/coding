package com.sjain.ds;

import java.util.*;

public class MatrixAlgo {

    /*
    matrix index: i -> (i/n, i%n)  n=cols
                  (i,j) -> i*n + j

    num of diagonals: 0 <= i < rows+cols
    diagonal iter: 2 loops  i:0 to n    j:1 to n
    one diagonal loop (both ways): (i,j)  i<=j  i--, j++

    matrix loop: 4 loops   0,0 to n,0 ; n,0 to n,n ; n,n to 0,n ; 0,n to 0,0
    spiral loop: 0 <= i <= min(rows, cols)/2

     */

    public static int index(int[][] m, int n, int i) {
        return m[i/n][i%n];
    }

    public static int binarySearch(int[][] m, int n, int v) {
        int low = 0;
        int high = n*n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int val = index(m, n, mid);
            if (v < val)
                high = mid - 1;
            else if (v > val)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void setRowColOnes(int[][] m, int r, int c) {
        BitSet rowColFlag = new BitSet(r+c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (m[i][j] == 1) {
                    rowColFlag.set(i);
                    rowColFlag.set(r + j);
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rowColFlag.get(i) || rowColFlag.get(r + j))
                    m[i][j] = 1;
            }
        }
    }

    public static void transpose(int[][] m, int n) {
        for (int i = 1; i < n; i++) {
            for (int j = 0; i-j>j; j++) {
                if (m[i - j][j] != m[j][i - j]) {
                    int tmp = m[i - j][j];
                    m[i - j][j] = m[j][i - j];
                    m[j][i - j] = tmp;
                }
            }
        }

        for (int i = n-1; i > 0; i--) {
            for (int j = 1; (n - j) > (n-i+(j-1)); j++) {
                if (m[n - j][n-i+(j-1)] != m[n-i+(j-1)][n - j]) {
                    int tmp = m[n - j][n-i+(j-1)];
                    m[n - j][n-i+(j-1)] = m[n-i+(j-1)][n - j];
                    m[n-i+(j-1)][n - j] = tmp;
                }
            }
        }
    }

    public static void printSpiral(int[][] m, int r, int c) {
        int nd = Math.min(r-1,c-1)/2;
        for (int s = 0; s <= nd; s++) {
            for (int j = c-s-1; j >= s; j--) {
                System.out.print(m[s][j]);
                System.out.print(' ');
            }
            for (int i = s+1; i < r-s-1; i++) {
                System.out.print(m[i][s]);
                System.out.print(' ');
            }
            if (r-1 > s)
                for (int j = s; j < c-s-1; j++) {
                    System.out.print(m[r-s-1][j]);
                    System.out.print(' ');
                }
            if (c-1 > s)
                for (int i = r-s-1; i > s; i--) {
                    System.out.print(m[i][c-s-1]);
                    System.out.print(' ');
                }
        }
    }

    public static void print(int[][] m, int r, int c) {
        for(int i = 0; i < r; i++)
        {
            for(int j = 0; j < c; j++)
            {
                System.out.print(m[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static int maxRectangeSum(int[][] m, int n) {
        int maxSum = m[0][0];
        int[] sumRows = new int[n];
        int startCol = 0;
        int endCol = 0;

        for (int i = 0; i < n; i++) {
            Arrays.fill(sumRows, 0);

            for (int j = i; j < n; j++) {

                for (int k = 0; k < n; k++)
                     sumRows[k] += m[k][j];    //algo

                int maxSumij = ListAlgos.maxSubArraySum(sumRows);
                if (maxSumij > maxSum) {
                    maxSum = maxSumij;
                    startCol = i;
                    endCol = j;
                }
            }
        }

        System.out.println("[Max Matrix Sum] StartCol: " + startCol + "; EndCol: " + endCol);

        return maxSum;
    }

    private static class MatrixNode implements Comparable<MatrixNode> {
        int i;
        int j;
        int val;

        public MatrixNode(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }

        @Override
        public int compareTo(MatrixNode o) {
            return Integer.compare(val, o.val);
        }
    }

    public static int[] mergeKSortedArrays(int[][] m, int r, int c) {
        int[] res = new int[r*c];

        MinHeap<MatrixNode> heap = new MinHeap<>();
        for (int i = 0; i < r; i++) {
            heap.push(new MatrixNode(i, 0, m[i][0]));
        }

        int k = 0;
        while (!heap.isEmpty()) {
            MatrixNode node = heap.pop();
            res[k++] = node.val;

            if (node.j < (c-1)) {
                ++node.j;
                node.val = m[node.i][node.j];
                heap.push(node);
            }
        }

        return res;
    }

    public static void rotate90(int[][] m, int n, boolean anticlockwise) {
        int[][] m2 = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m2[i][j] = m[j][n - 1 - i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m[i][j] = m2[i][j];
            }
        }
    }

    public static void rotate90InPlace(int[][] m, int n, boolean anticlockwise) {
        if (anticlockwise) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = i; j < (n - 1 - i); j++) {
                    int tmp = m[n - 1 - i][j];
                    m[n - 1 - i][j] = m[j][i];
    
                    int tmp2 = tmp;
                    tmp = m[n - 1 - j][n - 1 - i];
                    m[n - 1 - j][n - 1 - i] = tmp2;

                    tmp2 = tmp;
                    tmp = m[i][n - 1 - j];
                    m[i][n - 1 - j] = tmp2;

                    m[j][i] = tmp;
                }
            }
        } else {
            int nloops = n/2;
            for (int i = 0; i < nloops; i++) {
                for (int j = i; j < n - i - 1; j++) {
                    int tmp = m[i][n-j-1];
                    m[i][n-j-1] = m[j][i];

                    int tmp2 = m[n-j-1][n-i-1];
                    m[n-j-1][n-i-1] = tmp;
                    tmp = tmp2;

                    tmp2 = m[n-i-1][j];
                    m[n-i-1][j] = tmp;
                    tmp = tmp2;

                    m[j][i] = tmp;
                }

            }
        }
    }

    /*

    public static void rotateK(int[][] m, int r, int c, int k) {

        int nRects = Math.min(r, c) / 2;
        for (int d = 1; d <= nRects; d++) {
            int ri = r - d;
            int ci = c - d;
            for (int j = 0; j < ci; j++) {
                int i = 0;
                int ki = k;
                while (ki > 0) {
                    int move = 0;
                    if (i == 0 && j != 0) {
                        move = Math.min(j, ki);
                        j -= move;
                    } else if (j == 0 && i != ri) {
                        move = Math.min(ri - i, ki);
                        i += move;
                    } else if (i == ri && j != ci) {
                        move = Math.min(ci - j, ki);
                        j += move;
                    } else if (j == ci && i != 0) {
                        move = Math.min(i, ki);
                        i -= move;
                    }
                    ki -= move;
                }

        System.out.println(m[i][j]);
    }
    */

    private static void visitRegion(boolean[][] visited, int[][] m, int r, int c, int cols, int rows) {
        visited[r][c] = true;

        // -1,-1; -1,0; 0,1;... (8 neighbors)
        int[] rn = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] cn = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};


        for (int i = 0; i < 8; i++) {
            //neighbors (circular columns)
            int rowIndex = r + rn[i];
            int colIndex = (c + cn[i] + cols) % cols;

            if (rowIndex >= 0 &&
                    rowIndex < rows &&
                    colIndex >= 0 &&
                    colIndex < cols &&
                    (!visited[rowIndex][colIndex]) &&
                    m[rowIndex][colIndex] == 1)
            {

                visitRegion(visited, m, rowIndex, colIndex, cols, rows);
            }
        }

    }

    public static int countRegions(int[][] m, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        int c = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j] && m[i][j] == 1) {
                    visitRegion(visited, m, i, j, cols, rows);
                    ++c;
                }
            }
        }

        return c;
    }

    private static final List<Integer> mnrx = Collections.unmodifiableList(Arrays.asList(-1, -1, -1, 0, 0, 1, 1, 1));
    private static final List<Integer> mncx = Collections.unmodifiableList(Arrays.asList(-1, 0, 1, -1, 1, -1, 0, 1));

    public static int countRegionsIterative(int[][] m, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        int c = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (m[i][j] == 0)
                    continue;

                visited[i][j] = true;

                // check neighbors
                boolean foundNewRegion = true;
                for (int k = 0; k < 8; k++) {
                    int rowk = i + mnrx.get(k);
                    int colk = (j + mncx.get(k) + cols) % cols;

                    if (rowk >= 0 && rowk < rows && colk >= 0 && colk < cols) {
                        if (visited[rowk][colk])
                            foundNewRegion = false;
                    }
                }

                if (foundNewRegion)
                    ++c;
            }
        }

        return c;
    }

}

