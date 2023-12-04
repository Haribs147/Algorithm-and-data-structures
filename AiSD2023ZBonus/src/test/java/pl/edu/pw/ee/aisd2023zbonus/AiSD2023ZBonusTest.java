package pl.edu.pw.ee.aisd2023zbonus;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.edu.pw.ee.aisd2023zbonus.AiSD2023ZBonus.countPalindroms;


class AiSD2023ZBonusTest {

    @Test
    public void countPalindromIsRight() {
        String text = "alana";
        assertThat(countPalindroms(text)).isEqualTo(2);
    }
}