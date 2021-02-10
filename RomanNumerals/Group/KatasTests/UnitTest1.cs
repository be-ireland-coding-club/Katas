using System;
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
            Assert.True(RomanNumerals.Convert(100) == "C");
            Assert.True(RomanNumerals.Convert(49) == "XLIX");
        }
    }
}
