package pl.edu.pw.ee.aisd2023zlab1.heapSort;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class HeapSort implements Sorting {
    
    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        
        buildHeap(nums);
        
        int n = nums.length;
        
        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }        
    }
    
    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }
    
    private void buildHeap(double[] nums) {
        int n = nums.length;
        
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, i, n);
        }
    }
    
    private void heapify(double[] nums, int parentId, int maxId) {
        int leftChildId = 2 * parentId + 1;
        int rightChildId = 2 * parentId + 2;
        int largestValId = parentId;
        
        if (leftChildId < maxId && nums[leftChildId] > nums[largestValId]) {
            largestValId = leftChildId;
        }
        
        if (rightChildId < maxId && nums[rightChildId] > nums[largestValId]) {
            largestValId = rightChildId;
        }
        
        if (largestValId != parentId) {
            swap(nums, parentId, largestValId);
            heapify(nums, largestValId, maxId);
        }
    }
    
    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {
            
            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }
}
