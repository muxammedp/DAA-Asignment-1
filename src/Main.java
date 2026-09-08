import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Divide-and-Conquer Algorithm Testing");
        System.out.println("====================================");
        System.out.println("Enter array elements separated by spaces (or press enter for default array):");
        System.out.println("Example: 64 34 25 12 22 11 90");

        String input = scanner.nextLine().trim();
        int[] data;

        if (input.isEmpty()) {
            data = new int[]{64, 34, 25, 12, 22, 11, 90};
            System.out.println("Using default array: " + Arrays.toString(data));
        } else {
            String[] parts = input.split("\\s+");
            data = new int[parts.length];
            try {
                for (int i = 0; i < parts.length; i++) {
                    data[i] = Integer.parseInt(parts[i]);
                }
                System.out.println("Using input array: " + Arrays.toString(data));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Using default array.");
                data = new int[]{64, 34, 25, 12, 22, 11, 90};
                System.out.println("Default array: " + Arrays.toString(data));
            }
        }

        System.out.println();

        // Test MergeSort
        int[] dataCopy = Arrays.copyOf(data, data.length);
        MergeSorter mergeSorter = new MergeSorter();
        long startTime = System.nanoTime();
        mergeSorter.sort(dataCopy);
        long endTime = System.nanoTime();

        System.out.println("MergeSort Results:");
        System.out.println("  Sorted array: " + Arrays.toString(dataCopy));
        System.out.println("  Execution time: " + (endTime - startTime) + " ns");
        System.out.println("  Recursion depth: " + mergeSorter.getRecursionDepth());
        System.out.println("  Comparisons: " + mergeSorter.getComparisons());
        System.out.println();

        // Test QuickSort
        dataCopy = Arrays.copyOf(data, data.length);
        QuickSorter quickSorter = new QuickSorter();
        startTime = System.nanoTime();
        quickSorter.sort(dataCopy);
        endTime = System.nanoTime();

        System.out.println("QuickSort Results:");
        System.out.println("  Sorted array: " + Arrays.toString(dataCopy));
        System.out.println("  Execution time: " + (endTime - startTime) + " ns");
        System.out.println("  Recursion depth: " + quickSorter.getRecursionDepth());
        System.out.println("  Swaps: " + quickSorter.getSwaps());
        System.out.println("  Comparisons: " + quickSorter.getComparisons());
        System.out.println();

        // Test Deterministic Select (find median)
        dataCopy = Arrays.copyOf(data, data.length);
        DeterministicSelector selector = new DeterministicSelector();
        int medianIndex = dataCopy.length / 2;
        startTime = System.nanoTime();
        int median = selector.select(dataCopy, medianIndex);
        endTime = System.nanoTime();

        System.out.println("Deterministic Select Results (Median):");
        System.out.println("  Median (k=" + medianIndex + "): " + median);
        System.out.println("  Execution time: " + (endTime - startTime) + " ns");
        System.out.println("  Recursion depth: " + selector.getRecursionDepth());
        System.out.println("  Comparisons: " + selector.getComparisons());
        System.out.println();

        // Test Closest Pair
        Point[] points = new Point[data.length];
        for (int i = 0; i < data.length; i++) {
            points[i] = new Point(data[i], data[i]); // Using same value for x and y
        }

        ClosestPairSolver closestPairSolver = new ClosestPairSolver();
        startTime = System.nanoTime();
        double minDistance = closestPairSolver.closestPair(points);
        endTime = System.nanoTime();

        System.out.println("Closest Pair Results:");
        System.out.println("  Minimum distance: " + minDistance);
        System.out.println("  Execution time: " + (endTime - startTime) + " ns");
        System.out.println("  Recursion depth: " + closestPairSolver.getRecursionDepth());
        System.out.println("  Comparisons: " + closestPairSolver.getComparisons());

        scanner.close();
    }
}