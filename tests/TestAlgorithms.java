public class TestAlgorithms {
    public static void main(String[] args) {
        System.out.println("Testing Algorithms");
        System.out.println("==================");

        
        int[] data = {64, 34, 25, 12, 22, 11, 90};
        int[] dataCopy;

        System.out.println("Original array: ");
        printArray(data);

        
        dataCopy = data.clone();
        MergeSorter mergeSorter = new MergeSorter();
        mergeSorter.sort(dataCopy);
        System.out.println("MergeSort: ");
        printArray(dataCopy);

        
        dataCopy = data.clone();
        QuickSorter quickSorter = new QuickSorter();
        quickSorter.sort(dataCopy);
        System.out.println("QuickSort: ");
        printArray(dataCopy);

        
        dataCopy = data.clone();
        DeterministicSelector selector = new DeterministicSelector();
        int medianIndex = dataCopy.length / 2;
        int median = selector.select(dataCopy, medianIndex);
        System.out.println("Median: " + median);

        
        Point[] points = {
            new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50),
            new Point(5, 1),
            new Point(12, 10),
            new Point(3, 4)
        };

        ClosestPairSolver closestPairSolver = new ClosestPairSolver();
        double minDistance = closestPairSolver.closestPair(points);
        System.out.println("Closest pair distance: " + minDistance);

        System.out.println("All tests completed!");
    }

    private static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}