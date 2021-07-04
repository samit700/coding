package com.sjain;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;


public   class Test {

    /*
     * Complete the 'linearInterpolate' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. DOUBLE_ARRAY xKnots
     *  3. DOUBLE_ARRAY yKnots
     *  4. DOUBLE xInput
     */

        public static double linearInterpolate(int n, List<Double> xKnots, List<Double> yKnots, double xInput) {
            // need at least 2 points
            if (n <= 1 || xKnots.size() <= 1)
                return Double.NaN;

            // closest proximity points
            int cx1 = -1;
            int cx2 = -1;

            // distance from closest proximity points
            double min_dist1 = Double.MAX_VALUE;
            double min_dist2 = Double.MAX_VALUE;

            // find the closest coordinates linearly (interpolate)
            for (int i = 0; i < n; i++) {
                double xi = xKnots.get(i);
                double yi = yKnots.get(i);
                double dist = Math.abs(xKnots.get(i) - xInput);
                if (xi > xInput) {
                    if (dist < min_dist2) {
                        min_dist2 = dist;
                        cx2 = i;
                    } else if (dist == min_dist2 && yKnots.get(i) < yKnots.get(cx2)) {
                        cx2 = i;
                    }
                } else if (xi < xInput) {
                    if (dist < min_dist1) {
                        min_dist1 = dist;
                        cx1 = i;
                    } else if (dist == min_dist1 && yKnots.get(i) > yKnots.get(cx1)) {
                        cx1 = i;
                    }
                }
            }

            // find next closest point for extrapolation
            double min_dist_ex = Double.MAX_VALUE;
            boolean left_extrapolate = false;
            boolean right_extrapolate = false;
            if (cx1 == -1)
                left_extrapolate = true;
            if (cx2 == -1)
                right_extrapolate = true;
            if (cx1 == -1 || cx2 == -1) {
                for (int i = 0; i < n; i++) {
                    double xi = xKnots.get(i);
                    double yi = yKnots.get(i);
                    if (left_extrapolate) {
                        double dist = xi - xKnots.get(cx2);
                        if (xi > xKnots.get(cx2) && dist < min_dist_ex) {
                            min_dist_ex = dist;
                            cx1 = i;
                        }
                    } else if (right_extrapolate) {
                        double dist = xKnots.get(cx1) - xi;
                        if (xi < xKnots.get(cx1) && dist < min_dist_ex) {
                            min_dist_ex = dist;
                            cx2 = i;
                        }
                    }
                }
            }

            // cx1, cx2
            int x1 = cx1 < cx2? cx1 : cx2;
            int x2 = cx1 > cx2? cx1 : cx2;

            return yKnots.get(x1) +
                    ((xInput - xKnots.get(x1)) *
                            ((yKnots.get(x2) - yKnots.get(x1))/(xKnots.get(x2) - xKnots.get(x1)))
                    );
        }

        public static void main(String[] args) throws IOException {

            int n = 6;
            List<Double> x_knots = Arrays.asList(new Double[] {-2.0, -1.0, -1.0, 0.0, 1.0, 2.0});
            List<Double> y_knots = Arrays.asList(new Double[] {0.0, 10.0, 12.0, 15.0, 0.0, 5.0});
            double x_input = -.5;

            double result = Test.linearInterpolate(n, x_knots, y_knots, x_input);

            System.out.println(String.valueOf(result));
            //bufferedWriter.write(String.valueOf(result));
            //bufferedWriter.newLine();

        }
    }

