package pl.edu.pw.ee.aisd2023zlab1;

import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

import static java.util.Objects.isNull;

public class InsertionSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateParams(nums);
        for (int i = 1; i < nums.length; i++) {
            int j = i;
            double temp = nums[i];
            for (; j > 0 && nums[j - 1] > temp; j--)
                nums[j] = nums[j - 1];
            nums[j] = temp;
        }
    }

    private void validateParams(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }
}

