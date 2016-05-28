import java.util.Scanner;

public class Main {
    private static Scanner scanner;
    private static int array[];

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        int numberOfSets = scanner.nextInt();

        while (numberOfSets-- > 0) {
            int amountOfNumbers = scanner.nextInt();
            array = new int[amountOfNumbers];
            for (int i = 0; i < amountOfNumbers; i++) {
                array[i] = scanner.nextInt();
            }
            int amountOfRequests = scanner.nextInt();
            for (int i = 0; i < amountOfRequests; i++) {
                int k = scanner.nextInt() - 1;
                System.out.println(k+1 + " " + selectionAlgorithm(0, amountOfNumbers-1, k));
            }
        }
    }

    private static int selectionAlgorithm(int left, int right, int k) {
        int median = medianOfMedians(left, right);
        int less = left, greater = right;
        for (int i = left; i <= greater; i++) {
            if (array[i] < median) {
                swap(array[i], array[less]);
                less++;
            }
            if (array[i] > median) {
                swap(array[i], array[greater]);
                greater--;
            }
        }
        if (k > less - 1 && k < greater + 1)
            return median;
        else if (k <= less - 1)
            return selectionAlgorithm(left, less - 1, k);
        else
            return selectionAlgorithm(greater + 1, right, k);
    }

    private static int medianOfMedians(int left, int right) {
        if (right + 1 - left <= 10) {
            insertionSort(left, right);
            return array[(left + right) / 2];
        } else {
            int i = left;
            int j = min(right, i*5 + 4);
            while (i*5 < right) {
                insertionSort(i*5, j);
                swap(array[i], array[(i*5 + j) / 2]);
                i++;
                j = min(right, i*5 + 4);
            }
            return medianOfMedians(0, i);
        }
    }

    private static void insertionSort(int left, int right) {
        for (int i = left; i <= right; i++) {
            int tmp = array[i];
            int j;
            for (j = i - 1; j >= 0 && tmp < array[j]; j--)
                array[j+1] = array[j];
            array[j+1] = tmp;
        }
    }

    private static int min(int a, int b) {
        return a <= b ? a : b;
    }

    private static void swap(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
}