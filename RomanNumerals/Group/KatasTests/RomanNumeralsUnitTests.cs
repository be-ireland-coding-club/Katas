using Xunit;
using Katas;
using System;
using System.Collections.Generic;

namespace KatasTests
{
    public class RomanNumeralsUnitTests
    {

        [Fact]
        public void TestNumbersOutOfRange()
        {
            Assert.Throws<ArgumentOutOfRangeException>(() => (-1).ConvertToRomanNumber());
            Assert.Throws<ArgumentOutOfRangeException>(() => 0.ConvertToRomanNumber());

            Assert.Throws<ArgumentOutOfRangeException>(() => 4000.ConvertToRomanNumber());
            Assert.Throws<ArgumentOutOfRangeException>(() => 5001.ConvertToRomanNumber());
        }

        [Fact]
        public void TestAllBaseNumbers()
        {
            //Arrange
            Dictionary<int, string> dict = new Dictionary<int, string>(){
                {1,"I"}, {5,"V"},{10,"X"},
                {50, "L" }, {100, "C" }, {500, "D" }, {1000, "M" }
            };
            var result = true;

            //Act
            foreach (var item in dict)
            {
                var aux = item.Key.ConvertToRomanNumber();
                result = result && (aux == item.Value);
            }

            //Assert            
            Assert.True(result);
        }

        [Fact]
        public void TestNumbersComposedOnlyFromBaseNumbers()
        {
            //Arrange
            Dictionary<int, string> dict = new Dictionary<int, string>(){
                {15,"XV"},{55,"LV"},{150,"CL"},{155,"CLV"},
                {11,"XI"},{51,"LI"}, {101,"CI"},{111,"CXI"},
                {515,"DXV"},{1515,"MDXV"}
            };
            var result = true;

            //Act
            foreach (var item in dict)
            {
                var aux = item.Key.ConvertToRomanNumber();
                result = result && (aux == item.Value);
            }

            //Assert            
            Assert.True(result);
        }

        [Fact]
        public void TestNumbers_WhereSubstractOperationApplies()
        {
            Assert.True(4.ConvertToRomanNumber() == "IV");
            Assert.True(9.ConvertToRomanNumber() == "IX");

            Assert.True(40.ConvertToRomanNumber() == "XL");
            Assert.True(49.ConvertToRomanNumber() == "XLIX");

            Assert.True(199.ConvertToRomanNumber() == "CXCIX");

            Assert.True(900.ConvertToRomanNumber() == "CM");
            Assert.True(901.ConvertToRomanNumber() == "CMI");
            Assert.True(949.ConvertToRomanNumber() == "CMXLIX");
        }

        [Fact]
        public void TestNumbers_WhereAddOperationApplies()
        {
            Assert.True(2.ConvertToRomanNumber() == "II");
            Assert.True(3.ConvertToRomanNumber() == "III");
            Assert.True(8.ConvertToRomanNumber() == "VIII");

            Assert.True(37.ConvertToRomanNumber() == "XXXVII");

            Assert.True(628.ConvertToRomanNumber() == "DCXXVIII");

            Assert.True(3999.ConvertToRomanNumber() == "MMMCMXCIX");
        }

        [Fact]
        public void TestRandomNumbers()
        {
            //Arrange
            Dictionary<int, string> dict = new Dictionary<int, string>(){
                {7,"VII"},
                {11,"XI"},{12,"XII"},{14,"XIV"},{16,"XVI"},{18,"XVIII"}, {19,"XIX"},
                {30,"XXX"},{31,"XXXI"},{45,"XLV"},{76,"LXXVI"},{99,"XCIX"}
            };
            var result = true;

            //Act
            foreach (var item in dict)
            {
                var aux = item.Key.ConvertToRomanNumber();
                result = result && (aux == item.Value);
            }

            //Assert            
            Assert.True(result);
        }

        [Fact]
        public void TestDevidedNumberList()
        {
            var result = 49.GetDevidedNumberList();
            Assert.True(result.Count == 2);
            Assert.True(result[0] == 40);
            Assert.True(result[1] == 9);

            result = 571.GetDevidedNumberList();
            Assert.True(result.Count == 3);
            Assert.True(result[0] == 500);
            Assert.True(result[1] == 70);
            Assert.True(result[2] == 1);

            result = 2.GetDevidedNumberList();
            Assert.True(result.Count == 1);
            Assert.True(result[0] == 2);

            result = 2000.GetDevidedNumberList();
            Assert.True(result.Count == 1);
            Assert.True(result[0] == 2000);

            result = 610.GetDevidedNumberList();
            Assert.True(result.Count == 2);
            Assert.True(result[0] == 600);
            Assert.True(result[1] == 10);

            result = 601.GetDevidedNumberList();
            Assert.True(result.Count == 2);
            Assert.True(result[0] == 600);
            Assert.True(result[1] == 1);
        }
    }
}