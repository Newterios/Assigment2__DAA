package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Min-Heap implementation with decrease-key and merge operations
 * Optimized with bottom-up heapify for buildHeap operation
 *  -_-
 */

public class MinHeap {
    private List<Integer> heap;
    private int comparisons;
    private int swaps;

    public MinHeap() {
        this.heap = new ArrayList<>();
        resetMetrics();
    }

    public MinHeap(int[] array) {
        buildHeap(array);
    }

    // Basic operations
    public void insert(int key) {
        heap.add(key);
        heapifyUp(heap.size() - 1);
    }

    public int extractMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap.get(0);
        int last = heap.remove(heap.size() - 1);

        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }

        return min;
    }

    public int getMin() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    // Required operations for assignment
    public void decreaseKey(int index, int newKey) {
        if (index < 0 || index >= heap.size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        if (newKey > heap.get(index)) {
            throw new IllegalArgumentException("New key must be smaller than current key");
        }

        heap.set(index, newKey);
        heapifyUp(index);
    }

    public void merge(MinHeap other) {
        // Method 1: Simple insertion (O(m log n))
        for (int i = 0; i < other.heap.size(); i++) {
            insert(other.heap.get(i));
        }
    }

    // Efficient merge using array combination
    public void mergeEfficient(MinHeap other) {
        List<Integer> combined = new ArrayList<>(this.heap);
        combined.addAll(other.heap);
        buildHeapFromList(combined);
    }

    // Build heap from array using bottom-up heapify (O(n))
    public void buildHeap(int[] array) {
        resetMetrics();
        heap.clear();
        for (int num : array) {
            heap.add(num);
        }
        buildHeapFromList(heap);
    }

    private void buildHeapFromList(List<Integer> list) {
        this.heap = list;
        // Start from last non-leaf node and heapify down
        for (int i = (heap.size() / 2) - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // Heapify operations
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            comparisons++;

            if (heap.get(index) >= heap.get(parentIndex)) {
                break;
            }

            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(int index) {
        int size = heap.size();

        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            // Compare with left child
            if (leftChild < size) {
                comparisons++;
                if (heap.get(leftChild) < heap.get(smallest)) {
                    smallest = leftChild;
                }
            }

            // Compare with right child
            if (rightChild < size) {
                comparisons++;
                if (heap.get(rightChild) < heap.get(smallest)) {
                    smallest = rightChild;
                }
            }

            if (smallest == index) {
                break;
            }

            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        swaps++;
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // Metrics collection
    public void resetMetrics() {
        comparisons = 0;
        swaps = 0;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getSwaps() {
        return swaps;
    }

    // Utility method for testing (:
    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}