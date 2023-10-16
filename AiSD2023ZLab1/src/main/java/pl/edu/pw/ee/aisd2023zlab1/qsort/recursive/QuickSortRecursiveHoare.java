package pl.edu.pw.ee.aisd2023zlab1.qsort.recursive;

import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class QuickSortRecursiveHoare implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        int left = 0;
        int right = nums.length - 1;

        quickSort(nums, left, right);
    }

    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

    private void quickSort(double[] nums, int left, int right) {
        if (left < right) {

            int pivotId = partition(nums, left, right);

            quickSort(nums, left, pivotId);
            quickSort(nums, pivotId + 1, right);
        }
    }

    private int partition(double[] nums, int start, int end) {
        double pivot = nums[start];

        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (nums[++left] < pivot) {
            }

            while (nums[--right] > pivot) {
            }

            if (left < right) {
                swap(nums, left, right);
            } else {
                break;
            }

        }

        return right;
    }

    private void swap(double[] nums, int firstId, int secondId) {
        if (firstId != secondId) {

            double firstVal = nums[firstId];
            nums[firstId] = nums[secondId];
            nums[secondId] = firstVal;
        }
    }

}
