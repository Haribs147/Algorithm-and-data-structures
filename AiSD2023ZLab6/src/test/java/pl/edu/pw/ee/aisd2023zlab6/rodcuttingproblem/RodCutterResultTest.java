
package pl.edu.pw.ee.aisd2023zlab6.rodcuttingproblem;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RodCutterResultTest extends RodCutterTest {
@Test
    void should_ReturnCorrectValue_WhenCuttingIsNeeded() {
        // given
        int rodLength = 5;
        int expectedResult = 13;

        int[] prices = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

        // when
        RodCutterResult money = new RodCutterResult(prices, rodLength);

        // then
        int [] maxSumResults = money.getMaxSumResults();
        int [] solutions = money.getSolutions();
        assertThat(maxSumResults[rodLength]).isEqualTo(expectedResult);
    }

}