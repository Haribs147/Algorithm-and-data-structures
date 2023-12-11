/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.edu.pw.ee.aisd2023zlab6.lcs;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 *
 * @author userl
 */
public class LongestCommonSubsequenceTest {
    
    public LongestCommonSubsequenceTest() {
    }

    /**
     * Test of findLcs method, of class LongestCommonSubsequence.
     */
    @Test
    public void testFindLcs() {
        System.out.println("findLcs");
        String topText = "MIKOLAJ";
        String leftText = "NIKOGA";
        String expResult = "";
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        String result = lcs.findLcs(topText, leftText);
        assertThat(result).isEqualTo(null);
    }
    
}
