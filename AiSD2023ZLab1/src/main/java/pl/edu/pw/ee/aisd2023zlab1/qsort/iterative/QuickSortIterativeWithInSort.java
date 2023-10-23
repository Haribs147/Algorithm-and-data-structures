package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class QuickSortIterativeWithInSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        quicksortWithInSort(nums);
    }


    private void quicksortWithInSort(double[] data) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();

        Integer left = 0;
        Integer right = data.length - 1;

        starts.add(left);
        ends.add(right);

        int n = 1;
        int pivot;

        if (left < right) {

            while (n > 0) {
                n--;
                left = starts.remove(n);
                right = ends.remove(n);
                if (right - left < 15) {
                    insort(data, left + 1, right + 1);
                } else {
                    pivot = splitData(data, left, right);
                    if (pivot > left) {
                        starts.add(left);
                        ends.add(pivot);
                        n++;
                    }
                    if (pivot + 1 < right) {
                        starts.add(pivot + 1);
                        ends.add(right);
                        n++;
                    }
                }
            }
        }
    }

    private int splitData(double[] data, int start, int end) {
        double pivot = data[start];
        int left = start - 1;
        int right = end + 1;

        while (true) {

            while (data[++left] < pivot) {
            }

            while (data[--right] > pivot) {
            }

            if (left < right) {
                swap(data, left, right);
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

    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

    public void insort(double[] nums, int left, int right) {
        for (int i = left; i < right; i++) {
            double temp = nums[i];
            int j;
            for (j = i; j >= left && nums[j - 1] > temp; j--)
                nums[j] = nums[j - 1];
            nums[j] = temp;
        }
    }
}
