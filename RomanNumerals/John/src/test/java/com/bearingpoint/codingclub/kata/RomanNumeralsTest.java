package com.bearingpoint.codingclub.kata;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 *
 * @author John McDonnell
 */
public class RomanNumeralsTest {

    private RomanNumerals unitUnderTest = null;

    @Test
    public void testConvertReturnsIWhenGiven1() {
        unitUnderTest = new RomanNumerals();

        assertEquals("I", unitUnderTest.convert(1), "1 Should return I");
    }

    @Test
    public void testConvertReturnsIIWhenGiven2() {
        unitUnderTest = new RomanNumerals();

        assertEquals("II", unitUnderTest.convert(2), "2 Should return II");
    }

    @Test
    public void testConvertReturnsIIIWhenGiven3() {
        unitUnderTest = new RomanNumerals();

        assertEquals("III", unitUnderTest.convert(3), "3 Should return III");
    }

    @Test
    public void testConvertReturnsVWhenGiven5() {
        unitUnderTest = new RomanNumerals();

        assertEquals("V", unitUnderTest.convert(5), "5 Should return V");
    }

    @Test
    public void testConvertReturnsXWhenGiven10() {
        unitUnderTest = new RomanNumerals();

        assertEquals("X", unitUnderTest.convert(10), "10 Should return X");
    }

    @Test
    public void testConvertReturnsLWhenGiven50() {
        unitUnderTest = new RomanNumerals();

        assertEquals("L", unitUnderTest.convert(50), "50 Should return L");
    }

    @Test
    public void testConvertReturnsCWhenGiven100() {
        unitUnderTest = new RomanNumerals();

        assertEquals("C", unitUnderTest.convert(100), "100 Should return C");
    }

    @Test
    public void testConvertReturnsDWhenGiven500() {
        unitUnderTest = new RomanNumerals();

        assertEquals("D", unitUnderTest.convert(500), "500 Should return D");
    }

    @Test
    public void testConvertReturnsMWhenGiven1000() {
        unitUnderTest = new RomanNumerals();

        assertEquals("M", unitUnderTest.convert(1000), "1000 Should return M");
    }

    @Test
    public void testConvertReturnsIVWhenGiven4() {
        unitUnderTest = new RomanNumerals();

        assertEquals("IV", unitUnderTest.convert(4), "4 Should return IV");
    }

    @Test
    public void testConvertReturnsIXWhenGiven9() {
        unitUnderTest = new RomanNumerals();

        assertEquals("IX", unitUnderTest.convert(9), "9 Should return IX");
    }

    @Test
    public void testConvertReturnsXLWhenGiven40() {
        unitUnderTest = new RomanNumerals();

        assertEquals("XL", unitUnderTest.convert(40), "40 Should return XL");
    }

    @Test
    public void testConvertReturnsXCWhenGiven90() {
        unitUnderTest = new RomanNumerals();

        assertEquals("XC", unitUnderTest.convert(90), "90 Should return XC");
    }

    @Test
    public void testConvertReturnsCDWhenGiven400() {
        unitUnderTest = new RomanNumerals();

        assertEquals("CD", unitUnderTest.convert(400), "400 Should return CD");
    }

    @Test
    public void testConvertReturnsCMWhenGiven900() {
        unitUnderTest = new RomanNumerals();

        assertEquals("CM", unitUnderTest.convert(900), "900 Should return CM");
    }

    // Random Tests to confirm logic
    @ParameterizedTest
    @CsvSource(
            {
                "2021, MMXXI", 
                "938, CMXXXVIII",
                "735, DCCXXXV", 
                "687, DCLXXXVII",
                "825, DCCCXXV", 
                "21, XXI", 
                "190, CXC", 
                "375, CCCLXXV",
                "610, DCX"})
    void testRandomConversions(Integer number, String result) {
        unitUnderTest = new RomanNumerals();
        assertEquals(result, unitUnderTest.convert(number), number + " Should return " + result);
    }
}
