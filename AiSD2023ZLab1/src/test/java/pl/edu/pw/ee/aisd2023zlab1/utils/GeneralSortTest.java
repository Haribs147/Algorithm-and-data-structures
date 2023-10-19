package pl.edu.pw.ee.aisd2023zlab1.utils;

import java.util.ArrayList;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import org.junit.Test;
import pl.edu.pw.ee.aisd2023zlab1.services.Sorting;

public abstract class GeneralSortTest {

    protected Sorting sorter;
    protected Random rand = new Random();
    public GeneralSortTest(Sorting sorter) {
        this.sorter = sorter;
    }

    @Test
    public void should_ThrowException_WhenInputIsNull() {
        // given
        double[] nums = null;

        // when
        Throwable exceptionCaught = catchThrowable(() -> {
            sorter.sort(nums);
        });

        // then
        String message = "Input args (nums) cannot be null!";

        assertThat(exceptionCaught)
                .isInstanceOf(RuntimeException.class)
                .hasMessage(message);
    }

    @Test
    public void should_ReturnEmptyArray_WhenInputIsEmpty() {
        // given
        double[] nums = {};

        // when
        sorter.sort(nums);

        // then
        assertThat(nums).isEmpty();
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsSorted() {
        // given
        int size = 10_000_0;
        double[] nums = new double[size];
        for(int i = 0; i < size; i++)
            nums[i] = i;
        double[] numsCopy = nums.clone();
        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsTheSame() {
        // given
        int randomNum = rand.nextInt(1000);
        int size = 10_000_0;
        double[] nums = new double[size];
        for(int i = 0; i < size; i++)
            nums[i] = randomNum;
        double[] numsCopy = nums.clone();
        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }
    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsReverseSorted() {
        // given
        int size = 10_000_0;
        double[] nums = new double[size];
        int j = size;
        int i = 0;
        while ( size > 0) {
            nums[i] = size;
            size --;
            i ++;
        }
        double[] numsCopy = nums.clone();
        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsRandomAndHuge() {
        // given
        int size = 10_000_0;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    @Test
    public void should_CorrectlyAscendingSort_WhenInputIsNotEvenNumber() {
        // given
        int size = 10_000_1;
        double[] nums = createRandomData(size);
        double[] numsCopy = nums.clone();

        // when
        sorter.sort(nums);

        // then
        assertThat(nums)
                .isSorted()
                .containsExactlyInAnyOrder(numsCopy);
    }

    private double[] createRandomData(int size) {
        assert size >= 0;

        double[] nums = new double[size];

        long eliteSeed = 31337;
        Random rand = new Random(eliteSeed);

        for (int i = 0; i < size; i++) {
            nums[i] = rand.nextDouble();
        }

        return nums;
    }
}
