package pl.edu.pw.ee.aisd2023zlab1.qsort.iterative;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class QuickSortIterativeMedian3 implements Sorting {

    Random rand = new Random();

    @Override
    public void sort(double[] nums) {
        validateParams(nums);

        quicksortHoareMedian3(nums);
    }


    private void quicksortHoareMedian3(double[] data) {
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

    private int splitData(double[] data, int start, int end) {
        //mediana
        double pivot;
        int randomNum = rand.nextInt((end - start) + 1) + start;
        if (data[start] > data[randomNum]) {
            if (data[start] < data[end])
                pivot = data[start];
            else if (data[randomNum] > data[end])
                pivot = data[randomNum];
            else
                pivot = data[end];
        } else {
            if (data[start] > data[end])
                pivot = data[start];
            else if (data[randomNum] < data[end])
                pivot = data[randomNum];
            else
                pivot = data[end];
        }
        //zamiast jak jest w hoarze double pivot = nums[start]; mam pivot = mediana;
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
}
