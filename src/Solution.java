public class Solution {
    private static double calcF(double x) {
        return 1.5 * Math.pow(Math.E, x) - 0.5 * Math.cos(x);
    }

    private static void printArr(double[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static double calcNewtone(double[][] table, double x, int n, double a, double b) {
        double[][] divDif = new double[n + 2][n + 1];
        double result = 0;
        double tmp;
        for (int i = 0; i < n + 1; i++) {
            table[0][i] = (a + b) / 2 + (b - a) * Math.cos((2 * i + 1) * Math.PI / (2 * (n + 1))) / 2;
            table[1][i] = calcF(table[0][i]);
        }
        System.out.print("X: ");
        printArr(table[0]);
        System.out.print("Y: ");
        printArr(table[1]);
        for (int i = 0; i < n + 1; i++) {
            divDif[0][i] = table[0][i];
            divDif[1][i] = table[1][i];
        }
        for (int i = 2; i < n + 2; i++) {
            for (int j = 0; j < n - i + 2; j++) {
                divDif[i][j] = (divDif[i - 1][j + 1] - divDif[i - 1][j]) / (divDif[0][j + i - 1] - divDif[0][j]);
            }
        }
        for (int i = 0; i < n + 1; i++) {
            tmp = divDif[i + 1][0];
            for (int j = 0; j < i; j++) {
                tmp *= x - divDif[0][j];
            }
            result += tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        double[][] table = new double[2][11];
        System.out.println("Интерполяционный многочлен Ньютона с многочленами Чебышева:");
        System.out.println("r*: " + Math.abs(calcNewtone(table, 0.1 / 3, 10, 0, 1) - calcF(0.1 / 3)));
        System.out.println("r**: " + Math.abs(calcNewtone(table, 0.5 + 0.1 / 3, 10, 0, 1) - calcF(0.5 + 0.1 / 3)));
        System.out.println("r***: " + Math.abs(calcNewtone(table, 1 - 0.1 / 3, 10, 0, 1) - calcF(1 - 0.1 / 3)));
    }
}
