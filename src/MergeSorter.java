public class MergeSorter {
    private static final int INSERTION_SORT_THRESHOLD = 10;
    private long recursionDepth = 0;
    private long comparisons = 0;
    private int[] auxBuffer;

    public void sort(int[] array) {
        this.recursionDepth = 0;
        this.comparisons = 0;
        this.auxBuffer = new int[array.length];
        mergeSort(array, 0, array.length - 1, 0);
    }

    private void mergeSort(int[] array, int left, int right, int depth) {
        this.recursionDepth = Math.max(this.recursionDepth, depth);

        if (left >= right) {
            return;
        }

        if (right - left + 1 <= INSERTION_SORT_THRESHOLD) {
            insertionSort(array, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        mergeSort(array, left, mid, depth + 1);
        mergeSort(array, mid + 1, right, depth + 1);
        merge(array, left, mid, right);
    }

    private void insertionSort(int[] array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= left) {
                this.comparisons++;
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            auxBuffer[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            this.comparisons++;
            if (auxBuffer[i] <= auxBuffer[j]) {
                array[k++] = auxBuffer[i++];
            } else {
                array[k++] = auxBuffer[j++];
            }
        }

        while (i <= mid) {
            array[k++] = auxBuffer[i++];
        }

        while (j <= right) {
            array[k++] = auxBuffer[j++];
        }
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}