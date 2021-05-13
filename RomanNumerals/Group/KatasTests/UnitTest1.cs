using System;
using Xunit;
using Katas;

namespace KatasTests
{
    public class UnitTest1
    {
        
        [Fact]
        public void TestConvertingToRomanNumerals()
        {
            Assert.True(RomanNumerals.ConvertToRomanNumerals(1) == "I");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(3) == "III");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(5) == "V");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(10) == "X");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(50) == "L");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(70) == "LXX");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(14) == "XIV");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(27) == "XXVII");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(45) == "XLV");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(99) == "XCIX");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(34) == "XXXIV");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(86) == "LXXXVI");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(100) == "C");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(800) == "DCCC");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(124) == "CXXIV");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(970) == "CMLXX");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(1000) == "M");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(6000) == "MMMMMM");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(1788) == "MDCCCLXXXVIII");
            Assert.True(RomanNumerals.ConvertToRomanNumerals(4034) == "MMMMXXXIV");
        }
    }
}
