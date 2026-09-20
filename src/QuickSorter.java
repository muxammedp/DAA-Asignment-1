import java.util.Random;

public class QuickSorter {
    private long recursionDepth = 0;
    private long swaps = 0;
    private long comparisons = 0;
    private Random random;

    public QuickSorter() {
        this.recursionDepth = 0;
        this.swaps = 0;
        this.comparisons = 0;
        this.random = new Random();
    }

    public void sort(int[] array) {
        this.recursionDepth = 0;
        this.swaps = 0;
        this.comparisons = 0;
        quickSort(array, 0, array.length - 1, 0);
    }

    private void quickSort(int[] array, int left, int right, int depth) {
        this.recursionDepth = Math.max(this.recursionDepth, depth);

        if (left >= right) {
            return;
        }

        
        int pivotIndex = partition(array, left, right);

        
        int leftSize = pivotIndex - left;
        int rightSize = right - pivotIndex;

        if (leftSize < rightSize) {
            quickSort(array, left, pivotIndex - 1, depth + 1);
            
            left = pivotIndex + 1;
        } else {
            quickSort(array, pivotIndex + 1, right, depth + 1);
            
            right = pivotIndex - 1;
        }

        
        if (left < right) {
            quickSort(array, left, right, depth);
        }
    }

    private int partition(int[] array, int left, int right) {
        
        int pivotIndex = left + random.nextInt(right - left + 1);
        swap(array, pivotIndex, right); 
        int pivotValue = array[right];

        int i = left - 1;
        for (int j = left; j < right; j++) {
            this.comparisons++;
            if (array[j] <= pivotValue) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right); 
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        this.swaps++;
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getComparisons() {
        return comparisons;
    }
}