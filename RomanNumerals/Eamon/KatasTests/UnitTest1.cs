using Xunit;
using Katas;

namespace KatasTests
{
    public class UnitTest1
    {


        [Fact]
        public void Test1()
        {
            Assert.True(RomanNumerals.Convert(1) == "I");
            Assert.True(RomanNumerals.Convert(5) == "V");
            Assert.True(RomanNumerals.Convert(10) == "X");
            Assert.True(RomanNumerals.Convert(21) == "XXI");
            Assert.True(RomanNumerals.Convert(100) == "C");
            Assert.True(RomanNumerals.Convert(49) == "XLIX");
            Assert.True(RomanNumerals.Convert(1999) == "MCMXCIX");

            Assert.True(RomanNumerals.Convert(1000) == "M");
            Assert.True(RomanNumerals.Convert(1500) == "MD");
            Assert.True(RomanNumerals.Convert(1100) == "MC");
            Assert.True(RomanNumerals.Convert(1115) == "MCXV");
            Assert.True(RomanNumerals.Convert(1000) == "M");

            Assert.True(RomanNumerals.Convert(3999) == "MMMCMXCIX");
            Assert.True(RomanNumerals.Convert(2342) == "MMCCCXLII");
            Assert.True(RomanNumerals.Convert(782) == "DCCLXXXII");


            Assert.True(RomanNumerals.Convert(938) == "CMXXXVIII");
        }
    }
}
