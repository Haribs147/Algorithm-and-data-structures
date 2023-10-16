package pl.edu.pw.ee.aisd2023zlab1;

import java.util.Arrays;
import static java.util.Objects.isNull;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public class RefAlgSort implements Sorting {

    @Override
    public void sort(double[] nums) {
        validateInput(nums);

        Arrays.sort(nums);
    }

    private void validateInput(double[] nums) {
        if (isNull(nums)) {
            throw new RuntimeException("Input args (nums) cannot be null!");
        }
    }

}
