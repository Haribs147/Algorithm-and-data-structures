/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pl.edu.pw.ee.aisd_lab_00;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author userl
 */
public class BsearchTest {

    private Bsearch bSearch;

    @Before
    public void setUp() {
        bSearch = new Bsearch();
    }

    @Test
    public void ShouldReturnCorrectWhenSearchingSmallerThanFirst() {
        //given
        int[] nums = {1, 2, 3, 4, 5};
        int toFind = 0;
        int result = bSearch.search(nums, toFind);
        //when

        //then
    }
}
