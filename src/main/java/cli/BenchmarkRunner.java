package cli;

import algorithms.MinHeap;
import metrics.PerformanceTracker;

import java.util.Random;

public class BenchmarkRunner {
    private static final int[] INPUT_SIZES = {100, 1000, 10000, 100000};
    private static final Random random = new Random(42); // Fixed seed for reproducibility

    public static void main(String[] args) {
        PerformanceTracker tracker = new PerformanceTracker();

        System.out.println("MinHeap Performance Benchmark");
        System.out.println("=============================");

        for (int size : INPUT_SIZES) {
            System.out.printf("\nTesting with input size: %d%n", size);

            // Test with random data
            testWithRandomData(size, tracker);

            // Test with sorted data
            testWithSortedData(size, tracker);

            // Test with reverse sorted data
            testWithReverseSortedData(size, tracker);
        }

        // Save results to CSV
        tracker.saveToCSV("performance_metrics.csv");
        System.out.println("\nResults saved to performance_metrics.csv");
    }

    private static void testWithRandomData(int size, PerformanceTracker tracker) {
        int[] data = generateRandomArray(size);
        testHeapOperations("Random", data, tracker);
    }

    private static void testWithSortedData(int size, PerformanceTracker tracker) {
        int[] data = generateSortedArray(size);
        testHeapOperations("Sorted", data, tracker);
    }

    private static void testWithReverseSortedData(int size, PerformanceTracker tracker) {
        int[] data = generateReverseSortedArray(size);
        testHeapOperations("Reverse Sorted", data, tracker);
    }

    private static void testHeapOperations(String type, int[] data, PerformanceTracker tracker) {
        MinHeap heap = new MinHeap();

        long startTime = System.nanoTime();

        // Test build heap
        heap.buildHeap(data);

        // Test extract min operations
        for (int i = 0; i < Math.min(100, data.length); i++) {
            heap.extractMin();
        }

        long endTime = System.nanoTime();

        tracker.addMetrics(
                heap.getComparisons(),
                heap.getSwaps(),
                data.length,
                endTime - startTime
        );

        System.out.printf("  %s: %d comparisons, %d swaps, %d ns%n",
                type, heap.getComparisons(), heap.getSwaps(), endTime - startTime);
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size * 10);
        }
        return array;
    }

    private static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - i;
        }
        return array;
    }
}