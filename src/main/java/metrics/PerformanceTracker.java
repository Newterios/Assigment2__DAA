package metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceTracker {
    private List<PerformanceMetrics> metricsList;

    public PerformanceTracker() {
        this.metricsList = new ArrayList<>();
    }

    public void addMetrics(int comparisons, int swaps, int size, long timeNanos) {
        metricsList.add(new PerformanceMetrics(comparisons, swaps, size, timeNanos));
    }

    public void saveToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("input_size,comparisons,swaps,time_nanos\n");
            for (PerformanceMetrics metrics : metricsList) {
                writer.write(String.format("%d,%d,%d,%d\n",
                        metrics.size, metrics.comparisons, metrics.swaps, metrics.timeNanos));
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
        }
    }

    public void clear() {
        metricsList.clear();
    }

    public static class PerformanceMetrics {
        public final int comparisons;
        public final int swaps;
        public final int size;
        public final long timeNanos;

        public PerformanceMetrics(int comparisons, int swaps, int size, long timeNanos) {
            this.comparisons = comparisons;
            this.swaps = swaps;
            this.size = size;
            this.timeNanos = timeNanos;
        }
    }
}