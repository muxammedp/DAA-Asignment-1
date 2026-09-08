public class DeterministicSelector {
    private long recursionDepth = 0;
    private long comparisons = 0;

    public int select(int[] array, int k) {
        this.recursionDepth = 0;
        this.comparisons = 0;
        int[] copy = array.clone();
        return select(copy, 0, copy.length - 1, k, 0);
    }

    private int select(int[] array, int left, int right, int k, int depth) {
        this.recursionDepth = Math.max(this.recursionDepth, depth);

        if (left == right) {
            return array[left];
        }

        if (right - left + 1 <= 5) {
            insertionSort(array, left, right);
            return array[left + k];
        }

        int[] medians = new int[(right - left + 1 + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int groupLeft = left + i * 5;
            int groupRight = Math.min(left + i * 5 + 4, right);
            insertionSort(array, groupLeft, groupRight);
            medians[i] = array[groupLeft + (groupRight - groupLeft) / 2];
        }

        int medianOfMedians = select(medians, 0, medians.length - 1, medians.length / 2, depth + 1);

        int pivotIndex = partition(array, left, right, medianOfMedians);

        int leftSize = pivotIndex - left;
        if (k == leftSize) {
            return array[pivotIndex];
        } else if (k < leftSize) {
            return select(array, left, pivotIndex - 1, k, depth + 1);
        } else {
            return select(array, pivotIndex + 1, right, k - leftSize - 1, depth + 1);
        }
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

    private int partition(int[] array, int left, int right, int pivotValue) {
        int pivotIndex = left;
        for (int i = left; i <= right; i++) {
            if (array[i] == pivotValue) {
                pivotIndex = i;
                break;
            }
        }
        swap(array, pivotIndex, right);

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
    }

    public long getRecursionDepth() {
        return recursionDepth;
    }

    public long getComparisons() {
        return comparisons;
    }
}