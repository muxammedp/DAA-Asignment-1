import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Experiment {
    private static final int[] INPUT_SIZES = {100, 500, 1000, 2000, 5000, 10000};
    private static final String CSV_FILE = "results/results.csv";

    public static void main(String[] args) {
        initializeCSV();

        Random random = new Random(42); 

        for (int size : INPUT_SIZES) {
            System.out.println("Testing input size: " + size);

            
            int[] randomArray = generateRandomArray(size, random);
            int[] sortedArray = Arrays.copyOf(randomArray, size);
            Arrays.sort(sortedArray);
            int[] reverseSortedArray = Arrays.copyOf(sortedArray, size);
            reverse(reverseSortedArray);
            int[] duplicateArray = generateDuplicateArray(size, random);

            
            testMergeSort(size, "random", randomArray);
            testMergeSort(size, "sorted", sortedArray);
            testMergeSort(size, "reverse_sorted", reverseSortedArray);
            testMergeSort(size, "duplicate", duplicateArray);

            
            testQuickSort(size, "random", randomArray);
            testQuickSort(size, "sorted", sortedArray);
            testQuickSort(size, "reverse_sorted", reverseSortedArray);
            testQuickSort(size, "duplicate", duplicateArray);

            
            int k = size / 2;
            testDeterministicSelect(size, "random", randomArray, k);
            testDeterministicSelect(size, "sorted", sortedArray, k);
            testDeterministicSelect(size, "reverse_sorted", reverseSortedArray, k);
            testDeterministicSelect(size, "duplicate", duplicateArray, k);

            
            testClosestPair(size, "random", randomArray);
            testClosestPair(size, "sorted", sortedArray);
            testClosestPair(size, "reverse_sorted", reverseSortedArray);
            testClosestPair(size, "duplicate", duplicateArray);
        }

        System.out.println("Experiments completed. Results saved to " + CSV_FILE);
    }

    private static void initializeCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE))) {
            writer.write("Algorithm,InputSize,InputType,ExecutionTimeNs,RecursionDepth,AdditionalMetric,MetricName");
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error initializing CSV: " + e.getMessage());
        }
    }

    private static void appendToCSV(String algorithm, int inputSize, String inputType,
                                  long executionTimeNs, long recursionDepth, long additionalMetric, String metricName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE, true))) {
            writer.write(String.format("%s,%d,%s,%d,%d,%d,%s",
                    algorithm, inputSize, inputType, executionTimeNs, recursionDepth, additionalMetric, metricName));
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to CSV: " + e.getMessage());
        }
    }

    private static int[] generateRandomArray(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000000); 
        }
        return array;
    }

    private static int[] generateDuplicateArray(int size, Random random) {
        int[] array = new int[size];
        int duplicateValue = random.nextInt(100); 
        for (int i = 0; i < size; i++) {
            array[i] = duplicateValue + random.nextInt(10); 
        }
        return array;
    }

    private static void reverse(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    private static void testMergeSort(int size, String inputType, int[] array) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        MergeSorter sorter = new MergeSorter();

        long startTime = System.nanoTime();
        sorter.sort(arrayCopy);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        long recursionDepth = sorter.getRecursionDepth();
        long comparisons = sorter.getComparisons();

        appendToCSV("MergeSort", size, inputType, executionTime, recursionDepth, comparisons, "comparisons");
        System.out.printf("  MergeSort %s: %d ns, depth=%d, comparisons=%d%n",
                inputType, executionTime, recursionDepth, comparisons);
    }

    private static void testQuickSort(int size, String inputType, int[] array) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        QuickSorter sorter = new QuickSorter();

        long startTime = System.nanoTime();
        sorter.sort(arrayCopy);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        long recursionDepth = sorter.getRecursionDepth();
        long swaps = sorter.getSwaps();

        appendToCSV("QuickSort", size, inputType, executionTime, recursionDepth, swaps, "swaps");
        System.out.printf("  QuickSort %s: %d ns, depth=%d, swaps=%d%n",
                inputType, executionTime, recursionDepth, swaps);
    }

    private static void testDeterministicSelect(int size, String inputType, int[] array, int k) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        DeterministicSelector selector = new DeterministicSelector();

        long startTime = System.nanoTime();
        int result = selector.select(arrayCopy, k);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        long recursionDepth = selector.getRecursionDepth();
        long comparisons = selector.getComparisons();

        appendToCSV("DeterministicSelect", size, inputType, executionTime, recursionDepth, comparisons, "comparisons");
        System.out.printf("  DeterministicSelect %s: %d ns, depth=%d, comparisons=%d, result=%d%n",
                inputType, executionTime, recursionDepth, comparisons, result);
    }

    private static void testClosestPair(int size, String inputType, int[] array) {
        
        Point[] points = new Point[size];
        for (int i = 0; i < size; i++) {
            points[i] = new Point(array[i], array[i]); 
        }

        ClosestPairSolver solver = new ClosestPairSolver();

        long startTime = System.nanoTime();
        double distance = solver.closestPair(points);
        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;
        long recursionDepth = solver.getRecursionDepth();
        long comparisons = solver.getComparisons();

        appendToCSV("ClosestPair", size, inputType, executionTime, recursionDepth, comparisons, "comparisons");
        System.out.printf("  ClosestPair %s: %d ns, depth=%d, comparisons=%d, distance=%.2f%n",
                inputType, executionTime, recursionDepth, comparisons, distance);
    }
}