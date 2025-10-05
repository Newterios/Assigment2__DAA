# MinHeap Implementation - DAA Assignment 2

## Student A: MinHeap with decrease-key and merge operations

### Repository Structure
assignment2-minheap/
├── src/main/java/
│   ├── algorithms/MinHeap.java
│   ├── metrics/PerformanceTracker.java
│   └── cli/BenchmarkRunner.java
├── src/test/java/
│   └── algorithms/MinHeapTest.java
├── docs/
│   └── analysis-report.pdf
├── performance-plots/
├── README.md
└── pom.xml

### Implementation Features
- Complete MinHeap data structure
- decreaseKey operation with validation
- merge operation with two strategies (simple and efficient)
- Bottom-up heapify for O(n) buildHeap
- Comprehensive performance metrics tracking
- CLI benchmark interface
- Full unit test coverage

### Algorithmic Optimizations
- Bottom-up heap construction (Floyd's algorithm)
- In-place operations minimizing memory usage
- Efficient array-based storage
- Dynamic capacity management

### Complexity Characteristics
- buildHeap: O(n) with bottom-up approach
- insert/extractMin: O(log n)
- getMin: O(1)
- decreaseKey: O(log n)
- merge: O(m log n) or O(n+m)

### Testing Coverage
- Empty heap and single element cases
- Duplicate values handling
- Sorted and reverse-sorted inputs
- Error conditions and validation
- Performance metric accuracy

### Git Workflow
- main branch with release tags (v0.1, v1.0)
- Feature branches: algorithm, metrics, testing, cli
- Conventional commit messages
- Clean, logical commit history
