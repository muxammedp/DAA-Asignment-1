# Divide-and-Conquer Algorithm Analysis

## Project Overview
This project implements and analyzes four classic divide-and-conquer algorithms:
1. MergeSort
2. QuickSort (randomized pivot)
3. Deterministic Select (Median-of-Medians)
4. Closest Pair of Points

The implementation includes performance measurements, correctness testing, and experimental analysis.

## Implemented Algorithms
- **MergeSorter.java**: Merge sort with insertion sort cutoff and reusable auxiliary buffer
- **QuickSorter.java**: Randomized quicksort with in-place partitioning and smaller-first recursion
- **DeterministicSelector.java**: Median-of-medians selection algorithm with groups of 5
- **ClosestPairSolver.java**: Closest pair of points using divide-and-conquer approach
- **Point.java**: Simple 2D point class for closest pair algorithm
- **Experiment.java**: Framework for running performance tests and collecting metrics
- **Main.java**: Entry point to run experiments

## Repository Structure
```
assignment1-divide-and-conquer/
├── src/
│   ├── MergeSorter.java
│   ├── QuickSorter.java
│   ├── DeterministicSelector.java
│   ├── ClosestPairSolver.java
│   ├── Point.java
│   ├── Experiment.java
│   └── Main.java
├── tests/
│   ├── MergeSorterTest.java
│   ├── QuickSorterTest.java
│   ├── DeterministicSelectorTest.java
│   └── ClosestPairSolverTest.java
├── docs/
│   ├── screenshots/
│   └── plots/
├── results/
│   └── results.csv
├── README.md
├── pom.xml
└.gitignore
```

## Algorithm Analysis

### MergeSort
- **How it works**: Recursively divides array into halves, sorts each half, then merges them
- **Time Complexity**: Θ(n log n)
- **Space Complexity**: Θ(n) for auxiliary buffer
- **Recurrence**: T(n) = 2T(n/2) + Θ(n)
- **Master Theorem**: Case 2, a=2, b=2, f(n)=Θ(n) → T(n) = Θ(n log n)

### QuickSort
- **How it works**: Selects random pivot, partitions array, recursively sorts partitions
- **Time Complexity**: 
  - Average: Θ(n log n)
  - Worst-case: O(n²)
- **Space Complexity**: O(log n) due to recursion depth (smaller-first optimization)
- **Recurrence**: T(n) = T(k) + T(n-k-1) + Θ(n)
- **Average Case**: With random pivot, expected split is balanced → Θ(n log n)

### Deterministic Select (Median-of-Medians)
- **How it works**: 
  1. Divide array into groups of 5
  2. Find median of each group
  3. Recursively find median of medians
  4. Partition using median of medians as pivot
  5. Recurse into appropriate partition
- **Time Complexity**: Θ(n) worst-case
- **Space Complexity**: O(log n) due to recursion depth
- **Recurrence**: T(n) ≤ T(n/5) + T(7n/10) + Θ(n)
- **Solution**: By induction or recursion tree, this solves to Θ(n)

### Closest Pair of Points
- **How it works**:
  1. Sort points by x-coordinate
  2. Divide points into left and right halves
  3. Recursively find closest pair in each half
  4. Find closest pair with one point in each strip (within δ of dividing line)
  5. Check points in strip sorted by y-coordinate (only need to check 7 points ahead)
- **Time Complexity**: Θ(n log n)
- **Space Complexity**: O(n)
- **Recurrence**: T(n) = 2T(n/2) + Θ(n log n) for naive strip sorting
- **Optimized**: With pre-sorted arrays, T(n) = 2T(n/2) + Θ(n) → Θ(n log n)

## Experimental Results
The experiment framework tests algorithms with:
- Input sizes: 100, 500, 1000, 2000, 5000, 10000
- Input types: random, sorted, reverse-sorted, duplicate-heavy
- Metrics: execution time, recursion depth, comparisons/swaps

Results are saved to `results/results.csv` in CSV format.

## Discussion
### Do results match theoretical complexity?
- MergeSort and QuickSort show Θ(n log n) behavior on average
- Deterministic Select shows linear time growth
- Closest Pair shows Θ(n log n) behavior

### How does input structure affect performance?
- QuickSort degrades to O(n²) on sorted/reverse-sorted inputs without randomization
- MergeSort is unaffected by input order
- Deterministic Select maintains O(n) regardless of input
- Closest Pair performance varies with point distribution

### Why does smaller-first recursion help QuickSort?
Recurring on the smaller partition first ensures that the recursion depth is O(log n) even in worst-case scenarios, as the larger partition is handled iteratively.

### Why does Median-of-Medians guarantee O(n)?
The median-of-medians pivot guarantees a 30/70 split at worst, leading to the recurrence T(n) ≤ T(n/5) + T(7n/10) + Θ(n), which solves to Θ(n).

### Why is divide-and-conquer Closest Pair faster than O(n²) for large inputs?
The divide-and-conquer approach reduces the problem size exponentially while only doing linear work to combine results, leading to Θ(n log n) vs Θ(n²) for brute force.

### What practical factors affect performance?
- JVM warm-up and JIT compilation
- Garbage collection pauses
- Cache locality and memory access patterns
- Recursion overhead
- Input data characteristics

## Reflection
The main implementation challenges were:
1. Ensuring correct partitioning logic in QuickSort and Deterministic Select
2. Handling edge cases (empty arrays, duplicates, small inputs)
3. Implementing the strip construction efficiently in Closest Pair
4. Accurately measuring recursion depth and operation counts
5. Managing auxiliary buffers efficiently in MergeSort

Key learnings included:
- The importance of pivot selection in QuickSort
- How Median-of-Medians achieves guaranteed linear time
- The geometric insights that make Closest Pair efficient
- Trade-offs between theoretical complexity and practical performance
- The value of systematic testing and performance measurement

## Screenshots
(To be added after running experiments)

## GitHub Workflow
The repository follows a structured Git workflow with meaningful commits:
- Initial project structure and test setup
- Implementation of each algorithm
- Addition of performance measurements
- Correctness testing
- Documentation and analysis
- Bug fixes and edge case handling
- Final release

To run the experiments:
1. Ensure Java JDK is installed
2. Compile: `javac -d . src/*.java`
3. Run: `java src.Main`