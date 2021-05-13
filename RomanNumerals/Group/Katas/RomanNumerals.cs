using System;
using System.Collections.Generic;
using System.Text;

namespace Katas
{
    public class RomanNumerals
    {

        public static Dictionary<int, string> romanNumeral = new Dictionary<int, string>() {
            { 1, "I"},
            { 5, "V"},
            { 10, "X"},
            {50, "L" },
            {100, "C" },
            {500, "D" },
            {1000, "M" }
        };

        public static int ConvertFromRomanNumerals(string x)
        {
           return 1;
        }

        public static string ConvertToRomanNumerals(int x)
        {
            string romanNumeral = "";
            if (x > 1000) 
            {
                int thousandCount = x / 1000;
                romanNumeral += getMultipleNumerals(thousandCount, "M");
                x = x % 1000;
            }
            if (x > 900)
            {
                romanNumeral += "CM";
                x = x % 900;
            }
            if (x > 500)
            {
                x = x % 500;
                romanNumeral += "D";
            }
            if (x > 400)
            {
                romanNumeral += "CD";
                x = x % 400;
            }
            if (x > 100)
            {
                int hundredCount = x / 100;
                romanNumeral += getMultipleNumerals(hundredCount, "C");
                x = x % 100;
            }

            return romanNumeral;
        }

        public static string getMultipleNumerals(int x, string romanNumeral)
        {
            string multiple = "";
            for (int i=0; i<x; i++)
            {
                multiple += romanNumeral;
            }
           return multiple;
        }
    }
}
