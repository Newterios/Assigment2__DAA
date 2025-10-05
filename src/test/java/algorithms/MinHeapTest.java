package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinHeapTest {
    private MinHeap heap;

    @BeforeEach
    void setUp() {
        heap = new MinHeap();
    }

    @Test
    void testInsertAndExtractMin() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        heap.insert(1);

        assertEquals(1, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(7, heap.extractMin());
    }

    @Test
    void testGetMin() {
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);

        assertEquals(5, heap.getMin());
        // getMin should not remove the element
        assertEquals(5, heap.getMin());
    }

    @Test
    void testEmptyHeap() {
        assertTrue(heap.isEmpty());
        assertThrows(IllegalStateException.class, () -> heap.extractMin());
        assertThrows(IllegalStateException.class, () -> heap.getMin());
    }

    @Test
    void testDecreaseKey() {
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        // Decrease 20 to 5
        heap.decreaseKey(1, 5);
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(30, heap.extractMin());
    }

    @Test
    void testBuildHeap() {
        int[] array = {9, 5, 7, 1, 3};
        heap.buildHeap(array);

        assertEquals(1, heap.extractMin());
        assertEquals(3, heap.extractMin());
        assertEquals(5, heap.extractMin());
        assertEquals(7, heap.extractMin());
        assertEquals(9, heap.extractMin());
    }

    @Test
    void testMerge() {
        MinHeap heap1 = new MinHeap();
        heap1.insert(2);
        heap1.insert(4);

        MinHeap heap2 = new MinHeap();
        heap2.insert(1);
        heap2.insert(3);

        heap1.merge(heap2);

        assertEquals(1, heap1.extractMin());
        assertEquals(2, heap1.extractMin());
        assertEquals(3, heap1.extractMin());
        assertEquals(4, heap1.extractMin());
    }

    @Test
    void testMetricsCollection() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);

        assertTrue(heap.getComparisons() > 0);
        assertTrue(heap.getSwaps() > 0);

        heap.resetMetrics();
        assertEquals(0, heap.getComparisons());
        assertEquals(0, heap.getSwaps());
    }
}